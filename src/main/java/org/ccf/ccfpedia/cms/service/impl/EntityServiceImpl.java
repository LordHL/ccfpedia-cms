package org.ccf.ccfpedia.cms.service.impl;

import org.ccf.ccfpedia.cms.bean.EntityBean;
import org.ccf.ccfpedia.cms.bean.FirstClassBean;
import org.ccf.ccfpedia.cms.bean.SecondClassBean;
import org.ccf.ccfpedia.cms.dao.EntityMapper;
import org.ccf.ccfpedia.cms.dao.FirstClassMapper;
import org.ccf.ccfpedia.cms.dao.SecondClassMapper;
import org.ccf.ccfpedia.cms.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityServiceImpl implements EntityService {

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public List<EntityBean> getEntityList(String keyword,Integer firstClass,Integer secondClass,Integer pageNo, Integer pageSize){
        Integer limit = null;
        Integer offset = null;
        if(pageNo != null && pageSize != null) {
            offset = (pageNo - 1) * pageSize;
            limit = pageSize;
        }

        if(secondClass != null){
            return entityMapper.getEntityListWithSecondClass(keyword,secondClass,limit,offset);
        } else if(firstClass != null){
            return entityMapper.getEntityListWithFirstClass(keyword,firstClass,limit,offset);
        } else {
            return  entityMapper.getEntityListWithNoClass(keyword, limit, offset);
        }
    }

    @Override
    public int getEntityCount(String keyword,Integer firstClass,Integer secondClass){
        if(secondClass != null){
            return entityMapper.getEntityCountWithSecondClass(keyword,secondClass);
        } else if(firstClass != null){
            return entityMapper.getEntityCountWithFirstClass(keyword,firstClass);
        } else {
            return  entityMapper.getEntityCountWithNoClass(keyword);
        }
    }

    @Override
    public EntityBean getEntityByName(String name){
        return entityMapper.getEntityByName(name);
    }
    @Override
    public boolean entityIsExist(String name){
        List<String> list = entityMapper.entityIsExist(name);
        if(list != null && !list.isEmpty())
            return true;
        return false;
    }

}
