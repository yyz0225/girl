package com.yyz.girl.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * @Author: yyz
 * @Date: 2020/10/27 19:39
 * 根据枚举key获取value值工具类(线程安全)
 */
public class EnumUtils {

    private static Map<Class, Object> map = new ConcurrentHashMap<>();

    /**
     * 根据条件获取枚举对象
     *
     * @param className 枚举类
     * @param predicate 筛选条件
     * @param <T>
     * @return
     */
    public static <T> Optional<T> getEnumObject(Class<T> className, Predicate<T> predicate) {
        if (!className.isEnum()) {
            return null;
        }
        Object obj = map.get(className);
        T[] ts ;
        if (obj == null) {
            ts = className.getEnumConstants();
            map.put(className, ts);
        } else {
            ts = (T[]) obj;
        }
        return Arrays.stream(ts).filter(predicate).findAny();
    }

}
