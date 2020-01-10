package com.yyz.girl.dao;

import com.yyz.girl.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * user类数据访问层接口
 * @Author: yyz
 * @Date: 2020/1/10 15:46
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{userId}")
    User queryUserById(int userId);
}
