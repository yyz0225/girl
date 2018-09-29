package com.yyz.girl;

import com.yyz.girl.controller.HelloController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *helloworld的controller UT测试类
 */
public class HelloWorldControllerTest {

    @Test
    public void testSayHello(){
        assertEquals("Hello Spring Boot!",new HelloController().say());
    }
}
