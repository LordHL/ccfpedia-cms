package org.ccf.ccfpedia.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ccf.ccfpedia.cms.bean.FirstClassBean;
import org.ccf.ccfpedia.cms.bean.SecondClassBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SecondClassMapper {

    List<SecondClassBean> list(@Param("firstClassId") Integer firstClassId);
    int count(@Param("firstClassId") Integer firstClassId);

    List<SecondClassBean> listByGroupId(@Param("groupId") Integer groupId, @Param("firstClassId") Integer firstClassId);
    int countByGroupId(@Param("groupId") Integer groupId, @Param("firstClassId") Integer firstClassId);
}
