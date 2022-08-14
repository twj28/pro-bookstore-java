package com.twj.book.service.impl;


import com.twj.book.dao.UserDAO;
import com.twj.book.pojo.User;
import com.twj.book.service.UserService;

/**
 * @ClassNmae UserImpl
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/16 0:37
 * @Version 1.0
 **/
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname,pwd);

    }

    @Override
    public void regist(User user) {
        userDAO.addUser(user);
    }

    @Override
    public User ckGetUser(User user) {
        return userDAO.ckGetUser(user);
    }
}
