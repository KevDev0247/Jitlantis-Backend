package com.phiotonia.kniotcloud.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
