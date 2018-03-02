<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%
	ProductService proSvc = new ProductService();
	List<ProductVO> list = proSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE>
<html>
<head>
<title>商品內文</title>
</head>
<body>
		<%@include file="nav.jsp"%>
		<div class="col-sm-10">
			<h2>商品資訊</h2>
			<div class="col-sm-7">
				<img src=<%=request.getContextPath()%>/DBJpgReader4?prono=${productVO.prono } style="width:100%; height:auto;">
			</div>
			<div class="col-sm-5">
				<h4>${productVO.proname }</h4>
				<h5>NT$:&nbsp;${productVO.proprice }</h5>
				<div>${productVO.prodesc}</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>