package com.tencent.wxcloudrun.common;

/**
 * 响应状态类型枚举
 */
public enum StatusType {
    ILLEGAL_CITY_NAME(-1, "出发城市与目的城市需要为中文"),
    ILLEGAL_DATE(-2, "不合法的日期，形式：yyyy-MM-dd"),
    SERVER_ERROR(-3, "服务异常");

    private int code;
    private String msg;

    StatusType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
