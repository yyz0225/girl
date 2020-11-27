package com.yyz.girl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yyz
 * @Date: 2020/7/7 14:00
 */
public class Test2 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","b","c","d","e","f","g");
        System.out.println(list);
        /*java8对字符串连接优化*/
        String format = list.stream().collect(Collectors.joining(",","{","}"));
        System.out.println(format);
    }
}
