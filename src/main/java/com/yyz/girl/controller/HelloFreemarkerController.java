package com.yyz.girl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author: yyz
 * @Date: 2018/11/1 13:54
 * @RestController spring4之后引入的注解,相当于@controller和@ResponseBody注解的合成,用于返回字符串类型的数据,实现了REST API
 */
@Controller
@RequestMapping(value = "/freemarker")
public class HelloFreemarkerController {

    @GetMapping(value = "/hello")
    public String helloFreemarker(Model model){
        model.addAttribute("freemarker","hello freemarker!");
        return "hello";
    }

    @GetMapping(value = "/111")
    @ResponseBody
    public String say111(){
        return "Hello Spring Boot!";
    }
}
