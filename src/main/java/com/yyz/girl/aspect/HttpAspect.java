package com.yyz.girl.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/*AOP统一登录日志拦截处理*/
@Aspect    //
@Component //component注入引入spring容器里
public class HttpAspect {

    public static final Logger LOGGER= LoggerFactory.getLogger(HttpAspect.class);

    /*切点定义在方法上,切面处使用定义的切点的方法()即可*/
    @Pointcut("execution(public * com.yyz.girl.controller.GirlController.*(..))")
    public void log(){ }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        // System.out.println(1111);
        LOGGER.info("11111111111");

        ServletRequestAttributes attributes= (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();

        //url
        LOGGER.info("url={}",request.getRequestURL());

        //method
        LOGGER.info("method={}",request.getMethod());

        //ip
        LOGGER.info("ip={}",request.getRemoteAddr());

        //类方法
        LOGGER.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."
                +joinPoint.getSignature().getName());
        //参数
        LOGGER.info("args={}",joinPoint.getArgs());

    }
    @After("log()")
    public void doAfter(){
       // System.out.println(22222);
        LOGGER.info("222222222222");
    }
    @AfterReturning(returning = "object" ,pointcut = "log()")
    public void doAferReturing(Object object){
        LOGGER.info("response={}",object.toString());
    }

}
