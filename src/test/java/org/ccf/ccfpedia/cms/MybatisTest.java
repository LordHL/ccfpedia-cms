package org.ccf.ccfpedia.cms;

import com.alibaba.fastjson.JSONObject;
import org.ccf.ccfpedia.cms.bean.*;
import org.ccf.ccfpedia.cms.dao.EntityMapper;
import org.ccf.ccfpedia.cms.dao.GroupFirstClassMapper;
import org.ccf.ccfpedia.cms.service.*;
import org.ccf.ccfpedia.cms.util.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
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
    @Resource
    private EntityMapper entityMapper;
    @Resource
    private EntityService entityService;
    @Resource
    private GroupClassService groupClassService;

    @Resource
    private GroupFirstClassMapper groupFirstClassMapper;


    @Test
    public void tesEntity() {
        List<EntityBean> entityListByTaskId = entityMapper.getEntityListByTaskId(1);
        System.out.println(JSONObject.toJSON(entityListByTaskId));
    }
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
        GroupBean group = groupService.getGroupById(1);
        System.out.println(JsonUtils.toJson(group));
    }

    @Test
    public void testGroupClass() {
        List<FirstClassBean> firstClassBeans = groupFirstClassMapper.selectClassByGroupId(1);
        System.out.println(JsonUtils.toJson(firstClassBeans));
    }

    @Test
    public void testInsertMany() {
        List<Integer> fidList = new ArrayList<>();
        fidList.add(8);
        fidList.add(9);

        List<Integer> sidList = new ArrayList<>();
        sidList.add(13);
        sidList.add(14);
        groupClassService.update(1, fidList, sidList);
    }

    @Test
    public void testList() {
        List<UserBean> userList = userService.getUserList("王", 1, 1, 10);
        System.out.println(JSONObject.toJSON(userList));
    }

    @Test
    public void testCount() {
        String keyword = "王";
        int userCount = userService.getUserCount(keyword);
        System.out.println(userCount);
    }


    @Test//4.1工委专委创建任务
    public void testAddTask() {
       TaskBean taskBean = new TaskBean();
//        taskBean.setCommitteeId(3);
//        taskBean.setCreationTime(new Timestamp(new Date().getTime()));
//        taskBean.setDeadline(new Timestamp(new Date().getTime()));
//        taskBean.setDescription("工委测试创建任务");
//        taskBean.setFounderId(2);
        taskBean.setName("工委测试创建任务");
        taskBean.setMemo("工委测试创建任务");
        taskBean.setStatusId(1);
        System.out.println(taskService.addTask(taskBean));
    }

    @Test//4.2工委专委修改任务
    public void testModifyTask() {
        TaskBean taskBean = new TaskBean();
//        taskBean.setCommitteeId(3);
//        taskBean.setId(1);
//        taskBean.setCreationTime(new Timestamp(new Date().getTime()));
//        taskBean.setDeadline(new Timestamp(new Date().getTime()));
//        taskBean.setDescription("工委测试修改任务");
//        taskBean.setFounderId(2);
        List<EntryBean> entry = new ArrayList<>();
        EntryBean temp = new EntryBean();
        temp.setName("petritest");
        temp.setId(1);
        entry.add(temp);
        temp = new EntryBean();
        temp.setName("人工智能test");
        temp.setId(4);
        entry.add(temp);
        temp = new EntryBean();
        temp.setName("网络与数据通信test");
        temp.setId(5);
        entry.add(temp);
        taskBean.setEntry(entry);
        taskBean.setName("工委测试修改任务");
        taskBean.setMemo("工委测试修改任务");
        taskBean.setStatusId(1);
        System.out.println(taskService.modifyTask(taskBean));
    }

    @Test//4.3工委专委删除任务
    public void testDeleteTask() {
        TaskBean taskBean = taskService.getTaskView(1, 14);
        System.out.println(taskService.deleteTask(taskBean));
    }

    @Test//4.12任务详情
    public void testTaskView() {
        TaskBean taskBean = taskService.getTaskView(1, 1);
        System.out.println(JSONObject.toJSON(taskBean));
    }


    @Test//4.13用户确认任务完成
    public void testConfirm() {

        System.out.println(taskService.confirmTask(1,5));
    }

    @Test//4.8用户驳回任务
    public void testReject() {
        System.out.println(taskService.rejectTask(64,1,"测试用户驳回任务"));
    }

    /*
    @Test//6.5查询一级词条
    public void testFirstClassEntryList() {
        List<EntryBean> entryBean = entryService.getFirstClassEntry(2);
        int count = entryService.getFirstClassEntryCount(2);
        System.out.println(JSONObject.toJSON(entryBean));
        System.out.println("count:" + count);
    }

    @Test//6.6查询二级词条
    public void testSecondClassEntryList() {
        List<EntryBean> entryBean = entryService.getSecondClassEntry(2);
        int count = entryService.getSecondClassEntryCount(2);
        System.out.println(JSONObject.toJSON(entryBean));
        System.out.println("count:" + count);
    }

    @Test//6.7通过状态查询词条
    public void testEntryListByState() {
        List<EntryBean> entryBean = entryService.getEntryByStatus(1);
        int count = entryService.getEntryCountBystatus(1);
        System.out.println(JSONObject.toJSON(entryBean));
        System.out.println("count:" + count);
    }
    */
}
