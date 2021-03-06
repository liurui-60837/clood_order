package com.imoc.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imoc.order.Utls.EnumUtils;
import com.imoc.order.dataobject.OrderDetail;
import com.imoc.order.enums.OrderStatusEnum;
import com.imoc.order.enums.PaystatusEnum;
import lombok.Data;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderId;

    private  String buyerName;

    private  String buyerPhone;

    private String buyerAddress;
    //买家微信openid
    private String buyerOpenid;

    private BigDecimal orderAmount;
    //订单状态 默认为0新下单
    private Integer orderStatus;
    //z支付状态 默认为0未支付
    private Integer payStatus;

    private Date createTime;

    private  Date updateTime;

    private List<OrderDetail> orderDetailList;
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
       return EnumUtils.getByCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public PaystatusEnum getPayStatusEnum(){
        return EnumUtils.getByCode(payStatus,PaystatusEnum.class);
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerOpenid() {
        return buyerOpenid;
    }

    public void setBuyerOpenid(String buyerOpenid) {
        this.buyerOpenid = buyerOpenid;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
