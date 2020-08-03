package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ClientProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer clientId;

    private Integer productId;

    private String place;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    private String sales;

    private Date installTime;

    private Date guaranteeDueTime;

    private Date keepDueTime;

    private Date inspectDueTime;

    public Integer getId() {
        return id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getPlace() {
        return place;
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

    public String getSales() {
        return sales;
    }

    public Date getInstallTime() {
        return installTime;
    }

    public Date getGuaranteeDueTime() {
        return guaranteeDueTime;
    }

    public Date getKeepDueTime() {
        return keepDueTime;
    }

    public Date getInspectDueTime() {
        return inspectDueTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setPlace(String location) {
        this.place = location;
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

    public void setSales(String sales) {
        this.sales = sales;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public void setGuaranteeDueTime(Date guaranteeDueTime) {
        this.guaranteeDueTime = guaranteeDueTime;
    }

    public void setKeepDueTime(Date keepDueTime) {
        this.keepDueTime = keepDueTime;
    }

    public void setInspectDueTime(Date inspectDueTime) {
        this.inspectDueTime = inspectDueTime;
    }
}
