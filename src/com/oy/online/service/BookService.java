package com.oy.online.service;

import com.oy.online.pojo.Book;
import com.oy.online.pojo.Page;

import java.util.List;
/**
*@Description
*@Author OY
*@Date 2020/8/15
*@Time 17:03
*/
public interface BookService {
    /**
     * 添加book
     * @param book
     * @return
     */
    public int addBook(Book book);

    /**
     * 根据id删除book
     * @param id
     * @return
     */
    public int deleteBookById(Integer id);

    /**
     * 修改book
     * @param book
     * @return
     */
    public int updateBook(Book book);

    /**
     * 根据id 查询 book 信息
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查询所有的book信息
     * @return
     */
    public List<Book> queryBooks();


    /**
     * 处理分页业务
     */
    public Page<Book> page(int pageNo, int pageSize);

    /**
     * 根据图书价格查询图书信息
     * @param pageNo
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
