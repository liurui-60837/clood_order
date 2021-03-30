package com.imoc.order.controller;

import com.imoc.order.client.ProductClient;
import com.imoc.order.dataobject.ProductInfo;
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
        String response = productClient.productMsg();
        return  response;
    }

    @GetMapping("/getProductList")
    public String getProductList(){
     List<ProductInfo> list =  productClient.listForOrder(Arrays.asList("157875196366160022"));
     return list.get(0).getProductDescription();
    }
}
