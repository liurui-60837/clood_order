package com.imoc.order.enums;

public enum ResultEnum {
    SUCCESS_FUL(0,"订单取消成功"),
    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),

    PRODUCT_STOCK_ERROR(11,"库存不正确"),

    ORDER_NOT_EXIST(12,"订单不存在"),

    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),

    ORDER_STATUS_ERROR(14,"订单不正确"),

    ORDER_UPDATE_FAIL(15,"订单更新失败"),

    ORDER_DETAIL_EMPTY(16,"订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17,"支付状态不正确"),

    CART_EMPTY(18,"购物车为空"),

    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),

    WX_MP_ERROR(20,"微信公招账号错误"),

    SUCCESS_FUL_END(21,"订单成功完结"),

    PRODUCT_ALL_REDAY(22,"商品状态已经修改"),
    ;
    private  Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
