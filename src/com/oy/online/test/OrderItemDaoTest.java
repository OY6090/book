package com.oy.online.test;

import com.oy.online.dao.impl.OrderItemDaoImpl;
import com.oy.online.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemDaoTest {
    @Test
    public void saveOderItem(){
        OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();

        orderItemDao.saveOrderItem(new OrderItem(null, "java从入门到精通",1,new BigDecimal(100), new BigDecimal(100),"123456"));
        orderItemDao.saveOrderItem(new OrderItem(null, "java从入门到精通",2,new BigDecimal(100), new BigDecimal(200),"123456"));
        orderItemDao.saveOrderItem(new OrderItem(null, "java从入门到精通",3,new BigDecimal(100), new BigDecimal(300),"123456"));
    }
}

