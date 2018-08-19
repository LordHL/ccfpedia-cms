package org.ccf.ccfpedia.cms;

import com.alibaba.fastjson.JSONObject;
import org.ccf.ccfpedia.cms.bean.*;
import org.ccf.ccfpedia.cms.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

    @Resource
    private UserService userService;

    @Resource
    private UserApplyService userApplyService;
    @Resource
    private RoleService roleService;
    @Resource
    private GroupService groupService;
    @Resource
    private TaskService taskService;

    @Test
    public void testApplyList(){
        List<Integer> list = new ArrayList<>();
        list.add(0);
        List<UserApplyBean> userApplyList = userApplyService.getUserApplyList(list, 1, 10);
        System.out.println(userApplyList.size());
    }

    @Test
    public void testInsert() {
        UserBean user = new UserBean();
        user.setName("test");
        user.setPassword("test");
        RoleBean role = new RoleBean();
        role.setId(1);
        role.setName("test");
        user.setRole(role);
        userService.addUser(user);
    }

    @Test
    public void testOne() {
        UserBean user = userService.getUserById(1);
        System.out.println(user.getName());
    }

    @Test
    public void testOneGroup() {
        GroupBean role = groupService.getGroupById(1);
        System.out.println(role.getName());
    }

    @Test
    public void testList() {
        List<UserBean> userList = userService.getUserList("王", 1, 10);
        System.out.println(JSONObject.toJSON(userList));
    }

    @Test
    public void testCount() {
        int userCount = userService.getUserCount();
        System.out.println(userCount);
    }

    @Test
    public void testTaskView() {
        List<TaskViewBean> taskBean = taskService.getTaskViewList(1);
        System.out.println(JSONObject.toJSON(taskBean));
    }

}
