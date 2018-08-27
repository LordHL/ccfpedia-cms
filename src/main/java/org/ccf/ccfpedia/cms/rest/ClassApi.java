package org.ccf.ccfpedia.cms.rest;

import io.swagger.annotations.ApiOperation;
import org.ccf.ccfpedia.cms.bean.FirstClassBean;
import org.ccf.ccfpedia.cms.bean.SecondClassBean;
import org.ccf.ccfpedia.cms.bean.resp.DataArray;
import org.ccf.ccfpedia.cms.bean.resp.RestResp;
import org.ccf.ccfpedia.cms.dao.FirstClassMapper;
import org.ccf.ccfpedia.cms.dao.SecondClassMapper;
import org.ccf.ccfpedia.cms.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "class")
public class ClassApi {

    @Autowired
    private FirstClassMapper firstClassMapper;
    @Autowired
    private SecondClassMapper secondClassMapper;

    @ApiOperation("一级分类列表")
    @RequestMapping(value = "firstclass", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<FirstClassBean>> firstClassList(Integer groupId) {
        List<FirstClassBean> list = null;
        int count = 0;
        if(groupId != null) {
            list = firstClassMapper.listByGroupId(groupId);
            count = firstClassMapper.countByGroupId(groupId);
        } else {
            list = firstClassMapper.list();
            count = firstClassMapper.count();
        }
        DataArray<FirstClassBean> data = new DataArray<>();
        data.setCount(count);
        data.setArray(list);
        return new RestResp<>(data);

    }

    @ApiOperation("二级分类列表")
    @RequestMapping(value = "secondclass", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<SecondClassBean>> secondClassList(Integer groupId, Integer firstClassId) {
        List<SecondClassBean> list = null;
        int count = 0;
        if(groupId != null) {
            list = secondClassMapper.listByGroupId(groupId, firstClassId);
            count = secondClassMapper.countByGroupId(groupId, firstClassId);
        } else {
            list = secondClassMapper.list(firstClassId);
            count = secondClassMapper.count(firstClassId);
        }
        DataArray<SecondClassBean> data = new DataArray<>();
        data.setCount(count);
        data.setArray(list);
        return new RestResp<>(data);
    }
}
