<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
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
<title>商品管理</title>
</head>
<body>
		<%@include file="../nav.jsp"%>
		<div class="col-sm-10">
			<div class="bListTitle">商品管理</div>
			
			<div class="bListConent">
				<%@ include file="../pages/page1.file"%>
				<table class="table table-striped">
					<tr>
						<th style="width: 6%">編號</th>
						<th style="width: 39%">名稱</th>
						<th style="width: 12%">類別</th>
						<th style="width: 7%">價格</th>
						<th style="width: 9%">刊登人</th>
						<th style="width: 9%">狀態</th>
						<th style="width: 6%"></th>
						<th style="width: 6%"></th>
						<th style="width: 6%">
							<a class="btn btn-md btn-warning" href="<%=request.getContextPath()%>/back-end/front-web/productAdd.jsp?
								whichPage=${param.whichPage}&target=front" role="button">
								<span class="glyphicon glyphicon-plus"></span>新增
							</a>
						</th>
					</tr>
					
					<jsp:useBean id="product_classSvc" scope="page" class="com.product_class.model.Product_classService" />
					<jsp:useBean id="empSvc" scope="page" class="com.employee.model.EmployeeService" />
					<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr>
							<td>${productVO.prono}</td>
							<td>${productVO.proname}</td>
							<c:forEach var="product_classVO" items="${product_classSvc.all}">
								<c:if test="${product_classVO.classno == productVO.classno}">
									<td>${product_classVO.classname}</td>
								</c:if>
							</c:forEach>
							<td>${productVO.proprice}</td>
							<c:forEach var="empVO" items="${empSvc.all}">
								<c:if test="${empVO.empno == productVO.empno}">
									<td>${empVO.empname}</td>
								</c:if>
							</c:forEach>
							<td>
								<c:if test="${productVO.prostate == '1'}"><b>上架中</b></c:if> 
								<c:if test="${productVO.prostate == '0'}">已下架</c:if>
							</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-web/product.do">
									<c:if test="${productVO.prostate == '1'}">
										<button class="btn btn-info" role="button">
											<span class="glyphicon glyphicon-arrow-down"></span> 下架
										</button>
									</c:if>
									<c:if test="${productVO.prostate == '0'}">
										<button class="btn btn-primary" role="button">
											<span class="glyphicon glyphicon-arrow-up"></span> 上架
										</button>
									</c:if>
									<input type="hidden" name="whichPage" value="${param.whichPage}">
									<input type="hidden" name="prono" value="${productVO.prono}">
									<input type="hidden" name="prostate" value="${productVO.prostate}">
									<input type="hidden" name="action" value="changeState">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-web/product.do">
									<button class="btn btn-success" role="button">
										<span class="glyphicon glyphicon-pencil"></span> 修改
									</button>
									<input type="hidden" name="whichPage" value="${param.whichPage}">
									<input type="hidden" name="prono" value="${productVO.prono}">
									<input type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-web/product.do">
									<button class="btn btn-danger" role="button">
										<span class="glyphicon glyphicon-remove"></span> 刪除
									</button>
									<input type="hidden" name="prono" value="${productVO.prono}">
									<input type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="../pages/page2.file"%>
			</div>
			<div class="bFooter">sby318@gmail.com &copy; 2018 All rightsreserved.</div>
		</div>
	</div>
</div>
</body>
</html>