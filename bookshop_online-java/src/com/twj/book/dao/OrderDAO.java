package com.twj.book.dao;

import com.twj.book.pojo.OrderBean;
import com.twj.book.pojo.User;

import java.util.List;

/**
 * @author twj28
 * @create 2022 2022/5/18 14:17
 */
public interface OrderDAO {
    //增加订单
    void addOrder(OrderBean orderBean);
    //获取订单
    List<OrderBean> getOrderList(User user, Integer pageNo);
    //获取总订单数
    Integer getTotalOrderCount(User user);
}
