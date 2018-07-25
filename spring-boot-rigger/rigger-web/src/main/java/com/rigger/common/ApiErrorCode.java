package com.rigger.common;

public enum  ApiErrorCode implements ErrorCode{

    SUCCESS(200,"Success"),
    BAD_REQUEST(400,"Bad Request"),
    NOT_FOUND(404,"Not Found"),
    FORBIDDEN(403,"Forbidden"),
    UNAUTHORIZED(401,"Unauthorized"),
    SERVER_INTERNAL_ERROR(500,"Server Internal Error"),
    FAIL(1000,"Fail");

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
