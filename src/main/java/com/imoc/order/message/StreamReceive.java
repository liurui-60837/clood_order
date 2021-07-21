package com.imoc.order.message;

import com.alibaba.fastjson.JSON;
import com.imoc.order.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;

@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceive {

    @StreamListener(StreamClient.OUTMYMESSAGE)
    @SendTo(StreamClient.RECEMYMESSAGE)
    public String process(String message){
      //  OrderDto o = (OrderDto) message;
        OrderDto orderDto =  JSON.parseObject(message, OrderDto.class);
        log.info("StreamReceive: {}",(String)message);
        return "123";
    }

    @StreamListener(StreamClient.RECEMYMESSAGE)
    public void retu(String message){
        //  OrderDto o = (OrderDto) message;
       // OrderDto orderDto =  JSON.parseObject(message, OrderDto.class);
        log.info("retu: {}",message);
    }
}
