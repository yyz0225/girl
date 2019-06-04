package com.yyz.girl.controller;

import com.yyz.girl.annotitaion.AuthChecker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yyz
 * @Date: 2019/5/9 14:53
 */
@RestController
public class DemoController {
    @RequestMapping("/aop/http/alive")
    public String alive() {
        return "服务一切正常";
    }

    @AuthChecker
    @RequestMapping("/aop/http/user_info")
    public String callSomeInterface() {
        return "调用了 user_info 接口.";
    }
}