package org.ccf.ccfpedia.cms.service;

import org.ccf.ccfpedia.cms.bean.TaskBean;
import org.ccf.ccfpedia.cms.bean.TaskViewBean;

import java.util.List;

public interface TaskService {

    List<TaskBean> getTaskList(int id );
    int getCount();
    List<TaskViewBean> getTaskViewList(int id);
    int getViewCount();

}
