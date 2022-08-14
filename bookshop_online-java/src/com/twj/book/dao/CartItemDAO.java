package com.twj.book.dao;

import com.twj.book.pojo.CartItem;
import com.twj.book.pojo.User;

import java.util.List;

/**
 * @author twj28
 * @create 2022 2022/5/16 19:34
 */
public interface CartItemDAO {
    //增加购物车项
    void addCartItem(CartItem cartItem);
    //更新购物车项
    void updateCartItem(CartItem cartItem);
    //根据指定用户获取购物车项
    List<CartItem> getCartItem(User user);

    //删除购物车项
    void delCartItem(CartItem cartItem);
}
