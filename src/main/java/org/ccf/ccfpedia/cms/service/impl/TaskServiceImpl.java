package org.ccf.ccfpedia.cms.service.impl;

import org.ccf.ccfpedia.cms.bean.EntityBean;
import org.ccf.ccfpedia.cms.bean.EntryBean;
import org.ccf.ccfpedia.cms.bean.TaskBean;
import org.ccf.ccfpedia.cms.bean.TaskEntityBean;
import org.ccf.ccfpedia.cms.bean.TaskNewEntryBean;
import org.ccf.ccfpedia.cms.bean.TaskStatusBean;
import org.ccf.ccfpedia.cms.bean.UserBean;
import org.ccf.ccfpedia.cms.dao.TaskEntityMapper;
import org.ccf.ccfpedia.cms.dao.TaskMapper;
import org.ccf.ccfpedia.cms.dao.TaskNewEntryMapper;
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
    private TaskEntityMapper taskEntityMapper;
    @Autowired
    private TaskNewEntryMapper taskNewEntryMapper;
    @Autowired
    private UserService userService;

    @Override
    public TaskBean getTaskView(Integer roleId, Integer taskId) {
        TaskBean taskBean = taskMapper.selectById(taskId);
        taskBean.setStatus(getTaskStatusBeanByRoleIdAndStatusId(roleId, taskBean.getStatusId()));
        convertToView(taskBean);
        return taskBean;
    }

    @Override
    public List<TaskBean> getTaskViewListNew(Integer userId, String keyword, Integer status_id,
                                             Integer taskType, Integer pageNo, Integer pageSize) {
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
                        status.add(11);
                        break;
                    case 3:
                        status.add(5);
                        break;
                    case 4:
                        status.add(1);
                        status.add(2);
                        break;
                    case 5:
                        status.add(10);
                        status.add(12);
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
                        status.add(5);
                        status.add(6);
                        status.add(9);
                        break;
                    case 3:
                        status.add(11);
                        break;
                    case 4:
                        status.add(4);
                        status.add(7);
                        break;
                    case 5:
                        status.add(10);
                        status.add(12);
                        break;
                }
            } else if (roleId == 3) {//编辑
                switch (status_id) {
                    case 1:
                        status.add(6);
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
                        status.add(6);
                        status.add(9);
                        break;
                    case 5:
                        status.add(10);
                        break;
                }
            } else if (roleId == 4) { //管理员
                status.add(1);
                status.add(2);
                status.add(3);
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
            }
        }
        List<TaskBean> taskBeans = new ArrayList<>();
        if(keyword == null || keyword.isEmpty()){
            keyword = null;
        }
        if(status.size() > 0) {
            taskBeans = taskMapper.selectTaskViewList(userId, roleId, keyword, status,taskType, limit, offset);
            for (TaskBean taskBean : taskBeans) {
                taskBean.setStatus(getTaskStatusBeanByRoleIdAndStatusId(roleId, taskBean.getStatusId()));
                convertToView(taskBean);
            }
        }
        return taskBeans;
    }

    @Override
    public int getCountNew(Integer userId, String keyword, Integer status_id,Integer taskType) {
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
                        status.add(11);
                        break;
                    case 3:
                        status.add(5);
                        break;
                    case 4:
                        status.add(1);
                        status.add(2);
                        break;
                    case 5:
                        status.add(10);
                        status.add(12);
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
                        status.add(5);
                        status.add(6);
                        status.add(9);
                        break;
                    case 3:
                        status.add(11);
                        break;
                    case 4:
                        status.add(4);
                        status.add(7);
                        break;
                    case 5:
                        status.add(10);
                        status.add(12);
                        break;
                }
            } else if (roleId == 3) {//编辑
                switch (status_id) {
                    case 1:
                        status.add(6);
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
                        status.add(6);
                        status.add(9);
                        break;
                    case 5:
                        status.add(10);
                        break;
                }
            } else if (roleId == 4) { //管理员
                status.add(1);
                status.add(2);
                status.add(3);
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
            }
        }
        int count = 0;
        if(status.size() > 0){
            count = taskMapper.getCountNew(userId, roleId, keyword, status,taskType);
        }
        return count;
    }

    @Override
    public int addTask(TaskBean taskBean) {
        //编辑
        taskBean.setTaskType(0);
        int num = taskMapper.addTask(taskBean);
        //转义
        List<EntryBean> entryList = taskBean.getEntry();
        List<TaskEntityBean> taskEntityList = new ArrayList<>();
        for(EntryBean entry : entryList){
            entry.setCategory(0);
            TaskEntityBean taskEntity = new TaskEntityBean();
            taskEntity.setEntityId(entry.getId());
            taskEntity.setTaskId(taskBean.getId());
            taskEntityList.add(taskEntity);

        }
        if(taskEntityList.size() > 0) {
            taskEntityMapper.insertMany(taskEntityList);
        }

        return num;
    }
    @Override
    public int addEntityTask(TaskBean taskBean) {
        //新增词条
        taskBean.setTaskType(1);
        int num = taskMapper.addTask(taskBean);
        List<EntryBean> entryList = taskBean.getEntry();
        List<TaskNewEntryBean> taskNewEntryList = new ArrayList<>();
        for(EntryBean entry : entryList){
            entry.setCategory(1);
            TaskNewEntryBean taskNewEntryBean = new TaskNewEntryBean();
            taskNewEntryBean.setName(entry.getName());
            taskNewEntryBean.setTaskId(taskBean.getId());
            taskNewEntryList.add(taskNewEntryBean);

        }

        if(taskNewEntryList.size() > 0) {
            taskNewEntryMapper.insertMany(taskNewEntryList);
        }
        return num;
    }

    @Override
    public int modifyTask(TaskBean taskBean) {
        taskEntityMapper.deleteByTaskId(taskBean.getId());
        taskNewEntryMapper.deleteByTaskId(taskBean.getId());
        List<EntryBean> entryList = taskBean.getEntry();
        List<TaskEntityBean> taskEntityList = new ArrayList<>();
        List<TaskNewEntryBean> taskNewEntryList = new ArrayList<>();
        for(EntryBean entry : entryList){
            if(entry.getCategory() == 0){
                TaskEntityBean taskEntity = new TaskEntityBean();
                taskEntity.setEntityId(entry.getId());
                taskEntity.setTaskId(taskBean.getId());
                taskEntityList.add(taskEntity);
            } else {
                TaskNewEntryBean taskNewEntryBean = new TaskNewEntryBean();
                taskNewEntryBean.setName(entry.getName());
                taskNewEntryBean.setTaskId(taskBean.getId());
                taskNewEntryList.add(taskNewEntryBean);
            }
        }
        if(taskEntityList.size() > 0) {
            taskEntityMapper.insertMany(taskEntityList);
        }
        if(taskNewEntryList.size() > 0) {
            taskNewEntryMapper.insertMany(taskNewEntryList);
        }
        if(taskBean.getExecutor() != null){
            taskBean.setStatusId(9);
        } else {
            taskBean.setStatusId(4);
        }
        return taskMapper.modifyTask(taskBean);
    }

    @Override
    public int deleteTask(TaskBean taskBean) {
        return taskMapper.deleteTask(taskBean);
    }

    @Override
    public int confirmTask(Integer userId,Integer id) {
        Integer roleId = null;
        if(userId != null){
            UserBean user = userService.getUserById(userId);
            roleId = user.getRole().getId();
        }

        return taskMapper.confirmTask(roleId,id);    }

    @Override
    public int rejectTask(int userId, int taskId, String memo) {
        UserBean user = userService.getUserById(userId);
        int roleId = user.getRole().getId();
        return taskMapper.rejectTask(roleId,taskId,memo);}

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
            ongoingList.add(11);
            backList.add(5);
            tocompleteList.add(1);
            tocompleteList.add(2);
            completedList.add(10);
            completedList.add(12);
            completedList.add(13);
        } else if (roleId == 2){
            ongoingList.add(5);
            ongoingList.add(6);
            ongoingList.add(9);
            backList.add(11);
            tocompleteList.add(4);
            tocompleteList.add(7);
            completedList.add(10);
            completedList.add(12);
        } else if(roleId == 3){
            ongoingList.add(11);
            tocompleteList.add(6);
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

    private void convertToView(TaskBean taskBean){
        List<EntryBean> entryList = new ArrayList<>();
        if(taskBean.getEntityList() != null){
            for(EntityBean entity : taskBean.getEntityList()){
                EntryBean entry = new EntryBean(entity);
                entryList.add(entry);
            }
            taskBean.setEntityList(null);
        }
        if(taskBean.getNewEntryList() != null){
            for(String name : taskBean.getNewEntryList()){
                EntryBean entry = new EntryBean();
                entry.setName(name);
                entry.setCategory(1);
                entryList.add(entry);
            }
            taskBean.setNewEntryList(null);
        }
        taskBean.setEntry(entryList);
    }

}
