<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.msgbroad.model.*"%>
<%
List<MsgbroadVO> list = (List<MsgbroadVO>)session.getAttribute("list");
%>
<!DOCTYPE>
<html>
<head>
<title>文章搜索</title>
</head>
<body>
		<%@include file="../nav.jsp"%>
		<div class="col-sm-10">
			<div class="bListTitle">搜尋結果</div>
			<div class="bListConent">
				<%@ include file="../pages/page1.file"%>
				<table class="table">
					<tr>
						<th>編號</th>
						<th>日期</th>
						<th>作者</th>
						<th>標題</th>
					</tr>
					
					<c:forEach var="msgbroadVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					
						<tr class="msgtitle">
							<td>${msgbroadVO.msgno}</td>
							<td>${msgbroadVO.day}</td>
							
							<jsp:useBean id="employeeSvc" scope="page" class="com.employee.model.EmployeeService" />
							<td>
								<c:forEach var="employeeVO" items="${employeeSvc.all}">
									<c:if test="${msgbroadVO.empno == employeeVO.empno}">
										${employeeVO.empname}
									</c:if>
								</c:forEach>
							</td>
							<td>
								<a href="<%=request.getContextPath()%>/back-end/msgbroad/msgbroad.do?
									msgno=${msgbroadVO.msgno}&requestURL=<%=request.getServletPath()%>&whichPage=<%=whichPage%>&action=getOne_For_Show">
									<b>${msgbroadVO.title}</b>
								</a>
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