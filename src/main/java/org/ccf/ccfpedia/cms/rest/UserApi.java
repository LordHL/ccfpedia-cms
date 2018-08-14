package org.ccf.ccfpedia.cms.rest;

import io.swagger.annotations.ApiOperation;
import org.ccf.ccfpedia.cms.bean.UserBean;
import org.ccf.ccfpedia.cms.bean.resp.DataArray;
import org.ccf.ccfpedia.cms.bean.resp.RestResp;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "user")
public class UserApi {

    @ApiOperation("hello")
    @RequestMapping(value = "hello/{name}", method = RequestMethod.GET)
    public String hello(String name) {
        return "Hello " + name;
    }

    @ApiOperation("用户列表")
    @RequestMapping(value = "list", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<UserBean>> list(Integer pageNo, Integer pageSize) {
        return new RestResp<>();
    }
}
