package com.twj.book.controller;


import com.twj.book.pojo.Cart;
import com.twj.book.pojo.User;
import com.twj.book.service.CartItemService;
import com.twj.book.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassNmae UserController
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/16 0:24
 * @Version 1.0
 **/
public class UserController {
    private UserService userService;
    private CartItemService cartItemService;
    public String login(String uname, String pwd, HttpSession session){
        User currUser = userService.login(uname, pwd);
        if (currUser != null){
            Cart cart = cartItemService.getCart(currUser);
            currUser.setCart(cart);
            session.setAttribute("currUser",currUser);
        return "redirect:book.do?operate=index";
        }
        return "user/login";
    }
    public String logout(HttpSession session){
        User currUser = (User) session.getAttribute("currUser");
        currUser=null;
        session.setAttribute("currUser",currUser);

        return "redirect:book.do?operate=index";

//        return "user/login";
    }
    public String regist(String verifyCode , String uname, String pwd, String email, HttpSession session, HttpServletResponse response) throws IOException {
        String kaptcha_session_key = (String) session.getAttribute("KAPTCHA_SESSION_KEY");
        if (kaptcha_session_key == null || !kaptcha_session_key.equals(verifyCode)){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer =  response.getWriter();
//            writer.println("<script language='javascript'>alert('验证码不正确！');window.location.href='page.do?operate=page&page=user/regist';</script>");
            writer.println("<script language='javascript'>alert('验证码不正确！');</script>");
            return "user/regist";
        }
        if (kaptcha_session_key.equals(verifyCode)) {
            userService.regist(new User(uname, pwd, email, 0));
            return "user/regist_success";
        }
        return null;
    }
    public String ckName(String uname){
        User ckUser = userService.ckGetUser(new User(uname));
        if (ckUser == null){
            //可以注册
            return "json:{'uname':'0'}";
        }else {
            //已存在用户名，不可以注册
            return "json:{'uname':'1'}";
        }
    }
}
