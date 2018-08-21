package org.ccf.ccfpedia.cms.rest;

import com.google.common.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ccf.ccfpedia.cms.bean.GroupBean;
import org.ccf.ccfpedia.cms.bean.RoleBean;
import org.ccf.ccfpedia.cms.bean.UserBean;
import org.ccf.ccfpedia.cms.bean.resp.DataArray;
import org.ccf.ccfpedia.cms.bean.resp.RestResp;
import org.ccf.ccfpedia.cms.service.GroupClassService;
import org.ccf.ccfpedia.cms.service.GroupService;
import org.ccf.ccfpedia.cms.service.RoleService;
import org.ccf.ccfpedia.cms.service.UserService;
import org.ccf.ccfpedia.cms.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "group", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GroupApi {

    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupClassService groupClassService;

    @ApiOperation("专委列表")
    @GetMapping("list")
    public RestResp<DataArray<GroupBean>> list(Integer pageNo, Integer pageSize) {
        List<GroupBean> groupList = groupService.getGroupList(pageNo, pageSize);
        int userCount = groupService.getGroupCount();
        DataArray<GroupBean> data = new DataArray<>();
        data.setCount(userCount);
        data.setArray(groupList);
        return new RestResp<>(data);
    }

    @ApiOperation("根据id获得专委")
    @GetMapping("id")
    public RestResp<GroupBean> getById(Integer groupId) {
        GroupBean group = groupService.getGroupById(groupId);
        return new RestResp<>(group);
    }

    @ApiOperation("权限修改")
    @PostMapping("modify")
    public RestResp<GroupBean> modify(Integer groupId, String firstClasses, String secondClasses) {
        RestResp<GroupBean> resp = null;
        GroupBean group = groupService.getGroupById(groupId);
        if(group != null){
            List<Integer> firstClassList = null;
            List<Integer> secondClassList = null;
            if(firstClasses != null) {
                firstClassList = JsonUtils.fromJson(firstClasses, new TypeToken<List<Integer>>() {
                }.getType());
            }
            if(secondClasses != null) {
                secondClassList = JsonUtils.fromJson(secondClasses, new TypeToken<List<Integer>>() {
                }.getType());
            }
            groupClassService.update(group.getId(), firstClassList, secondClassList);
            resp = new RestResp<>(200, "成功");
        } else {
            resp = new RestResp<>(400, "专委不存在");
        }
        return resp;
    }

}
