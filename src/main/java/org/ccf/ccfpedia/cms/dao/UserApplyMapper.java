package org.ccf.ccfpedia.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ccf.ccfpedia.cms.bean.UserApplyBean;
import org.ccf.ccfpedia.cms.bean.UserBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserApplyMapper {

    UserApplyBean selectByPrimaryKey(Integer id);

    List<UserApplyBean> selectByPrimaryKeyList(@Param("idList") List<Integer> idList);

    UserApplyBean selectByAccount(String account);

    List<UserApplyBean> selectByPaging(@Param("status") List<Integer> status, @Param("limit") Integer limit, @Param("offset") Integer offset);

    int count(@Param("status") List<Integer> status);

    int insert(UserApplyBean userApplyBean);

    int updateByPrimaryKeySelective(UserApplyBean userApplyBean);
}
