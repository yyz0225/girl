package com.yyz.girl.enums;

import org.junit.Test;

/**
 * @Author: yyz
 * @Date: 2020/9/11 10:11
 */
public class ResultEnumTest {

    @Test
    public void getMsg() {
        System.out.println("直接获取某个指定枚举下的值:"+ResultEnum.ERROR.getMsg());
        String successMsg = ResultEnum.getMsg(ResultEnum.SUCCESS.getCode());
        System.out.println("for获取枚举值:"+successMsg);
    }

    @Test
    public void getMsgNew() {
        String successMsgNew = ResultEnum.getMsgNew(ResultEnum.UNKNOWN_ERROR.getCode());
        System.out.println("java8下获取枚举值:"+successMsgNew);
    }

    @Test
    public void getCode() {
        System.out.println("getCode:"+ResultEnum.MIDDLE_SCHOOL.getCode());
    }
}