package com.yyz.girl.annotitaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: yyz
 * @Date: 2019/5/9 14:38
 * AuthChecker注解,自定义注解,有此注解标注的controller中的方法需要进行权限验证
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthChecker {
}
