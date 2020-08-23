package com.oy.online.web;

import com.google.gson.Gson;
import com.oy.online.pojo.User;
import com.oy.online.service.UserService;
import com.oy.online.service.impl.UserServiceImpl;
import com.oy.online.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
*@Description 处理登入和注册
*@Author OY
*@Date 2020/8/14
*@Time 17:18
*/
public class UserServlet extends BaseServlet{

    private UserService userService = new UserServiceImpl();

    /**
     *  处理登入的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 调用userService() 登录处理业务
        User login = userService.login(new User(null, username, password, null));
        // 如果等于null, 说明登录 失败
        if(login == null){
            // 把错误的信息，和回显的表单项信息，保存到Request 域中
            req.setAttribute("username", username);
            req.setAttribute("msg", "用户名或密码错误！！！");
            // 跳回登入界面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }else{
            // 登入成功
            req.getSession().setAttribute("user",login);
            // 跳到成功页面login_success.html
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);

        // 删除Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        // 1. 获取请求的我参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        // 2.检查 验证码是否正确
        if(token != null && token.equalsIgnoreCase(code)){
            // 3 检查 用户名是否可用
            if(userService.existsUsername(user.getUsername())){
                System.out.println("用户名[" + user.getUsername() + "]已存在!");

                // 把错误信息回显
                req.setAttribute("msg","用户名已存在！！！" );
                req.setAttribute("username", user.getUsername());
                req.setAttribute("email", user.getEmail());

                // 跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }else{
                // 调用Service 保存到数据库
                userService.registUser(new User(null, username, password, email));
                // 跳转到注册成功的页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        }else{
            // 把回显信息， 保存到Request 域中
            req.setAttribute("msg","验证码错误！！！");
            req.setAttribute("username", username);
            req.setAttribute("email",email);

            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 1. 注销Session 中的用户登录信息（或者销毁Session）
        req.getSession().invalidate();
        // 2. 重定项到首页（或登入页面）。
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * Ajax 处理注册用户检查
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取请求参数 username
        String username = req.getParameter("username");
//        System.out.println(username);
        // 调用 userService.existUsername();
        Boolean existsUsername = userService.existsUsername(username);

        // 把返回的结果封装成为map 对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        // 把Map集合转换为 json 字符串
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}
