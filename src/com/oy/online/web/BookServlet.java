package com.oy.online.web;

import com.oy.online.pojo.Book;
import com.oy.online.pojo.Page;
import com.oy.online.service.BookService;
import com.oy.online.service.impl.BookServiceImpl;
import com.oy.online.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();
    /**
     * 展示图书列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.通过BookService 查询全部图书
        List<Book> books = bookService.queryBooks();

        // 2.把全部的图画保存到Request 域中
        req.setAttribute("books", books);

        // 3、请求转发到/pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    /**
     *  添加图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 1.获取请求的参数==封住成为bean对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 2. 调用bookService.addService()保存图书
        bookService.addBook(book);

        // 3.重定向图书列表
//        System.out.println(req.getContextPath());
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");
    }

    /**
     * 删除图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 1.去取请求的参数 id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 2.调用bookService.deleteBookById();删除图书
        bookService.deleteBookById(id);
        // 3.重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");
    }

    /**
     * 根据id,查询图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 1.获取请求的参数图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 2.调用bookService.queryBookById查询图书
        Book book = bookService.queryBookById(id);
        // 3.保存到图书到Request 域中
        req.setAttribute("book",book);
        // 4.请求转发到 pages/manager/book_edit.jsp页面
       req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    /**
     * 修改图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 1. 获取请求的参数==封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 2.调用BookService.updateBook(books);修改图书
        bookService.updateBook(book);
        // 3、重定向回到列表管理页面
        // 4、地址： /工程名/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNO=" +req.getParameter("pageNO"));
    }

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNO"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2 调用BookService.page(pageNO, pageSize);  Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3 保存Page 对象到Requset 域中
        req.setAttribute("page", page);
        //4 请求转发到 /pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
