package com.jitlantis.backend.API.dto;

public class BaseItemDto {

    private Integer id;

    private String name;

    private String title;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
