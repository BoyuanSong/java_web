<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.emp_permission.model.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>
<title>���u�v���ק�</title>
</head>
<body>
		<%@include file="../nav.jsp"%>
		<div class="col-sm-10">
			<div class="bListTitle">���u�v���ק�</div>
			
			<div class="bListConent">
				<h3>���u:&nbsp;${empname}</h3>
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/employee/emp_permission.do" style="margin-top:20px">
			
					<h4>�G�i��:</h4>
					<c:if test="${fn:contains(perm, 'B01')}">
						<input type="checkbox" name="perm" id= "B01" value="B01" checked>
						<label for="B01">�G�i��i�K</label>
					</c:if>
					<c:if test="${not fn:contains(perm, 'B01')}">
						<input type="checkbox" name="perm" id= "B01" value="B01">
						<label for="B01">�G�i��i�K</label>
					</c:if>
					
					<h4>���u��ƺ޲z:</h4>
					<c:if test="${fn:contains(perm, 'E01')}">
						<input type="checkbox" name="perm" id= "E01" value="E01" checked>
						<label for="E01">���u�޲z</label>
					</c:if>
					<c:if test="${not fn:contains(perm, 'E01')}">
						<input type="checkbox" name="perm" id= "E01" value="E01">
						<label for="E01">���u�޲z</label>
					</c:if>
					
					<h4>�e�ݺ����޲z:</h4>
					<c:if test="${fn:contains(perm, 'F01')}">
						<input type="checkbox" name="perm" id= "F01" value="F01" checked>
						<label for="F01">�����޲z&nbsp;</label>
					</c:if>
					<c:if test="${not fn:contains(perm, 'F01')}">
						<input type="checkbox" name="perm" id= "F01" value="F01">
						<label for="F01">�����޲z&nbsp;</label>
					</c:if>
			
					<c:if test="${fn:contains(perm, 'F02')}">
						<input type="checkbox" name="perm" id= "F02" value="F02" checked>
						<label for="F02">�ӫ~�޲z&nbsp;</label>
					</c:if>
					<c:if test="${not fn:contains(perm, 'F02')}">
						<input type="checkbox" name="perm" id= "F02" value="F02">
						<label for="F02">�ӫ~�޲z&nbsp;</label>
					</c:if>
					
					<input type="hidden" name="action" value="changePerm"> 
					<input type="hidden" name="empno" value="${empno}">
					<input type="hidden" name="empname" value="${empname}">
					<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>"> 
					<input type="submit" value="�ק�" class="btn btn-default">
				</FORM>
			</div>
			<div class="bFooter">lst448@hotmail.com &copy; 2018 All rightsreserved.</div>
		</div>
	</div>
</div>
</body>
</html>