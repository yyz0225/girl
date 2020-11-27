package com.yyz.girl.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 根据枚举code获取msg
     * @param code
     * @return
     */
    public static String getMsg(Integer code){
        ResultEnum[] values = ResultEnum.values();
        for (ResultEnum value : values) {
            if (value.getCode().equals(code)) {
                return value.getMsg();
            }
        }
        return null;
    }

    /**
     * java8下枚举获取值
     * @param code
     * @return
     */
    public static String getMsgNew(Integer code){
        return Arrays.stream(ResultEnum.values())
                .filter(resultEnum -> resultEnum.getCode().equals(code))
                .map(ResultEnum::getMsg)
                .collect(Collectors.joining());
    }

}
