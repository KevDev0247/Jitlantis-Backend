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

    private String typeid;

    private String typename;

    public String getBasecode() {
        return basecode;
    }

    public String getTypeid() {
        return typeid;
    }

    public String getTypename() {
        return typename;
    }

    public String getBasecodename() {
        return basecodename;
    }

    public void setBasecode(String id) {
        this.basecode = id;
    }

    public void setTypeid(String typeId) {
        this.typeid = typeId;
    }

    public void setTypename(String typeName) {
        this.typename = typeName;
    }

    public void setBasecodename(String value) {
        this.basecodename = value;
    }
}
