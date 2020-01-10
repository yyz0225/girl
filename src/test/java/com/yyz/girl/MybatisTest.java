package com.yyz.girl;

import com.yyz.girl.dao.UserMapper;
import com.yyz.girl.entity.User;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author: yyz
 * @Date: 2020/1/10 16:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Log
public class MybatisTest {


    @Resource
    private UserMapper userMapper;

    @Test
    public void queryUserByUserIdTest(){
        User user = userMapper.queryUserById(1);
        System.out.println(user);
    }

}
