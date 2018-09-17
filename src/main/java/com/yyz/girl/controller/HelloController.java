package com.yyz.girl.controller;

import com.yyz.girl.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
@RestController spring4之后引入的注解,相当于@controller和@
ResponseBody注解的合成,用于返回字符串类型的数据*/
@RestController
public class HelloController {

    /*获取spring-boot配置文件里配置的属性使用@value注解*/
  /*  @Value("${cupSize}")
    private String cupSize;
    @Value("${age}")
    private Integer age;
    @Value("${name}")
    private String name;
    @Value("${content}")
    private String content;*/
    @Autowired
    private GirlProperties girlProperties;

    //@RequestMapping(value = "/hello",method = RequestMethod.GET)
    @GetMapping(value = "/hello")
    public String say(){
        return "Hello Spring Boot!";
    }

    //@RequestMapping(value = {"/cup","/cupSize"},method = RequestMethod.GET)
    @GetMapping(value = {"/cup","/cupSize"})
    public String cup(){
        return "cup:"+girlProperties.getCupSize()+",age:"+girlProperties.getAge()/*+",name:"+name+",content:"+content*/;
    }

}
