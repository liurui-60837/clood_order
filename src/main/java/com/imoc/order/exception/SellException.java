package com.imoc.order.exception;

import com.imoc.order.enums.ResultEnum;


public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(String message, Integer code) {
        super(message);
        this.code = code;
    }
    public SellException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
