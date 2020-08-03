package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ProDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String operation;

    private Integer clientId;

    private Integer projectId;

    private Integer attachmentId;

    private Integer staffId;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public String getOperation() {
        return operation;
    }

    public Integer getClientId() {
        return clientId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public Integer getAttachmentId() {
        return attachmentId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
