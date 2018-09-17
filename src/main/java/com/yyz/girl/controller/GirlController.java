package com.yyz.girl.controller;

import com.yyz.girl.domains.Girl;
import com.yyz.girl.domains.Result;
import com.yyz.girl.repository.GirlReposity;
import com.yyz.girl.service.GirlService;
import com.yyz.girl.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    public static final Logger LOGGER= LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlReposity girlReposity;
    @Autowired
    private GirlService girlService;

    /*获取所有的女生列表 get方式*/
    @GetMapping(value="/girls")
    public List<Girl> girlList(){
       return girlReposity.findAll();
    }
    /*新建一个女生 POST方式 增加表单验证
    * Girl girl创建一个对象,保证代码的简洁性
    * */
    @PostMapping(value = "/girls")
    public Result<Girl> createGirl(@Valid Girl girl, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        girl.setCupSize(girl.getCupSize());
        girl.setAge(girl.getAge());

        Girl girlResult= girlReposity.save(girl);
        if(!StringUtils.isEmpty(girlResult)){
            return ResultUtil.success(girlResult);
        }
        return null;
    }
    /*通过ID查询女生信息*/
    @GetMapping(value = "/girls/{id}")
    public Girl find(@PathVariable("id")Integer id){
        //System.out.println("kkkkk");
        LOGGER.info("find");
       Girl girl= girlReposity.findById(id).get();
       // Girl girl=girlReposity.getOne(id);
      return  girl;
    }
    /*通过ID更新女生信息*/
    @PutMapping(value = "/girls/{id}")
    public Girl update(@PathVariable("id") Integer id,@RequestParam("cupSize") String cupSize,@RequestParam("age") Integer age){
        Girl girl=new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlReposity.save(girl);
    }
    /*通过ID删除女生信息*/
    @DeleteMapping(value = "/girls/{id}")
    public void delete(@PathVariable("id") Integer id){
       girlReposity.deleteById(id);
    }
    /*通过年龄查询女生信息*/
    @GetMapping(value = "/girls/find/{age}")
    public List<Girl> findByAge(@PathVariable("age")Integer age){
       return girlReposity.findByAge(age);
    }

    @PostMapping(value = "/girls/two")
    public void insertTwo(){
        girlService.insertTwo();
    }

    @GetMapping(value ="/girls/getAge/{id}")
    public void getAge(@PathVariable Integer id) throws Exception{
        girlService.getAge(id);
    }
}
