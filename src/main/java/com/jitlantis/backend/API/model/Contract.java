package com.jitlantis.backend.API.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The model for Contract that maps the structure from the database entities.
 * This model will carry the data in other sections
 *
 * @author Kevin Zhijun Wang, Yonggang Su
 * created on 2020/08/31
 */
@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String signPlace;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date startDate;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date endDate;

    private Integer projectId;

    private String clientId;

    private String content;

    private String pattern;

    private String name;

    private String address;

    private String telno;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date signDate;

    private Integer signmanId;

    private Integer status;

    private Integer price;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date updateTime;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public String getSignPlace() {
        return signPlace;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getContent() {
        return content;
    }

    public String getPattern() {
        return pattern;
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

    public Date getSignDate() {
        return signDate;
    }

    public Integer getSignmanId() {
        return signmanId;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getPrice() {
        return price;
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

    public void setSignPlace(String signPlace) {
        this.signPlace = signPlace;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
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

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public void setSignmanId(Integer signmanId) {
        this.signmanId = signmanId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
