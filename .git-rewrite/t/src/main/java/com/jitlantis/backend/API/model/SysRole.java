package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The model for SysRole that maps the structure from the database entities.
 * This model will carry the data in other sections
 * SysRole refers to the roles of different types of users in System section
 *
 * @author Kevin Zhijun Wang
 * created on 2020/07/21
 */
@Entity
public class SysRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer type;

    private String roleName;

    private String remark;

    private Integer createUserId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public Integer getType() {
        return type;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRemark() {
        return remark;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
