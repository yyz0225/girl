package com.yyz.girl.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yyz
 * @Date: 2018/11/1 13:54
 * AOP统一登录日志拦截处理
 * component注解引入spring容器里
 */
@Slf4j
@Aspect
@Component
public class HttpAspect {

    //public static final Logger LOGGER= LoggerFactory.getLogger(HttpAspect.class);

    /**切点定义在方法上,切面处使用定义的切点的方法()即可*/
    @Pointcut("execution(public * com.yyz.girl.controller.GirlController.*(..))")
    public void log(){ }

    /*advice:   符合point cut规则的joinPoint的一段代码,模拟为一个interceptor拦截器
    * 例如 HTTP 鉴权的实现, 我们可以为每个使用 RequestMapping 标注的方法织入 advice, 当 HTTP 请求到来时, 首先进入到 advice 代码中,
    * 在这里我们可以分析这个 HTTP 请求是否有相应的权限, 如果有, 则执行 Controller, 如果没有, 则抛出异常.
    * 这里的 advice 就扮演着鉴权拦截器的角色了.
    *
    * */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
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
        log.info("222222222222");
    }

    @AfterReturning(returning = "object" ,pointcut = "log()")
    public void doAfterReturning(Object object){
        log.info("response={}",object.toString());
    }

}
