package org.ccf.ccfpedia.cms.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ccf.ccfpedia.cms.bean.RoleBean;
import org.ccf.ccfpedia.cms.bean.resp.DataArray;
import org.ccf.ccfpedia.cms.bean.resp.RestResp;
import org.ccf.ccfpedia.cms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "role")
public class RoleApi {

    @Autowired
    private RoleService roleService;

    @ApiOperation("角色列表")
    @RequestMapping(value = "list", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<RoleBean>> list(Integer pageNo, Integer pageSize) {
        List<RoleBean> roleList = roleService.getRoleList(pageNo, pageSize);
        int userCount = roleService.getRoleCount();
        DataArray<RoleBean> data = new DataArray<>();
        data.setCount(userCount);
        data.setArray(roleList);
        return new RestResp<>(data);
    }

}
