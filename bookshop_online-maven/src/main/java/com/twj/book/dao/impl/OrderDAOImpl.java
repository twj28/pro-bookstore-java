package com.twj.book.dao.impl;

import com.twj.book.dao.OrderDAO;
import com.twj.book.pojo.OrderBean;
import com.twj.book.pojo.User;
import com.twj.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * @ClassNmae OrderDAOImpl
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/18 14:18
 * @Version 1.0
 **/
public class OrderDAOImpl extends BaseDAO<OrderBean> implements OrderDAO {
    @Override
    public void addOrder(OrderBean orderBean) {
        int orderBeanId = super.executeUpdate("insert into t_order values(0,?,?,?,?,?)", orderBean.getOrderNo(), orderBean.getOrderDate(), orderBean.getOrderUser().getId(), orderBean.getOrderMoney(), orderBean.getOrderStatus());
        orderBean.setId(orderBeanId);

    }

    @Override
    public List<OrderBean> getOrderList(User user, Integer pageNo) {
        return super.executeQuery("select * from t_order where orderUser = ? limit ?,7",user.getId(),7*(pageNo - 1));
    }

    @Override
    public Integer getTotalOrderCount(User user) {
        return ((Long)super.executeComplexQuery("select count(*) from t_order where orderUser = ?",user.getId())[0]).intValue();
    }
}
