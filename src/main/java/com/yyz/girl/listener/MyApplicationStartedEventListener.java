package com.yyz.girl.listener;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Author: yyz
 * @Date: 2019/12/11 14:42
 * 应用启动结束时事件监听(spring boot启动完成时执行的事件)
 */
@Log
public class MyApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent>{

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        SpringApplication springApplication =event.getSpringApplication();
        System.out.println("---MyApplicationStartedEventListener---");
    }
}
