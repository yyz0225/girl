package com.yyz.girl.service;

import com.yyz.girl.entity.Girl;

import java.util.List;

/**
 * @Author: yyz
 * @Date: 2018/11/1 13:54
 */
public interface GirlService {

    /**
     * 查询所有信息列表
     * @return
     */
    List<Girl> listGirls();

    /**
     * 新增信息
     * @param girl
     * @return
     */
    Girl postGirl(Girl girl);

    /**
     * 查询信息
     * @param id
     * @return
     */
    Girl getGirl(Integer id);

    /**
     * 更新信息
     * @param girl
     * @return
     */
    Girl putGirl(Girl girl);

    /**
     * 删除信息
     * @param id
     */
    void deleteGirl(Integer id);

    /**
     * 年龄查询
     * @param age
     * @return
     */
    List<Girl> getGirlByAge(Integer age);

    /**
     * 原子性测试
     */
    void insertTwo();
}
