package com.oy.online.test;

import com.oy.online.dao.UserDao;
import com.oy.online.dao.impl.UserDaoImpl;
import com.oy.online.pojo.User;
import org.junit.Test;

public class UserDaoTest {
    private UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername(){
        if(userDao.queryUserByUsername("admin") == null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUsernameAndPassword(){
        if(userDao.queryUserByUsernameAndPassword("admin", "admin") == null){
            System.out.println("用户名或密码错误，登入失败");
        }else{
            System.out.println("登入成功");
        }
    }

    @Test
    public void saveUser(){
        System.out.println(userDao.saveUser(new User(null, "admit", "admit", "admit@qq.com")));
    }

}
