package org.ccf.ccfpedia.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.ccf.ccfpedia.cms.bean.FirstClassBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface GroupFirstClassMapper {

    List<FirstClassBean> selectClassByGroupId(Integer id);

    int insertMany(List<Map<String, Integer>> list);

    void delete(Integer id);
}
