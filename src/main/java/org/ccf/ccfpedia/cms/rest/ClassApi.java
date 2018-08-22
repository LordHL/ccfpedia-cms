package org.ccf.ccfpedia.cms.rest;

import io.swagger.annotations.ApiOperation;
import org.ccf.ccfpedia.cms.bean.EntryBean;
import org.ccf.ccfpedia.cms.bean.FirstClassBean;
import org.ccf.ccfpedia.cms.bean.RoleBean;
import org.ccf.ccfpedia.cms.bean.SecondClassBean;
import org.ccf.ccfpedia.cms.bean.resp.DataArray;
import org.ccf.ccfpedia.cms.bean.resp.RestResp;
import org.ccf.ccfpedia.cms.service.EntryService;
import org.ccf.ccfpedia.cms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "class")
public class ClassApi {

    @Autowired
    private EntryService entryService;


    @ApiOperation("一级分类列表")
    @RequestMapping(value = "firstclass", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<FirstClassBean>> firstClassList() {
        List<FirstClassBean> firstClassList = entryService.getFirstClassList();
        int firstclassCount = entryService.getFirstClassEntryCount();
        DataArray<FirstClassBean> data = new DataArray<>();
        data.setCount(firstclassCount);
        data.setArray(firstClassList);
        return new RestResp<>(data);

    }

    @ApiOperation("二级分类列表")
    @RequestMapping(value = "secondclass", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<SecondClassBean>> secondClassList(Integer firstClassId) {
        List<SecondClassBean> secondClassList = entryService.getSecondClassEntryByFirstClassId(firstClassId);
        int secondclassCount = entryService.getSecondClassEntryByFirstCount(firstClassId);
        DataArray<SecondClassBean> data = new DataArray<>();
        data.setCount(secondclassCount);
        data.setArray(secondClassList);
        return new RestResp<>(data);
    }

    @ApiOperation("新建一级分类")
    @RequestMapping(value = "firstclass/add", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp addFirstClass(@RequestBody FirstClassBean firstClassBean) {
        RestResp<FirstClassBean> resp = null;
        int temp = entryService.addFirstClassEntry(firstClassBean);
        if(temp==1){
            resp = new RestResp<>(200, "新建成功");
        }else{
            resp = new RestResp<>(400, "新建失败");
        }
        return resp;
    }


    @ApiOperation("修改一级分类")
    @RequestMapping(value = "firstclass/modify", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp modifyFirstClass(@RequestBody FirstClassBean firstClassBean){
        RestResp<SecondClassBean> resp = null;
        if(firstClassBean != null){
            entryService.updateFirstClassEntry(firstClassBean);
            resp= new RestResp<>(200, "修改成功");
        }else{
            resp = new RestResp<>(400, "修改失败");
        }
        return resp;
    }

    @ApiOperation("新建二级分类")
    @RequestMapping(value = "secondclass/add", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp addSecondClass(@RequestBody SecondClassBean secondClassBean) {
        RestResp<SecondClassBean> resp = null;
        int temp = entryService.addSecondClassEntry(secondClassBean);
        if(temp==1){
            resp = new RestResp<>(200, "新建成功");
        }else{
            resp = new RestResp<>(400, "新建失败");
        }
        return resp;
    }


    @ApiOperation("修改二级分类")
    @RequestMapping(value = "secondclass/modify", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp modifySecondClass(@RequestBody SecondClassBean secondClassBean){
        RestResp<SecondClassBean> resp = null;
        if(secondClassBean != null){
            entryService.updateSecondClassEntry(secondClassBean);
            resp= new RestResp<>(200, "修改成功");
        }else{
            resp = new RestResp<>(400, "修改失败");
        }
        return resp;
    }
    /*
    @ApiOperation("查询一级词条")
    @RequestMapping(value = "first/entry/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<EntryBean>> ByFirstClassId(@PathVariable("firstClass")Integer firstClass) {
        List<EntryBean> firstClassEntry = entryService.getFirstClassEntry(firstClass);
        int firstClassCount = entryService.getFirstClassEntryCount(firstClass);
        DataArray<EntryBean> data = new DataArray<>();
        data.setCount(firstClassCount);
        data.setArray(firstClassEntry);
        return new RestResp<>(data);
    }

    @ApiOperation("查询二级词条")
    @RequestMapping(value = "second/entry/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<EntryBean>> EntryBySecondClassId(@PathVariable("secondClass")Integer secondClass) {
        List<EntryBean> secondClassEntry = entryService.getSecondClassEntry(secondClass);
        int secondClassCount = entryService.getSecondClassEntryCount(secondClass);
        DataArray<EntryBean> data = new DataArray<>();
        data.setCount(secondClassCount);
        data.setArray(secondClassEntry);
        return new RestResp<>(data);
    }
    */
}
