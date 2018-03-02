<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%
	EmployeeService empSvc = new EmployeeService();
	List<EmployeeVO> list = empSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE>
<html>
<head>
<title>���u�޲z</title>
</head>
<body>
		<%@include file="../nav.jsp"%>
		<div class="col-sm-10">
			<div class="bListTitle">���u�޲z</div>
			
			
			<div class="bListConent">
				<%@ include file="../pages/page1.file"%><%--�����W�b��--%>
				<c:if test="${not empty successMsg}">
					<strong style="color:green">&nbsp;&nbsp;${successMsg}</strong>
				</c:if>
				
				<c:if test="${not empty errorMsgs }">
					<c:forEach var="errorMsg" items="${errorMsgs }">
						<strong style="color:red">&nbsp;&nbsp;${errorMsg}</strong>
					</c:forEach>
				</c:if>
				<table class="table table-striped">
					<tr>
						<th>���u�s��</th>
						<th>�b��</th>
						<th>���u�m�W</th>
						<th>�H�c</th>
						<th>�ͤ�</th>
						<th>���A</th>
						<th>���u�޲z</th>
						<th>
							<a class="btn btn-md btn-warning" href="<%=request.getContextPath()%>/back-end/employee/empAdd.jsp?target=emp" role="button">
								<span class="glyphicon glyphicon-plus"></span>�s�W���u
							</a>
						</th>
					</tr>
					<c:forEach var="employeeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr>
							<td>${employeeVO.empno}</td>
							<td>${employeeVO.empid}</td>
							<td>${employeeVO.empname}</td>
							<td>${employeeVO.empemail}</td>
							<td>${employeeVO.empbirth}</td>
							<td>
								<c:if test="${employeeVO.empstate == '1'}">�b¾</c:if> 
								<c:if test="${employeeVO.empstate == '0'}">��¾</c:if>
							</td>
							<td>
								<c:if test="${employeeVO.empstate == '1'}">
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/employee/emp_permission.do">
										<button class="btn btn-primary" href="#" role="button">
											<span class="glyphicon glyphicon-pencil"></span> �޲z�v��
										</button>
										<input type="hidden" name="empno" value="${employeeVO.empno}">
										<input type="hidden" name="empname" value="${employeeVO.empname}">
										<input type="hidden" name="whichPage" value="${param.whichPage}">
										<input type="hidden" name="action" value="getOneEmp_perm">
									</FORM>
								</c:if>
							</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/employee/employee.do">
									<button class="btn btn-info" href="#" role="button">
										<span class="glyphicon glyphicon-retweet"></span>��窱�A
									</button>
									<input type="hidden" name="empno" value="${employeeVO.empno}">
									<input type="hidden" name="whichPage" value="${param.whichPage}">
									<input type="hidden" name="empstate" value="${employeeVO.empstate}"> 
									<input type="hidden" name="action" value="changeState">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="../pages/page2.file"%><%--�����U�b��--%>
			</div>
			<div class="bFooter">lst448@hotmail.com &copy; 2018 All rightsreserved.</div>
		</div>
	</div>
</div>
</body>
</html>