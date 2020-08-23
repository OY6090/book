package com.oy.online.web;

import com.google.gson.Gson;
import com.oy.online.pojo.Book;
import com.oy.online.pojo.Cart;
import com.oy.online.pojo.CartItem;
import com.oy.online.service.BookService;
import com.oy.online.service.impl.BookServiceImpl;
import com.oy.online.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 加入购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        // 调用bookServer.queryBookById():Book得到图书的信息
        Book book = bookService.queryBookById(id);
        // 把图书信息，转为成为CartItem 商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        // 调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItems(cartItem);

//        System.out.println(cart);

//        System.out.println("请求头Referer的值："+ req.getHeader("Referer"));

        // 重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));

        req.getSession().setAttribute("lastName", cartItem.getName());
    }

    /**
     * 删除商品项
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            // 删除了购物车商品项
            cart.deleteItem(id);
            // 重定向回原来的购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            // 清空购物车
            cart.clear();
            // 重定向回原来的购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取请求参数、商品编号、商品数量
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        // 获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if(cart != null){
            // 修改商品数量
            cart.updateCount(id,count);
            // 重定向回原来的购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     *  AJAX 处理图书数据回显
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 调用 bookService.queryBookById(id):Book 得到图书的信息
        Book book = bookService.queryBookById(id);

        // 把图书信息，转化成为CartItem 商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        // 调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItems(cartItem);
        // 最后一个添加的商品名称
        req.getSession().setAttribute("lastName",cartItem.getName());

        // 6、返回购物车总的商品数量和最后一个添加的商品名称
        HashMap<String, Object> resultMap = new HashMap<>();

        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        // 把Map集合转为JSON
        String resultMapJsonString = gson.toJson(resultMap);
        resp.getWriter().write(resultMapJsonString);
    }
}
