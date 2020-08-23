package com.oy.online.test;

import com.oy.online.pojo.Cart;
import com.oy.online.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {

    @Test
    public void addItem(){
        Cart cart = new Cart();

        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItems(new CartItem(2, "java从入门到精通", 1, new BigDecimal(100),new BigDecimal(100)));

        System.out.println(cart);
    }

    @Test
    public void deleteItem(){
        Cart cart = new Cart();

        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItems(new CartItem(2, "java从入门到精通", 1, new BigDecimal(100),new BigDecimal(100)));

        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear(){
        Cart cart = new Cart();

        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItems(new CartItem(2, "java从入门到精通", 1, new BigDecimal(100),new BigDecimal(100)));

        cart.deleteItem(1);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount(){
        Cart cart = new Cart();

        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItems(new CartItem(2, "java从入门到精通", 1, new BigDecimal(100),new BigDecimal(100)));

        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.updateCount(1,10);
        System.out.println(cart);
    }
}
