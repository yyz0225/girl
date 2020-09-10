package com.yyz.girl.controller;

import com.yyz.girl.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yyz
 * @Date: 2018/11/1 13:54
 * @RestController spring4之后引入的注解,相当于@controller和@ResponseBody注解的合成,用于返回字符串类型的数据,实现了REST API
 */
@RestController
//@Scope(value = "prototype")
public class HelloController {

    private int i=0;

    /**
     * 1)获取springboot配置文件里配置的属性推荐使用@Value("${属性名}")注解来加载对应的配置属性
     * 2)application.properties中配置通用内容，并设置spring.profiles.active=dev，以开发环境为默认配置
     * 3)application-{profile}.properties中配置各个环境不同的内容
     * 4)通过命令行方式去激活不同环境的配置
     */

    /**
     * 注入以girl开头的配置文件里的实体
     */
    @Autowired
    private GirlProperties girlProperties;

    /**
     * spring boot第一个hello world!
     * @return
     */
    //@RequestMapping(value = "/hello",method = RequestMethod.GET)
    @GetMapping(value = "/hello")
    public String say(){
        return "Hello Spring Boot!";
    }

    /**
     * 属性配置文件添加实体配置
     * @return
     */
    //@RequestMapping(value = {"/cup","/cupSize"},method = RequestMethod.GET)
    @GetMapping(value = {"/cup","/cupSize"})
    public String cup(){
        return "cup:"+girlProperties.getCupSize()+",age:"+girlProperties.getAge();
    }

    /**
     * 测试spring中controller是单例 single还是多例(原型 prototype)
     * 单例即容器初始化后只产生一个对象,单个实例,单例是不安全的，会导致属性重复使用
     * 原型即每次请求访问都会产生新的对象
     * 结论: spring中controller是单例的,故在controller中不要使用非静态成员变量,会导致数据逻辑混乱
     * @return
     */
    @RequestMapping("/test/controller")
    public String testControllerSingle(){
        /*this代表当前HelloController对象*/
        System.out.println(this);
        return ++i+"";
    }

}
