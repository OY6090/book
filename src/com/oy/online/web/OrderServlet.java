package com.oy.online.web;

import com.oy.online.pojo.Cart;
import com.oy.online.pojo.User;
import com.oy.online.service.OrderService;
import com.oy.online.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取Cart 购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取Userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if(loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return ;
        }

        Integer userId = loginUser.getId();
        // 调用orderService.createOrder(Cart, Userid);生成订单
        String orderId = orderService.createOrder(cart,userId);

        req.getSession().setAttribute("orderId", orderId);

        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}
