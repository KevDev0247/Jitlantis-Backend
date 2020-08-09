package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The model for SysBasecode that maps the structure from the database entities.
 * This model will carry the data in other sections
 * SysBasecode refers to the foundation data in System section
 * Foundation data refers to specific information on essential features that change rarely or slowly,
 * such as area code, criteria, etc.
 *
 * @author Kevin Zhijun Wang
 * created on 2020/07/23
 */
@Entity
public class SysBasecode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String basecode;

    private String basecodename;

    private String typeId;

    private String typeName;

    public String getBasecode() {
        return basecode;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getBasecodename() {
        return basecodename;
    }

    public void setBasecode(String id) {
        this.basecode = id;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setBasecodename(String value) {
        this.basecodename = value;
    }
}
