package org.ccf.ccfpedia.cms.service.impl;

import org.ccf.ccfpedia.cms.bean.UserApplyBean;
import org.ccf.ccfpedia.cms.bean.UserBean;
import org.ccf.ccfpedia.cms.dao.UserApplyMapper;
import org.ccf.ccfpedia.cms.dao.UserMapper;
import org.ccf.ccfpedia.cms.service.UserApplyService;
import org.ccf.ccfpedia.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserApplyServiceImpl implements UserApplyService {

    @Autowired
    private UserApplyMapper userApplyMapper;

    @Override
    public UserApplyBean getUserApplyById(Integer id) {
        return userApplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserApplyBean> getUserApplyListByIdList(List<Integer> idList) {
        return userApplyMapper.selectByPrimaryKeyList(idList);
    }

    @Override
    public int addUserApply(UserApplyBean userApplyBean) {
        return userApplyMapper.insert(userApplyBean);
    }

    @Override
    public List<UserApplyBean> getUserApplyList(List<Integer> status, Integer pageNo, Integer pageSize){
        Integer limit = null;
        Integer offset = null;
        if(pageNo != null && pageSize != null){
            offset = (pageNo - 1) * pageSize;
            limit = pageSize;
        }
        List<UserApplyBean> userApplyList = userApplyMapper.selectByPaging(status, limit, offset);
        return userApplyList;
    }

    @Override
    public UserApplyBean getUserApplyByAccount(String account) {
        return userApplyMapper.selectByAccount(account);
    }

    @Override
    public int getUserApplyCount(List<Integer> status){
        return userApplyMapper.count(status);
    }

    @Override
    public void update(UserApplyBean userApplyBean) {
        userApplyMapper.updateByPrimaryKeySelective(userApplyBean);
    }

}
