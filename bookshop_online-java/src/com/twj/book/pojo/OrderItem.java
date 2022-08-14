package com.twj.book.pojo;

/**
 * @ClassNmae OrderItem
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/16 0:17
 * @Version 1.0
 **/
//订单详情
public class OrderItem {
    private Integer id;
    private Book book;//书的类型
    private Integer buyCount; //购买数量
    private OrderBean orderBean; //所属哪个订单

    public OrderItem() {
    }

    public OrderItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public OrderBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }
}
