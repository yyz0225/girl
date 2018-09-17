package com.yyz.girl.repository;


import com.yyz.girl.domains.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GirlReposity extends JpaRepository<Girl,Integer>{
    /*根据年龄来查询女生信息列表*/
    public List<Girl> findByAge(Integer age);
}
