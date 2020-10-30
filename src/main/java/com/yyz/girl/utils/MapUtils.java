package com.yyz.girl.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: yyz
 * @Date: 2020/9/29 16:43
 * map工具类
 */
public class MapUtils {

    /**
     * map根据key值进行升序排序
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public <K extends Comparable<? super K>,V> Map<K, V> sortByKeyAsc(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();

        map.entrySet().stream()
                .sorted(Map.Entry.<K, V>comparingByKey())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    /**
     * map根据key值进行降序排序
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public <K extends Comparable<? super K>, V > Map<K, V> sortByKeyDesc(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();

        map.entrySet().stream()
                .sorted(Map.Entry.<K, V>comparingByKey().reversed())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    /**
     * map根据value值进行升序排序
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V extends Comparable<? super V>> Map<K, V> sortByValueAsc(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();

        map.entrySet().stream()
                .sorted(Map.Entry.<K, V>comparingByValue()
                ).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    /**
     * map根据value值进行降序排序
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V extends Comparable<? super V>> Map<K, V> sortByValueDesc(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();

        map.entrySet().stream()
                .sorted(Map.Entry.<K, V>comparingByValue()
                        .reversed()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }
}
