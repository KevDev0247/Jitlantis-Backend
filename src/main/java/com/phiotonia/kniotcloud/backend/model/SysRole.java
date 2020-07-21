package com.phiotonia.kniotcloud.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SysRole {

    private Integer id;
    private Integer type;
    private String roleName;
    private String remark;
    private Integer createUserId;

    @JsonFormat(pattern = "yyyy/mm/dd", timezone = "GMT+8")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public Integer getType() {
        return type;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRemark() {
        return remark;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
