package com.yyz.girl.service;

import com.yyz.girl.entity.Girl;
import com.yyz.girl.enums.ResultEnum;
import com.yyz.girl.exception.GirlException;
import com.yyz.girl.dao.GirlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: yyz
 * @Date: 2018/11/1 13:54
 */
@Service
public class GirlService {

    @Autowired
    private GirlDao girlDao;

    /**
     * 添加事务注解,要么全部执行,要么全部不执行,A,B都插入成功时,数据库才会有数据
     */
    @Transactional(rollbackOn = Exception.class)
    public void insertTwo() {

        Girl girlA = new Girl();
        girlA.setCupSize("B");
        girlA.setAge(18);
        girlDao.save(girlA);

        Girl girlB = new Girl();
        girlB.setCupSize("F");
        girlB.setAge(24);
        girlDao.save(girlB);
    }

    /**
     * 统一异常处理
     */
    public Girl getAge(Integer id) {

        //1.先查询出这个Girl对象
        Girl girl = girlDao.findById(id).get();
        //2.判断年龄大小,返回结果
        if (girl.getAge() < 10) {
            //你还在上小学吧,,, code:100
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (girl.getAge() > 10 && girl.getAge() < 16) {
            //你怕是个初中生哦,,, code:101
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
        return girl;
    }

    /**
     * 获取实体列表
     *
     * @return
     */
    public List<Girl> findAll() {
        return girlDao.findAll();
    }

    /**
     * 单实体保存
     *
     * @param girl
     * @return
     */
    public Girl save(Girl girl) {
        return girlDao.save(girl);
    }

    /**
     * 单实体获取
     *
     * @param id
     * @return
     */
    public Girl findById(Integer id) {
        return girlDao.findById(id).get();
    }

    /**
     * 删除单个实体
     *
     * @param id
     */
    public void deleteById(Integer id) {
        girlDao.deleteById(id);
    }

    /**
     * 根据条件查询实体列表
     *
     * @param age
     * @return
     */
    public List<Girl> findByAge(Integer age){
        return girlDao.findByAge(age);
    }
}
