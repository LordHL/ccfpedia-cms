package org.ccf.ccfpedia.cms.service.impl;

import org.ccf.ccfpedia.cms.bean.EntryBean;
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

    @Autowired
    private EntryMapper entryMapper;


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
    public List<SecondClassBean> getSecondClassEntryByFirstClassId(Integer firstClass){
        return secondClassMapper.getSecondClassEntryListByFirstClassId(firstClass);
    }

    @Override
    public int getSecondClassEntryByFirstCount(Integer firstClass) {
        int count = secondClassMapper.secondClassCount(firstClass);
        return count;
    }

    @Override
    public EntryBean getEntryById(Integer id){
        return entryMapper.getEntryById(id);
    }

    @Override
    public List<EntryBean> getEntryViewList(String keywords,Integer status,Integer firstClass,Integer secondClass,Integer pageNo, Integer pageSize){
        Integer limit = null;
        Integer offset = null;
        if(pageNo != null && pageSize != null) {
            offset = (pageNo - 1) * pageSize;
            limit = pageSize;
        }
        return entryMapper.getEntryViewList(keywords,status,firstClass,secondClass,limit,offset);
    }

    @Override
    public int getEntryViewCount(String keywords,Integer status,Integer firstClass,Integer secondClass) {
        int count= entryMapper.entryViewCount(keywords,status,firstClass,secondClass);
        return count;
    }

    @Override
    public List<EntryBean> getEntryList(){
        return entryMapper.getEntryList();
    }

    @Override
    public int getEntryCount() {
        int count= entryMapper.entryAllCount();
        return count;
    }

    @Override
    public int addEntry(EntryBean entryBean) {
        return entryMapper.addEntry(entryBean);
    }

    @Override
    public void updateEntry(EntryBean entryBean) {
        entryMapper.updateByPrimaryKeySelective(entryBean);
    }

    @Override
    public int deleteEntry(Integer id) {

        return entryMapper.deleteEntry(id);
    }

    @Override
    public List<EntryBean> getFirstClassEntry(Integer firstClass){
        return entryMapper.getFirstClassEntry(firstClass);
    }

    @Override
    public int getFirstClassEntryCount(Integer firstClass) {
        int count= entryMapper.firstClassEntryCount(firstClass);
        return count;
    }

    @Override
    public List<EntryBean> getSecondClassEntry(Integer secondClass){
        return entryMapper.getSecondClassEntry(secondClass);
    }

    @Override
    public int getSecondClassEntryCount(Integer secondClass) {
        int count= entryMapper.secondClassEntryCount(secondClass);
        return count;
    }

    @Override
    public List<EntryBean> getEntryByStatus(Integer stateId){
        return entryMapper.getEntryBystatus(stateId);
    }

    @Override
    public int getEntryCountBystatus(Integer stateId){
        int count = entryMapper.EntryCountBystatus(stateId);
        return count;
    }

}
