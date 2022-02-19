package pqk.learning.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pqk.learning.springboot.entity.User;

import javax.annotation.Resource;

@RestController
public class TestController {

    // @Resource("user1")  根据bean id注入
    @Autowired //根据实例的类的 类型注入
    private User user;


    @GetMapping("/test")
    public User test(){
        return user;
    }

}
