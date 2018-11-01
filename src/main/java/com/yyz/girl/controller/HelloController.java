package com.yyz.girl.controller;

import com.yyz.girl.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
/**
 * @Author: yyz
 * @Date: 2018/11/1 13:54
 * @RestController spring4之后引入的注解,相当于@controller和@ResponseBody注解的合成,用于返回字符串类型的数据,实现了REST API
 */
public class HelloController {


    /*获取spring-boot配置文件里配置的属性使用@value注解*/

    /**注入实体引入*/
    @Autowired
    private GirlProperties girlProperties;

    //@RequestMapping(value = "/hello",method = RequestMethod.GET)
    /**第一个hello spring boot*/
    @GetMapping(value = "/hello")
    public String say(){
        return "Hello Spring Boot!";
    }

    //@RequestMapping(value = {"/cup","/cupSize"},method = RequestMethod.GET)
    /**属性配置文件添加实体配置*/
    @GetMapping(value = {"/cup","/cupSize"})
    public String cup(){
        return "cup:"+girlProperties.getCupSize()+",age:"+girlProperties.getAge();
    }

}
