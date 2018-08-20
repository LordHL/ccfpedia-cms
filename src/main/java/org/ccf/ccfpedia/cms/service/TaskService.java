package org.ccf.ccfpedia.cms.service;

import org.ccf.ccfpedia.cms.bean.TaskBean;
import org.ccf.ccfpedia.cms.bean.TaskViewBean;

import java.util.List;

public interface TaskService {

    List<TaskBean> getTaskList(int id );
    //通过ID查询任务
    TaskBean getTaskById(int id);
    //获取工委所有任务列表
    List<TaskViewBean> getCommitteeTaskViewList(int id);
    //获取工委个状态任务列表
    List<TaskViewBean> getCommitteeStateViewList(int id,int stateId);
    //获取工委所有任务数量
    int getCommitteeTaskCount(int id);
    //获取工委各状态任务数量
    int getCommitteeTaskStateCount(int id,int statusId);
    //获取编辑所有任务列表
    List<TaskViewBean> getEditTaskViewList(int id);
    //获取编辑个状态任务列表
    List<TaskViewBean> getEditStateViewList(int id,int stateId);
    //获取编辑所有任务数量
    int getEditTaskCount(int id);
    //获取编辑各状态任务数量
    int getEditTaskStateCount(int id,int statusId);
    //工委专委新建任务
    int addTask(TaskBean taskBean);
    //工委专委修改任务
    int modifyTask(TaskBean taskBean);
    //工委专委删除任务
    int deleteTask(TaskBean taskBean);

    List<TaskBean> getExpertTaskList(int id );
    //获取专委所有任务列表
    List<TaskViewBean> getExpertTaskViewList(int id);
    //获取专委各状态任务列表
    List<TaskViewBean> getExpertStateViewList(int id,int statusId);
    //获取专委所有任务数量
    int getExpertTaskCount(int id);
    //获取专委各状态任务数量
    int getExpertTaskStateCount(int id,int statusId);
}
