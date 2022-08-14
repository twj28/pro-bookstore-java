package com.twj.book.service.impl;


import com.twj.book.dao.OrderItemDAO;
import com.twj.book.pojo.OrderBean;
import com.twj.book.pojo.OrderItem;
import com.twj.book.service.OrderItemService;

import java.util.List;

/**
 * @ClassNmae OrderItemServiceImp
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/18 15:05
 * @Version 1.0
 **/
public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDAO orderItemDAO;
    @Override
    public void addOrderItem(OrderItem orderItem) {
        orderItemDAO.addOrderItem(orderItem);
    }

    @Override
    public List<OrderItem> getOrderItemList(OrderBean orderBean) {
        return orderItemDAO.getOrderItemList(orderBean);
    }

    @Override
    public Integer getBuyCount(OrderBean orderBean) {
        return orderItemDAO.getBuyCount(orderBean);
    }
}
