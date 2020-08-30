package com.jitlantis.backend.API.model;

import java.util.Date;

public class RepairRecord {

    private Integer id;

    private Integer repairId;

    private String description;

    private String dcode;

    private String content;

    private String ccode;

    private Integer repairmanId;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public Integer getRepairId() {
        return repairId;
    }

    public String getDescription() {
        return description;
    }

    public String getDcode() {
        return dcode;
    }

    public String getContent() {
        return content;
    }

    public String getCcode() {
        return ccode;
    }

    public Integer getRepairmanId() {
        return repairmanId;
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

    public void setRepairId(Integer repairId) {
        this.repairId = repairId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public void setRepairmanId(Integer repairmanId) {
        this.repairmanId = repairmanId;
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
