package org.ccf.ccfpedia.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ccf.ccfpedia.cms.bean.TaskViewBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskViewMapper {
    List<TaskViewBean> selectByCommitteeId(@Param("id") Integer id);
    int committeeAllCount(@Param("id") Integer id);
    int committeeStateCount(@Param("id") Integer id,@Param("statusId") Integer stateId);
    List<TaskViewBean> selectByCommitteeAndState(@Param("id") Integer id,@Param("statusId") Integer stateId);
    List<TaskViewBean> selectByEditId(@Param("id") Integer id);
    int editAllCount(@Param("id") Integer id);
    int editStateCount(@Param("id") Integer id,@Param("statusId") Integer stateId);
    List<TaskViewBean> selectByEditAndState(@Param("id") Integer id,@Param("statusId") Integer stateId);

    List<TaskViewBean> selectByExpertId(@Param("id") Integer id);
    int allExpertCount(@Param("id") Integer id);
    int expertStateCount(@Param("id") Integer id,@Param("statusId") Integer statusId);
    List<TaskViewBean> selectByExpertAndState(@Param("id") Integer id,@Param("statusId") Integer statusId);
}
