package com.yyz.girl.utils;

import com.yyz.girl.enums.ResultEnum;
import org.junit.Test;

import java.util.Optional;

/**
 * 测试枚举工具类
 * @Author: yyz
 * @Date: 2020/10/27 19:43
 */
public class EnumUtilsTest {

    @Test
    public void getEnumObject() {

        String msg = getEnumValue("101");
        System.out.println(msg);
    }

    /**
     * 获取枚举值
     *
     * @param code
     * @return
     */
    private String getEnumValue(String code) {
        Optional<ResultEnum> enumOptional = EnumUtils.getEnumObject(ResultEnum.class, e -> e.getCode().equals(Integer.parseInt(code)));
        return enumOptional.isPresent() ? enumOptional.get().getMsg() : null;
    }
}