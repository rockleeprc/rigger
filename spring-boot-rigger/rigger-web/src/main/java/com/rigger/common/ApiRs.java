package com.rigger.common;

public class ApiRs<T> {

    private Integer code;
    private String msg;
    private T data;

    public ApiRs() {
    }

    public ApiRs(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Deprecated
    private static <T> ApiRs<T> restResult(Integer code, String msg, T data) {
        ApiRs<T> apiResult = new ApiRs();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    private static <T> ApiRs<T> restResult(ErrorCode errorCode, T data) {
        ApiRs<T> apiResult = new ApiRs();
        apiResult.setCode(errorCode.getCode());
        apiResult.setData(data);
        apiResult.setMsg(errorCode.getMsg());
        return apiResult;
    }

    public static <T> ApiRs<T> Success() {
        return restResult(ApiErrorCode.SUCCESS, null);
    }

    public static <T> ApiRs<T> fail(T data) {
        return restResult(ApiErrorCode.FAIL, data);
    }

    public static <T> ApiRs<T> notFound() {
        return restResult(ApiErrorCode.NOT_FOUND, null);
    }
    public static <T> ApiRs<T> notFound(T data) {
        return restResult(ApiErrorCode.NOT_FOUND, data);
    }

    public static <T> ApiRs<T> badRequest(T data) {
        return restResult(ApiErrorCode.BAD_REQUEST, data);
    }

    public static <T> ApiRs<T> forbidden(T data) {
        return restResult(ApiErrorCode.FORBIDDEN, data);
    }

    public static <T> ApiRs<T> unauthorized(T data) {
        return restResult(ApiErrorCode.UNAUTHORIZED, data);
    }

    public static <T> ApiRs<T> serverInternalError(T data) {
        return restResult(ApiErrorCode.SERVER_INTERNAL_ERROR, data);
    }

    public static <T> ApiRs<T> Fail() {
        return restResult(ApiErrorCode.FAIL, null);
    }
    public static <T> ApiRs<T> Fail(T data) {
        return restResult(ApiErrorCode.FAIL, data);
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
