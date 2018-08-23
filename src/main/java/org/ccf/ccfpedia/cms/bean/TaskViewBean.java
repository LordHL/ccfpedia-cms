package org.ccf.ccfpedia.cms.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskViewBean {
    private int id;
    private String name;
    private String description;
    private UserBean founder;
    private String creationTime;
    private UserBean committee;
    private UserBean executor;
    private String deadline;
    private Integer statusId;
    private TaskStatusBean status;
    private String memo;
    private GroupBean expert;
    private List<EntryBean> entry;

    public TaskStatusBean getStatus() {
        return status;
    }

    public void setStatus(TaskStatusBean status) {
        this.status = status;
    }

    public GroupBean getExpert() {
        return expert;
    }

    public void setExpert(GroupBean expert) {
        this.expert = expert;
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

    public String getDescription() {
        return description;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public UserBean getFounder() {
        return founder;
    }

    public void setFounder(UserBean founder) {
        this.founder = founder;
    }

    public UserBean getCommittee() {
        return committee;
    }

    public void setCommittee(UserBean committee) {
        this.committee = committee;
    }

    public UserBean getExecutor() {
        return executor;
    }

    public void setExecutor(UserBean executor) {
        this.executor = executor;
    }

    public List<EntryBean> getEntry() {
        return entry;
    }

    public void setEntry(List<EntryBean> entry) {
        this.entry = entry;
    }
}
