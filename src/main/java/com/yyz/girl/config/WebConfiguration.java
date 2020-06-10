package com.yyz.girl.config;

import com.yyz.girl.filter.MyFilter;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yyz
 * @Date: 2019/12/10 16:40
 * SpringBoot中自定义Filter
 */
@Configuration
public class WebConfiguration {

    /**
     * RemoteIpFilter可以定制化过滤url,包括IP,协议等
     * @return
     */
    @Bean
    public RemoteIpFilter remoteIpFilter(){
        return new RemoteIpFilter();
    }

    /**
     * 把自定义的过滤器加入过滤链
     * @return
     */
    @Bean
    public FilterRegistrationBean registrationBean(){

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("paramName", "paramValue");
        filterRegistrationBean.setName("MyFilter");
        filterRegistrationBean.setOrder(1);

        return filterRegistrationBean;
    }
}
