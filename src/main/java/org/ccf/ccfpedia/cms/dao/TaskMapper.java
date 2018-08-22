package org.ccf.ccfpedia.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ccf.ccfpedia.cms.bean.TaskBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskMapper {
    List<TaskBean> selectByCommitteeId(@Param("id") Integer id);
    List<TaskBean> selectByExpertId(@Param("id") Integer id);
    TaskBean selectByTaskId(@Param("id") Integer id);
    int editCompleteTask(@Param("id") Integer id);
    int modifyTask(TaskBean taskBean);
    int deleteTask(TaskBean taskBean);
    int completeTask(TaskBean taskBean);
    int confirmTask(@Param("roleId") Integer id,@Param("id") Integer taskId);
    int expertRejectTask(@Param("id") Integer id,@Param("memo")String memo);
    int editRejectTask(@Param("id") Integer id,@Param("memo")String memo);
    int count();
    int addTask(TaskBean taskBean);
}
