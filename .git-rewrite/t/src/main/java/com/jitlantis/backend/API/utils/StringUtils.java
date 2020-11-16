package com.jitlantis.backend.API.utils;

public class StringUtils {

    public static boolean isNotBlank(String content) {
        return content != null && content.length() > 0 && !content.equals("null");
    }
}
