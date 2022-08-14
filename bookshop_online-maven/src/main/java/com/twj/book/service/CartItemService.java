package com.twj.book.service;


import com.twj.book.pojo.Cart;
import com.twj.book.pojo.CartItem;
import com.twj.book.pojo.User;

import java.util.List;

/**
 * @author twj28
 * @create 2022 2022/5/16 19:25
 */
public interface CartItemService {

    //增加购物车项
    void addCartItem(CartItem cartItem);

    //更新购物车项
    void updateCartItem(CartItem cartItem);

    void addOrUpdateCartItem(CartItem cartItem, Cart cart);
    //根据用户获取购物车
    Cart getCart(User user);
    //获取购物车项list
    List<CartItem> getCartItemList(User user);

    //删除购物车项
     void delCartItem(CartItem cartItem);

}
