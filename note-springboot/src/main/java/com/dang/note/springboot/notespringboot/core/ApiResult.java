package com.dang.note.springboot.notespringboot.core;

import java.util.UUID;

public class ApiResult {
    private int code;
    private String message;
    private String queryId = UUID.randomUUID().toString();
    private Object data;

    /**
     * 构造函数
     *
     * @param code 状态码
     * @param data 数据对象
     */
    public ApiResult(Code code, Object data) {
        this.code = code.getCode();
        this.message = code.getMessage();
        this.data = data;
    }

    /**
     * 构造函数
     *
     * @param code    状态码
     * @param message 消息
     * @param data    数据对象
     */
    public ApiResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ApiResult get(Code code, Object data) {
        return new ApiResult(code, data);
    }

    public static ApiResult error(String message) {
        return new ApiResult(Code.ERROR.getCode(), message, null);
    }

    public static ApiResult success(Object data) {
        return new ApiResult(Code.SUCCESS, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
