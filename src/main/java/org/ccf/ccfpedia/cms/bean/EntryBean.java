package org.ccf.ccfpedia.cms.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntryBean {
    private int id;
    private String name;
    private int status;
    private int category;

    public EntryBean() {
    }

    public EntryBean(EntityBean entity){
        if(entity.getId() > 0){
            this.id = entity.getId();
        } else {
            this.category = 1;
        }
        this.name = entity.getName();
        if(entity.getTaskId() > 0){
            this.status = 1;
        }
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

}
