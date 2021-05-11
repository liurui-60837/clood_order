package com.imoc.order.message;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
public class MqReceiver {

    //@RabbitListener(queues = "myreceive")
    @RabbitListener(queuesToDeclare = @Queue("myreceive"))//自动创建队列
    public void receive(String message){
        System.out.println("你好: {}"+message);
    }
}
