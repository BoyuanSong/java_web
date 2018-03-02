<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp_permission.model.*"%>
<%@ page import="com.employee.model.*"%>
<!DOCTYPE>
<html>
<head>
<title>online-shop</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/whitebox.css">
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<style>
li {
margin-top:10px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-2" style="margin-top:50px">
				<a href="<%=request.getContextPath()%>/front-end/web/washida_about.html">
					<img src="<%=request.getContextPath()%>/images/shoplogo.jpg"></img>
				</a>
				<nav>
					<ul style="margin-top: 30px;list-style-type: none;">
						<li><a href="<%=request.getContextPath()%>/front-end/shop/productAll.jsp">所有服飾</a></li>
						<li><a href="<%=request.getContextPath()%>/front-end/shop/productClass.jsp?classno=000001">外套</a></li>
						<li><a href="<%=request.getContextPath()%>/front-end/shop/productClass.jsp?classno=000002">長袖上衣</a></li>
						<li><a href="<%=request.getContextPath()%>/front-end/shop/productClass.jsp?classno=000003">短袖上衣</a></li>
						<li><a href="<%=request.getContextPath()%>/front-end/shop/productClass.jsp?classno=000004">長褲</a></li>
						<li><a href="<%=request.getContextPath()%>/front-end/shop/productClass.jsp?classno=000005">短褲</a></li>
					</ul>
				</nav>
			</div>