package com.imoc.order.message;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.imooc.product.common.ProductInfoDTO;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductInfoReceive {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RabbitListener(bindings = @QueueBinding(
            value =  @Queue("productInfo"),
            exchange = @Exchange("myExange")
    ))
    public void receive(String mess){

        List<String> list =JSONObject.parseArray(mess,String.class);
        for(int i=0;i<list.size();i++){
            ProductInfoDTO productInfoDTO = new ProductInfoDTO();
            productInfoDTO = JSONObject.parseObject(list.get(i),ProductInfoDTO.class);
            redisTemplate.opsForValue().set(productInfoDTO.getProductId(),productInfoDTO.getProductStock().toString());
        }
        //存到redis
       // redisTemplate.opsForValue().set(productInfoDTO.getProductId(),productInfoDTO.getProductStock().toString());
    }
}
