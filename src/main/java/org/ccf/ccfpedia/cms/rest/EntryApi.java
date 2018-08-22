package org.ccf.ccfpedia.cms.rest;

import io.swagger.annotations.ApiOperation;
import org.ccf.ccfpedia.cms.bean.EntryBean;
import org.ccf.ccfpedia.cms.bean.FirstClassBean;
import org.ccf.ccfpedia.cms.bean.SecondClassBean;
import org.ccf.ccfpedia.cms.bean.resp.DataArray;
import org.ccf.ccfpedia.cms.bean.resp.RestResp;
import org.ccf.ccfpedia.cms.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "entry")
public class EntryApi {

    @Autowired
    private EntryService entryService;


    @ApiOperation("查询词条列表")
    @RequestMapping(value = "/entrylist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<EntryBean>> EntryViewList(String keywords,Integer status,Integer firstClass,Integer secondClass,Integer pageNo, Integer pageSize) {
        List<EntryBean> entryViewList = entryService.getEntryViewList(keywords,status,firstClass,secondClass,pageNo,pageSize);
        int entryCount = entryService.getEntryViewCount(keywords,status,firstClass,secondClass);
        DataArray<EntryBean> data = new DataArray<>();
        data.setCount(entryCount);
        data.setArray(entryViewList);
        return new RestResp<>(data);
    }

    /*
    @ApiOperation("所有词条列表")
    @RequestMapping(value = "/queryentry", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<EntryBean>> EntryList() {
        List<EntryBean> entryList = entryService.getEntryList();
        int entryCount = entryService.getEntryCount();
        DataArray<EntryBean> data = new DataArray<>();
        data.setCount(entryCount);
        data.setArray(entryList);
        return new RestResp<>(data);
    }
    */

    @ApiOperation("新建词条")
    @RequestMapping(value = "/addentry", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp addEntry(@RequestBody EntryBean entryBean) {
        RestResp<SecondClassBean> resp = null;
        int temp = entryService.addEntry(entryBean);
        if(temp==1){
            resp = new RestResp<>(200, "新建成功");
        }else{
            resp = new RestResp<>(400, "新建失败");
        }
        return resp;
    }

    @ApiOperation("修改词条")
    @RequestMapping(value = "/modifyentry", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp modifyEntry(@RequestBody EntryBean entryBean){
        RestResp<EntryBean> resp = null;
        if(entryBean != null){
            entryService.updateEntry(entryBean);
            resp= new RestResp<>(200, "修改成功");
        }else{
            resp = new RestResp<>(400, "修改失败");
        }
        return resp;
    }

    @ApiOperation("删除词条")
    @RequestMapping(value = "deleteentry/{id}", method = RequestMethod.PUT, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp deleteEntry(@PathVariable("id")Integer id) {
        RestResp<EntryBean> resp = null;
        int temp = entryService.deleteEntry(id);
        if(temp==1){
            resp = new RestResp<>(200, "词条删除成功");
        }else{
            resp = new RestResp<>(400, "词条删除失败");
        }
        return resp;
    }

}
