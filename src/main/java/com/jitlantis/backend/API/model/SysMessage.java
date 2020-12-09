package com.jitlantis.backend.API.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * The model for SysMessage that maps the structure from the database entities.
 * This model will carry the data in other sections.
 * System Messages refers to the messages sent to different users and tenants in the system
 *
 * @author Kevin Zhijun Wang
 * created on 2020/08/24
 */
public class SysMessage {

    private Integer id;

    private Integer ptype;

    private String content;

    private Integer userId;

    private Integer isRead;

    private Integer createUserId;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date updateTime;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public Integer getPtype() {
        return ptype;
    }

    public String getContent() {
        return content;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public Integer getCreateUserId() {
        return createUserId;
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

    public void setPtype(Integer ptype) {
        this.ptype = ptype;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
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
