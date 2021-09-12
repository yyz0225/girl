package com.yyz.girl.annotitaion;

import java.lang.annotation.*;

/**
 * @Author: yyz
 * @Date: 2019/5/9 14:38
 * AuthChecker注解,自定义注解,有此注解标注的controller中的方法需要进行权限验证
 * @Target(ElementType.METHOD) 注解使用目标位置
 * @Retention(RetentionPolicy.RUNTIME) 注解生命周期
 * @Documented 指明修饰的注解，可以被例如javadoc此类的工具文档化，只负责标记，没有成员取值。
 * ElementType.METHOD 方法上使用
 * ElementType.TYPE 类或者接口上使用
 * ElementType.FIELD 字段上使用
 * ElementType.PARAMETER 参数上使用
 * ElementType.CONSTRUCTOR 构造器上使用
 *
 * RetentionPolicy.SOURCE java源文件中,编译期就丢弃
 * RetentionPolicy.CLASS 字节码文件中,JVM加载字节码文件时丢弃
 * RetentionPolicy.RUNTIME 程序运行期间一直存在
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthChecker {
}
