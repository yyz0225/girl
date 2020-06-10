package com.yyz.girl.listener;

import lombok.extern.java.Log;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Author: yyz
 * @Date: 2019/12/11 14:42
 * 应用启动时事件监听(spring boot启动开始时执行的事件)
 */
@Log
public class MyApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent>{

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        SpringApplication springApplication =event.getSpringApplication();
        /** 可做一些应用执行前的设置,比如关闭banner显示**/
        springApplication.setBannerMode(Banner.Mode.OFF);
        //log.info("---MyApplicationStartedEventListener---");
        System.out.println("---MyApplicationStartingEventListener---");
    }
}
