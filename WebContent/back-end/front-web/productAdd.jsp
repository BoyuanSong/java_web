<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>
<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>
<!DOCTYPE>
<html>
<head>
<title>�ӫ~�޲z</title>
<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
</head>
<body>
		<%@include file="../nav.jsp"%>
		<div class="col-xs-12 col-sm-8">
			<h4><b>�ӫ~�s�W</b></h4>
			
			<form method="post" action="<%=request.getContextPath()%>/front-web/product.do" enctype="multipart/form-data">
				<div class="form-group">
					<b>���D:&nbsp;</b><input type="text"name="proname" value="<%=(productVO == null) ? "" : productVO.getProname()%>">
				</div>
				<div class="form-group">
					<b>����:&nbsp;</b> 
					<select name="classno">
						<option value="X">--������O--</option>
						<option value="000001">�~�M</option>
						<option value="000002">���S�W��</option>
						<option value="000003">�u�S�W��</option>
						<option value="000004">����</option>
						<option value="000005">�u��</option>
					</select>
				</div>
				<div class="form-group">
					<b>����:&nbsp;</b><input type="text" name="proprice" value="<%=(productVO == null) ? "" : productVO.getProprice()%>">
				</div>
				<div class="form-group">
					<p>�ӫ~����:</p>
					<textarea class="ckeditor" cols="80" name="prodesc" rows="12"
						value="<%=(productVO == null) ? "" : productVO.getProdesc()%>"></textarea>
				</div>
				<div class="form-group">
					<b>�Ϥ�:</b> 
					<input type="File" name="propic" id="propic" class="fileinput-button" /> 
					<img src="<%=request.getContextPath()%>/images/shoplogo.jpg" id="showpic"style="width: 30%; height: auto;"> 
					<input type="hidden" name="empno" value="${employeeVO.empno }">
				</div>
				<hr>
				<div class="form-actions">
					<input type="hidden" name="action" value="insert">
					<button class="btn btn-primary" href="#" role="button">�e�X</button>
					<a class="btn btn-default" href="<%=request.getContextPath()%>/back-end/front-web/product.jsp?
						whichPage=<%=request.getParameter("whichPage")%>&target=front" role="button">����</a>
				</div>
			</form>
		</div>
	</div>
</div>
<script>
	function doFirst() {
		document.getElementById('propic').onchange = fileChange;
		//clickBtnSend();
	}
	function fileChange() {
		var file = document.getElementById('propic').files[0];
		var readFile = new FileReader();
		readFile.readAsDataURL(file);
		readFile.onload = function() {
			var image = document.getElementById('showpic');
			image.src = readFile.result;
			//image.style.width = '250px';
		};
	}
	window.addEventListener('load', doFirst, false);
</script>
</body>
</html>