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

    int count();
    int addTask(TaskBean taskBean);
}
