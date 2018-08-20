package org.ccf.ccfpedia.cms.rest;

import io.swagger.annotations.ApiOperation;
import org.ccf.ccfpedia.cms.bean.TaskBean;
import org.ccf.ccfpedia.cms.bean.TaskViewBean;
import org.ccf.ccfpedia.cms.bean.resp.DataArray;
import org.ccf.ccfpedia.cms.bean.resp.RestResp;
import org.ccf.ccfpedia.cms.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "role")
public class TaskApi {

    @Autowired
    private TaskService taskService;

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
    @RequestMapping(value = "/committee/{id}/taskstate/{stateId}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<TaskViewBean>> committeeTaskStateList(@PathVariable("id")Integer id,@PathVariable("stateId")Integer stateId) {
        List<TaskViewBean> taskViewList = taskService.getCommitteeStateViewList(id,stateId);
        int userCount = taskService.getCommitteeTaskStateCount(id,stateId);
        DataArray<TaskViewBean> data = new DataArray<>();
        data.setCount(userCount);
        data.setArray(taskViewList);
        return new RestResp<>(data);
    }

}
