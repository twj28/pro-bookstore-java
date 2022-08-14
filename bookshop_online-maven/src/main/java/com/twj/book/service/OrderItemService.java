package com.twj.book.service;

import com.twj.book.pojo.OrderBean;
import com.twj.book.pojo.OrderItem;

import java.util.List;

/**
 * @author twj28
 * @create 2022 2022/5/18 15:04
 */
public interface OrderItemService {
    void addOrderItem(OrderItem orderItem);
    //通过orderBean获取orderItemList
    List<OrderItem> getOrderItemList(OrderBean orderBean);

    Integer getBuyCount(OrderBean orderBean);
}
