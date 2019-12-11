package com.yyz.girl.dao;

import com.yyz.girl.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Specification规范查询
 * @Author: yyz
 * @Date: 2019/9/27 9:39
 */
public interface GirlRepository extends JpaRepository<Girl,String>,JpaSpecificationExecutor {
}
