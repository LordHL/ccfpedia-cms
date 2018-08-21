package org.ccf.ccfpedia.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ccf.ccfpedia.cms.bean.FirstClassBean;
import org.ccf.ccfpedia.cms.bean.SecondClassBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SecondClassMapper {

    List<SecondClassBean> getSecondClassEntryList();
    int secondClassAllCount();
    int addSecondClassEntry(SecondClassBean secondClassBean);
    int updateByPrimaryKeySelective(SecondClassBean secondClassBean);
    List<SecondClassBean> getSecondClassEntryListByFirstClassId(@Param("id") Integer id);
    int secondClassCount(@Param("id") Integer id);
}
