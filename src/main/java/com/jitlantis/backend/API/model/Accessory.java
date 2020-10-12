package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The model for Accessory that maps the structure from the database entities.
 * This model will carry the data in other sections
 *
 * @author Kevin Zhijun Wang
 * created on 2020/09/10
 */
@Entity
public class Accessory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String code;

    private String spec;

    private String unit;

    private Integer count;

    private Integer safeCount;

    private Integer status;

    private Integer isReturn;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getSpec() {
        return spec;
    }

    public String getUnit() {
        return unit;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getSafeCount() {
        return safeCount;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getIsReturn() {
        return isReturn;
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

    public void setCode(String code) {
        this.code = code;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setSafeCount(Integer safeCount) {
        this.safeCount = safeCount;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setIsReturn(Integer isReturn) {
        this.isReturn = isReturn;
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
