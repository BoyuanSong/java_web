<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.msgbroad.model.*"%>
<%
	MsgbroadDAO msgbroadDAO = new MsgbroadDAO();
	List<MsgbroadVO> list = (List<MsgbroadVO>) msgbroadDAO.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE>
<html>
<head>
<title>�G�i���s��</title>
<style>
</style>
</head>
<body>
		<%@include file="../nav.jsp"%>
		<div class="col-sm-10">
			<div class="bListTitle">�G�i�歺��</div>
			
			<div class="bListConent">
				<%@ include file="../pages/page1.file"%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/msgbroad/msgbroad.do" style="display:inline;float:right">
					<b>�ƦX�d��&nbsp;&nbsp;�@��:&nbsp;</b><input type="text" name="empname" size="7">
					<b>&nbsp;���D:</b><input type="text" name="title" size="10">
					<input type="submit" value="�e�X" class="btn btn-default btn-sm"> 
					<input type="hidden" name="action" value="listMsg_ByCompositeQuery">
				</FORM>
				
				<table class="table">
					<tr>
						<th>�s��</th>
						<th>���</th>
						<th>�@��</th>
						<th>
							<b>���D</b>
							<c:if test="${fn:contains(panel_Body, 'B01')}">
								<a class="btn btn-md btn-warning btn-xs" href="msgAdd.jsp?target=msg" role="button" style="float:right">
									<span class="glyphicon glyphicon-plus"></span>�i�K�峹
								</a>
							</c:if>
						</th>
					</tr>
					<jsp:useBean id="employeeSvc" scope="page" class="com.employee.model.EmployeeService" />
					<c:forEach var="msgbroadVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr class="msgtitle">
							<td>${msgbroadVO.msgno}</td>
							<td>${msgbroadVO.day}</td>
							<c:forEach var="employeeVO" items="${employeeSvc.all}">
								<c:if test="${msgbroadVO.empno == employeeVO.empno}">
									<td>${employeeVO.empname}</td>
								</c:if>
							</c:forEach>
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