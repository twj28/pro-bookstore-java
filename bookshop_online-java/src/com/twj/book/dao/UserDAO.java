package com.twj.book.dao;


import com.twj.book.pojo.User;

/**
 * @author twj28
 * @create 2022 2022/5/16 0:39
 */
public interface UserDAO {
    //获取user对象
    User getUser(String uname, String pwd);
    //添加
    void addUser(User user);
    //查询User
    User ckGetUser(User user);
}
