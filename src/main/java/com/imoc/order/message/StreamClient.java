package com.imoc.order.message;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface StreamClient {

    String OUTMYMESSAGE = "outmyMessage";

    String RECEMYMESSAGE = "recemyMessage";

    @Input("myMessage")
    SubscribableChannel input();

    @Output(StreamClient.OUTMYMESSAGE)
    MessageChannel output();

    @Output(StreamClient.RECEMYMESSAGE)
    MessageChannel output2();
}
