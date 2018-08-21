package org.ccf.ccfpedia.cms.service;

import org.ccf.ccfpedia.cms.bean.GroupBean;
import org.ccf.ccfpedia.cms.bean.UserBean;

import java.util.List;

public interface GroupClassService {

    void update(Integer groupId, List<Integer> firstClassIdList, List<Integer> secondClassIdList);
}
