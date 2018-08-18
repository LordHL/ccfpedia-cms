package org.ccf.ccfpedia.cms.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ccf.ccfpedia.cms.bean.GroupBean;
import org.ccf.ccfpedia.cms.bean.UserBean;
import org.ccf.ccfpedia.cms.bean.resp.DataArray;
import org.ccf.ccfpedia.cms.bean.resp.RestResp;
import org.ccf.ccfpedia.cms.service.GroupService;
import org.ccf.ccfpedia.cms.service.RoleService;
import org.ccf.ccfpedia.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "group")
public class GroupApi {

    @Autowired
    private GroupService groupService;

    @ApiOperation("专委列表")
    @RequestMapping(value = "list", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<GroupBean>> list(Integer pageNo, Integer pageSize) {
        List<GroupBean> groupList = groupService.getGroupList(pageNo, pageSize);
        int userCount = groupService.getGroupCount();
        DataArray<GroupBean> data = new DataArray<>();
        data.setCount(userCount);
        data.setArray(groupList);
        return new RestResp<>(data);
    }

}
