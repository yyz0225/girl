package com.yyz.girl.service;

import com.yyz.girl.domains.Girl;
import com.yyz.girl.enums.ResultEnum;
import com.yyz.girl.exception.GirlException;
import com.yyz.girl.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: yyz
 * @Date: 2018/11/1 13:54
 */
@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    /**添加事务注解,要么全部执行,要么全部不执行,A,B都插入成功时,数据库才会有数据*/
    @Transactional(rollbackOn = Exception.class)
    public void insertTwo(){

        Girl girlA=new Girl();
        girlA.setCupSize("B");
        girlA.setAge(18);
        girlRepository.save(girlA);

        Girl girlB=new Girl();
        girlB.setCupSize("F");
        girlB.setAge(24);
        girlRepository.save(girlB);
    }

    /**统一异常处理*/
    public Girl getAge(Integer id){

        //1.先查询出这个Girl对象
        Girl girl=girlRepository.findById(id).get();
        //2.判断年龄大小,返回结果
        if(girl.getAge()<10){
            //你还在上小学吧,,, code:100
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if (girl.getAge()>10 && girl.getAge()<16){
            //你怕是个初中生哦,,, code:101
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
        return girl;
    }
}
