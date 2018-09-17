package com.spbs.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//当返回的json为空得时候，如果json==nul则key==null
public class ServerSponse<T> implements Serializable {
    private int status;
    private String msg;
    private T data;

    public ServerSponse(int status) {
        this.status = status;
    }

    public ServerSponse(int status, T data) {
        this.status = status;
        this.data = data;

    }

    public ServerSponse(int status, String msg, T data) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public ServerSponse(int status, String msg) {
        this.status = status;
        this.msg = msg;

    }

    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess() {
        return this.status == Sta_Type.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> ServerSponse<T> createBySuccess() {
        return new ServerSponse<T>(Sta_Type.SUCCESS.getCode());
    }

    public static <T> ServerSponse<T> createBySuccessMessage(String msg) {
        return new ServerSponse<T>(Sta_Type.SUCCESS.getCode(), msg);
    }

    public static <T> ServerSponse<T> createBySuccess(T data) {
        return new ServerSponse<T>(Sta_Type.SUCCESS.getCode(), data);
    }

    public static <T> ServerSponse<T> createBySuccess(String msg, T data) {
        return new ServerSponse<T>(Sta_Type.SUCCESS.getCode(), msg, data);
    }


    public static <T> ServerSponse<T> createByError() {
        return new ServerSponse<T>(Sta_Type.ERROR.getCode(), Sta_Type.ERROR.getDesc());
    }


    public static <T> ServerSponse<T> createByErrorMessage(String errorMessage) {
        return new ServerSponse<T>(Sta_Type.ERROR.getCode(), errorMessage);
    }

    public static <T> ServerSponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new ServerSponse<T>(errorCode, errorMessage);
    }
}
