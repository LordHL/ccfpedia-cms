package org.ccf.ccfpedia.cms.rest;

import com.google.common.reflect.TypeToken;
import io.swagger.annotations.Api;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "user")
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
    @RequestMapping(value = "list", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataArray<UserBean>> list(String keyword, Integer pageNo, Integer pageSize) {
        List<UserBean> userList = userService.getUserList(keyword, pageNo, pageSize);
        int userCount = userService.getUserCount();
        DataArray<UserBean> data = new DataArray<>();
        data.setCount(userCount);
        data.setArray(userList);
        return new RestResp<>(data);
    }

    @ApiOperation("用户登录")
    @RequestMapping(value = "login", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<UserBean> login(String account, String password) {
        RestResp<UserBean> resp = null;
        UserBean user = userService.login(account, password);
        if(user != null) {
            resp = new RestResp<>(user);
        } else {
            resp = new RestResp<>(400, "登录失败");
        }
        return resp;
    }

    @ApiOperation("用户删除")
    @RequestMapping(value = "delete", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<UserBean> delete(Integer id) {
        RestResp<UserBean> resp = null;
        UserBean user = userService.getUserById(id);
        if(user != null) {
            user.setIsDelete(1);
            userService.update(user);
            resp = new RestResp<>(200, "成功");
        } else {
            resp = new RestResp<>(400, "用户不存在");
        }
        return resp;
    }

    @ApiOperation("用户注册")
    @RequestMapping(value = "sign", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @RequestMapping(value = "apply/list", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @RequestMapping(value = "approve", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @RequestMapping(value = "modify", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
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
