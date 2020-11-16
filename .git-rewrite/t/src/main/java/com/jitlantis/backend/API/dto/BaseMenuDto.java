package com.jitlantis.backend.API.dto;

public class BaseMenuDto {

    private Integer id;
    private String text;
    private String i18n;

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getI18n() {
        return i18n;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setI18n(String i18n) {
        this.i18n = i18n;
    }
}
