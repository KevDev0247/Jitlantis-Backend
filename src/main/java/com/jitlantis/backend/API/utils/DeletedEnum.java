package com.jitlantis.backend.API.utils;

public enum DeletedEnum {
    Y(1),N(0);
    private final int value;

    DeletedEnum(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
