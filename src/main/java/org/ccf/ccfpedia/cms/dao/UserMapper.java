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

    UserBean selectSimpleByPrimaryKey(Integer id);

    List<UserBean> selectByPrimaryKeyList(@Param("idList") List<Integer> idList);

    UserBean selectByAccount(String account);

    List<UserBean> selectByKeyword(@Param("keyword") String keyword, @Param("limit") Integer limit, @Param("offset") Integer offset);

    int count(@Param("keyword") String keyword);

    int deleteByPrimaryKey(Integer id);

    int insert(UserBean user);

    int updateByPrimaryKeySelective(UserBean user);
}
