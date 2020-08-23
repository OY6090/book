package com.oy.online.dao.impl;

import com.oy.online.dao.BaseDao;
import com.oy.online.dao.OrderDao;
import com.oy.online.pojo.Order;


public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOder(Order order) {
        String sql = "insert into t_order(order_id, create_time, price, status, user_id) value(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
