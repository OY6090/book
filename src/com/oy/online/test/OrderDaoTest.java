package com.oy.online.test;

import com.oy.online.dao.impl.OrderDaoImpl;
import com.oy.online.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDaoTest {

    @Test
    public void saveOrder(){
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderDao.saveOder(new Order("123456",new Date(), new BigDecimal(100),0,1));
    }
}
