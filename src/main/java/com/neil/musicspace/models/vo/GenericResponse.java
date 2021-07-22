package com.neil.musicspace.models.vo;

import com.neil.musicspace.models.enums.Result;

/**
 * @Description GenericResponse
 * @Author neil
 * @Date 2021/7/12 13:55
 * @Version 1.0
 **/
public class GenericResponse {

    private boolean success;
    private int statusCode;
    private Object content;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public GenericResponse(){}

    public GenericResponse(boolean success, int code, String msg, Object data) {

        this.success = success;
        this.statusCode = code;
        this.msg = msg;
        this.content = data;
    }

    public static GenericResponse response(Result error) {

        return GenericResponse.response(error, null);
    }

    public static GenericResponse response(Result error, Object data) {

        if (error == null) {
            error = Result.UN_KNOW_ERROR;
        }
        if (error.equals(Result.NORMAL)) {
            return GenericResponse.response(true, error.getCode(), error.getMsg(), data);
        }
        return GenericResponse.response(false, error.getCode(), error.getMsg(), data);
    }

    public static GenericResponse response(boolean success, int code, String msg, Object data) {

        return new GenericResponse(success, code, msg, data);
    }
}