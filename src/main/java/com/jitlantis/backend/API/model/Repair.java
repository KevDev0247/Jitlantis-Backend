package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String repairUnit;

    private String fixDate;

    private Integer projectId;

    private Integer contactId;

    private String name;

    private String address;

    private String telno;

    private Integer installId;

    private Integer status;

    private Integer staffId;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public String getRepairUnit() {
        return repairUnit;
    }

    public String getFixDate() {
        return fixDate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelno() {
        return telno;
    }

    public Integer getInstallId() {
        return installId;
    }

    public Integer getStatus() {
        return status;
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

    public void setRepairUnit(String repairUnit) {
        this.repairUnit = repairUnit;
    }

    public void setFixDate(String fixDate) {
        this.fixDate = fixDate;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public void setInstallId(Integer installId) {
        this.installId = installId;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
