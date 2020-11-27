package com.yyz.girl.controller;

import com.yyz.girl.builder.Builder;
import com.yyz.girl.entity.Girl;
import com.yyz.girl.entity.Result;
import com.yyz.girl.enums.ResultEnum;
import com.yyz.girl.service.GirlService;
import com.yyz.girl.utils.ResultUtil;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yyz
 * @date: 20181101
 * lombok的@log注解自动生成一个log对象
 * 修改接口符合restful风格
 */
@Log
@RestController
@RequestMapping("/girls")
public class GirlController {

    public static final Logger LOGGER= LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlService girlService;

    // 创建线程安全的Map
    static Map<Long, Girl> users = Collections.synchronizedMap(new HashMap<>());

    /**
     * 查询所有信息
     * @return
     */
    @GetMapping(value = {"","/"})
    public Result listGirls(){
       return ResultUtil.success(girlService.listGirls());
    }

    /**
     * 新建一个女生 POST方式 增加表单验证
     * Girl girl创建一个对象,保证代码的简洁性
     * @param girl
     * @param bindingResult
     * @return
     */
    @PostMapping(value = {"","/"})
    public Result postGirl(@Valid Girl girl, BindingResult bindingResult){
        LOGGER.info("新增信息");
        if (bindingResult.hasErrors()){
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            LOGGER.error("校验失败,errMsg:{}",errorMsg);
            return ResultUtil.error(ResultEnum.ERROR.getCode(),errorMsg);
        }
        girl.setCupSize(girl.getCupSize());
        girl.setAge(girl.getAge());

        Girl girlResult= girlService.postGirl(girl);
        if (girlResult == null) {
            return ResultUtil.error(ResultEnum.ERROR.getCode(),"新增失败");
        }
        return ResultUtil.success(girlResult);
    }

    /**
     * 参数查询
     * @param id
     * @return
     */
    @GetMapping(value = "/id/{id}")
    public Result getGirl(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResultUtil.error(ResultEnum.ERROR.getCode(), "id不能为空!");
        }
        Girl girl = girlService.getGirl(id);
        if (girl == null) {
            return ResultUtil.error(ResultEnum.ERROR.getCode(), "id不存在,请检查后重新输入");
        }
        return ResultUtil.success(girl);
    }

    /**
     * 更新实体信息
     * @param id
     * @param cupSize
     * @param age
     * @return
     */
    @PutMapping(value = "/id/{id}")
    public Result putGirl(@PathVariable("id") Integer id,@RequestParam("cupSize") String cupSize,@RequestParam("age") Integer age){
        Girl girl = Builder.of(Girl::new)
                .with(Girl::setId,id)
                .with(Girl::setCupSize,cupSize)
                .with(Girl::setAge,age)
                .build();

        Girl girlResult = girlService.putGirl(girl);
        if (girlResult == null) {
            ResultUtil.error(ResultEnum.ERROR.getCode(),"更新实体信息失败");
        }
        return ResultUtil.success(girlResult);
    }

    /**
     * 更新实体信息
     * @param girl
     * @param bindingResult
     * @return
     */
    @PutMapping(value = {"","/"})
    public Result putGirlNew(@Valid Girl girl, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            LOGGER.error("校验失败,errMsg:{}",errorMsg);
            return ResultUtil.error(ResultEnum.ERROR.getCode(),errorMsg);
        }
        Girl girlResult = girlService.putGirl(girl);
        if (girlResult == null) {
            ResultUtil.error(ResultEnum.ERROR.getCode(),"更新实体信息失败");
        }
        return ResultUtil.success(girlResult);
    }

    /**
     * 删除实体
     * @param id
     * @return
     */
    @DeleteMapping(value = "/id/{id}")
    public Result deleteGirl(@PathVariable("id") Integer id){
        if (id == null) {
            return ResultUtil.error(ResultEnum.ERROR.getCode(),"删除失败!");
        }
        girlService.deleteGirl(id);
        return ResultUtil.success();
    }

    /**
     * 通过年龄查询
     * @param age
     * @return
     */
    @GetMapping(value = "/{age}")
    public Result getGirlByAge(@PathVariable("age")Integer age){
        if (age == null) {
            return ResultUtil.error(ResultEnum.ERROR.getCode(),"请输入年龄查询参数");
        }
        List<Girl> girlList = girlService.getGirlByAge(age);
        if (CollectionUtils.isEmpty(girlList)) {
            return ResultUtil.error(ResultEnum.ERROR.getCode(),"年龄不存在,请检查后重新输入");
        }
       return ResultUtil.success(girlList);
    }

    /**
     * 同时插入两条记录
     * @return
     */
    @PostMapping(value = "/two")
    public Result<Girl> insertTwo(){
        girlService.insertTwo();
        return ResultUtil.success();
    }
}
