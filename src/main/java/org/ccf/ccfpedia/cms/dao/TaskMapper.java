package org.ccf.ccfpedia.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ccf.ccfpedia.cms.bean.TaskBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskMapper {
    TaskBean selectById(@Param("taskId") Integer id);
    List<TaskBean> selectTaskViewByState(@Param("id") Integer id);
    int taskViewStateCount(@Param("id") Integer id);
    List<TaskBean> selectTaskViewList(@Param("userId") Integer userId, @Param("roleId") Integer roleId,
                                      @Param("keyword") String keyword, @Param("status") List<Integer> idList,
                                      @Param("taskType") Integer taskType, @Param("limit") Integer limit,
                                      @Param("offset") Integer offset);
    int getCountNew(@Param("userId") Integer userid, @Param("roleId") Integer roleId, @Param("keyword") String keyword,
                    @Param("status") List<Integer> statusId, @Param("taskType") Integer taskType);


    int addTask(TaskBean taskBean);
    int editCompleteTask(@Param("id") Integer id);
    int modifyTask(TaskBean taskBean);
    int deleteTask(TaskBean taskBean);
    int completeTask(TaskBean taskBean);
    int confirmTask(@Param("roleId") Integer id, @Param("id") Integer taskId);
    int expertRejectTask(@Param("id") Integer id, @Param("memo") String memo);
    int rejectTask(@Param("roleId") Integer userId, @Param("taskId") Integer taskId, @Param("memo") String memo);
    int editRejectTask(@Param("id") Integer id, @Param("memo") String memo);
}
