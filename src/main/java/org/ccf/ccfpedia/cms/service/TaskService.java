package org.ccf.ccfpedia.cms.service;

import org.ccf.ccfpedia.cms.bean.EntryBean;
import org.ccf.ccfpedia.cms.bean.TaskBean;

import java.util.List;

public interface TaskService {

    //获取任务
    TaskBean getTaskView(Integer roleId, Integer taskId );
    //获取任务列表新
    List<TaskBean> getTaskViewListNew(Integer userid, String keyword, Integer status_id, Integer pageNo, Integer pageSize );
    //获取任务数量
    int getCountNew(Integer userId, String keyword, Integer status_id);
    //工委专委新建任务
    int addTask(TaskBean taskBean);
    //工委专委修改任务
    int modifyTask(TaskBean taskBean);
    //工委专委删除任务
    int deleteTask(TaskBean taskBean);
    //用户完成任务
    int confirmTask(Integer userId,Integer id);
    //驳回任务
    int rejectTask(int userId, int taskId, String memo);
}
