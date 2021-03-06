package com.yyz.girl.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * lombok的@Data注解用于优化项目中的getter,setter,toString,equasl等方法,增加代码的维护性
 * @Author: yyz
 * @Date: 2018/11/1 13:54
 */
@Data
@Entity
/*
@NamedNativeQuery(name = "Girl.findByAge2",query = "select girl from Girl girl where girl.age = ?1 ")
*/
public class Girl {
    @Id
    @GeneratedValue
    private Integer id;

    private String cupSize;

    @Min(value = 18,message = "未成年少女禁止入内!")
    private Integer age;

    public Girl() {
    }

    public Girl(String cupSize, @Min(value = 18, message = "未成年少女禁止入内!") Integer age) {
        this.cupSize = cupSize;
        this.age = age;
    }

    /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Girl{" +
                "id=" + id +
                ", cupSize='" + cupSize + '\'' +
                ", age=" + age +
                '}';
    }*/
}
