package org.ccf.ccfpedia.cms.service;

import org.ccf.ccfpedia.cms.bean.FirstClassBean;
import org.ccf.ccfpedia.cms.bean.RoleBean;
import org.ccf.ccfpedia.cms.bean.SecondClassBean;

import java.util.List;

public interface EntryService {

    //查询一级分类词表
    List<FirstClassBean> getFirstClassList();
    //获取一级分类词表数量
    int getFirstClassEntryCount();
    //新增一级词表
    int addFirstClassEntry(FirstClassBean firstClassBean);
    //更新一级词条
    void updateFirstClassEntry(FirstClassBean firstClassBean);

    //查询二级分类词表
    List<SecondClassBean> getSecondClassList();
    //获取二级分类词表数量
    int getSecondClassEntryCount();
    //新增二级词表
    int addSecondClassEntry(SecondClassBean secondClassBean);
    //更新二级词条
    void updateSecondClassEntry(SecondClassBean secondClassBean);
    //查询一级词条下的二级词条
    List<SecondClassBean> getSecondClassEntryByFirstClassId(Integer id);
    //获取一级词条下二级词条数量
    int getSecondClassEntryByFirstCount(Integer id);

}
