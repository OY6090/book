package com.oy.online.dao.impl;

import com.oy.online.dao.BaseDao;
import com.oy.online.dao.UserDao;
import com.oy.online.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select id, username, password, email  from t_user where username = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select id, username, password, email from t_user where username = ?  and password = ?";
        return queryForOne(User.class, sql, username, password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username, password,email) value(?,?,?)";
        return update(sql, user.getUsername(), user.getPassword(),user.getEmail());
    }
}
