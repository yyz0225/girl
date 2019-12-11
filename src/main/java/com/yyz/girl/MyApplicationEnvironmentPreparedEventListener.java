package com.yyz.girl;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Author: yyz
 * @Date: 2019/12/11 14:42
 * spring boot 对应Enviroment已经准备完毕，但此时上下文context还没有创建。
 * 在该监听中获取到ConfigurableEnvironment后可以对配置信息做操作，
 * 例如：修改默认的配置信息，增加额外的配置信息等等。
 */
@Log
public class MyApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent>{

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        SpringApplication springApplication =event.getSpringApplication();
        System.out.println("---MyApplicationEnvironmentPreparedEventListener---");
    }
}
