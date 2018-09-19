package com.yyz.girl.exception;

import com.yyz.girl.enums.ResultEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常,用于统一异常处理
 */
public class GirlException extends RuntimeException{
    @Getter
    @Setter
    private Integer code;

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
