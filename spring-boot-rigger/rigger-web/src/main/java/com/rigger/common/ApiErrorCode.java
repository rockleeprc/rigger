package com.rigger.common;

public enum ApiErrorCode implements ErrorCode {

    OK(200, "Success"),
    NO(201, "Fail");


    private final Integer code;
    private final String msg;

    private ApiErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
