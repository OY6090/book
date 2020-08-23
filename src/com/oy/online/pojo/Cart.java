package com.oy.online.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
*@Description 购物车对象
*@Author OY
*@Date 2020/8/19
*@Time 17:53
*/
public class Cart {
    /**
     * key 是商品变编号
     * value 是商品信息
     */
    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    public void addItems(CartItem cartItem){
        // 先查看购物车中是否添加过此商品，如果已添加，则数量累加，总金额更新，如果没有添加过，直接放到集合中即可
        CartItem item = items.get(cartItem.getId());

        if(item == null){
            // 之前没添加过此商品
            items.put(cartItem.getId(),cartItem);
        }else{
            // 已经添加过的情况
            item.setCount(item.getCount() +1);
            // 更新总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除商品项
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    public void clear(){
        items.clear();
    }

    /**
     * 先查看购物车中是否有此商品。如果有修改商品数量，更新总金额
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count){
        // 先查看购物车中是否有此商品。如果有，修改商品数量，更新总金额
        CartItem cartItem = items.get(id);
        if(cartItem != null){
            // 修改商品数量
           cartItem.setCount(count);
           // 更新总金额
           cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public Integer getTotalCount(){
        Integer totalCount = 0;
        for(Map.Entry<Integer, CartItem> entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice(){
        BigDecimal totalprice = new BigDecimal(0);

        for(Map.Entry<Integer, CartItem> entry : items.entrySet()){
            totalprice = totalprice.add(entry.getValue().getTotalPrice());
        }
        return totalprice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }
    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
