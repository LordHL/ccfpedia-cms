package org.ccf.ccfpedia.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.ccf.ccfpedia.cms.bean.TaskEntityBean;
import org.ccf.ccfpedia.cms.bean.TaskNewEntryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskNewEntryMapper {
    List<String> getEntityListByTaskId(Integer taskId);
    int deleteByTaskId(Integer taskId);
    int insertMany(List<TaskNewEntryBean> list);
}
