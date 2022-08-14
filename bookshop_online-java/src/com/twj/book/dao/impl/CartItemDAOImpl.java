package com.twj.book.dao.impl;


import com.twj.book.dao.CartItemDAO;
import com.twj.book.pojo.CartItem;
import com.twj.book.pojo.User;
import com.twj.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * @ClassNmae CartItemDAOImpl
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/16 19:35
 * @Version 1.0
 **/
public class CartItemDAOImpl extends BaseDAO<CartItem> implements CartItemDAO {
    @Override
    public void addCartItem(CartItem cartItem) {
        super.executeUpdate("insert into t_cart_item values(0,?,?,?)",cartItem.getBook().getId(),cartItem.getBuyCount(),cartItem.getUserBean().getId());
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        super.executeUpdate("update t_cart_item set buyCount = ? where id = ?",cartItem.getBuyCount(),cartItem.getId());
    }

    @Override
    public List<CartItem> getCartItem(User user) {
        return super.executeQuery("select * from t_cart_item where userBean = ?",user.getId());
    }

    @Override
    public void delCartItem(CartItem cartItem) {
        super.executeUpdate("delete from t_cart_item where id = ?",cartItem.getId());
    }
}
