package com.twj.book.pojo;

import java.math.BigDecimal;

/**
 * @ClassNmae CartItem
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/16 0:08
 * @Version 1.0
 **/
//购物车项 类
public class CartItem {
    private Integer id;
    private Book book;//书类型 哪本书
    private Integer buyCount;//数量 买了多少书
    private User userBean;//所属用户
    private Double xj;

    public Double getXj() {
        BigDecimal bigDecimalPrice = new BigDecimal(getBook().getPrice() + "");
        BigDecimal bigDecimalBuyCount = new BigDecimal(buyCount + "");
        xj = bigDecimalPrice.multiply(bigDecimalBuyCount).doubleValue();
        return xj;
    }

    public void setXj(Double xj) {
        this.xj = xj;
    }

    public CartItem() {
    }

    public CartItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public CartItem(Book book, Integer buyCount, User userBean) {
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
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

    public User getUserBean() {
        return userBean;
    }

    public void setUserBean(User userBean) {
        this.userBean = userBean;
    }

}
