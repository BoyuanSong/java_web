<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>

<!DOCTYPE>
<html>
<head>
<title>�K�X�ק�</title>
</head>
<body>
		<%@include file="../nav.jsp"%>
		<div class="col-sm-10">
			<div class="bListTitle">���u�K�X�ק�</div>
			
			<div class="bListConent">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/employee/employee.do">
					<div class="form-group">
						<label for="typedOldEmppw">
							<span style="color: #FF0000">*</span>�±K�X��J: 
						</label>
						<input type="password" name="typedOldEmppw" id="typedOldEmppw" maxlength="20"> 
					</div>
					<div class="form-group">
						<label for="newEmppw1">
							<span style="color: #FF0000">*</span>�s�K�X��J: 
						</label>
						<input type="password" name="newEmppw1" id="newEmppw1" maxlength="20">
					</div>
					<div class="form-group">
						<label for="newEmppw2">
							<span style="color: #FF0000">*</span>�s�K�X�T�{: 
						</label>
						<input type="password" name="newEmppw2" id="newEmppw2" maxlength="20">
					</div>
					
					<div class="form-actions">
						<input type="hidden" name="action" value="changeEmppw"> 
						<input type="hidden" name="oldEmppw" value="${employeeVO.emppw}">
						<input type="submit" value="�ק�" class="btn btn-default">
						<input type="button" value="quick" id="quickPwChangeBtn" class="btn">
					
						<c:if test="${not empty successMsg}">
							<strong style="color:green">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${successMsg}</strong>
						</c:if>
						<c:if test="${not empty errorMsgs }">
							<c:forEach var="errorMsg" items="${errorMsgs }">
								<strong style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errorMsg}</strong>
							</c:forEach>
						</c:if>
					</div>
				</form>
			</div>
			<div class="bFooter">sby318@gmail.com &copy; 2018 All rightsreserved.</div>
		</div>
	</div>
</div>
</body>
</html>