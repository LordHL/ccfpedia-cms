package org.ccf.ccfpedia.cms.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBean {
    private int id;
    private String name;
    private String email;
    private String password;
    private String account;
    private GroupBean group;
    private RoleBean role;
    @JsonIgnore
    private int isDelete;

    public UserBean() {
    }

    public UserBean(UserApplyBean userApplyBean) {
        this.name = userApplyBean.getName();
        this.email = userApplyBean.getEmail();
        this.password = userApplyBean.getPassword();
        this.account = userApplyBean.getAccount();
        this.group = userApplyBean.getGroup();
        this.role = userApplyBean.getRole();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public RoleBean getRole() {
        return role;
    }

    public void setRole(RoleBean role) {
        this.role = role;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
