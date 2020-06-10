package com.yyz.girl.listener;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Author: yyz
 * @Date: 2019/12/11 14:42
 * 应用启动时异常事件监听(spring boot启动异常时执行的事件)
 */
@Log
public class MyApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent>{

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        SpringApplication springApplication =event.getSpringApplication();
        System.out.println("---MyApplicationFailedEventListener---");
    }
}
