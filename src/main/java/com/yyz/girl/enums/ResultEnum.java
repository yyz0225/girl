package com.yyz.girl.enums;

public enum ResultEnum {
    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(0,"OK"),
    PRIMARY_SCHOOL(100,"你还在上小学吧,,"),
    MIDDLE_SCHOOL(101,"你怕是还在上初中哦.."),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
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
