package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class InstallProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer productId;

    private Integer projectId;

    private Integer contactId;

    private String name;

    private String address;

    private String telno;

    private String machineType;

    private Date installDate;

    private Date startDate;

    private Date endDate;

    private String installUnit;

    private Integer status;

    private Integer installmanId;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public Integer getProductId() {
        return productId;
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

    public String getMachineType() {
        return machineType;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getInstallUnit() {
        return installUnit;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getInstallmanId() {
        return installmanId;
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

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setInstallUnit(String installUnit) {
        this.installUnit = installUnit;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setInstallmanId(Integer installmanId) {
        this.installmanId = installmanId;
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
