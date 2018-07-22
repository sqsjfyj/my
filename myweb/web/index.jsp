<%--
  Created by IntelliJ IDEA.
  User: qwj
  Date: 2018/7/13
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h3>登陆成功</h3>
  <h3>欢迎访问</h3>
  <%
    String uri = request.getRequestURI();//返回请求行中的资源名称
    String url = request.getRequestURL().toString();//获得客户端发送请求的完整url
    String ip = request.getRemoteAddr();//返回发出请求的IP地址
    String params = request.getQueryString();//返回请求行中的参数部分
    String host=request.getRemoteHost();//返回发出请求的客户机的主机名
    int port =request.getRemotePort();//返回发出请求的客户机的端口号。
  %>
  <%
    Cookie c=new Cookie("user","SM");
    c.setMaxAge(6000);
    response.addCookie(c);
    response.sendRedirect("cookie.jsp");
  %>
  </body>
</html>
