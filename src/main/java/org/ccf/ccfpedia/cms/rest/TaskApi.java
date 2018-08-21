package org.ccf.ccfpedia.cms.rest;

import io.swagger.annotations.ApiOperation;
import org.ccf.ccfpedia.cms.bean.EntryBean;
import org.ccf.ccfpedia.cms.bean.FirstClassBean;
import org.ccf.ccfpedia.cms.bean.TaskBean;
import org.ccf.ccfpedia.cms.bean.TaskViewBean;
import org.ccf.ccfpedia.cms.bean.resp.DataArray;
import org.ccf.ccfpedia.cms.bean.resp.RestResp;
import org.ccf.ccfpedia.cms.service.EntryService;
import org.ccf.ccfpedia.cms.service.TaskService;
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
    private EntryService entryService;


    @ApiOperation("工委任务列表")
    @RequestMapping(value = "committee/{id}/tasklist", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<TaskViewBean>> committeeTaskViewList(@PathVariable("id")Integer id) {
        List<TaskViewBean> taskViewList = taskService.getCommitteeTaskViewList(id);
        int userCount = taskService.getCommitteeTaskCount(id);
        DataArray<TaskViewBean> data = new DataArray<>();
        data.setCount(userCount);
        data.setArray(taskViewList);
        return new RestResp<>(data);
    }

    @ApiOperation("工委任务状态列表")
    @RequestMapping(value = "committee/{id}/taskstate/{stateId}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<TaskViewBean>> committeeTaskStateList(@PathVariable("id")Integer id,@PathVariable("stateId")Integer stateId) {
        List<TaskViewBean> taskViewList = taskService.getCommitteeStateViewList(id,stateId);
        int userCount = taskService.getCommitteeTaskStateCount(id,stateId);
        DataArray<TaskViewBean> data = new DataArray<>();
        data.setCount(userCount);
        data.setArray(taskViewList);
        return new RestResp<>(data);
    }

    @ApiOperation("编辑任务列表")
    @RequestMapping(value = "edit/{id}/tasklist", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<TaskViewBean>> editTaskViewList(@PathVariable("id")Integer id) {
        List<TaskViewBean> taskViewList = taskService.getEditTaskViewList(id);
        int userCount = taskService.getEditTaskCount(id);
        DataArray<TaskViewBean> data = new DataArray<>();
        data.setCount(userCount);
        data.setArray(taskViewList);
        return new RestResp<>(data);
    }

    @ApiOperation("编辑任务状态列表")
    @RequestMapping(value = "edit/{id}/taskstate/{stateId}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<TaskViewBean>> editTaskStateList(@PathVariable("id")Integer id,@PathVariable("stateId")Integer stateId) {
        List<TaskViewBean> taskViewList = taskService.getEditStateViewList(id,stateId);
        int userCount = taskService.getEditTaskStateCount(id,stateId);
        DataArray<TaskViewBean> data = new DataArray<>();
        data.setCount(userCount);
        data.setArray(taskViewList);
        return new RestResp<>(data);
    }

    @ApiOperation("工委专委新建任务")
    @RequestMapping(value = "create", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp addTask(@RequestBody TaskBean taskBean) {
        RestResp<TaskBean> resp = null;
        int temp = taskService.addTask(taskBean);
        if(temp==1){
            resp = new RestResp<>(200, "任务创建成功");
        }else{
            resp = new RestResp<>(400, "任务创建失败");
        }
        return resp;
    }

    @ApiOperation("工委专委修改任务")
    @RequestMapping(value = "modify", method = RequestMethod.PUT, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp modifyTask(@RequestBody TaskBean taskBean) {
        RestResp<TaskBean> resp = null;
        int temp = taskService.modifyTask(taskBean);
        if(temp==1){
            resp = new RestResp<>(200, "任务修改成功");
        }else{
            resp = new RestResp<>(400, "任务修改失败");
        }
        return resp;
    }

    @ApiOperation("工委专委删除任务")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.PUT, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp deleteTask(@PathVariable("id")Integer id) {
        RestResp<TaskBean> resp = null;
        TaskBean taskBean = taskService.getTaskById(id);
        int temp = taskService.deleteTask(taskBean);
        if(temp==1){
            resp = new RestResp<>(200, "任务删除成功");
        }else{
            resp = new RestResp<>(400, "任务删除失败");
        }
        return resp;
    }

    @ApiOperation("工委专委确认任务完成")
    @RequestMapping(value = "taskstate/completed/{id}", method = RequestMethod.PUT, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp commnitteeCompletedTask(@PathVariable("id")Integer id) {
        RestResp<TaskBean> resp = null;
        TaskBean taskBean = taskService.getTaskById(id);
        int temp = taskService.completeTask(taskBean);
        if(temp==1){
            resp = new RestResp<>(200, "任务成功");
        }else{
            resp = new RestResp<>(400, "任务失败");
        }
        return resp;
    }

    @ApiOperation("专委驳回任务")
    @RequestMapping(value = "taskstate/expertreject/{id}", method = RequestMethod.PUT, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp espertRejectTask(@PathVariable("id")Integer id,@RequestBody String memo) {
        RestResp<TaskBean> resp = null;
        int temp = taskService.expertRejectTask(id,memo);
        if(temp==1){
            resp = new RestResp<>(200, "驳回成功");
        }else{
            resp = new RestResp<>(400, "驳回失败");
        }
        return resp;
    }

    @ApiOperation("编辑确认任务完成")
    @RequestMapping(value = "taskstate/editcompleted/{id}", method = RequestMethod.PUT, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp editCompletedTask(@PathVariable("id")Integer id) {
        RestResp<TaskBean> resp = null;
        int temp = taskService.editCompleteTask(id);
        if(temp==1){
            resp = new RestResp<>(200, "任务成功");
        }else{
            resp = new RestResp<>(400, "任务失败");
        }
        return resp;
    }

    @ApiOperation("编辑驳回任务")
    @RequestMapping(value = "taskstate/editreject/{id}", method = RequestMethod.PUT, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp editRejectTask(@PathVariable("id")Integer id,@RequestBody String memo) {
        RestResp<TaskBean> resp = null;
        int temp = taskService.editRejectTask(id,memo);
        if(temp==1){
            resp = new RestResp<>(200, "驳回成功");
        }else{
            resp = new RestResp<>(400, "驳回失败");
        }
        return resp;
    }

    @ApiOperation("任务词条列表")
    @RequestMapping(value = "taskentry/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<EntryBean>> selectTaskEntryByTaskId(@PathVariable("id")Integer id) {
        List<EntryBean> entryBeanList = taskService.getTaskEntryByTaskId(id);
        int userCount = taskService.getTaskEntryCount(id);
        DataArray<EntryBean> data = new DataArray<>();
        data.setCount(userCount);
        data.setArray(entryBeanList);
        return new RestResp<>(data);
    }

    @ApiOperation("各状态任务列表")
    @RequestMapping(value = "taskstate/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<TaskViewBean>> taskViewStateList(@PathVariable("id")Integer id) {
        List<TaskViewBean> taskViewList = taskService.getTaskViewByState(id);
        int userCount = taskService.taskViewStateCount(id);
        DataArray<TaskViewBean> data = new DataArray<>();
        data.setCount(userCount);
        data.setArray(taskViewList);
        return new RestResp<>(data);
    }

    @ApiOperation("专委任务列表")
    @RequestMapping(value = "expert/{id}/tasklist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<TaskViewBean>> expertTaskViewList(@PathVariable("id") Integer id) {
        List<TaskViewBean> expertTaskViewList = taskService.getExpertTaskViewList(id);
        int taskCount = taskService.getExpertTaskCount(id);
        DataArray<TaskViewBean> data = new DataArray<>();
        data.setCount(taskCount);
        data.setArray(expertTaskViewList);
        return new RestResp<>(data);
    }

    @ApiOperation("专委任务状态列表")
    @RequestMapping(value = "expert/{id}/taskstate/{statusId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<TaskViewBean>> expertTaskViewList(@PathVariable("id") Integer id, @PathVariable("statusId") Integer statusId) {
        List<TaskViewBean> exepertTaskStatusViewList = taskService.getExpertStateViewList(id, statusId);
        int taskCount = taskService.getExpertTaskStateCount(id, statusId);
        DataArray<TaskViewBean> data = new DataArray<>();
        data.setCount(taskCount);
        data.setArray(exepertTaskStatusViewList);
        return new RestResp<>(data);

    }

    @ApiOperation("一级分类词条列表")
    @RequestMapping(value = "/queryFirstClass", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<FirstClassBean>> expertTaskViewList() {
        List<FirstClassBean> firstClassList = entryService.getFirstClassList();
        int firstclassCount = entryService.getExpertTaskStateCount();
        DataArray<FirstClassBean> data = new DataArray<>();
        data.setCount(firstclassCount);
        data.setArray(firstClassList);
        return new RestResp<>(data);

    }

    @ApiOperation("新建一级分类词条")
    @RequestMapping(value = "/addFirstClass", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FirstClassBean addFirstClassEntry(@RequestBody FirstClassBean firstClassBean) {
        entryService.addFirstClassEntry(firstClassBean);
        return firstClassBean;
    }


    @ApiOperation("修改一级分类词条")
    @RequestMapping(value = "/modifyFirstClass", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FirstClassBean modifyFirstClassEntry(@RequestBody FirstClassBean firstClassBean){
        if(firstClassBean != null){
            entryService.updateFirstClassEntry(firstClassBean);
        }
        return firstClassBean;
    }
}
