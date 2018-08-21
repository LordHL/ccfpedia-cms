package org.ccf.ccfpedia.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ccf.ccfpedia.cms.bean.FirstClassBean;
import org.ccf.ccfpedia.cms.bean.EntryBean;
import org.ccf.ccfpedia.cms.bean.RoleBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EntryMapper {

    List<FirstClassBean> getFirstClassEntryList();
    int firstClassAllCount();
    int addFirstClassEntry(FirstClassBean firstCla0ssBean);
    int updateByPrimaryKeySelective(FirstClassBean firstClassBean);
    List<EntryBean> selectEntryByTaskId(@Param("id") Integer id);
    int selectEntryCountByTaskId(@Param("id") Integer id);
}
