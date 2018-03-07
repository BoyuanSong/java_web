<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.msgbroad.model.*"%>
<%
	MsgbroadVO msgbroadVO = (MsgbroadVO) request.getAttribute("msgbroadVO");
%>
<!DOCTYPE>
<html>
<head>
<title>文章修改</title>
</head>
<body>
		<%@include file="../nav.jsp"%>
		<div class="col-sm-10">
			<div class="bListTitle">文章修改</div>
			
			<div class="bListConent">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/msgbroad/msgbroad.do">
					<div class="form-group">
						<span style="color: #FF0000">*</span><b>標題:&nbsp;</b>
						<input type="TEXT" name="title" size="45" value="<%=msgbroadVO.getTitle()%>"/>
					</div>
					<div class="form-group">
						<span style="color: #FF0000">*</span><b>內容:&nbsp;</b>
						<textarea class="ckeditor" cols="80" name="msg" rows="12"><%=msgbroadVO.getMsg()%></textarea>
					</div>
					<div class="form-actions">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="msgno" value="<%=msgbroadVO.getMsgno()%>">
						<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
						<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
						<input type="submit" value="送出修改" class="btn btn-default">
						
						<c:if test="${not empty errorMsgs }">
							<c:forEach var="errorMsg" items="${errorMsgs }">
								<strong style="color:red;display:inline">&nbsp;${errorMsg}</strong>
							</c:forEach>
						</c:if>
					</div>
				</FORM>
				
			</div>
			<div class="bFooter">sby318@gmail.com &copy; 2018 All rightsreserved.</div>
		</div>
	</div>
</div>
</body>
</html>