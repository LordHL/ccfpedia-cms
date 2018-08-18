package org.ccf.ccfpedia.cms.service.impl;

import org.ccf.ccfpedia.cms.bean.UserBean;
import org.ccf.ccfpedia.cms.dao.UserMapper;
import org.ccf.ccfpedia.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(UserBean user) {
        return userMapper.insert(user);
    }

    @Override
    public UserBean getUserById(Integer id){
        UserBean user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public List<UserBean> getUserList(String keyword, Integer pageNo, Integer pageSize){
        Integer limit = null;
        Integer offset = null;
        if(pageNo != null && pageSize != null){
            offset = (pageNo - 1) * pageSize;
            limit = pageSize;
        }
        List<UserBean> userList = userMapper.selectByKeyword(keyword, limit, offset);
        return userList;
    }

    @Override
    public int getUserCount(){
        return userMapper.count();
    }

    @Override
    public UserBean login(String account, String password){
        UserBean user = userMapper.selectByAccount(account);
        if(user != null){
            if(user.getPassword() != null && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void update(UserBean user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public UserBean getUserByAccount(String account){
        UserBean user = userMapper.selectByAccount(account);
        return user;
    }
}
