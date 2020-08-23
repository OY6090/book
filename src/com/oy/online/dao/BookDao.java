package com.oy.online.dao;

import com.oy.online.pojo.Book;

import java.util.List;

/**
*@Description
*@Author OY
*@Date 2020/8/15
*@Time 16:30
*/
public interface BookDao {
    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Integer queryForPageTotalCount();

    public List<Book> queryForPageItems(int begin, int pageSize);

    public Integer queryForPageTotalCountByPrice(int min, int max);

    public List<Book> queryForPageItems(int begin, int pagesize, int min, int max);

}
