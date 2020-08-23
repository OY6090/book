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

public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页的操作
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
        page.setUrl("client/bookServlet?action=page");
        //3 保存Page 对象到Requset 域中
        req.setAttribute("page", page);
        //4 请求转发到 /pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    /**
     *  处理价格之间的分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 1. 获取请求的参数 pageNO, pageSize, min, max
        int pageNO = WebUtils.parseInt(req.getParameter("pageNO"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"),0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        // 2.调用bookService.pageByPrice(pageNo, pageSize, min, max); Page对象
        Page<Book> page = bookService.pageByPrice(pageNO, pageSize, min, max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        // 如果有最小值的参数，追加到分页的地址参数
        if(req.getParameter("min")!= null){
            sb.append("&min=").append(req.getParameter("min"));
        }

        // 如果有最大价格参数，追加到分页条的地址参数中
        if(req.getParameter("max") != null){
            sb.append("&max=").append(req.getParameter("max"));
        }

        page.setUrl(sb.toString());
        // 3.保存Page 对象到Request域中
        req.setAttribute("page", page);
        // 4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
