package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The model for SysLog that maps the structure from the database entities.
 * This model will carry the data in other sections.
 * System Logs record all actions taken on this platform.
 *
 * @author Kevin Zhijun Wang
 * created on 2020/08/24
 */
@Entity
public class SysLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
