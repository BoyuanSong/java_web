<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.emp_permission.model.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>
<title>員工權限修改</title>
</head>
<body>
		<%@include file="../nav.jsp"%>
		<div class="col-sm-10">
			<div class="bListTitle">員工權限修改</div>
			
			<div class="bListConent">
				<h3>員工:&nbsp;${empname}</h3>
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/employee/emp_permission.do" style="margin-top:20px">
			
					<h4>佈告欄:</h4>
					<c:if test="${fn:contains(perm, 'B01')}">
						<input type="checkbox" name="perm" id= "B01" value="B01" checked>
						<label for="B01">佈告欄張貼</label>
					</c:if>
					<c:if test="${not fn:contains(perm, 'B01')}">
						<input type="checkbox" name="perm" id= "B01" value="B01">
						<label for="B01">佈告欄張貼</label>
					</c:if>
					
					<h4>員工資料管理:</h4>
					<c:if test="${fn:contains(perm, 'E01')}">
						<input type="checkbox" name="perm" id= "E01" value="E01" checked>
						<label for="E01">員工管理</label>
					</c:if>
					<c:if test="${not fn:contains(perm, 'E01')}">
						<input type="checkbox" name="perm" id= "E01" value="E01">
						<label for="E01">員工管理</label>
					</c:if>
					
					<h4>前端網站管理:</h4>
					<c:if test="${fn:contains(perm, 'F01')}">
						<input type="checkbox" name="perm" id= "F01" value="F01" checked>
						<label for="F01">消息管理&nbsp;</label>
					</c:if>
					<c:if test="${not fn:contains(perm, 'F01')}">
						<input type="checkbox" name="perm" id= "F01" value="F01">
						<label for="F01">消息管理&nbsp;</label>
					</c:if>
			
					<c:if test="${fn:contains(perm, 'F02')}">
						<input type="checkbox" name="perm" id= "F02" value="F02" checked>
						<label for="F02">商品管理&nbsp;</label>
					</c:if>
					<c:if test="${not fn:contains(perm, 'F02')}">
						<input type="checkbox" name="perm" id= "F02" value="F02">
						<label for="F02">商品管理&nbsp;</label>
					</c:if>
					
					<input type="hidden" name="action" value="changePerm"> 
					<input type="hidden" name="empno" value="${empno}">
					<input type="hidden" name="empname" value="${empname}">
					<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>"> 
					<input type="submit" value="修改" class="btn btn-default">
				</FORM>
			</div>
			<div class="bFooter">lst448@hotmail.com &copy; 2018 All rightsreserved.</div>
		</div>
	</div>
</div>
</body>
</html>