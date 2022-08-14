package com.twj.book.service.impl;


import com.twj.book.dao.CartItemDAO;
import com.twj.book.dao.OrderDAO;
import com.twj.book.dao.OrderItemDAO;
import com.twj.book.pojo.CartItem;
import com.twj.book.pojo.OrderBean;
import com.twj.book.pojo.OrderItem;
import com.twj.book.pojo.User;
import com.twj.book.service.BookService;
import com.twj.book.service.OrderService;

import java.util.List;
import java.util.Map;

/**
 * @ClassNmae OrderServiceImpl
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/18 15:00
 * @Version 1.0
 **/
public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;
    private CartItemDAO cartItemDAO;
    private BookService bookService;

    @Override
    public void addOrderBean(OrderBean orderBean) {
        //1.订单项增加一条记录
        //2.订单详情增加记录
        //3.购物车删除记录

        //第一步添加一个订单
        orderDAO.addOrder(orderBean);

        /*
        //第二步：向orderItem中添加记录
        Map<Integer, CartItem> cartItemMap1 = user.getCart().getCartItemMap();
        List<OrderItem> orderItemList = orderBean.getOrderItemList();
        for (OrderItem orderItem : orderItemList) {
            orderItemDAO.addOrderItem(orderItem);
        }
        //第三步：删除购物车项记录
        */

        //第二步：向orderItem中添加记录
        User currUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = currUser.getCart().getCartItemMap();
        for (CartItem cartItem : cartItemMap.values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDAO.addOrderItem(orderItem);
            //更新图书的销量和库存
            bookService.updateBook(orderItem.getBook(),orderItem.getBuyCount());
        }

        //第三步：删除购物车项记录
        for (CartItem cartItem : cartItemMap.values()) {
            cartItemDAO.delCartItem(cartItem);
        }
    }

    @Override
    public List<OrderBean> getOrderList(User user, Integer pageNo) {
        return orderDAO.getOrderList(user,pageNo);
    }

    @Override
    public Integer getTotalOrderCount(User user) {
        return orderDAO.getTotalOrderCount(user);
    }

    @Override
    public Integer getTotalPageNo(User user) {
        Integer totalOrderCount = getTotalOrderCount(user);
//        Integer.valueOf(((int)Math.ceil(totalOrderCount / 7.0)))
        return (int)Math.ceil(totalOrderCount / 7.0);
    }
}
