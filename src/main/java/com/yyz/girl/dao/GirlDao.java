package com.yyz.girl.dao;


import com.yyz.girl.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @Author: yyz
 * @Date: 2018/11/1 13:54
 */
public interface GirlDao extends JpaRepository<Girl,Integer>{
    /**
     * 根据年龄来查询女生信息列表
     * @param age
     * @return
     */
    List<Girl> findByAge(Integer age);
}
