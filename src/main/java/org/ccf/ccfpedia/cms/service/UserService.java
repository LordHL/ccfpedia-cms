package org.ccf.ccfpedia.cms.service;

import org.ccf.ccfpedia.cms.bean.UserBean;

import java.util.List;

public interface UserService {

    int addUser(UserBean user);

    UserBean getUserById(Integer id);

    List<UserBean> getUserList(Integer pageNo, Integer pageSize);

    int getUserCount();

    UserBean login(String account, String password);
}
