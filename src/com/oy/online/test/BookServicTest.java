package com.oy.online.test;

import com.oy.online.pojo.Book;
import com.oy.online.pojo.Page;
import com.oy.online.service.BookService;
import com.oy.online.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BookServicTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook(){
        bookService.addBook(new Book(null, "java思想","小明", new BigDecimal(1200), 1000, 12, null));
    }

    @Test
    public void updateBook(){
        bookService.updateBook(new Book(22,"java思想与编程", "大名", new BigDecimal(100),12000, 1233, null ));
    }

    @Test
    public void queryBookbyId(){
        Book book = bookService.queryBookById(22);
        System.out.println(book);

    }

    @Test
    public void deleteById(){
        bookService.deleteBookById(22);
    }

    @Test
    public void queryBook(){
        List<Book> books = bookService.queryBooks();
        books.forEach(System.out::println);
    }

    @Test
    public void page(){
        Page<Book> page = bookService.page(1, 4);
        System.out.println(page);
    }
}
