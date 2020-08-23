package com.oy.online.service;

import com.oy.online.pojo.User;

/**
*@Description UserService
*@Author OY
*@Date 2020/8/13
*@Time 16:08
*/
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登入
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 检查 用户名是否可用
     * @param username
     * @return 返回true 表示用户名已存在，返回false 表示用户名可用
     */
    public boolean existsUsername(String username);
}
