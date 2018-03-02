<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%
	ProductService productSvc = new ProductService();
	List<ProductVO> list = productSvc.getAllForShop();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE>
<html>
<head>
<title>所有商品瀏覽</title>
</head>
<body>
		<%@include file="nav.jsp"%>
		<div class="col-sm-10">
		
			<h2>商品瀏覽</h2>
		
			<c:forEach var="productVO" items="${list}">
				<div style="width: 25%; float: left;" class="work">
					<a href=<%=request.getContextPath()%>/front-web/product.do?prono=${productVO.prono}&action=view>
						<img src=<%=request.getContextPath()%>/DBJpgReader4?prono=${productVO.prono }>
						<!-- 灰色屏幕部分 -->
						<div class="info">
							<div class="whitebox" style="border-color: white; border-style: solid;">
								<h5><b>${productVO.proname}</b></h5>
								<h5><b>NT$:&nbsp;${productVO.proprice}.00</b></h5>
							</div>
						</div>
					</a>
				</div>
			</c:forEach>
			
		</div>
	</div>
</div>
</body>
</html>