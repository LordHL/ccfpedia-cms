package org.ccf.ccfpedia.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ccf.ccfpedia.cms.bean.UserBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    UserBean selectByPrimaryKey(Integer id);

    UserBean selectByAccount(String account);

    List<UserBean> selectByPaging(@Param("limit") Integer limit, @Param("offset") Integer offset);

    int count();

    int deleteByPrimaryKey(Integer id);

    int insert(UserBean user);

    int updateByPrimaryKeySelective(UserBean user);
}
