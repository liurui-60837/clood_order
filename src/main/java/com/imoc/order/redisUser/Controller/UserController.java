package com.imoc.order.redisUser.Controller;

import com.imoc.order.redisUser.entity.User;
import com.imoc.order.redisUser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/user/add")
    public void addUser() {
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setUsername("zzyy" + i);
            user.setPassword("123123123");
            user.setSex(1);
            userService.addUser(user);
        }
    }
}
