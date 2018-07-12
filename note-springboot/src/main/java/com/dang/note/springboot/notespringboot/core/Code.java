package com.dang.note.springboot.notespringboot.core;

public enum Code {
    SUCCESS(200, "操作成功！"),
    ERROR(500, "对不起，操作出错！"),
    API_ERROR(500, "接口出错！"),
    NOTFOUND(404, "对不起，您请求的资源不存在！"),
    DUPLICATE(302, "重复操作！"),
    NOPERM(403, "对不起，您没有进行此项操作的权限"),
    CONFLICT(409, "资源状态冲突"),
    PRECONDITION_FAILED(412, "前置验证条件异常"),
    ILLEGA(422, "请求服务参数异常"),
    UNLOGIN(407, "未登录，请先登录！");

    private final int code;
    private final String message;

    private Code(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
