package com.yyz.girl.listener;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Author: yyz
 * @Date: 2019/12/11 14:42
 * spring boot上下文context创建完成，但此时spring中的bean是没有完全加载完成的
 */
@Log
public class MyApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent>{

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        SpringApplication springApplication =event.getSpringApplication();
        System.out.println("---MyApplicationPreparedEventListener---");
    }
}
