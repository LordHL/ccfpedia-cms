package org.ccf.ccfpedia.cms.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ccf.ccfpedia.cms.bean.UserBean;
import org.ccf.ccfpedia.cms.bean.resp.DataArray;
import org.ccf.ccfpedia.cms.bean.resp.RestResp;
import org.ccf.ccfpedia.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Api("用户模块")
@RequestMapping(value = "user")
public class UserApi {

    @Autowired
    private UserService userService;

    @ApiOperation("hello")
    @RequestMapping(value = "hello/{name}", method = RequestMethod.GET)
    public String hello(String name) {
        return "Hello " + name;
    }

    @ApiOperation("用户列表")
    @RequestMapping(value = "list", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<UserBean>> list(Integer pageNo, Integer pageSize) {
        List<UserBean> userList = userService.getUserList(pageNo, pageSize);
        int userCount = userService.getUserCount();
        DataArray<UserBean> data = new DataArray<>();
        data.setCount(userCount);
        data.setArray(userList);
        return new RestResp<>(data);
    }

    @ApiOperation("用户登录")
    @RequestMapping(value = "login", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<UserBean> login(String account, String password) {
        RestResp<UserBean> resp = null;
        UserBean user = userService.login(account, password);
        if(user != null) {
            resp = new RestResp<>(user);
        } else {
            resp = new RestResp<>(400, "登录失败");
        }
        return resp;
    }
}
