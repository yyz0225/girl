package com.yyz.girl.test;

import org.junit.Test;

/**
 * @Author: yyz
 * @Date: 2019/8/19 10:02
 */
public class JavaTest {
    @Test
    public void test(){
        String requestUri= "/projects/a/b/procy ";
        String contextPath= "/projects";
        System.out.println(org.apache.commons.lang.StringUtils.substring(requestUri,contextPath.length()));
    }
}
