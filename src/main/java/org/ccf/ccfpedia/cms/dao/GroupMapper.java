package org.ccf.ccfpedia.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ccf.ccfpedia.cms.bean.GroupBean;
import org.ccf.ccfpedia.cms.bean.RoleBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupMapper {

    GroupBean selectByPrimaryKey(@Param("id") Integer id);

    GroupBean selectSimpleByPrimaryKey(@Param("id") Integer id);

    List<GroupBean> selectByPaging(@Param("limit") Integer limit, @Param("offset") Integer offset);

    int count();
}
