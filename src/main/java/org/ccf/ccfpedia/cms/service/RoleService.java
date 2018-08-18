package org.ccf.ccfpedia.cms.service;

import org.ccf.ccfpedia.cms.bean.RoleBean;

import java.util.List;

public interface RoleService {

    RoleBean getRoleById(Integer id);

    List<RoleBean> getRoleList(Integer pageNo, Integer pageSize);

    int getRoleCount();
}
