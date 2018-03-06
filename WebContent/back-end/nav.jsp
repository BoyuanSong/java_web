<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.emp_permission.model.*"%>
<%@ page import="com.employee.model.*"%>
<%
	// 用URL?target=XXX 來判斷哪個panel會打開
	String target = request.getParameter("target");	
%>
<!DOCTYPE>
<html>
<head>
<title>後端頁面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/quickBtn.js"></script>
<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-2" id="navBackground">
			
				<div id="navHeader">
					<h4 style="display: inline"><b>&nbsp;${employeeVO.empname}&nbsp;你好</h4>
					<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/employee/employee.do" style="display: inline">
						<input type="hidden" name="action" value="logOut">
						<button class="btn btn-success btn-xs" role="button" style="float: right">登出</button>
					</form>
				</div>
				
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#area1">
									佈告欄<span class="glyphicon glyphicon-chevron-down" style="float: right"></span>
								</a>
							</h4>
						</div>
						
						<div id="area1" class="panel-collapse collapse <%if(target.equals("msg")){%>in<%}%>">
							<div class="panel-body">
								<a href="<%=request.getContextPath()%>/back-end/msgbroad/msgAll.jsp?target=msg">佈告欄瀏覽</a>
							</div>
						</div>
					</div>
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#area2">
									員工資料管理<span class="glyphicon glyphicon-chevron-down" style="float: right"></span>
								</a>
							</h4>
						</div>
						
						<div id="area2" class="panel-collapse collapse <%if(target.equals("emp")){%>in<%}%>">
							<c:if test="${fn:contains(panel_Body, 'E01')}">
								<div class="panel-body">
									<a href="<%=request.getContextPath()%>/back-end/employee/empListAll.jsp?target=emp">員工管理</a>
								</div>
							</c:if>
							<div class="panel-body">
								<a href="<%=request.getContextPath()%>/back-end/employee/empChangePw.jsp?target=emp">密碼修改</a>
							</div>
							<div class="panel-body">
								<a href="<%=request.getContextPath()%>/back-end/employee/empChangeEmail.jsp?target=emp">信箱修改</a>
							</div>
						</div>
					</div>	
					
					<c:if test="${fn:contains(panel_Heading, 'F')}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#area3">
										前端網頁管理<span class="glyphicon glyphicon-chevron-down" style="float: right"></span>
									</a>
								</h4>
							</div>
							
							<div id="area3" class="panel-collapse collapse <%if(target.equals("front")){%>in<%}%>">
								<c:if test="${fn:contains(panel_Body, 'F01')}">
									<div class="panel-body">
										<a href="<%=request.getContextPath()%>/back-end/front-web/news.jsp?target=front">消息管理</a>
									</div>
								</c:if>
								<c:if test="${fn:contains(panel_Body, 'F02')}">
									<div class="panel-body">
										<a href="<%=request.getContextPath()%>/back-end/front-web/product.jsp?target=front">商品管理</a>
									</div>
								</c:if>
							</div>
						</div>
					</c:if>
				</div>
			</div>