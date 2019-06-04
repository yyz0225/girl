package com.yyz.girl.entity;

import lombok.Data;

/**
 * HashMap的用户实体类
 * @Author: yyz
 * @Date: 2019/4/17 9:28
 */
@Data
public class User {

    private String name;

    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
