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
     * @Select 是查询类的注解，所有的查询均使用这个
     * @Result 修饰返回的结果集，关联实体类属性和数据库字段一一对应，如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。
     * @Insert 插入数据库使用，直接传入实体类会自动解析属性到对应的值
     * @Update 负责修改，也可以直接传入对象
     * @delete 负责删除
     * @param userId
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{userId}")
    User queryUserById(int userId);
}
