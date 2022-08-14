package com.twj.book.service.impl;


import com.twj.book.dao.CartItemDAO;
import com.twj.book.pojo.Book;
import com.twj.book.pojo.Cart;
import com.twj.book.pojo.CartItem;
import com.twj.book.pojo.User;
import com.twj.book.service.BookService;
import com.twj.book.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassNmae CartItemServiceImpl
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/16 19:32
 * @Version 1.0
 **/
public class CartItemServiceImpl implements CartItemService {
    private CartItemDAO cartItemDAO;
    private BookService bookService;


    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDAO.updateCartItem(cartItem);
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        //购物车不为空
        if (cart != null) {
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if (cartItemMap == null) {
                cartItemMap = new HashMap<>();
            }
            //购物车项 的Map集合中存在 该图书
            if (cartItemMap.containsKey(cartItem.getBook().getId())) {
                //获得当前购物车中 该书的购物车项
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount() + 1);
                //更新购物车项目
                updateCartItem(cartItemTemp);
            } else {
                //增加购物车项
                addCartItem(cartItem);
            }
        } else {
            addCartItem(cartItem);
        }
    }

    @Override
    public Cart getCart(User user) {
        //调用自身的getCartItemList，其中给book已经赋予详细信息
        List<CartItem> cartItemList = getCartItemList(user);

        Map<Integer, CartItem> cartItemMap = new HashMap<>();
        for (CartItem cartItem : cartItemList) {
            cartItemMap.put(cartItem.getBook().getId(),cartItem);
        }
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);
        return cart;
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        List<CartItem> cartItemList = cartItemDAO.getCartItem(user);
        for (CartItem cartItem : cartItemList) {
            Book book = bookService.getBook(cartItem.getBook().getId());
            cartItem.setBook(book);
            cartItem.getXj();
        }
        return cartItemList;
    }

    @Override
    public void delCartItem(CartItem cartItem) {
        cartItemDAO.delCartItem(cartItem);
    }

}
