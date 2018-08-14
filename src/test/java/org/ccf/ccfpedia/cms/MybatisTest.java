package org.ccf.ccfpedia.cms;

import org.ccf.ccfpedia.cms.bean.RoleBean;
import org.ccf.ccfpedia.cms.bean.UserBean;
import org.ccf.ccfpedia.cms.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

    @Resource
    private UserService userService;

    @Test
    public void testInsert() {
        UserBean user = new UserBean();
        user.setName("test");
        user.setPassword("test");
        RoleBean role = new RoleBean();
        role.setId(1);
        role.setName("test");
        user.setRole(role);
        user.setStatus(1);
        userService.addUser(user);
    }
}
