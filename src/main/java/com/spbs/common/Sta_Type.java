package com.spbs.common;

public enum Sta_Type {
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    USER_PSW_NULL(3,"USER_PSW_NULL"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");
    private final int code;
    private final String desc;
     Sta_Type(int code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
