package com.yyz.girl.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: yyz
 * @Date: 2018/11/1 13:54
 * @ConfigurationProperties注解作用:实体注入,实体的值从配置文件里读取,prefix属性制定配置的yml文件对应实体的实体名
 * @component注解用于bean注入,缺失会报找不到这个对应的bean错误,从而导致不能注入实体
 */
@Data
@Component
@ConfigurationProperties(prefix = "girl")
public class GirlProperties {
    private String cupSize;
    private Integer age;

}
