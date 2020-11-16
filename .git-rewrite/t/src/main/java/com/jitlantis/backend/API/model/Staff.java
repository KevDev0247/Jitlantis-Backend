package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The model for Staff that maps the structure from the database entities.
 * This model will carry the data in other sections
 * Staff are the personnel who is in charge of a specific project
 *
 * @author Kevin Zhijun Wang
 * created on 2020/07/23
 */
@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String sCode;

    private String name;

    private String dept;

    private String company;

    private String email;

    private String telno;

    private String address;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public String getsCode() {
        return sCode;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public String getTelno() {
        return telno;
    }

    public String getAddress() {
        return address;
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

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public void setAddress(String address) {
        this.address = address;
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
