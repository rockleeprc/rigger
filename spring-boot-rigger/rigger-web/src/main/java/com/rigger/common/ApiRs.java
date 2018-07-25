package com.rigger.common;

public class ApiRs<T> {

    private Integer code;
    private String msg;
    private T data;

    private ApiRs() {
    }

    public ApiRs(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiRs(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Deprecated
    private static <T> ApiRs<T> restResult(Integer code, String msg, T data) {
        ApiRs<T> apiResult = new ApiRs(code,msg,data);
        return apiResult;
    }

    private static <T> ApiRs<T> restResult(ErrorCode errorCode, T data) {
        ApiRs<T> apiResult = new ApiRs(errorCode.getCode(),errorCode.getMsg(),data);
        return apiResult;
    }

    public static <T> ApiRs<T> ok() {
        return ok(null);
    }

    public static <T> ApiRs<T> ok(T data) {
        return restResult(ApiErrorCode.OK, data);
    }

    public static <T> ApiRs<T> no() {
        return no(null);
    }

    public static <T> ApiRs<T> no(T data) {
        return restResult(ApiErrorCode.NO, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
