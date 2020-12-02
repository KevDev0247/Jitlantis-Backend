package com.jitlantis.backend.API.dto;

import com.jitlantis.backend.API.model.SysMessage;

public class NotificationDto extends SysMessage {

    private String name;

    private String company;

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
