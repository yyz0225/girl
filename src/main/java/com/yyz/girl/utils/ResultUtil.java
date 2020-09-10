package com.yyz.girl.utils;

import com.yyz.girl.entity.Result;
import com.yyz.girl.enums.ResultEnum;

/**
 * @author : yyz
 * @date : 20181101
 * result工具类 统一封装返回结果 0 失败 1 成功
 */
public class ResultUtil {

    public static Result success(Object obj){
        Result result=new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg("OK");
        result.setData(obj);

        return result;
    }

    public static Result success(){
        Result result=new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg("OK");
        result.setData(null);

        return result;
    }

    public static Result error(Integer code,String msg){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);

        return result;
    }
}
