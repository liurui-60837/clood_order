package com.imoc.order.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * 商品详情
 */
public class ProductInfoVo {
    @JsonProperty("id")
    private  String productId;

    @JsonProperty("name")
    private  String ProductName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private  String productDescrtption;

    @JsonProperty("icon")
    private  String  productIcon;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescrtption() {
        return productDescrtption;
    }

    public void setProductDescrtption(String productDescrtption) {
        this.productDescrtption = productDescrtption;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }
}
