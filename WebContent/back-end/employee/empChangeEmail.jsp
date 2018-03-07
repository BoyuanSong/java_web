<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>

<!DOCTYPE>
<html>
<head>
<title>信箱修改</title>
</head>
<body>
		<%@include file="../nav.jsp"%>
		<div class="col-sm-10">
			<div class="bListTitle">信箱修改</div>
			
			<div class="bListConent">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/employee/employee.do" class="form-inline">
					<div class="form-group">
						<label for="empemail">
							<h4 style="display: inline"><span style="color: #FF0000">*</span>新信箱:&nbsp;</h4> 
						</lebal> 
						<input type="text" name="empemail" id="empemail" maxlength="30"size="30" class="form-control"> 
					</div>
					<div class="form-actions">
						<input type="hidden" name="action" value="changeEmail">
						<input type="submit" value="修改" class="btn btn-default">
						<input type="button" value="quick" id="quickEmailChangeBtn" class="btn">
						
						<c:if test="${not empty successMsg}">
							<strong style="color:green">&nbsp;&nbsp;&nbsp;${successMsg}</strong>
						</c:if>
						<c:if test="${not empty errorMsgs }">
							<c:forEach var="errorMsg" items="${errorMsgs }">
								<strong style="color:red">&nbsp;&nbsp;&nbsp;${errorMsg}</strong>
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