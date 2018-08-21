package org.ccf.ccfpedia.cms.service;

import org.ccf.ccfpedia.cms.bean.FirstClassBean;
import org.ccf.ccfpedia.cms.bean.RoleBean;

import java.util.List;

public interface EntryService {

    //查询一级分类词表
    List<FirstClassBean> getFirstClassList();
    //获取一级分类词表数量
    int getExpertTaskStateCount();
    //新增一级词表
    int addFirstClassEntry(FirstClassBean firstClassBean);
    //更新一级词条
    void updateFirstClassEntry(FirstClassBean firstClassBean);
}
