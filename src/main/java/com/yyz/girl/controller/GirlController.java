package com.yyz.girl.controller;

import com.yyz.girl.entity.Girl;
import com.yyz.girl.entity.Result;
import com.yyz.girl.service.GirlService;
import com.yyz.girl.utils.ResultUtil;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * lombok的@log注解自动生成一个log对象
 * @author: yyz
 * @date: 20181101
 */
@Log
@RestController
public class GirlController {

    public static final Logger LOGGER= LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlService girlService;

    /**获取所有的女生列表 get方式*/
    @GetMapping(value="/girls")
    public Result<Girl> girlList(){
       return ResultUtil.success(girlService.findAll());
    }

    /**新建一个女生 POST方式 增加表单验证
    * Girl girl创建一个对象,保证代码的简洁性
    * */
    @PostMapping(value = "/girls")
    public Result<Girl> createGirl(@Valid Girl girl, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            LOGGER.error(bindingResult.getFieldError().getDefaultMessage());
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        girl.setCupSize(girl.getCupSize());
        girl.setAge(girl.getAge());

        Girl girlResult= girlService.save(girl);
        if(!StringUtils.isEmpty(girlResult)){
            return ResultUtil.success(girlResult);
        }
        return null;
    }

    /**通过ID查询女生信息*/
    @GetMapping(value = "/girls/{id}")
    public Result<Girl> find(@PathVariable("id")Integer id){
        log.info("find");
       Girl girl= girlService.findById(id);
        LOGGER.debug("method:{},param:{},result:{}",new Object[]{"find",id,girl});
        return  ResultUtil.success(girl);
    }
    /**通过ID更新女生信息*/
    @PutMapping(value = "/girls/{id}")
    public Result<Girl> update(@PathVariable("id") Integer id,@RequestParam("cupSize") String cupSize,@RequestParam("age") Integer age){
        Girl girl=new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return ResultUtil.success(girlService.save(girl));
    }
    /**通过ID删除女生信息*/
    @DeleteMapping(value = "/girls/{id}")
    public Result<Girl>  delete(@PathVariable("id") Integer id){
        girlService.deleteById(id);
        return ResultUtil.success();
    }
    /**通过年龄查询女生信息*/
    @GetMapping(value = "/girls/find/{age}")
    public Result<Girl> findByAge(@PathVariable("age")Integer age){
       return ResultUtil.success(girlService.findByAge(age));
    }

    /**同时插入两条记录*/
    @PostMapping(value = "/girls/two")
    public Result<Girl> insertTwo(){
        girlService.insertTwo();
        return ResultUtil.success();
    }

    /**通过年龄获取girl信息*/
    @GetMapping(value ="/girls/getAge/{id}")
    @Cacheable(value = "user-key")
    public Result<Girl> getAge(@PathVariable Integer id) throws Exception {
        return ResultUtil.success(girlService.getAge(id));
    }
}
