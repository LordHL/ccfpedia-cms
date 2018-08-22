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

    List<EntryBean> selectEntryByTaskId(@Param("id") Integer id);
    int selectEntryCountByTaskId(@Param("id") Integer id);
    EntryBean getEntryById(@Param("id") Integer id);
    List<EntryBean> getEntryViewList(@Param("keywords") String keywords,@Param("status") Integer status,@Param("firstClass") Integer firstClass,@Param("secondClass") Integer secondClass,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);
    int entryViewCount(@Param("keywords") String keywords,@Param("status") Integer status,@Param("firstClass") Integer firstClass,@Param("secondClass") Integer secondClass);
    List<EntryBean> getEntryList();
    int entryAllCount();
    int addEntry(EntryBean entryBean);
    EntryBean addEntryCount(@Param("name")String name);
    int addExistEntry(@Param("name")String name);
    int updateByPrimaryKeySelective(EntryBean entryBean);
    int deleteEntry(@Param("id") Integer id);
    List<EntryBean> getFirstClassEntry(@Param("firstClass") Integer firstClass);
    int firstClassEntryCount(@Param("firstClass") Integer firstClass);
    List<EntryBean> getSecondClassEntry(@Param("secondClass") Integer secondClass);
    int secondClassEntryCount(@Param("secondClass") Integer secondClass);
    List<EntryBean> getEntryBystatus(@Param("status") Integer stateId);
    int EntryCountBystatus(@Param("status") Integer stateId);

}
