package org.ccf.ccfpedia.cms.rest;

import io.swagger.annotations.ApiOperation;
import org.ccf.ccfpedia.cms.bean.EntityBean;
import org.ccf.ccfpedia.cms.bean.EntryBean;
import org.ccf.ccfpedia.cms.bean.SecondClassBean;
import org.ccf.ccfpedia.cms.bean.UserBean;
import org.ccf.ccfpedia.cms.bean.resp.DataArray;
import org.ccf.ccfpedia.cms.bean.resp.RestResp;
import org.ccf.ccfpedia.cms.service.EntityService;
import org.ccf.ccfpedia.cms.service.UserService;
import org.ccf.ccfpedia.cms.util.MediawikiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "entry")
public class EntryApi {

    @Autowired
    private EntityService entityService;
    @Autowired
    private UserService userService;


    @ApiOperation("根据名称查询词条")
    @RequestMapping(value = "search", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<EntityBean> search(String name) {
        EntityBean entity = entityService.getEntityByName(name);
        return new RestResp<>(entity);
    }

    @ApiOperation("查询词条列表")
    @RequestMapping(value = "/entrylist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<EntryBean>> EntryViewList(String keywords,Integer firstClass,Integer secondClass,Integer pageNo, Integer pageSize) {
        List<EntityBean> entityList = entityService.getEntityList(keywords,firstClass,secondClass,pageNo,pageSize);
        List<EntryBean> entryList = new ArrayList<>();
        if(entityList != null){
            for(EntityBean entity : entityList){
                entryList.add(new EntryBean(entity));
            }
        }
        int count = entityService.getEntityCount(keywords,firstClass,secondClass);
        DataArray<EntryBean> data = new DataArray<>();
        data.setCount(count);
        data.setArray(entryList);
        return new RestResp<>(data);
    }

    @ApiOperation("词条编辑地址")
    @RequestMapping(value = "url", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<String> entryUrl(Integer userId, String word) {
        RestResp<String> resp = null;
        UserBean user = userService.getUserById(userId);
        if(user != null){
            if(word != null && !word.equals("")) {
                String url = MediawikiUtils.getURL(user.getName(), user.getPassword(), word);
                resp = new RestResp<>(url);
            } else {
                resp = new RestResp<>(400, "词条不符合要求");
            }
        } else {
            resp = new RestResp<>(400, "用户不存在");
        }
        return resp;
    }

}
