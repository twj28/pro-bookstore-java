package com.twj.book.dao.impl;
import com.twj.book.dao.OrderItemDAO;
import com.twj.book.pojo.OrderBean;
import com.twj.book.pojo.OrderItem;
import com.twj.myssm.basedao.BaseDAO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassNmae OrderItemImpl
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/18 14:05
 * @Version 1.0
 **/
public class OrderItemImpl extends BaseDAO<OrderItem> implements OrderItemDAO {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        super.executeUpdate("insert into t_order_item values(0,?,?,?)",orderItem.getBook().getId(),orderItem.getBuyCount(),orderItem.getOrderBean().getId());
    }

    @Override
    public List<OrderItem> getOrderItemList(OrderBean orderBean) {
        return super.executeQuery("select * from t_order_item where orderBean = ?",orderBean.getId());
    }

    @Override
    public Integer getBuyCount(OrderBean orderBean) {
        return ((BigDecimal)super.executeComplexQuery("SELECT SUM(buyCount) FROM t_order_item WHERE orderBean = ? GROUP BY orderBean",orderBean.getId())[0]).intValue();
    }
}
