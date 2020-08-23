package com.oy.online.test;

import com.oy.online.pojo.Cart;
import com.oy.online.pojo.CartItem;
import com.oy.online.service.OrderService;
import com.oy.online.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class createOrderTest {
    @Test
    public void createOrder(){
        Cart cart = new Cart();

        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItems(new CartItem(2, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));

        OrderService orderService = new OrderServiceImpl();

        System.out.println("订单号是："+ orderService.createOrder(cart,1));
    }
}
