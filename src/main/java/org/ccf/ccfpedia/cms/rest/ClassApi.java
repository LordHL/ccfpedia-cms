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
    @RequestMapping(value = "/queryFirstClass", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<FirstClassBean>> firstClassEntryList() {
        List<FirstClassBean> firstClassList = entryService.getFirstClassList();
        int firstclassCount = entryService.getFirstClassEntryCount();
        DataArray<FirstClassBean> data = new DataArray<>();
        data.setCount(firstclassCount);
        data.setArray(firstClassList);
        return new RestResp<>(data);

    }

    @ApiOperation("新建一级分类")
    @RequestMapping(value = "/addFirstClass", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp addFirstClassEntry(@RequestBody FirstClassBean firstClassBean) {
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
    @RequestMapping(value = "/modifyFirstClass", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp modifyFirstClassEntry(@RequestBody FirstClassBean firstClassBean){
        RestResp<SecondClassBean> resp = null;
        if(firstClassBean != null){
            entryService.updateFirstClassEntry(firstClassBean);
            resp= new RestResp<>(200, "修改成功");
        }else{
            resp = new RestResp<>(400, "修改失败");
        }
        return resp;
    }


    /*@ApiOperation("二级分类列表")
    @RequestMapping(value = "/querySecondClass", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<SecondClassBean>> secondClassEntryList() {
        List<SecondClassBean> secondClassList = entryService.getSecondClassList();
        int secondclassCount = entryService.getSecondClassEntryCount();
        DataArray<SecondClassBean> data = new DataArray<>();
        data.setCount(secondclassCount);
        data.setArray(secondClassList);
        return new RestResp<>(data);

    }*/

    @ApiOperation("新建二级分类")
    @RequestMapping(value = "/addSecondClass", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp addSecondClassEntry(@RequestBody SecondClassBean secondClassBean) {
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
    @RequestMapping(value = "/modifySecondClass", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp modifySecondClassEntry(@RequestBody SecondClassBean secondClassBean){
        RestResp<SecondClassBean> resp = null;
        if(secondClassBean != null){
            entryService.updateSecondClassEntry(secondClassBean);
            resp= new RestResp<>(200, "修改成功");
        }else{
            resp = new RestResp<>(400, "修改失败");
        }
        return resp;
    }

    @ApiOperation("二级分类列表")
    @RequestMapping(value = "firstClass/{id}/secondClass", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<SecondClassBean>> secondClassEntryByFirstClassId(@PathVariable("firstClass")Integer firstClass) {
        List<SecondClassBean> secondClassList = entryService.getSecondClassEntryByFirstClassId(firstClass);
        int secondClassCount = entryService.getSecondClassEntryByFirstCount(firstClass);
        DataArray<SecondClassBean> data = new DataArray<>();
        data.setCount(secondClassCount);
        data.setArray(secondClassList);
        return new RestResp<>(data);
    }


    @ApiOperation("查询一级词条")
    @RequestMapping(value = "entry/firstClass/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<EntryBean>> EntryByFirstClassId(@PathVariable("firstClass")Integer firstClass) {
        List<EntryBean> firstClassEntry = entryService.getFirstClassEntry(firstClass);
        int firstClassCount = entryService.getFirstClassEntryCount(firstClass);
        DataArray<EntryBean> data = new DataArray<>();
        data.setCount(firstClassCount);
        data.setArray(firstClassEntry);
        return new RestResp<>(data);
    }

    @ApiOperation("查询二级词条")
    @RequestMapping(value = "entry/secondClass/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<EntryBean>> EntryBySecondClassId(@PathVariable("secondClass")Integer secondClass) {
        List<EntryBean> secondClassEntry = entryService.getSecondClassEntry(secondClass);
        int secondClassCount = entryService.getSecondClassEntryCount(secondClass);
        DataArray<EntryBean> data = new DataArray<>();
        data.setCount(secondClassCount);
        data.setArray(secondClassEntry);
        return new RestResp<>(data);
    }
}
