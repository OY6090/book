package com.oy.online.test;

import com.oy.online.dao.BookDao;
import com.oy.online.dao.impl.BookDaoImpl;
import com.oy.online.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
*@Description
*@Author OY
*@Date 2020/8/15
*@Time 16:50
*/
public class BookTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook(){
        bookDao.addBook(new Book(null, "java思想","小明", new BigDecimal(9999), 100000,0,null));
    }

    @Test
    public void deleteBookByid(){
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook(){
        bookDao.updateBook(new Book(21, "java编程与思想", "小明",new BigDecimal(100 ), 10000, 12,null));
    }

    @Test
    public void queryBooks(){
        List<Book> books = bookDao.queryBooks();
        books.forEach(System.out::println);
    }

    @Test
    public void queryBookById(){
        Book book = bookDao.queryBookById(20);
        System.out.println(book);
    }

    @Test
    public void queryForPageItems(){
        List<Book> books = bookDao.queryForPageItems(8, 4);
        books.forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCountByPrice(){
        Integer integer = bookDao.queryForPageTotalCountByPrice(100, 120);
        System.out.println(integer);
    }

    @Test
    public void queryForPageItemss(){
        List<Book> books = bookDao.queryForPageItems(0, 4, 100, 120);
        books.forEach(System.out::println);
    }
}

