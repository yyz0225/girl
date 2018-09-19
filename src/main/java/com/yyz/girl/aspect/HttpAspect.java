package com.yyz.girl.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/*AOP统一登录日志拦截处理*/
//@Log4j2
@Slf4j
@Aspect    //
@Component //component注入引入spring容器里
public class HttpAspect {

    //public static final Logger LOGGER= LoggerFactory.getLogger(HttpAspect.class);

    /*切点定义在方法上,切面处使用定义的切点的方法()即可*/
    @Pointcut("execution(public * com.yyz.girl.controller.GirlController.*(..))")
    public void log(){ }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        // System.out.println(1111);
        log.info("11111111111");

        ServletRequestAttributes attributes= (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();

        //url
        log.info("url={}",request.getRequestURL());

        //method
        log.info("method={}",request.getMethod());

        //ip
        log.info("ip={}",request.getRemoteAddr());

        //类方法
        log.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."
                +joinPoint.getSignature().getName());
        //参数
        log.info("args={}",joinPoint.getArgs());

    }
    @After("log()")
    public void doAfter(){
       // System.out.println(22222);
        log.info("222222222222");
    }
    @AfterReturning(returning = "object" ,pointcut = "log()")
    public void doAferReturing(Object object){
        log.info("response={}",object.toString());
    }

}
