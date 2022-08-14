package com.twj.book.pojo;

import java.util.Date;
import java.util.List;

/**
 * @ClassNmae OrderBean
 * @Description 订单
 * @Author twj280
 * @Date 2022/5/16 0:02
 * @Version 1.0
 **/
//订单类
public class OrderBean {
    private Integer id;
    private String orderNo;//订单编号
    private Date orderDate;//订单日期
    private User orderUser;//订单所属用户
    private Double orderMoney;//订单价格
    private  Integer orderStatus;//订单状态
    private Integer orderCount;

    private List<OrderItem> orderItemList;//订单有多个订单详情

    public OrderBean() {
    }

    public OrderBean(String orderNo, Date orderDate, User orderUser, Double orderMoney, Integer orderStatus) {
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.orderUser = orderUser;
        this.orderMoney = orderMoney;
        this.orderStatus = orderStatus;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public OrderBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public User getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(User orderUser) {
        this.orderUser = orderUser;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
