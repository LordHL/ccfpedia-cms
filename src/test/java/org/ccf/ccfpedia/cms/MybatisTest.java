package org.ccf.ccfpedia.cms;

import com.alibaba.fastjson.JSONObject;
import org.ccf.ccfpedia.cms.bean.*;
import org.ccf.ccfpedia.cms.dao.GroupFirstClassMapper;
import org.ccf.ccfpedia.cms.service.*;
import org.ccf.ccfpedia.cms.util.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
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
    @Resource
    private EntryService entryService;
    @Resource
    private GroupClassService groupClassService;

    @Resource
    private GroupFirstClassMapper groupFirstClassMapper;


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
        List<UserBean> userList = userService.getUserList("王", 1, 10);
        System.out.println(JSONObject.toJSON(userList));
    }

    @Test
    public void testCount() {
        String keyword = "王";
        int userCount = userService.getUserCount(keyword);
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

    @Test//4.4、4.5工委专委确认任务完成
    public void testCompleteTask() {
        TaskBean taskBean = taskService.getTaskById(14);
        System.out.println(taskService.completeTask(taskBean));
    }

    @Test//4.6专委驳回任务
    public void testExpertRejectTask() {
        System.out.println(taskService.expertRejectTask(15,"测试专委驳回任务"));
    }

    @Test//4.7编辑完成任务
    public void testEditCompleteTask() {
        System.out.println(taskService.editCompleteTask(18));
    }

    @Test//4.8编辑驳回任务
    public void testEditRejectTask() {
        System.out.println(taskService.editRejectTask(16,"测试编辑驳回任务"));
    }

    @Test//4.9获取任务中的词条
    public void testTaskEntry() {
        List<EntryBean> taskBean = taskService.getTaskEntryByTaskId(1);
        int count=taskService.getTaskEntryCount(1);
        System.out.println(JSONObject.toJSON(taskBean));
        System.out.println("count:"+count);
    }

    @Test//4.10通过任务状态查询所有任务
    public void testSelectTaskByStatus() {
        List<TaskViewBean> taskBean = taskService.getTaskViewByState(1);
        int count = taskService.taskViewStateCount(1);
        System.out.println(JSONObject.toJSON(taskBean));
        System.out.println(count);
    }

    @Test//2.1查看专委的所有任务
    public void testExpertTaskView() {
        List<TaskViewBean> taskBean = taskService.getExpertTaskViewList(2);
        System.out.println(JSONObject.toJSON(taskBean));
    }

    @Test//2.2查看专委各状态的任务
    public void testExpertTaskStateView() {
        List<TaskViewBean> taskBean = taskService.getExpertStateViewList(2, 2);
        System.out.println(JSONObject.toJSON(taskBean));
    }

    @Test//5.1查询一级词条分类列表
    public void firstClassEntryList() {
        List<FirstClassBean> firstClassBean = entryService.getFirstClassList();
        int count = entryService.getExpertTaskStateCount();
        System.out.println(JSONObject.toJSON(firstClassBean));
        System.out.println("count:" + count);
    }

    @Test//5.2创建一级词条
    public void testAddFirstClassEntry() {
        FirstClassBean firstClassBean = new FirstClassBean();
        firstClassBean.setName("测试一级词条");
        entryService.addFirstClassEntry(firstClassBean);
        System.out.println(JSONObject.toJSON(firstClassBean));
    }

    @Test//5.3修改一级词条
    public void modifyfirstClassEntryList() {
        FirstClassBean firstClassBean = new FirstClassBean();
        firstClassBean.setId(13);
        firstClassBean.setName("测试修改一级词条");
        entryService.updateFirstClassEntry(firstClassBean);
        System.out.println(JSONObject.toJSON(firstClassBean));
    }
}
