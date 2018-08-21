package org.ccf.ccfpedia.cms.service.impl;

import org.ccf.ccfpedia.cms.bean.EntryBean;
import org.ccf.ccfpedia.cms.bean.TaskBean;
import org.ccf.ccfpedia.cms.bean.TaskViewBean;
import org.ccf.ccfpedia.cms.bean.UserBean;
import org.ccf.ccfpedia.cms.dao.EntryMapper;
import org.ccf.ccfpedia.cms.dao.TaskMapper;
import org.ccf.ccfpedia.cms.dao.TaskViewMapper;
import org.ccf.ccfpedia.cms.service.TaskService;
import org.ccf.ccfpedia.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskViewMapper taskViewMapper;
    @Autowired
    private EntryMapper entryMapper;
    @Autowired
    private UserService userService;


    @Override
    public List<TaskViewBean> getTaskViewList(Integer userId, String keyword, Integer status_id, Integer pageNo, Integer pageSize) {
        Integer roleId = null;
        if(userId != null){
            UserBean user = userService.getUserById(userId);
            roleId = user.getRole().getId();
        }
        Integer limit = null;
        Integer offset = null;
        if(pageNo != null && pageSize != null){
            offset = (pageNo - 1) * pageSize;
            limit = pageSize;
        }
        return taskViewMapper.selectTaskViewList(userId, roleId, keyword, status_id, limit, offset);
    }

    @Override
    public TaskBean getTaskById(int id) {
        TaskBean taskBean = taskMapper.selectByTaskId(id);
        return taskBean;
    }

    @Override
    public int getCount(Integer userId, String keyword, Integer status_id) {
        Integer roleId = null;
        if(userId != null){
            UserBean user = userService.getUserById(userId);
            roleId = user.getRole().getId();
        }
        return taskViewMapper.getCount(userId, roleId, keyword, status_id);
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
    public List<TaskBean> getExpertTaskList(int id){
        List<TaskBean> TaskList = taskMapper.selectByExpertId(id);
        return TaskList;
    }

    @Override
    public List<TaskViewBean> getExpertTaskViewList(int id) {
        List<TaskViewBean> TaskViewList = taskViewMapper.selectByExpertId(id);
        return TaskViewList;
    }

    @Override
    public List<TaskViewBean> getExpertStateViewList(int id, int statusId) {
        List<TaskViewBean> TaskViewList = taskViewMapper.selectByExpertAndState(id,statusId);
        return TaskViewList;
    }

    @Override
    public int getExpertTaskCount(int id) {
        int count= taskViewMapper.allExpertCount(id);
        return count;
    }

    @Override
    public int getExpertTaskStateCount(int id, int statusId) {
        int count= taskViewMapper.expertStateCount(id,statusId);
        return count;
    }
    @Override
    public int getEditTaskStateCount(int id, int statusId) {
        int count= taskViewMapper.editStateCount(id,statusId);
        return count;
    }

    @Override
    public int addTask(TaskBean taskBean) {
        return taskMapper.addTask(taskBean);
    }

    @Override
    public int modifyTask(TaskBean taskBean) {
        return taskMapper.modifyTask(taskBean);
    }

    @Override
    public int deleteTask(TaskBean taskBean) {
        return taskMapper.deleteTask(taskBean);
    }

    @Override
    public int completeTask(TaskBean taskBean) { return taskMapper.completeTask(taskBean); }

    @Override
    public int expertRejectTask(int id, String memo) { return taskMapper.expertRejectTask(id,memo);}

    @Override
    public int editCompleteTask(int id) { return taskMapper.editCompleteTask(id);}

    @Override
    public int editRejectTask(int id, String memo) { return taskMapper.editRejectTask(id,memo);}

    @Override
    public List<TaskViewBean> getTaskViewByState(int id) { return taskViewMapper.selectTaskViewByState(id); }

    @Override
    public int taskViewStateCount(int id) {
        int count= taskViewMapper.taskViewStateCount(id);
        return count;
    }

    @Override
    public List<EntryBean> getTaskEntryByTaskId(int id) {
        return entryMapper.selectEntryByTaskId(id);
    }

    @Override
    public int getTaskEntryCount(int id) {
        return entryMapper.selectEntryCountByTaskId(id);
    }

}
