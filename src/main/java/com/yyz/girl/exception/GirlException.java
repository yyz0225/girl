package com.yyz.girl.exception;

import com.yyz.girl.enums.ResultEnum;

/**
 * 自定义异常,用于统一异常处理
 */
public class GirlException extends RuntimeException{
    private Integer code;

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
