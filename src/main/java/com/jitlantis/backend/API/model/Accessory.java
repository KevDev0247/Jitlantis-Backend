package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    private Integer repairId;

    private String name;

    private String code;

    private Integer isReturn;

    public Integer getId() {
        return id;
    }

    public Integer getRepairId() {
        return repairId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getIsReturn() {
        return isReturn;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRepairId(Integer repairId) {
        this.repairId = repairId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setIsReturn(Integer isReturn) {
        this.isReturn = isReturn;
    }
}
