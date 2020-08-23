package com.oy.online.service.impl;

import com.oy.online.dao.BookDao;
import com.oy.online.dao.impl.BookDaoImpl;
import com.oy.online.pojo.Book;
import com.oy.online.pojo.Page;
import com.oy.online.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService{
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public int deleteBookById(Integer id) {
        return bookDao.deleteBookById(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);

        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();

        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);

        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal +=1;
        }

        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页面
        page.setPageNO(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNO() - 1) * pageSize;
        // 求当前数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        
        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();

        // 设置每页显示数量
        page.setPageSize(pageSize);
        // 求总的记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal +=1;
        }
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNO(pageNo);

        // 求当前页面数据的开始的索引
        int begin = (page.getPageNO()- 1)*pageSize;
        // 求当前页数据
        List<Book> itmes = bookDao.queryForPageItems(begin, pageSize,min,max);

        page.setItems(itmes);
        return page;
    }
}
