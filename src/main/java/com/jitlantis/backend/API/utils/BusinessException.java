package com.jitlantis.backend.API.utils;

public class BusinessException extends RuntimeException {

    private String code;
    private String msg;

    public BusinessException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String string, String message, Exception e) {
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static BusinessException paramException(String message) {
        return new BusinessException(HttpCode.CODE_400, message);
    }

    public static BusinessException serverErrorException(String message) {
        return new BusinessException(HttpCode.CODE_500, message);
    }

    public static BusinessException serverErrorException(String message, Exception exception) {
        return new BusinessException(HttpCode.CODE_500, message, exception);
    }
}
