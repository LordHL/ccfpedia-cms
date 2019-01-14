package org.ccf.ccfpedia.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ccf.ccfpedia.cms.bean.EntityBean;
import org.ccf.ccfpedia.cms.bean.EntityBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EntityMapper {

    EntityBean getEntityByName(String name);
    List<EntityBean> getEntityListWithFirstClass(@Param("keyword") String keyword,@Param("classId") Integer classId,@Param("limit") Integer limit,@Param("offset") Integer offset);
    List<EntityBean> getEntityListWithSecondClass(@Param("keyword") String keyword,@Param("classId") Integer classId,@Param("limit") Integer limit,@Param("offset") Integer offset);
    List<EntityBean> getEntityListWithNoClass(@Param("keyword") String keyword,@Param("limit") Integer limit,@Param("offset") Integer offset);


    int getEntityCountWithSecondClass(@Param("keyword") String keyword, @Param("classId") Integer classId);

    int getEntityCountWithFirstClass(@Param("keyword") String keyword, @Param("classId") Integer classId);

    int getEntityCountWithNoClass(@Param("keyword") String keyword);

    List<EntityBean> getEntityListByTaskId(@Param("id") Integer id);
    //查询新增词条是否存在
    List<String> entityIsExist(@Param("name") String name);
}
