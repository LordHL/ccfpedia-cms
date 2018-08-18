package org.ccf.ccfpedia.cms.service;

import org.ccf.ccfpedia.cms.bean.UserApplyBean;
import org.ccf.ccfpedia.cms.bean.UserBean;

import java.util.List;

public interface UserApplyService {

    UserApplyBean getUserApplyById(Integer id);

    List<UserApplyBean> getUserApplyListByIdList(List<Integer> idList);

    int addUserApply(UserApplyBean userApplyBean);

    List<UserApplyBean> getUserApplyList(List<Integer> status, Integer pageNo, Integer pageSize);

    UserApplyBean getUserApplyByAccount(String account);

    int getUserApplyCount(List<Integer> status);

    void update(UserApplyBean userApplyBean);
}
