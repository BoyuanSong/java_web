<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%
	EmployeeVO errorEmpVO = (EmployeeVO) request.getAttribute("errorEmpVO");
%>
<!DOCTYPE>
<html>
<head>
<title>���u�s�W</title>
</head>
<body>
		<%@include file="../nav.jsp"%>
		<div class="col-sm-10">
			<div class="bListTitle">���u�s�W</div>
			
			<div class="bListConent">
				<form METHOD="post" id="empAdd" ACTION="<%=request.getContextPath()%>/back-end/employee/employee.do">
					<div class="form-group">
						<span>�b��:&nbsp;</span>
						<input type="TEXT" name="empid" id="empid" maxlength="15"  value="<%=(errorEmpVO == null) ? "" : errorEmpVO.getEmpid()%>"/> 
					</div>
					<div class="form-group">
						<span>�m�W:&nbsp;</span>
						<input type="TEXT" name="empname" id="empname" maxlength="15" value="<%=(errorEmpVO == null) ? "" : errorEmpVO.getEmpname()%>"/>
					</div>
					<div class="form-group">
						<span>�H�c:&nbsp;</span>
						<input type="TEXT" name="empemail" id="empemail" maxlength="25" value="<%=(errorEmpVO == null)? "" : errorEmpVO.getEmpemail()%>"/>
					</div>
					<div class="form-group">
						<span>�ͤ�:&nbsp;</span>
						<input type="date" name="empbirth" id="empdate" value="<%=(errorEmpVO == null) ? "" : errorEmpVO.getEmpbirth()%>" />
					</div>
					<div class="form-actions">
						<input type="submit" value="�s�W���u" class="btn btn-default">
						<input type="button" value="quick" class="btn" id="quickEmpAddBtn">
						<input type="hidden" name="action" value="insert">
					</div>
				</form>
				
				<c:if test="${not empty errorMsgs }">
						<c:forEach var="errorMsg" items="${errorMsgs }">
							<p style="color:red">${errorMsg}</p>
						</c:forEach>
				</c:if>
			</div>
			<div class="bFooter">sby318@gmail.com &copy; 2018 All rightsreserved.</div>
		</div>
	</div>
</div>
<script>
$('#empAdd').submit(function(){
	$('input[type=submit]').prop('disabled',true).val('�ǰe��...');
}); // ������h������
</script>
</body>
</html>