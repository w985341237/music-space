package com.neil.musicspace.models.enums;

/**
 * @Description ServiceErrorEnum
 * @Author neil
 * @Date 2021/7/12 14:01
 * @Version 1.0
 **/
public enum Result {

    NORMAL(1, "操作成功"),
    UN_KNOW_ERROR(-1, "未知错误"),

    /** Global Error */
    GLOBAL_ERR_NO_SIGN_IN(-10001,"未登录或登录过期/Not sign in"),
    GLOBAL_ERR_NO_CODE(-10002,"code错误/error code"),
    GLOBAL_ERR_NO_AUTHORITY(-10003, "没有操作权限/No operating rights"),
    GLOBAL_ERROR_PARAM_ERROR(-10004, "请求参数错误"),

    /**
     * Weixin Error
     */
    WEIXIN_LOGIN_ERROR(-20001, "微信登录错误"),
    ;

    private int code;
    private String msg;

    private Result(int code, String msg)
    {
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
