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
<title>商品管理</title>
<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
</head>
<body>
		<%@include file="../nav.jsp"%>
		<div class="col-xs-12 col-sm-8">
			<h4><b>商品新增</b></h4>
			
			<form method="post" action="<%=request.getContextPath()%>/front-web/product.do" enctype="multipart/form-data">
				<div class="form-group">
					<b>標題:&nbsp;</b><input type="text"name="proname" value="<%=(productVO == null) ? "" : productVO.getProname()%>">
				</div>
				<div class="form-group">
					<b>種類:&nbsp;</b> 
					<select name="classno">
						<option value="X">--選擇類別--</option>
						<option value="000001">外套</option>
						<option value="000002">長袖上衣</option>
						<option value="000003">短袖上衣</option>
						<option value="000004">長褲</option>
						<option value="000005">短褲</option>
					</select>
				</div>
				<div class="form-group">
					<b>價格:&nbsp;</b><input type="text" name="proprice" value="<%=(productVO == null) ? "" : productVO.getProprice()%>">
				</div>
				<div class="form-group">
					<p>商品介紹:</p>
					<textarea class="ckeditor" cols="80" name="prodesc" rows="12"
						value="<%=(productVO == null) ? "" : productVO.getProdesc()%>"></textarea>
				</div>
				<div class="form-group">
					<b>圖片:</b> 
					<input type="File" name="propic" id="propic" class="fileinput-button" /> 
					<img src="<%=request.getContextPath()%>/images/shoplogo.jpg" id="showpic"style="width: 30%; height: auto;"> 
					<input type="hidden" name="empno" value="${employeeVO.empno }">
				</div>
				<hr>
				<div class="form-actions">
					<input type="hidden" name="action" value="insert">
					<button class="btn btn-primary" href="#" role="button">送出</button>
					<a class="btn btn-default" href="<%=request.getContextPath()%>/back-end/front-web/product.jsp?
						whichPage=<%=request.getParameter("whichPage")%>&target=front" role="button">取消</a>
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