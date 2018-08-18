package org.ccf.ccfpedia.cms.service;

import org.ccf.ccfpedia.cms.bean.GroupBean;

import java.util.List;

public interface GroupService {

    GroupBean getGroupById(Integer id);

    List<GroupBean> getGroupList(Integer pageNo, Integer pageSize);

    int getGroupCount();
}
