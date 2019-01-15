package com.yyz.girl.utils;

import com.yyz.girl.entity.Result;

/**
 * @author : yyz
 * @date : 20181101
 */
public class ResultUtil {

    public static Result success(Object obj){
        Result result=new Result();
        result.setCode(0);
        result.setMsg("OK");
        result.setData(obj);

        return result;
    }

    public static Result success(){
        Result result=new Result();
        result.setCode(0);
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
