package com.phiotonia.kniotcloud.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ProFollow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String operation;

    private String type;

    private Date contact_date;

    private Date next_date;

    private Integer contact_id;

    private String title;

    private String content;

    private Integer staffId;

    private Date createDate;

    private Date updateDate;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public String getOperation() {
        return operation;
    }

    public String getType() {
        return type;
    }

    public Date getContact_date() {
        return contact_date;
    }

    public Date getNext_date() {
        return next_date;
    }

    public Integer getContact_id() {
        return contact_id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContact_date(Date contact_date) {
        this.contact_date = contact_date;
    }

    public void setNext_date(Date next_date) {
        this.next_date = next_date;
    }

    public void setContact_id(Integer contact_id) {
        this.contact_id = contact_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
