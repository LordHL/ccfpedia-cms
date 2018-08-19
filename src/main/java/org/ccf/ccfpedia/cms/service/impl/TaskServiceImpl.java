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
    public int getCount(){
        int count= taskMapper.count();
        return count;
    }

    @Override
    public List<TaskViewBean> getTaskViewList(int id){
        List<TaskViewBean> TaskViewList = taskViewMapper.selectByCommitteeId(id);
        return TaskViewList;
    }

    @Override
    public int getViewCount(){
        int count= taskViewMapper.count();
        return count;
    }


}
