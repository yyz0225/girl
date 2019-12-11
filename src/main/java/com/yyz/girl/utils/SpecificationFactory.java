package com.yyz.girl.utils;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Path;

/**
 * 这个工具类定义了一些基本的查询，包括模糊匹配（containsLike）,
 * 数值区间（isBetween）以及枚举类参数匹配（enumMatcher），这些也是在查询中常用的方法，
 * 在查询的时候，通过Specifications进行调用组织参数，即可获得接口Specification的实例，然后用于查询。
 * root --> javax.persistence.criteria.Root
 * query--> javax.persistence.criteria.CriteriaQuery
 * cb   --> javax.persistence.criteria.CriteriaBuilder
 *
 * @Author: yyz
 * @Date: 2019/9/27 9:45
 */
public final class SpecificationFactory {

    /**
     * 模糊匹配
     * @param attribute
     * @param value
     * @return
     */
    public static Specification containsLike(String attribute, String value) {
        return (root, query, cb) -> cb.like(root.get(attribute), "%" + value + "%");
    }

    /**
     * 数值区间
     * @param attribute
     * @param min
     * @param max
     * @return
     */
    public static Specification isBetween(String attribute, int min, int max) {
        return (root, query, cb) -> cb.between(root.get(attribute), min, max);
    }
    public static Specification isBetween(String attribute, double min, double max) {
        return (root, query, cb) ->cb.between(root.get(attribute), min, max);
    }

    /**
     * 美剧类型参数匹配
     * @param attribute
     * @param queriedValue
     * @param <T>
     * @return
     */
    public static <T extends Enum<T>> Specification enumMatcher(String attribute, T queriedValue) {
        return (root, query, cb) -> {
            Path<T> actualValue = root.get(attribute);
            if (queriedValue == null) {
                return null;
            }
            return cb.equal(actualValue, queriedValue);
        };
    }
}
