package org.ccf.ccfpedia.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ccf.ccfpedia.cms.bean.EntryBean;
import org.ccf.ccfpedia.cms.bean.FirstClassBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FirstClassMapper {

    List<FirstClassBean> list();
    int count();

    List<FirstClassBean> listByGroupId(Integer groupId);
    int countByGroupId(Integer groupId);
}
