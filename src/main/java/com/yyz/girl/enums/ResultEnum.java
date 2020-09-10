package com.yyz.girl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * lombok全参构造方法
 * @Author: yyz
 * @Date: 2018/11/1 13:54
 */
@AllArgsConstructor
public enum ResultEnum {

    /**
     * UNKNOWN_ERROR
     */
    UNKNOWN_ERROR(-1, "未知错误"),
    /**
     * SUCCESS
     **/
    SUCCESS(1, "OK"),
    /**
     * error
     */
    ERROR(0, "error"),

    /**
     * PRIMARY_SCHOOL
     */
    PRIMARY_SCHOOL(100, "你还在上小学吧,,"),
    /**
     * MIDDLE_SCHOOL
     */
    MIDDLE_SCHOOL(101, "你怕是还在上初中哦.."),
    ;

    @Getter
    private Integer code;

    @Getter
    private String msg;

    /*ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }*/
}
