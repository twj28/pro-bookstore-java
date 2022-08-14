package com.twj.book.controller;

import com.twj.book.pojo.Book;
import com.twj.book.service.BookService;
import com.twj.book.service.OrderItemService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassNmae BookController
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/16 15:39
 * @Version 1.0
 **/
public class BookController {
    private BookService bookService;
    private OrderItemService orderItemService;
    public String index(HttpSession session){
        List<Book> bookList = bookService.getBookList();
        session.setAttribute("bookList",bookList);

        return "index";
    }
    /*
    public String getBookList(HttpSession session){
        User currUser = (User) session.getAttribute("currUser");
        OrderBean orderBean = currUser.getOrderBean();

        List<OrderItem> orderItemList = orderItemService.getOrderItemList(orderBean);
        for (OrderItem orderItem : orderItemList) {
            bookService.updateBook(orderItem.getBook(),orderItem.getBuyCount());
        }
        session.setAttribute("currUser",currUser);
        return "redirect:book.do";
    }*/
}
