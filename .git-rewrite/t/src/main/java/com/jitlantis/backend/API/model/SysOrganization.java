package com.jitlantis.backend.API.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The model for SysOrganization that maps the structure from the database entities.
 * This model will carry the data in other sections
 *
 * @author Kevin Zhijun Wang
 * created on 2020/08/12
 */
@Entity
public class SysOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String OrgNo;

    private String OrgName;

    private String OrgAbr;

    private String OrgStatus;

    private String OrgParentNo;

    private String sort;

    private String remark;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date orgFoundDate;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date orgDissolveDate;

    public Integer getId() {
        return id;
    }

    public String getOrgNo() {
        return OrgNo;
    }

    public String getOrgName() {
        return OrgName;
    }

    public String getOrgAbr() {
        return OrgAbr;
    }

    public String getOrgStatus() {
        return OrgStatus;
    }

    public String getOrgParentNo() {
        return OrgParentNo;
    }

    public String getSort() {
        return sort;
    }

    public String getRemark() {
        return remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Date getOrgFoundDate() {
        return orgFoundDate;
    }

    public Date getOrgDissolveDate() {
        return orgDissolveDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrgNo(String orgNo) {
        OrgNo = orgNo;
    }

    public void setOrgName(String orgName) {
        OrgName = orgName;
    }

    public void setOrgAbr(String orgAbr) {
        OrgAbr = orgAbr;
    }

    public void setOrgStatus(String orgStatus) {
        OrgStatus = orgStatus;
    }

    public void setOrgParentNo(String orgParentNo) {
        OrgParentNo = orgParentNo;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setOrgFoundDate(Date orgFoundDate) {
        this.orgFoundDate = orgFoundDate;
    }

    public void setOrgDissolveDate(Date orgDissolveDate) {
        this.orgDissolveDate = orgDissolveDate;
    }
}
