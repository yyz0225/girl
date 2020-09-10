package com.yyz.girl;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.repository.query.Param;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

/**
 * @Author: yyz
 * @Date: 2020/9/9 15:08
 * 适配工具
 */
public class ApiAdapter {
     private ApiAdapter(){

     }

    /**
     * 请求转换bean map转换成对应的bean对象
     * @param clazz
     * @param map
     * @param <T>
     * @return
     */
     public static <T> T toBean(Class clazz, Map<String,Object> map){
         if (clazz == null) {
             return null;
         }
         Object bean = null;

         try {
             bean = clazz.newInstance();
         } catch (Exception e) {
             e.printStackTrace();
         }
         if (bean == null) {
             return null;
         }
         Class superClazz = clazz;
         while (!superClazz.getName().equals(Object.class.getName())){
             try {
                 for (Field field: superClazz.getDeclaredFields()) {
                     field.setAccessible(true);
                     String fieldKey = field.getName();
                     Param annotation = field.getAnnotation(Param.class);
                     if (annotation != null && StringUtils.isNotBlank(annotation.value())) {
                         fieldKey = annotation.value();
                     }
                     Object mapValue = map.get(fieldKey);
                     if (mapValue == null) {
                         continue;
                     }
                     field.set(bean,caseVal(mapValue,field.getType()));
                 }
             }catch (Exception e){
                 e.printStackTrace();
                 break;
             }
             superClazz = superClazz.getSuperclass();
         }
         return (T) bean;
     }

    /**
     * 根据类型转换值
     * @param mapValue
     * @param type
     * @return
     */
    private static Object caseVal(Object mapValue, Class<?> type) {
        ObjectUtils.toString(mapValue);
        if (Date.class.isAssignableFrom(type)) {

        }else {
            /*String类型单独处理*/
        }
        return null;
    }
}
