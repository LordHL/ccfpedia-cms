package org.ccf.ccfpedia.cms.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecondClassBean {
    private int id;
    private String name;
    private int firstClass;

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

    public int getFirstClass() {
        return firstClass;
    }

    public void setFirstClass(int firstClass) {
        this.firstClass = firstClass;
    }
}
