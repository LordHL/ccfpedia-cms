package org.ccf.ccfpedia.cms.service.impl;

import org.ccf.ccfpedia.cms.bean.TaskBean;
import org.ccf.ccfpedia.cms.bean.TaskViewBean;
import org.ccf.ccfpedia.cms.dao.TaskMapper;
import org.ccf.ccfpedia.cms.dao.TaskViewMapper;
import org.ccf.ccfpedia.cms.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskViewMapper taskViewMapper;

    @Override
    public List<TaskBean> getTaskList(int id){
        List<TaskBean> TaskList = taskMapper.selectByCommitteeId(id);
        return TaskList;
    }

    @Override
    public List<TaskViewBean> getCommitteeTaskViewList(int id) {
        List<TaskViewBean> TaskViewList = taskViewMapper.selectByCommitteeId(id);
        return TaskViewList;
    }

    @Override
    public List<TaskViewBean> getCommitteeStateViewList(int id, int stateId) {
        List<TaskViewBean> TaskViewList = taskViewMapper.selectByCommitteeAndState(id,stateId);
        return TaskViewList;
    }

    @Override
    public int getCommitteeTaskCount(int id) {
        int count= taskViewMapper.committeeAllCount(id);
        return count;
    }

    @Override
    public int getCommitteeTaskStateCount(int id, int statusId) {
        int count= taskViewMapper.committeeStateCount(id,statusId);
        return count;
    }

    @Override
    public List<TaskViewBean> getEditTaskViewList(int id) {
        List<TaskViewBean> TaskViewList = taskViewMapper.selectByEditId(id);
        return TaskViewList;
    }

    @Override
    public List<TaskViewBean> getEditStateViewList(int id, int stateId) {
        List<TaskViewBean> TaskViewList = taskViewMapper.selectByEditAndState(id,stateId);
        return TaskViewList;
    }

    @Override
    public int getEditTaskCount(int id) {
        int count= taskViewMapper.editAllCount(id);
        return count;
    }

    @Override
    public int getEditTaskStateCount(int id, int statusId) {
        int count= taskViewMapper.editStateCount(id,statusId);
        return count;
    }

}
