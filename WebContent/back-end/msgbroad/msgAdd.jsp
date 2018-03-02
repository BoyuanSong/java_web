<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.msgbroad.model.*"%>
<%
	MsgbroadVO msgbroadVO = (MsgbroadVO) request.getAttribute("msgbroadVO");
%>
<!DOCTYPE>
<html>
<head>
<title>�峹�s�W</title>
</head>
<body>

		<%@include file="../nav.jsp"%>
		<div class="col-sm-10">
			<div class="bListTitle">�峹�s�W</div>
			
			<div class="bListConent">
				<h4>
					<span style="color: #FF0000">* </span>�峹����:
					<button type="button" class="btn btn-link" value="[�s�D] ">[ �s�D ]</button>
					<button type="button" class="btn btn-link" value="[�Q��] ">[ �Q�� ]</button>
					<button type="button" class="btn btn-link" value="[�n�p] ">[ �n�p ]</button>
					<button type="button" class="btn btn-link" value="[�t�p] ">[ �t�p ]</button>
					<button type="button" class="btn btn-link" value="[�L�p] ">[ �L�p ]</button>
				</h4>
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/msgbroad/msgbroad.do">
					<h4>
						<span style="color: #FF0000">* </span>���D:&nbsp;
						<input type="TEXT" name="title" size="40" value="<%=(msgbroadVO == null) ? "" : msgbroadVO.getTitle()%>" id="title" />
					</h4>
					<h4><span style="color: #FF0000">* </span>����</h4>
					<textarea class="ckeditor" cols="80" name="msg" rows="12">
						<%=(msgbroadVO == null) ? "" : msgbroadVO.getMsg()%> 
					</textarea>
					<input type="submit" value="�e�X" class="btn btn-info">
					<input type="hidden" name="action" value="insert"> 
					<input type="hidden" name="empno" value="${employeeVO.empno}">
					
					<c:if test="${not empty errorMsgs }">
						<c:forEach var="errorMsg" items="${errorMsgs }">
							<b style="color:red; font-size: 150%;">&nbsp;${errorMsg}</b>
						</c:forEach>
					</c:if>
				</form>
				
			</div>
			<div class="bFooter">lst448@hotmail.com &copy; 2018 All rightsreserved.</div>
		</div>
	</div>
</div>
<script>
	$('button').click(function() {
		var type = $(this).attr('value');
		$('#title').val(type);
	}); //end click ���D�����ֱ���
</script>
</body>
</html>