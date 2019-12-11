package com.yyz.girl;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

    public class MyFilter implements Filter {
        @Override
        public void destroy() {
        }

        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
                throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) srequest;
            System.out.println("this is MyFilter,url :"+request.getRequestURI());
            filterChain.doFilter(srequest, sresponse);
        }

        @Override
        public void init(FilterConfig arg0) throws ServletException {
        }
    }

}
