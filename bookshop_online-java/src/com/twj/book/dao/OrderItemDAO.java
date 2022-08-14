package com.twj.book.dao;

import com.twj.book.pojo.OrderBean;
import com.twj.book.pojo.OrderItem;

import java.util.List;

/**
 * @author twj28
 * @create 2022 2022/5/18 14:02
 */
public interface OrderItemDAO {
    //增加订单项目
    void addOrderItem(OrderItem orderItem);

    List<OrderItem> getOrderItemList(OrderBean orderBean);

    Integer getBuyCount(OrderBean orderBean);
}
