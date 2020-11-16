package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The model for SendProduct that maps the structure from the database entities.
 * This model will carry the data in other sections
 *
 * @author Kevin Zhijun Wang
 * created on 2020/09/01
 */
@Entity
public class SendProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String code;

    private Date sendDate;

    private String businessType;

    private String saleType;

    private String clientId;

    private Integer devmanId;

    private Integer keepmanId;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public String getBusinessType() {
        return businessType;
    }

    public String getSaleType() {
        return saleType;
    }

    public String getClientId() {
        return clientId;
    }

    public Integer getDevmanId() {
        return devmanId;
    }

    public Integer getKeepmanId() {
        return keepmanId;
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

    public void setCode(String code) {
        this.code = code;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setDevmanId(Integer devmanId) {
        this.devmanId = devmanId;
    }

    public void setKeepmanId(Integer keepmanId) {
        this.keepmanId = keepmanId;
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
