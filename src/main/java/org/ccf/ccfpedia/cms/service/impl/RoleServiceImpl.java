package org.ccf.ccfpedia.cms.service.impl;

import org.ccf.ccfpedia.cms.bean.RoleBean;
import org.ccf.ccfpedia.cms.bean.UserBean;
import org.ccf.ccfpedia.cms.dao.RoleMapper;
import org.ccf.ccfpedia.cms.dao.UserMapper;
import org.ccf.ccfpedia.cms.service.RoleService;
import org.ccf.ccfpedia.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleBean getRoleById(Integer id){
        RoleBean role = roleMapper.selectByPrimaryKey(id);
        return role;
    }

    @Override
    public List<RoleBean> getRoleList(Integer pageNo, Integer pageSize){
        Integer limit = null;
        Integer offset = null;
        if(pageNo != null && pageSize != null){
            offset = (pageNo - 1) * pageSize;
            limit = pageSize;
        }
        List<RoleBean> roleList = roleMapper.selectByPaging(limit, offset);
        return roleList;
    }

    @Override
    public int getRoleCount(){
        return roleMapper.count();
    }
}
