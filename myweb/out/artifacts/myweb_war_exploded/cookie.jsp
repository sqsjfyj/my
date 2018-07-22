<%--
  Created by IntelliJ IDEA.
  User: qwj
  Date: 2018/7/13
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>从cookie.jsp页面中获取的信息</h3>
<%
    String ip=null;
    Cookie[] cookies=request.getCookies();
    for(int i=0;i<cookies.length;i++) {
        if(cookies[i].getName().equals("user")) {
            ip=cookies[i].getValue();
            break;
        }
    }
%>
ip:<%=ip %>
</body>
</html>
