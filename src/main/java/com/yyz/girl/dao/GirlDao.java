package com.yyz.girl.dao;


import com.yyz.girl.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
/**
 * 查询方式一:继承JpaRepository,使用默认的方法调用,
 * 例如save,findAll,findOne,findById等等
 * @Author: yyz
 * @Date: 2018/11/1 13:54
 */
public interface GirlDao extends JpaRepository<Girl,Integer>{
    /**
     * 查询方式二:定义查询名,以findByXxx形式,
     * 方法在调用时,会自动根据查询名称转换为对应
     * 的SQL条件查询语句
     * 根据年龄来查询女生信息列表
     * @param age
     * @return
     */
    List<Girl> findByAge(Integer age);

    /**
     * 查询方式三:使用预定义查询方式(NamedQueries)
     * 预定义查询有两种，一种是通过XML配置<named-query />或<named-native-query />
     * 另一种是注解配置@NamedQuery或@NamedNativeQuery(实体模型上进行查询)
     * example:
     *   @Entity
     *   @NamedQuery(name="Customer.findByFirstName",query = "select c from Customer c where c.firstName = ?1")
     *   public class Customer {.......}
     *
     */

    /**
     * 查询方式四:使用@Query注解,方式一:原生SQL 方式二:JPQL语句
     * 这里使用方式一根据cupSize大小来获取实体信息
     * @param cupSize
     * @return
     */
    @Query("select girl from Girl girl where girl.cupSize = ?1")
    Girl findByCupSize2(String cupSize);

    @Query("select girl from Girl girl where girl.cupSize = : cupSize")
    Girl findByCupSize3(@Param("cupSize") String cupSize);
    /**
     * 1）直接创建Sort对象，适合对单一属性做排序
     *    eg: Sort sort = new Sort(Sort.Direction.DESC,"id");
     * 2）通过Sort.Order对象创建Sort对象，适合对单一属性做排序
     *    eg: Sort sortx = new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
     * 3）通过属性的List集合创建Sort对象，适合对多个属性，采取同一种排序方式的排序
     *    eg:  List<String> sortProperties = new ArrayList<>();
     *         sortProperties.add("id");
     *         sortProperties.add("firstName");
     *         Sort sort2 = new Sort(Sort.Direction.DESC,sortProperties);
     * 4）通过Sort.Order对象的List集合创建Sort对象，适合所有情况，比较容易设置排序方式
     *    eg: List&lt;Sort.Order&gt; orders = new ArrayList&lt;&gt;();
     *          orders.add(new Sort.Order(Sort.Direction.DESC,"id"));
     *          orders.add(new Sort.Order(Sort.Direction.ASC,"firstName"));
     *          Sort sort2 = new Sort(orders);
     *    对应着我们的使用场景来进行选择创建Sort对象的方式。
     */
    /**
     * 一个参数，匹配两个字段
     * @param name2
     * @param sort 指定排序的参数，可以根据需要进行调整
     * @return
     * 这里Param的值和=:后面的参数匹配，但不需要和方法名对应的参数值对应
     *  @Query("select c from Customer c where c.firstName=:name or c.lastName=:name")
     *  List&lt;Customer&gt; findByName4(@Param("name") String name2,Sort sort);
     */

    /**
     * 更新实体信息,返回更改结果集行数
     *
     * @param id
     * @return
     */
    @Modifying//更新查询
    @Transactional//开启事务
    @Query("update Girl girl set girl.cupSize = ?1 where girl.id = ?2")
    int updateGirlCupSize(String cupSize, String id);

}
