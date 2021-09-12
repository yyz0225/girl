package com.yyz.girl.handle;

import com.yyz.girl.entity.Result;
import com.yyz.girl.enums.ResultEnum;
import com.yyz.girl.exception.GirlException;
import com.yyz.girl.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理 使用@ControllerAdvice和@ExceptionHandler注解
 * 返回的信息进行统一异常处理,捕获controller层的异常,然后分类进行异常处理
 * @ControllerAdvice 注解表示这是一个增强版的Controller,用于全局数据处理
 * @ExceptionHandler 异常处理器,用来捕获异常,对异常进行分类处理
 * @author : yyz
 * @date : 20181101
 *
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof GirlException){
            GirlException girlException=(GirlException) e;
            return ResultUtil.error(girlException.getCode(),girlException.getMessage());
        }else {
            log.error("【系统异常】{}",e);
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(),ResultEnum.UNKNOWN_ERROR.getMsg());
        }
    }
}
