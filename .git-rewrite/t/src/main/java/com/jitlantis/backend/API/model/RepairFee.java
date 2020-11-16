package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The model for RepairFee that maps the structure from the database entities.
 * This model will carry the data in other sections
 *
 * @author Kevin Zhijun Wang
 * created on 2020/08/29
 */
@Entity
public class RepairFee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer projectId;

    private Integer fee;

    private Integer auditStatus;

    public Integer getId() {
        return id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public Integer getFee() {
        return fee;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
}
