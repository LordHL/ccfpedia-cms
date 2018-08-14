package org.ccf.ccfpedia.cms.dao;

import org.ccf.ccfpedia.cms.bean.UserBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
public interface UserMapper {

    UserBean selectByPrimaryKey(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(UserBean user);

    int updateByPrimaryKeySelective(UserBean user);
}
