package org.ccf.ccfpedia.cms.service.impl;

import org.ccf.ccfpedia.cms.bean.GroupBean;
import org.ccf.ccfpedia.cms.dao.GroupMapper;
import org.ccf.ccfpedia.cms.dao.GroupMapper;
import org.ccf.ccfpedia.cms.service.GroupService;
import org.ccf.ccfpedia.cms.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public GroupBean getGroupById(Integer id){
        GroupBean group = groupMapper.selectByPrimaryKey(id);
        return group;
    }

    @Override
    public List<GroupBean> getGroupList(Integer pageNo, Integer pageSize){
        Integer limit = null;
        Integer offset = null;
        if(pageNo != null && pageSize != null){
            offset = (pageNo - 1) * pageSize;
            limit = pageSize;
        }
        List<GroupBean> groupList = groupMapper.selectByPaging(limit, offset);
        return groupList;
    }

    @Override
    public int getGroupCount(){
        return groupMapper.count();
    }
}
