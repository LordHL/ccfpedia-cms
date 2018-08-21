package org.ccf.ccfpedia.cms.service.impl;

import org.ccf.ccfpedia.cms.bean.FirstClassBean;
import org.ccf.ccfpedia.cms.bean.RoleBean;
import org.ccf.ccfpedia.cms.bean.SecondClassBean;
import org.ccf.ccfpedia.cms.dao.EntryMapper;
import org.ccf.ccfpedia.cms.dao.FirstClassMapper;
import org.ccf.ccfpedia.cms.dao.RoleMapper;
import org.ccf.ccfpedia.cms.dao.SecondClassMapper;
import org.ccf.ccfpedia.cms.service.EntryService;
import org.ccf.ccfpedia.cms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private FirstClassMapper firstClassMapper;

    @Autowired
    private SecondClassMapper secondClassMapper;


    @Override
    public List<FirstClassBean> getFirstClassList(){
        return firstClassMapper.getFirstClassEntryList();
    }

    @Override
    public int getFirstClassEntryCount() {
        int count= firstClassMapper.firstClassAllCount();
        return count;
    }

    @Override
    public int addFirstClassEntry(FirstClassBean firstClassBean) {
        return firstClassMapper.addFirstClassEntry(firstClassBean);
    }

    @Override
    public void updateFirstClassEntry(FirstClassBean firstClassBean) {
        firstClassMapper.updateByPrimaryKeySelective(firstClassBean);
    }


    @Override
    public List<SecondClassBean> getSecondClassList(){
        return secondClassMapper.getSecondClassEntryList();
    }

    @Override
    public int getSecondClassEntryCount() {
        int count= secondClassMapper.secondClassAllCount();
        return count;
    }

    @Override
    public int addSecondClassEntry(SecondClassBean secondClassBean) {
        return secondClassMapper.addSecondClassEntry(secondClassBean);
    }

    @Override
    public void updateSecondClassEntry(SecondClassBean secondClassBean) {
        secondClassMapper.updateByPrimaryKeySelective(secondClassBean);
    }

    @Override
    public List<SecondClassBean> getSecondClassEntryByFirstClassId(Integer id){
        return secondClassMapper.getSecondClassEntryListByFirstClassId(id);
    }

    @Override
    public int getSecondClassEntryByFirstCount(Integer id) {
        int count = secondClassMapper.secondClassCount(id);
        return count;

    }
}
