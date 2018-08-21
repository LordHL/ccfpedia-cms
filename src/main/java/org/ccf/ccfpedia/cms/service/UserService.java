package org.ccf.ccfpedia.cms.service;

import org.ccf.ccfpedia.cms.bean.UserBean;

import java.util.List;

public interface UserService {

    int addUser(UserBean user);

    UserBean getUserById(Integer id);

    List<UserBean> getUserList(String keyword, Integer pageNo, Integer pageSize);

    List<UserBean> getUserListByIdList(List<Integer> idList);

    int getUserCount(String keyword);

    UserBean getUserByAccount(String account);

    UserBean login(String account, String password);

    void update(UserBean user);
}
