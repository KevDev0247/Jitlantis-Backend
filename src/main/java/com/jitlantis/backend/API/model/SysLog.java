package com.jitlantis.backend.API.model;

import java.util.Date;

public class SysLog {

    private Integer id;

    private String userName;

    private String operation;

    private String method;

    private String params;

    private String ip;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getOperation() {
        return operation;
    }

    public String getMethod() {
        return method;
    }

    public String getParams() {
        return params;
    }

    public String getIp() {
        return ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
