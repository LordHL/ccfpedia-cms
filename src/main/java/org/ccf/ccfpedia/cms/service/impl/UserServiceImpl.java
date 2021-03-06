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
    public List<UserBean> getUserListByIdList(List<Integer> idList){
        return userMapper.selectByPrimaryKeyList(idList);
    }

    @Override
    public List<UserBean> getUserList(String keyword, Integer groupId, Integer pageNo, Integer pageSize){
        Integer limit = null;
        Integer offset = null;
        if(pageNo != null && pageSize != null){
            offset = (pageNo - 1) * pageSize;
            limit = pageSize;
        }
        List<UserBean> userList = userMapper.selectByKeyword(keyword, groupId, limit, offset);
        return userList;
    }

    @Override
    public List<UserBean> getEditorListByGroupId(Integer groupId, Integer pageNo, Integer pageSize){
        Integer limit = null;
        Integer offset = null;
        if(pageNo != null && pageSize != null){
            offset = (pageNo - 1) * pageSize;
            limit = pageSize;
        }
        List<UserBean> userList = userMapper.selectEditorListByGroupId(groupId, limit, offset);
        return userList;
    }

    @Override
    public int getEditorCountByGroupId(Integer groupId){
        return userMapper.countEditorListByGroupId(groupId);
    }

    @Override
    public int getUserCount(String keyword){
        return userMapper.count(keyword);
    }

    @Override
    public UserBean login(String account, String password){
        return userMapper.selectByAccount(account);
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

    @Override
    public UserBean getLeaderByGroup(Integer id){
        return userMapper.getLeaderByGroup(id);
    }
}
