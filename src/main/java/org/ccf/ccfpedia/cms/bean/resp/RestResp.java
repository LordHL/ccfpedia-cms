package org.ccf.ccfpedia.cms.bean.resp;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResp<T> {
    private int code;
    private String msg;
    private T data;

    public RestResp(T data) {
        this.code = 200;
        this.msg = "成功";
        this.data = data;
    }

    public RestResp(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
