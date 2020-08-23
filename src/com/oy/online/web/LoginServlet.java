package com.oy.online.web;

import com.oy.online.pojo.User;
import com.oy.online.service.UserService;
import com.oy.online.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 调用 userService.login() 登录处理业务
        User loginUser = userService.login(new User(null, username, password, null));

        if(loginUser == null){
            // 把错误信息回显给表当项信息，保存到Request域中
            req.setAttribute("msg", "用户名或密码输入错误");
            req.setAttribute("username", username);
            // 跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }else{
            // 登入成功
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }
}
