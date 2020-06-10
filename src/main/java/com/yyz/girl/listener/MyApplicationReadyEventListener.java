package com.yyz.girl.listener;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Author: yyz
 * @Date: 2019/12/11 14:42
 * 应用加载完成时事件监听(spring boot启动加载完成时执行的事件)
 */
@Log
public class MyApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent>{

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        SpringApplication springApplication =event.getSpringApplication();
        System.out.println("---MyApplicationReadyEventListener---");
    }
}
