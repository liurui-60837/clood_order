package com.imoc.order.enums;

/**
 * 商品状态
 */

public enum ProductStatusEnum implements  codeEnum {
    UP(0,"在架"),
    DOWN(1,"下架")
    ;
    private  Integer code;

    private  String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }}
