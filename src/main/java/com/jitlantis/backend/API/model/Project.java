package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The model for Project that maps the structure from the database entities.
 * This model will carry the data in other sections
 *
 * @author Kevin Zhijun Wang
 * created on 2020/07/23
 */
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer contractId;

    private String description;

    private String address;

    private Integer clientId;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    private Date contractStartTime;

    private Date contractEndTime;

    private Date deliveryTime;

    private Date acceptTime;

    private String guaranteeType;

    private Date guaranteeDueTime;

    private Date guaranteeMonth;

    private Integer staffId;

    private Integer fileId;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getContractId() {
        return contractId;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public Integer getClientId() {
        return clientId;
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

    public Date getContractStartTime() {
        return contractStartTime;
    }

    public Date getContractEndTime() {
        return contractEndTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public String getGuaranteeType() {
        return guaranteeType;
    }

    public Date getGuaranteeDueTime() {
        return guaranteeDueTime;
    }

    public Date getGuaranteeMonth() {
        return guaranteeMonth;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContractId(Integer contactId) {
        this.contractId = contactId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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

    public void setContractStartTime(Date contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public void setContractEndTime(Date contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    public void setGuaranteeDueTime(Date guaranteeDueTime) {
        this.guaranteeDueTime = guaranteeDueTime;
    }

    public void setGuaranteeMonth(Date guaranteeMonth) {
        this.guaranteeMonth = guaranteeMonth;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
}
