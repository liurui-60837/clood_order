package com.imoc.order.controller;

import com.imoc.order.dto.OrderDto;
import com.imoc.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process(){
        //String mess = ("now :"+new Date());
        String mess = "123";
        OrderDto o  = new OrderDto();
        o.setBuyerName("你好");
        streamClient.output().send(MessageBuilder.withPayload(o).build());
    }
}
