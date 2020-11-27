package com.yyz.girl.service.impl;

import com.yyz.girl.builder.Builder;
import com.yyz.girl.dao.GirlDao;
import com.yyz.girl.entity.Girl;
import com.yyz.girl.enums.ResultEnum;
import com.yyz.girl.exception.GirlException;
import com.yyz.girl.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: yyz
 * @Date: 2018/11/1 13:54
 */
@Service
public class GirlServiceImpl implements GirlService{

    @Autowired
    private GirlDao girlDao;

    /**
     * 添加事务注解,要么全部执行,要么全部不执行,A,B都插入成功时,数据库才会有数据
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public void insertTwo() {

        Girl girlA = Builder.of(Girl::new)
                .with(Girl::setCupSize,"B")
                .with(Girl::setAge,18)
                .build();
        girlDao.save(girlA);

        Girl girlB = Builder.of(Girl::new)
                .with(Girl::setCupSize,"F")
                .with(Girl::setAge,24)
                .build();
        girlDao.save(girlB);
    }

    /**
     * 查询所有信息列表
     * @return
     */
    @Transactional
    @Override
    public List<Girl> listGirls() {
        return girlDao.findAll();
    }

    /**
     * 新增一条信息
     * @param girl
     * @return
     */
    @Transactional
    @Override
    public Girl postGirl(Girl girl) {
        return girlDao.save(girl);
    }

    /**
     * 单实体获取
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Girl getGirl(Integer id) {
        return girlDao.findOne(id);
    }

    /**
     * 更新实体信息
     * @param girl
     * @return
     */
    @Transactional
    @Override
    public Girl putGirl(Girl girl) {

        if (girl.getAge() < 10) {
            //你还在上小学吧,,, code:100
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (girl.getAge() > 10 && girl.getAge() < 16) {
            //你怕是个初中生哦,,, code:101
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }

        return girlDao.save(girl);
    }

    /**
     * 删除实体信息
     * @param id
     */
    @Transactional
    @Override
    public void deleteGirl(Integer id) {
        girlDao.delete(id);
    }

    /**
     * 年龄查询
     * @param age
     * @return
     */
    @Transactional
    @Override
    public List<Girl> getGirlByAge(Integer age) {
        return girlDao.findByAge(age);
    }

}

