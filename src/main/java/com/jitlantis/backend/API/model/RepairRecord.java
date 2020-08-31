package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The model for RepairRecord that maps the structure from the database entities.
 * This model will carry the data in other sections
 *
 * @author Kevin Zhijun Wang
 * created on 2020/08/29
 */
@Entity
public class RepairRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer repairId;

    private String description;

    private String dcode;

    private String content;

    private String ccode;

    private Integer repairmanId;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public Integer getRepairId() {
        return repairId;
    }

    public String getDescription() {
        return description;
    }

    public String getDcode() {
        return dcode;
    }

    public String getContent() {
        return content;
    }

    public String getCcode() {
        return ccode;
    }

    public Integer getRepairmanId() {
        return repairmanId;
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

    public void setRepairId(Integer repairId) {
        this.repairId = repairId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public void setRepairmanId(Integer repairmanId) {
        this.repairmanId = repairmanId;
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
