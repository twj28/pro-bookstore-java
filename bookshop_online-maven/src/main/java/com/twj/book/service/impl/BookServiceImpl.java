package com.twj.book.service.impl;

import com.twj.book.dao.BookDAO;
import com.twj.book.pojo.Book;
import com.twj.book.service.BookService;

import java.util.List;

/**
 * @ClassNmae BookServiceImpl
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/16 15:44
 * @Version 1.0
 **/
public class BookServiceImpl implements BookService {
    private BookDAO bookDAO;

    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }

    @Override
    public Book getBook(Integer id) {
        return bookDAO.getBook(id);
    }

    @Override
    public void updateBook(Book book, Integer count) {
        bookDAO.updateBook(book,count);
    }
}
