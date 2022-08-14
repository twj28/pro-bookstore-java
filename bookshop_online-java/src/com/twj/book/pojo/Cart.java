package com.twj.book.pojo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassNmae Cart
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/16 20:18
 * @Version 1.0
 **/
public class Cart {
    private Map<Integer,CartItem> cartItemMap = new HashMap<>();//Integer的key指book的id,
    private Double totalMoney;//购物车总共的价格
    private Integer totalCount;//购物车项目数

    public Integer getTotalBookCount() {
        totalBookCount = 0;
        if(cartItemMap != null && cartItemMap.size() > 0){
            for (CartItem cartItem : cartItemMap.values()) {
               totalBookCount = totalBookCount + cartItem.getBuyCount();
            }
        }
        return totalBookCount;
    }

    private Integer totalBookCount;

    public Cart() {
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {
        totalMoney = 0.0;
        BigDecimal bigDecimalTotalMoney = new BigDecimal(totalMoney);
        Set<Map.Entry<Integer, CartItem>> cartItemEntries = cartItemMap.entrySet();
        for (Map.Entry<Integer, CartItem> cartItemEntry : cartItemEntries) {
            CartItem cartItemEntryValue = cartItemEntry.getValue();
            BigDecimal bigDecimalPrice = new BigDecimal(cartItemEntryValue.getBook().getPrice() + "");
            BigDecimal bigDecimalBuyCount = new BigDecimal(cartItemEntryValue.getBuyCount() + "");
            bigDecimalTotalMoney = bigDecimalTotalMoney.add(bigDecimalPrice.multiply(bigDecimalBuyCount));

        }
        return totalMoney = bigDecimalTotalMoney.doubleValue();
    }

    public Integer getTotalCount() {
        totalCount = 0;
        if(cartItemMap != null && cartItemMap.size() > 0){
        totalCount = cartItemMap.size();
        }
        return totalCount;
    }
}
