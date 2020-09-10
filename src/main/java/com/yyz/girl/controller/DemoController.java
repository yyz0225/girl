package com.yyz.girl.controller;

import com.yyz.girl.annotitaion.AuthChecker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @Author: yyz
 * @Date: 2019/5/9 14:53
 * aop实现http接口鉴权
 */
@RestController
public class DemoController {

    @RequestMapping("/aop/http/alive")
    public String alive() {
        return "服务一切正常";
    }

    /**
     * 利用aop实现http接口鉴权,根据用户是否携带token来判断
     * @return
     */
    @AuthChecker
    @RequestMapping("/aop/http/user_info")
    public String callSomeInterface() {
        return "调用了 user_info 接口.";
    }

    @RequestMapping("/uid")
    public String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }
}