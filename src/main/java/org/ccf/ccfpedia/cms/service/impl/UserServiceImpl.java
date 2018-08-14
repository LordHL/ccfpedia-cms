package org.ccf.ccfpedia.cms.service.impl;

import org.ccf.ccfpedia.cms.bean.UserBean;
import org.ccf.ccfpedia.cms.dao.UserMapper;
import org.ccf.ccfpedia.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(UserBean user) {
        return userMapper.insert(user);
    }
}
