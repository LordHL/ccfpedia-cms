package org.ccf.ccfpedia.cms.rest;

import com.google.common.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.ccf.ccfpedia.cms.bean.GroupBean;
import org.ccf.ccfpedia.cms.bean.RoleBean;
import org.ccf.ccfpedia.cms.bean.UserApplyBean;
import org.ccf.ccfpedia.cms.bean.UserBean;
import org.ccf.ccfpedia.cms.bean.resp.DataArray;
import org.ccf.ccfpedia.cms.bean.resp.RestResp;
import org.ccf.ccfpedia.cms.service.GroupService;
import org.ccf.ccfpedia.cms.service.RoleService;
import org.ccf.ccfpedia.cms.service.UserApplyService;
import org.ccf.ccfpedia.cms.service.UserService;
import org.ccf.ccfpedia.cms.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "user", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserApi {

    @Autowired
    private UserService userService;
    @Autowired
    private UserApplyService userApplyService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private GroupService groupService;

    @ApiOperation("用户列表")
    @GetMapping("list")
    public RestResp<DataArray<UserBean>> list(String keyword, Integer pageNo, Integer pageSize) {
        List<UserBean> userList = userService.getUserList(keyword, pageNo, pageSize);
        int userCount = userService.getUserCount(keyword);
        DataArray<UserBean> data = new DataArray<>();
        data.setCount(userCount);
        data.setArray(userList);
        return new RestResp<>(data);
    }

    @ApiOperation("用户登录")
    @PostMapping("login")
    public RestResp<UserBean> login(String account, String password) {
        RestResp<UserBean> resp = null;
        UserBean user = userService.getUserByAccount(account);
        if(user != null){
            if(user.getPassword() != null && user.getPassword().equals(password)){
                resp = new RestResp<>(user);
            } else {
                resp = new RestResp<>(401, "登录失败");
            }
        } else {
            UserApplyBean userApplyByAccount = userApplyService.getUserApplyByAccount(account);
            if(userApplyByAccount.getStatus() == 0) {
                resp = new RestResp<>(402, "审核中");
            } else if(userApplyByAccount.getStatus() == 2) {
                resp = new RestResp<>(403, "审核未通过");
            } else {
                resp = new RestResp<>(401, "登录失败");
            }
        }
        return resp;
    }

    @ApiOperation("用户删除")
    @PostMapping("delete")
    public RestResp<UserBean> delete(String id) {
        RestResp<UserBean> resp = null;
        List<Integer> idList = JsonUtils.fromJson(id, new TypeToken<List<Integer>>() {}.getType());
        List<UserBean> userList = userService.getUserListByIdList(idList);
        if(userList != null) {
            for(UserBean user : userList) {
                user.setIsDelete(1);
                userService.update(user);
            }
            resp = new RestResp<>(200, "成功");
        } else {
            resp = new RestResp<>(400, "用户不存在");
        }
        return resp;
    }

    @ApiOperation("用户注册")
    @PostMapping("sign")
    public RestResp login(String account, String password, String name, String email, Integer roleId, Integer groupId) {
        RestResp<UserBean> resp = null;
        RoleBean role = null;
        GroupBean group = null;
        if(roleId != null) {
            role = roleService.getRoleById(roleId);
        }
        if(groupId != null) {
            group = groupService.getGroupById(groupId);
        }
        UserBean user = userService.getUserByAccount(account);
        UserApplyBean userApplyByAccount = userApplyService.getUserApplyByAccount(account);
        if(user == null && userApplyByAccount == null) {
            UserApplyBean userApplyBean = new UserApplyBean(name, email, password, account, group, role, 0);
            userApplyService.addUserApply(userApplyBean);
            resp = new RestResp<>(200, "成功");
        } else {
            resp = new RestResp<>(400, "账号重复");
        }
        return resp;
    }

    @ApiOperation("审批列表")
    @PostMapping("apply/list")
    public RestResp<DataArray<UserApplyBean>> getApplyList(String status, Integer pageNo, Integer pageSize) {
        List<Integer> statusList = JsonUtils.fromJson(status, new TypeToken<List<Integer>>() {}.getType());
        List<UserApplyBean> userApplyBeanList = userApplyService.getUserApplyList(statusList, pageNo, pageSize);
        int count = userApplyService.getUserApplyCount(statusList);
        DataArray<UserApplyBean> data = new DataArray<>();
        data.setArray(userApplyBeanList);
        data.setCount(count);
        return new RestResp<>(data);
    }

    @ApiOperation("用户审批")
    @PostMapping("approve")
    public RestResp<UserBean> approve(String id, Integer status) {
        RestResp<UserBean> resp = null;
        if(status == 1 || status == 2) {
            List<Integer> idList = JsonUtils.fromJson(id, new TypeToken<List<Integer>>() {
            }.getType());
            List<UserApplyBean> userApplyBeanList = userApplyService.getUserApplyListByIdList(idList);
            if (userApplyBeanList != null) {
                for (UserApplyBean userApplyBean : userApplyBeanList) {
                    if (1 == status) {
                        userApplyBean.setStatus(status);
                        UserBean user = new UserBean(userApplyBean);
                        userService.addUser(user);
                    } else if (2 == status) {
                        userApplyBean.setStatus(status);
                    }
                    userApplyService.update(userApplyBean);
                }
                resp = new RestResp<>(200, "成功");
            } else {
                resp = new RestResp<>(400, "申请不存在");
            }
        } else {
            resp = new RestResp<>(400, "无效操作");
        }
        return resp;
    }

    @ApiOperation("权限修改")
    @PostMapping("modify")
    public RestResp<UserBean> modify(Integer userId, Integer groupId, Integer roleId) {
        RestResp<UserBean> resp = null;
        UserBean user = userService.getUserById(userId);
        if(user != null){
            if(roleId != null) {
                RoleBean role = roleService.getRoleById(roleId);
                user.setRole(role);
            }
            if(groupId != null) {
                GroupBean group = groupService.getGroupById(groupId);
                user.setGroup(group);
            }
            userService.update(user);
            resp = new RestResp<>(200, "成功");
        } else {
            resp = new RestResp<>(400, "用户不存在");
        }
        return resp;
    }
}
