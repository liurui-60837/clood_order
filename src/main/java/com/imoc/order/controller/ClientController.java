package com.imoc.order.controller;

import com.imoc.order.dataobject.ProductInfo;
import com.imooc.product.client.ProductClient;
import com.imooc.product.common.ProductInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProdcutMsg(){
        //String response = productClient.productMsg();
        return  null;
    }

    @GetMapping("/getProductList")
    public String getProductList(){
     List<ProductInfoDTO> list =  productClient.listForOrder(Arrays.asList("157875196366160022"));
     return list.get(0).getProductDescription();
    }
}
