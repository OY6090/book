<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2020/8/13
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- http://localhost:8080/book/
request.getScheme(): http 或 htpps
request.getServerName(): 服务器的名字
request.getServerPort(): 服务器端口号
request.getContextPath: /book 返回当前页面所在的应用的名字
--%>
<%
    String basePath = request.getScheme()
    + "://"
    + request.getServerName()
    + ":"
    + request.getServerPort()
    + request.getContextPath()
    +"/";
    pageContext.setAttribute("basePath", basePath);
%>
<!--写在base标签，永远固定相当路径跳转的结果 -->
<base href="<%= basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
