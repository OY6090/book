package com.oy.online.test;

import com.oy.online.pojo.User;
import com.oy.online.service.UserService;
import com.oy.online.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {

    private UserService userService = new UserServiceImpl();

    @Test
    public void registUser(){
        userService.registUser(new User(null, "admin1", "admin1", "admin1@123.com"));
    }

    @Test
    public void login(){
        System.out.println(userService.login(new User(null, "admin", "admin",null)));
    }

    @Test
    public void existsUsername(){
        if(userService.existsUsername("admin")){
            System.out.println("用户名以存在");
        }else{
            System.out.println("用户名可用！");
        }
    }
}
