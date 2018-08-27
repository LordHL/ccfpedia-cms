package org.ccf.ccfpedia.cms.service;

import org.ccf.ccfpedia.cms.bean.EntityBean;
import org.ccf.ccfpedia.cms.bean.FirstClassBean;
import org.ccf.ccfpedia.cms.bean.RoleBean;
import org.ccf.ccfpedia.cms.bean.SecondClassBean;

import java.util.List;

public interface EntityService {

    EntityBean getEntityByName(String name);
    List<EntityBean> getEntityList(String keyword, Integer firstClass,Integer secondClass,Integer pageNo, Integer pageSize);
    int getEntityCount(String keyword,Integer firstClass,Integer secondClass);
}
