package com.yyz.girl.handle;

import com.yyz.girl.domains.Result;
import com.yyz.girl.exception.GirlException;
import com.yyz.girl.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*返回的信息进行统一异常处理,补获controller层的异常,然后分类进行异常处理*/
@ControllerAdvice
public class ExceptionHandle {

    private static final Logger LOGGER= LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof GirlException){
            GirlException girlException=(GirlException) e;
            return ResultUtil.error(girlException.getCode(),girlException.getMessage());
        }else {
            LOGGER.error("【系统异常】{}",e);
            return ResultUtil.error(-1,"未知错误");
        }
    }
}
