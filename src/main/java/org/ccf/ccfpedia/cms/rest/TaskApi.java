package org.ccf.ccfpedia.cms.rest;

import io.swagger.annotations.ApiOperation;
import org.ccf.ccfpedia.cms.bean.*;
import org.ccf.ccfpedia.cms.bean.resp.DataArray;
import org.ccf.ccfpedia.cms.bean.resp.RestResp;
import org.ccf.ccfpedia.cms.service.EntityService;
import org.ccf.ccfpedia.cms.service.TaskService;
import org.ccf.ccfpedia.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "task")
public class TaskApi {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @Autowired
    private EntityService entityService;

    @ApiOperation("任务列表")
    @RequestMapping(value = "taskviewlistnew", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<TaskBean>> taskViewListNew(Integer userId, Integer status_id, String keyword, Integer pageNo, Integer pageSize) {
        List<TaskBean> taskViewList = taskService.getTaskViewListNew(userId, keyword, status_id, pageNo, pageSize);
        int userCount = taskService.getCountNew(userId, keyword, status_id);
        DataArray<TaskBean> data = new DataArray<>();
        data.setCount(userCount);
        data.setArray(taskViewList);
        return new RestResp<>(data);
    }

    @ApiOperation("任务详情")
    @RequestMapping(value = "taskview", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<TaskBean> taskView(Integer userId, Integer taskId) {
        UserBean user = userService.getUserById(userId);
        if(user != null){
            TaskBean taskView = taskService.getTaskView(user.getRole().getId(), taskId);
            return new RestResp<>(taskView);
        } else {
            return new RestResp<>(400, "用户不存在");
        }
    }

    @ApiOperation("工委专委新建任务")
    @RequestMapping(value = "create", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp addTask(@RequestBody TaskBean taskBean) {
        RestResp<TaskBean> resp = null;
        if(taskBean != null) {
            if(taskBean.getGroup() != null) {
                UserBean leader = userService.getLeaderByGroup(taskBean.getGroup().getId());
                taskBean.setCommittee(leader);
                if(leader.getId() == taskBean.getFounder().getId()){
                    taskBean.setStatusId(6);
                } else {
                    taskBean.setStatusId(4);
                }
            }
            int temp = taskService.addTask(taskBean);
            if (temp == 1) {
                resp = new RestResp<>(200, "任务创建成功");
            } else {
                resp = new RestResp<>(400, "任务创建失败");
            }
        }
        return resp;
    }

    @ApiOperation("工委专委修改任务")
    @RequestMapping(value = "modify", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp modifyTask(@RequestBody TaskBean taskBean) {
        taskService.modifyTask(taskBean);
        return new RestResp<>(200, "任务修改成功");
    }

    @ApiOperation("工委专委删除任务")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp deleteTask(@PathVariable("id")Integer id) {
        RestResp<TaskBean> resp = null;
        TaskBean taskBean = taskService.getTaskView(1, id);
        int temp = taskService.deleteTask(taskBean);
        if(temp==1){
            resp = new RestResp<>(200, "任务删除成功");
        }else{
            resp = new RestResp<>(400, "任务删除失败");
        }
        return resp;
    }

    @ApiOperation("确认任务完成")
    @RequestMapping(value = "confirm", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp confirmTask(Integer userId,Integer taskId) {
        RestResp<TaskBean> resp = null;
        int temp = taskService.confirmTask(userId,taskId);
        if(temp==1){
            resp = new RestResp<>(200, "任务成功");
        }else{
            resp = new RestResp<>(400, "任务失败");
        }
        return resp;
    }

    @ApiOperation("驳回任务")
    @RequestMapping(value = "reject", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp rejectTask(Integer userId,Integer taskId, String memo) {
        RestResp<TaskBean> resp = null;
        int temp = taskService.rejectTask(userId,taskId,memo);
        if(temp==1){
            resp = new RestResp<>(200, "驳回成功");
        }else{
            resp = new RestResp<>(400, "驳回失败");
        }
        return resp;
    }

}
