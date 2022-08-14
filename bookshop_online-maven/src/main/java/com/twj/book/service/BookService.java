package com.twj.book.service;


import com.twj.book.pojo.Book;

import java.util.List;

/**
 * @author twj28
 * @create 2022 2022/5/16 15:42
 */
public interface BookService {
    List<Book> getBookList();
    Book getBook(Integer id);
    void updateBook(Book book,Integer count);
}
