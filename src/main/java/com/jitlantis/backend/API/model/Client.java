package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The model for Client that maps the structure from the database entities.
 * This model will carry the data in other sections
 *
 * @author Kevin Zhijun Wang
 * created on 2020/07/23
 */
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer orgId;

    private String name;

    private String telno;

    private String area;

    private String address;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public String getName() {
        return name;
    }

    public String getTelno() {
        return telno;
    }

    public String getArea() {
        return area;
    }

    public String getAddress() {
        return address;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelno(String telNo) {
        this.telno = telNo;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
