package com.twj.book.service;

import com.twj.book.pojo.OrderBean;
import com.twj.book.pojo.User;

import java.util.List;

/**
 * @author twj28
 * @create 2022 2022/5/17 21:10
 */
public interface OrderService {
    //增加一个订单
    void addOrderBean(OrderBean orderBean);
    //获取所有的订单
    List<OrderBean> getOrderList(User user, Integer pageNo);

    //获取订单总数
    Integer getTotalOrderCount(User user);
    //获取总页数
    Integer getTotalPageNo(User user);


}
