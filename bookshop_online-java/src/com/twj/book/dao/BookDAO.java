package com.twj.book.dao;


import com.twj.book.pojo.Book;

import java.util.List;

/**
 * @author twj28
 * @create 2022 2022/5/16 15:45
 */
public interface BookDAO {
    //获取bookList对象
    List<Book> getBookList();
    //获取单个book对象
    Book getBook(Integer id);
    //更新库存和销量
    void updateBook(Book book,Integer count);
}
