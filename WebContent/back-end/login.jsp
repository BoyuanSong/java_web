<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<title>後端員工登入</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/quickBtn.js"></script>
</head>
<body>
	<div class="container-fluid" id="loginBackground">
		<div class="row">
			<div class="col-sm-4 col-sm-offset-4" id= "loginBox">
			
				<div class="col-xs-12 col-sm-12" style="text-align: center">
					<h2><b>後端管理系統</b></h2>
				</div>

				<div class="col-xs-12 col-sm-6 text-right">
					<img src="<%=request.getContextPath()%>/images/back_logo.png">
				</div>
				
				<div class="col-xs-12 col-sm-6">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/employee/employee.do">
						<div class="form-group">
							<input type="text" name="empid" id="empid" placeholder="帳號" size="12" maxlength="15" class="form-control">
						</div>
						<div class="form-group">
							<input type="password" name="emppw" id="emppw" placeholder="密碼" size="12" maxlength="15" class="form-control">
						</div>
						<div class="form-actions">
							<input type="hidden" name="action" value="login">
							<input type="submit" value="登入" class="btn btn-info">
							<input type="button" value="quick" class="btn" id="quickLoginBtn">
						</div>
						<c:if test="${not empty errorMsgs}">
							<c:forEach var="message" items="${errorMsgs}">
								<span align="center" style="color: red"><b>&nbsp;${message}</b></span>
							</c:forEach>
						</c:if>
					</FORM>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>