package com.imoc.order;

import com.imoc.order.redisUser.entity.User;
import com.imoc.order.redisUser.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.log4j2.AmqpAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class mqTest {

    @Resource
    private UserService userService;
    @Test
    public void send(){
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setUsername("zzyy" + i);
            user.setPassword("123123123");
            user.setSex("1");
            userService.addUser(user);
        }
    }
}
