package org.ccf.ccfpedia.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ccf.ccfpedia.cms.bean.RoleBean;
import org.ccf.ccfpedia.cms.bean.UserBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {

    RoleBean selectByPrimaryKey(Integer id);

    List<RoleBean> selectByPaging(@Param("limit") Integer limit, @Param("offset") Integer offset);

    int count();
}
