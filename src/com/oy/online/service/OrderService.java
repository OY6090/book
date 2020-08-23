package com.oy.online.service;

import com.oy.online.pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart, Integer username);
}
