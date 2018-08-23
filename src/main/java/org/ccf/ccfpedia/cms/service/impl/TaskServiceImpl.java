package org.ccf.ccfpedia.cms.service.impl;

import org.ccf.ccfpedia.cms.bean.*;
import org.ccf.ccfpedia.cms.dao.EntryMapper;
import org.ccf.ccfpedia.cms.dao.TaskEntryMapper;
import org.ccf.ccfpedia.cms.dao.TaskMapper;
import org.ccf.ccfpedia.cms.dao.TaskViewMapper;
import org.ccf.ccfpedia.cms.service.TaskService;
import org.ccf.ccfpedia.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskViewMapper taskViewMapper;
    @Autowired
    private TaskEntryMapper taskEntryMapper;
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
    public TaskViewBean getTaskView(Integer roleId, Integer taskId) {
        TaskViewBean taskViewBean = taskViewMapper.selectById(taskId);
        taskViewBean.setStatus(getTaskStatusBeanByRoleIdAndStatusId(roleId, taskViewBean.getStatusId()));
        return taskViewBean;
    }

    @Override
    public List<TaskViewBean> getTaskViewListNew(Integer userId, String keyword, Integer status_id, Integer pageNo, Integer pageSize) {
        List<Integer> status = new ArrayList<Integer>();
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
        if(status_id != null) {
            if (roleId == 1) {//工委
                switch (status_id) {
                    case 1:
                        status.add(1);
                        status.add(2);
                        status.add(4);
                        status.add(5);
                        status.add(6);
                        status.add(7);
                        status.add(8);
                        status.add(9);
                        status.add(10);
                        status.add(11);
                        status.add(12);
                        status.add(13);
                        break;
                    case 2:
                        status.add(4);
                        status.add(6);
                        status.add(7);
                        status.add(8);
                        status.add(9);
                        status.add(10);
                        status.add(11);
                        break;
                    case 3:
                        status.add(5);
                        break;
                    case 4:
                        status.add(1);
                        status.add(2);
                        status.add(12);
                        break;
                    case 5:
                        status.add(13);
                        break;
                }
            } else if (roleId == 2) {//专委
                switch (status_id) {
                    case 1:
                        status.add(4);
                        status.add(5);
                        status.add(6);
                        status.add(7);
                        status.add(9);
                        status.add(10);
                        status.add(11);
                        status.add(12);
                        break;
                    case 2:
                        status.add(9);
                        status.add(10);
                        status.add(11);
                        break;
                    case 3:
                        status.add(11);
                        break;
                    case 4:
                        status.add(6);
                        status.add(7);
                        status.add(10);
                        break;
                    case 5:
                        status.add(12);
                        break;
                }
            } else if (roleId == 3) {//编辑
                switch (status_id) {
                    case 1:
                        status.add(9);
                        status.add(10);
                        status.add(11);
                        break;
                    case 2:
                        status.add(11);
                        break;
                    case 3:
                        break;
                    case 4:
                        status.add(9);
                        break;
                    case 5:
                        status.add(10);
                        break;
                }
            }
        }
        List<TaskViewBean> taskViewBeans = taskViewMapper.selectTaskViewListNew(userId, roleId, keyword, status, limit, offset);
        for(TaskViewBean taskViewBean : taskViewBeans){
            taskViewBean.setStatus(getTaskStatusBeanByRoleIdAndStatusId(roleId, taskViewBean.getStatusId()));
        }
        return taskViewBeans;
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
    public int getCountNew(Integer userId, String keyword, Integer status_id) {
        List<Integer> status = new ArrayList<Integer>();
        Integer roleId = null;
        if(userId != null){
            UserBean user = userService.getUserById(userId);
            roleId = user.getRole().getId();
        }
        if(status_id != null) {
            if (roleId == 1) {//工委
                switch (status_id) {
                    case 1:
                        status.add(1);
                        status.add(2);
                        status.add(4);
                        status.add(5);
                        status.add(6);
                        status.add(7);
                        status.add(8);
                        status.add(9);
                        status.add(10);
                        status.add(11);
                        status.add(12);
                        status.add(13);
                        break;
                    case 2:
                        status.add(4);
                        status.add(6);
                        status.add(7);
                        status.add(8);
                        status.add(9);
                        status.add(10);
                        status.add(11);
                        break;
                    case 3:
                        status.add(5);
                        break;
                    case 4:
                        status.add(1);
                        status.add(2);
                        status.add(12);
                        break;
                    case 5:
                        status.add(13);
                        break;
                }
            } else if (roleId == 2) {//专委
                switch (status_id) {
                    case 1:
                        status.add(4);
                        status.add(5);
                        status.add(6);
                        status.add(7);
                        status.add(9);
                        status.add(10);
                        status.add(11);
                        status.add(12);
                        break;
                    case 2:
                        status.add(9);
                        status.add(10);
                        status.add(11);
                        break;
                    case 3:
                        status.add(11);
                        break;
                    case 4:
                        status.add(6);
                        status.add(7);
                        status.add(10);
                        break;
                    case 5:
                        status.add(12);
                        break;
                }
            } else if (roleId == 3) {//编辑
                switch (status_id) {
                    case 1:
                        status.add(9);
                        status.add(10);
                        status.add(11);
                        break;
                    case 2:
                        status.add(11);
                        break;
                    case 3:
                        break;
                    case 4:
                        status.add(9);
                        break;
                    case 5:
                        status.add(10);
                        break;
                }
            }
        }
        return taskViewMapper.getCountNew(userId, roleId, keyword, status);
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
        List<EntryBean> entry = taskBean.getEntry();
        int number = entry.size();
        TaskEntryBean taskEntry = new TaskEntryBean();
        for(int i = 0;i<number;i++){
            taskEntry.setTaskId(taskBean.getId());
            taskEntry.setName(entry.get(i).getName());
            taskEntry.setEntryId(entry.get(i).getId());
            taskEntryMapper.create(taskEntry);
        }
        return taskMapper.addTask(taskBean);
    }

    @Override
    public int modifyTask(TaskBean taskBean) {
        taskEntryMapper.delete(taskBean.getId());
        List<EntryBean> entry = taskBean.getEntry();
        int number = entry.size();
        for(int i = 0;i<number;i++){
            TaskEntryBean taskEntryBean = new TaskEntryBean();
            taskEntryBean.setEntryId(entry.get(i).getId());
            taskEntryBean.setName(entry.get(i).getName());
            taskEntryBean.setTaskId(taskBean.getId());
            taskEntryMapper.create(taskEntryBean);
        }
        return taskMapper.modifyTask(taskBean);
    }

    @Override
    public int deleteTask(TaskBean taskBean) {
        return taskMapper.deleteTask(taskBean);
    }

    @Override
    public int completeTask(TaskBean taskBean) { return taskMapper.completeTask(taskBean); }

    @Override
    public int confirmTask(Integer userId,Integer id) {
        Integer roleId = null;
        if(userId != null){
            UserBean user = userService.getUserById(userId);
            roleId = user.getRole().getId();
        }

        return taskMapper.confirmTask(roleId,id);    }

    @Override
    public int expertRejectTask(int id, String memo) { return taskMapper.expertRejectTask(id,memo);}

    @Override
    public int rejectTask(int userId, int taskId, String memo) {
        UserBean user = userService.getUserById(userId);
        int roleId = user.getRole().getId();
        return taskMapper.rejectTask(roleId,taskId,memo);}

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

    private TaskStatusBean getTaskStatusBeanByRoleIdAndStatusId(int roleId, int statusId){
        TaskStatusBean statusBean = new TaskStatusBean();
        List<Integer> ongoingList = new ArrayList<>();
        List<Integer> backList = new ArrayList<>();
        List<Integer> tocompleteList = new ArrayList<>();
        List<Integer> completedList = new ArrayList<>();
        if(roleId == 1){
            ongoingList.add(4);
            ongoingList.add(6);
            ongoingList.add(7);
            ongoingList.add(8);
            ongoingList.add(9);
            ongoingList.add(10);
            ongoingList.add(11);
            backList.add(5);
            tocompleteList.add(1);
            tocompleteList.add(2);
            tocompleteList.add(12);
            completedList.add(13);
        } else if (roleId == 2){
            ongoingList.add(9);
            ongoingList.add(10);
            ongoingList.add(11);
            backList.add(11);
            tocompleteList.add(6);
            tocompleteList.add(7);
            tocompleteList.add(10);
            completedList.add(12);
        } else if(roleId == 3){
            ongoingList.add(11);
            tocompleteList.add(9);
            completedList.add(10);
        }

        if(ongoingList.contains(statusId)){
            statusBean.setId(2);
            statusBean.setName("进行中");
        } else if(backList.contains(statusId)){
            statusBean.setId(3);
            statusBean.setName("被退回");
        } else if(tocompleteList.contains(statusId)){
            statusBean.setId(4);
            statusBean.setName("待完成");
        } else if(completedList.contains(statusId)){
            statusBean.setId(5);
            statusBean.setName("已完成");
        }
        return statusBean;
    }

}
