package com.yyz.girl.controller;

import com.yyz.girl.domains.Girl;
import com.yyz.girl.domains.Result;
import com.yyz.girl.repository.GirlReposity;
import com.yyz.girl.service.GirlService;
import com.yyz.girl.utils.ResultUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/*lombok的@log注解自动生成一个log对象*/
@Log
@RestController
public class GirlController {

    //public static final Logger LOGGER= LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlReposity girlReposity;
    @Autowired
    private GirlService girlService;

    /*获取所有的女生列表 get方式*/
    @GetMapping(value="/girls")
    public Result<Girl> girlList(){
       return ResultUtil.success(girlReposity.findAll());
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
    public Result<Girl> find(@PathVariable("id")Integer id){
        //System.out.println("kkkkk");
        log.info("find");
       Girl girl= girlReposity.findById(id).get();
       // Girl girl=girlReposity.getOne(id);
      return  ResultUtil.success(girl);
    }
    /*通过ID更新女生信息*/
    @PutMapping(value = "/girls/{id}")
    public Result<Girl> update(@PathVariable("id") Integer id,@RequestParam("cupSize") String cupSize,@RequestParam("age") Integer age){
        Girl girl=new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return ResultUtil.success(girlReposity.save(girl));
    }
    /*通过ID删除女生信息*/
    @DeleteMapping(value = "/girls/{id}")
    public Result<Girl>  delete(@PathVariable("id") Integer id){
        girlReposity.deleteById(id);
        return ResultUtil.success();
    }
    /*通过年龄查询女生信息*/
    @GetMapping(value = "/girls/find/{age}")
    public Result<Girl> findByAge(@PathVariable("age")Integer age){
       return ResultUtil.success(girlReposity.findByAge(age));
    }

    @PostMapping(value = "/girls/two")
    public Result<Girl> insertTwo(){
        girlService.insertTwo();
        return ResultUtil.success();
    }

    @GetMapping(value ="/girls/getAge/{id}")
    public Result<Girl> getAge(@PathVariable Integer id) throws Exception{
        return ResultUtil.success(girlService.getAge(id));
    }
}
