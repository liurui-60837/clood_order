package com.imoc.order.dataobject;


import com.imoc.order.enums.OrderStatusEnum;
import com.imoc.order.enums.PaystatusEnum;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 */

@Entity
public class OrderMaster {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;
    //买家微信openid
    private String buyerOpenid;

    private BigDecimal orderAmount;
    //订单状态 默认为0新下单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    //z支付状态 默认为0未支付
    private Integer payStatus = PaystatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

    public OrderMaster() {
    }

    public OrderMaster(String orerId, String buyerName, String buyerPhone, String buyerAddress, String buyerOpenid, BigDecimal orderAmount, Integer orderStatus, Integer payStatus, Date createTime, Date updateTime) {
        this.orderId = orerId;
        this.buyerName = buyerName;
        this.buyerPhone = buyerPhone;
        this.buyerAddress = buyerAddress;
        this.buyerOpenid = buyerOpenid;
        this.orderAmount = orderAmount;
        this.orderStatus = orderStatus;
        this.payStatus = payStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getOrerId() {
        return orderId;
    }

    public void setOrerId(String orerId) {
        this.orderId = orerId;
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
}
