package com.tramp.idea.entity;

import java.io.Serializable;
import java.util.List;


public class ApiDocEntity implements Serializable {
    /**
     * 路径
     */
    private String path;
    /**
     * title
     */
    private String title;
    /**
     * 请求方法
     */
    private String method = "POST";
    /**
     * 描述
     */
    private String desc;
    /**
     * 请求参数
     */
    private List req_params;
    /**
     * 响应
     */
    private String response;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List getReq_params() {
        return req_params;
    }

    public void setReq_params(List req_params) {
        this.req_params = req_params;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
