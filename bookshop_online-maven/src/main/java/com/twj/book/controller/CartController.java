package com.twj.book.controller;


import com.google.gson.Gson;
import com.twj.book.pojo.Book;
import com.twj.book.pojo.Cart;
import com.twj.book.pojo.CartItem;
import com.twj.book.pojo.User;
import com.twj.book.service.CartItemService;

import javax.servlet.http.HttpSession;

/**
 * @ClassNmae CartController
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/16 19:16
 * @Version 1.0
 **/
public class CartController {
    private CartItemService cartItemService;

    public String addCart(Integer bookId, HttpSession session) {
        //将指定的图书添加到当前用户的购物车中
        //1.如果当前用户的购物车中该图书存在，购物车中这图书项 更新，即数量+1
        //2.如果图书不存在，在我的购物车中新增图书这个购物车项CartItem，数量为1

        User currUser = (User) session.getAttribute("currUser");

        CartItem cartItem = new CartItem(new Book(bookId), 1, currUser);
        Cart cart = currUser.getCart();
        cartItemService.addOrUpdateCartItem(cartItem, cart);
        return "redirect:cart.do";

    }
    public String index(HttpSession session){
        User currUser = (User) session.getAttribute("currUser");
        if (currUser != null){
        Cart cart = cartItemService.getCart(currUser);
        currUser.setCart(cart);
        session.setAttribute("currUser",currUser);
        return "cart/cart";
        }
        return "redirect:user.do?operate=regist";
    }


   /* public String cartShow(HttpSession session){
        User currUser = (User) session.getAttribute("currUser");
        if(currUser != null){
            List<CartItem> cartItemList = cartItemService.getCartItemList(currUser);
            session.setAttribute("cartItemList",cartItemList);
        }
        return "cart/cart";
    }*/
    public String editCart(Integer cartItemId,Integer buyCount){
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemId);
        cartItem.setBuyCount(buyCount);
        cartItemService.updateCartItem(cartItem);
        return "";
    }
    public String cartInfo(HttpSession session){
        User currUser = (User) session.getAttribute("currUser");
        if (currUser != null){
            Cart cart = cartItemService.getCart(currUser);
            cart.getTotalMoney();
            cart.getTotalBookCount();
            cart.getTotalCount();
            Gson gson = new Gson();
            String cartJson = gson.toJson(cart);
            return "json:" + cartJson;
        }
        return "";
    }
}
