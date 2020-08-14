package com.jitlantis.backend.API.model;

import com.baomidou.mybatisplus.annotations.TableField;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * The model for SysUser that maps the structure from the database entities.
 * This model will carry the data in other sections
 * SysUser refers to a user in System section
 *
 * @author Kevin Zhijun Wang
 * created on 2020/07/17
 */
@Entity
public class SysUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer orgId;

    private String name;

    private String username;

    private String email;

    private String password;

    private Integer roleId;

    private Date createTime;

    @TableField(update = "now()")
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

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getRoleId() {
        return roleId;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
