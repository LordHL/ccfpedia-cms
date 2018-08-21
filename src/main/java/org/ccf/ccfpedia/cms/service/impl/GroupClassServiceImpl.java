package org.ccf.ccfpedia.cms.service.impl;

import org.ccf.ccfpedia.cms.bean.GroupBean;
import org.ccf.ccfpedia.cms.dao.GroupFirstClassMapper;
import org.ccf.ccfpedia.cms.dao.GroupMapper;
import org.ccf.ccfpedia.cms.dao.GroupSecondClassMapper;
import org.ccf.ccfpedia.cms.service.GroupClassService;
import org.ccf.ccfpedia.cms.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupClassServiceImpl implements GroupClassService {

    @Autowired
    private GroupFirstClassMapper groupFirstClassMapper;
    @Autowired
    private GroupSecondClassMapper groupSecondClassMapper;

    @Override
    public void update(Integer groupId, List<Integer> firstClassIdList, List<Integer> secondClassIdList) {
        if(firstClassIdList != null){
            groupFirstClassMapper.delete(groupId);
            List<Map<String, Integer>> relationList = new ArrayList();
            for(Integer classId : firstClassIdList){
                Map<String, Integer> map = new HashMap<>();
                map.put("groupId", groupId);
                map.put("classId", classId);
                relationList.add(map);
            }
            groupFirstClassMapper.insertMany(relationList);
        }
        if(secondClassIdList != null){
            groupSecondClassMapper.delete(groupId);
            List<Map<String, Integer>> relationList = new ArrayList();
            for(Integer classId : secondClassIdList){
                Map<String, Integer> map = new HashMap<>();
                map.put("groupId", groupId);
                map.put("classId", classId);
                relationList.add(map);
            }
            groupSecondClassMapper.insertMany(relationList);
        }
    }
}
