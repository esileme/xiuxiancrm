<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();

	// 注意本页面有两种应用路径的解决方法：
	// 方法1：用JSTL的URL标签（需要下载JSTL的2个jar包，并在web.xml中配置，关于JSTL用一个学一个）。
	// 方法2：用request.getContextPath()获取并拼接到URL上。
	// 组织学生讨论：使用哪个方法比较好？

	// 总结：
	// 【JSTL】
	// <c:url> 可以帮助我们生成URL，不需要考虑应用路径问题
	// 【EL】
	// ${param.username}  相当于 《%= request.getParameter("username") %》 但是比getParameter更友好（会出现null）
	// ${requestScope.errorMessage}  相当于 《%= request.getAttribute("errorMessage") %》
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录 - 修仙管理系统</title>
<link rel="stylesheet" href="<%=basePath + "/css/index.css"%>">
</head>
<body>
	<div class="login">
		<img src="<c:url value="/image/logo.jpg" />" alt="修仙">
		<p>修仙管理系统</p>
		<form action="<c:url value="/user/login" />" method="post">
			<div>
				<input type="text" id="username" name="username"
					value="${param.username}" placeholder="请输入用户名">
			</div>
			<div>
				<input type="password" id="password" name="password"
					value="${param.password}" placeholder="请输入密码">
			</div>
			<div>
				<p class="error-message">${requestScope.errorMessage}</p>
				<input type="submit" value="开始修仙">
			</div>
		</form>
	</div>
	<script>
		if (window.top !== window) {
			window.top.location.reload();
		}
	</script>
</body>
</html>