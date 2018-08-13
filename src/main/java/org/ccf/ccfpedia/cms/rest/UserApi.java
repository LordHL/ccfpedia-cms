package org.ccf.ccfpedia.cms.rest;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "user")
public class UserApi {

    @ApiOperation("hello")
    @RequestMapping(value = "hello/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable(value = "name") String name) {
        return "Hello " + name;
    }
}
