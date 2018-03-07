<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.emp_permission.model.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    List<String> perm = (List<String>)request.getAttribute("perm");
%>
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
					<input type="checkbox" name="perm" id= "B01" value="B01" <%if(perm.contains("B01")){%>checked<%}%>>
					<label for="B01">佈告欄張貼</label>
					
					<h4>員工資料管理:</h4>
					<input type="checkbox" name="perm" id= "E01" value="E01" <%if(perm.contains("E01")){%>checked<%}%>>
					<label for="E01">員工管理</label>
					
					<h4>前端網站管理:</h4>
					<input type="checkbox" name="perm" id= "F01" value="F01" <%if(perm.contains("F01")){%>checked<%}%>>
					<label for="F01">消息管理&nbsp;</label>
					<input type="checkbox" name="perm" id= "F02" value="F02" <%if(perm.contains("F02")){%>checked<%}%>>
					<label for="F02">商品管理&nbsp;</label>
					
				    <input type="hidden" name="action" value="changePerm"> 
				    <input type="hidden" name="empno" value="${empno}">
					<input type="hidden" name="empname" value="${empname}">
					<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>"> 
					<input type="submit" value="修改" class="btn btn-default">
				</FORM>
			</div>
			<div class="bFooter">sby318@gmail.com &copy; 2018 All rightsreserved.</div>
		</div>
	</div>
</div>
</body>
</html>