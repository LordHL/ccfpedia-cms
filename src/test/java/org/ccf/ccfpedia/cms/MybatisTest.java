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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

    @Test//1.1查看工委所有任务
    public void committeeTestTask() {
        List<TaskViewBean> taskBean = taskService.getCommitteeTaskViewList(1);
        int count=taskService.getCommitteeTaskCount(1);
        System.out.println(JSONObject.toJSON(taskBean));
        System.out.println("count:"+count);
    }
    @Test//1.2查看工委各状态任务
    public void committeeTestState() {
        List<TaskViewBean> taskBean = taskService.getCommitteeStateViewList(1, 1);
        int count = taskService.getCommitteeTaskStateCount(1, 1);
        System.out.println(JSONObject.toJSON(taskBean));
        System.out.println("count:"+count);
    }
    @Test//3.1查看编辑所有任务
    public void editTestTask() {
        List<TaskViewBean> taskBean = taskService.getEditTaskViewList(5);
        int count=taskService.getEditTaskCount(5);
        System.out.println(JSONObject.toJSON(taskBean));
        System.out.println("count:"+count);
    }
    @Test//3.2查看编辑各状态任务
    public void editTestState() {
        List<TaskViewBean> taskBean = taskService.getEditStateViewList(5,1);
        int count=taskService.getEditTaskStateCount(5,1);
        System.out.println(JSONObject.toJSON(taskBean));
        System.out.println("count:"+count);
    }

    @Test//4.1工委专委创建任务
    public void testAddTask() {
       TaskBean taskBean = new TaskBean();
        taskBean.setCommitteeId(3);
        taskBean.setCreationTime(new Timestamp(new Date().getTime()));
        taskBean.setDeadline(new Timestamp(new Date().getTime()));
        taskBean.setDescription("工委测试创建任务");
        taskBean.setFounderId(2);
        taskBean.setName("工委测试创建任务");
        taskBean.setMemo("工委测试创建任务");
        taskBean.setStatusId(1);
        System.out.println(taskService.addTask(taskBean));
    }

    @Test//4.2工委专委修改任务
    public void testModifyTask() {
        TaskBean taskBean = new TaskBean();
        taskBean.setCommitteeId(3);
        taskBean.setId(14);
        taskBean.setCreationTime(new Timestamp(new Date().getTime()));
        taskBean.setDeadline(new Timestamp(new Date().getTime()));
        taskBean.setDescription("工委测试修改任务");
        taskBean.setFounderId(2);
        taskBean.setName("工委测试修改任务");
        taskBean.setMemo("工委测试修改任务");
        taskBean.setStatusId(1);
        System.out.println(taskService.modifyTask(taskBean));
    }

    @Test//4.3工委专委删除任务
    public void testDeleteTask() {
        TaskBean taskBean = taskService.getTaskById(16);
        System.out.println(taskService.deleteTask(taskBean));
    }

    @Test//4.4工委专委确认任务完成
    public void testCompleteTask() {
        TaskBean taskBean = taskService.getTaskById(14);
        System.out.println(taskService.completeTask(taskBean));
    }

    @Test
    public void testExpertTaskView() {
        List<TaskViewBean> taskBean = taskService.getExpertTaskViewList(2);
        System.out.println(JSONObject.toJSON(taskBean));
    }

    @Test
    public void testExpertTaskStateView() {
        List<TaskViewBean> taskBean = taskService.getExpertStateViewList(2,2);
        System.out.println(JSONObject.toJSON(taskBean));
    }

}
