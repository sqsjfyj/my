<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String path = request.getContextPath() + "/"; %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
</head>
<body>
	<s:fielderror cssStyle="color:red"></s:fielderror>
	<font color="red"><s:property value="msg" /></font>
	<form method="post" action="<%=path%>user/login">
		<table>
			<tr><th colspan="2">用户登录</th></tr>
			<tr><td align="right">用户名：</td>
			<td><input type="text" name="user.userName" value="${user.userName }"></td></tr>
			<tr><td align="right">密码：</td>
			<td><input type="password" name="user.userPassword"></td></tr>
			<tr><td align="left"><input type="submit" value="登录"></td>
			<td>未注册者，请先注册，单击<a href="<%=path%>user/register.jsp">注册</a></td></tr>
		</table>
	</form>
</body>
</html>