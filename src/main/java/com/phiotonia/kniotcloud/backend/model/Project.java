package com.phiotonia.kniotcloud.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer contactId;

    private String description;

    private String address;

    private Integer clientId;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getContactId() {
        return contactId;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
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
}
