package org.ccf.ccfpedia.cms.service.impl;

import org.ccf.ccfpedia.cms.bean.FirstClassBean;
import org.ccf.ccfpedia.cms.bean.RoleBean;
import org.ccf.ccfpedia.cms.dao.EntryMapper;
import org.ccf.ccfpedia.cms.dao.RoleMapper;
import org.ccf.ccfpedia.cms.service.EntryService;
import org.ccf.ccfpedia.cms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryMapper entryMapper;



    @Override
    public List<FirstClassBean> getFirstClassList(){
        return entryMapper.getFirstClassEntryList();
    }

    @Override
    public int getExpertTaskStateCount() {
        int count= entryMapper.firstClassAllCount();
        return count;
    }

    @Override
    public int addFirstClassEntry(FirstClassBean firstClassBean) {
        return entryMapper.addFirstClassEntry(firstClassBean);
    }

    @Override
    public void updateFirstClassEntry(FirstClassBean firstClassBean) {
        entryMapper.updateByPrimaryKeySelective(firstClassBean);
    }
}
