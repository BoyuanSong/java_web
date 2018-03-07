<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<!DOCTYPE>
<html>
<head>
<title>文章內容</title>
<style>
.comments {
	margin: 0px;
	padding: 4px 0px;
}
.touched {
	background-color: #F0F8FF;
}
.touchedId {
	padding: 1px 0px;
	background-color: #5599FF;
	background-color: #87CEFA;
	color: white;
}
</style>
</head>
<body>
		<%@include file="../nav.jsp"%>
		<div class="col-sm-10">
			<div class="bListTitle">文章內容</div>
			
			<div class="bListConent">
			
				<div style="float:right">
					<c:if test="${employeeVO.empno == msgbroadVO.empno}">
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/msgbroad/msgbroad.do" style="display:inline">
							<input type="submit" value="修改" class="btn btn-default">
							<input type="hidden" name="msgno" value="${msgbroadVO.msgno}">
							<input type="hidden" name="requestURL"	value="<%=request.getParameter("requestURL")%>">
							<input type="hidden" name="whichPage"	value="<%=request.getParameter("whichPage")%>">
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</c:if>
						
					<c:if test="${employeeVO.empno == msgbroadVO.empno}">							
						<button type="button" class="btn btn btn-default" data-toggle="modal" data-target="#deleteMsgModal">刪除</button>
							
						<!-- Modal -->
						<div class="modal fade" id="deleteMsgModal" tabindex="-1" role="dialog" aria-labelledby="#deleteMsgModal" aria-hidden="true">
							<div class="modal-dialog" role="document" style="width=200">
								<div class="modal-content">
									<div class="modal-body">
										<h4>
											<span>刪除後無法復原,請確認後刪除</span>
											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</h4>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/msgbroad/msgbroad.do">
											<input type="hidden" name="msgno" value="${msgbroadVO.msgno}">
											<input type="hidden" name="requestURL"	value="<%=request.getParameter("requestURL")%>">
											<input type="hidden" name="whichPage"	value="<%=request.getParameter("whichPage")%>">
											<input type="hidden" name="action" value="delete">
											<button type="submit" class="btn btn-primary btn-sm">確認</button>
											<button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">取消</button>
										</FORM>
									</div>
								</div>
							</div>
						</div>
						<!-- Modal end -->
					</c:if>
				</div>
					
				<jsp:useBean id="employeeSvc" scope="page" class="com.employee.model.EmployeeService" />
				<c:forEach var="empVO" items="${employeeSvc.all}">
					<c:if test="${msgbroadVO.empno == empVO.empno}">
						<h4 style="display:inline"><span>作者:&nbsp;</span>;&nbsp;${empVO.empname}</h4>
						<c:if test="${not empty successMsg}">
							<span style="color:green">&nbsp;&nbsp;${successMsg}</span>
						</c:if>
					</c:if>
				</c:forEach>
				<h4><span>標題:&nbsp;</span>&nbsp;${msgbroadVO.title}</h4>
				<h4><span>時間:&nbsp;</span>&nbsp;${msgbroadVO.year}${msgbroadVO.day}&nbsp;${msgbroadVO.hour}</h4>
				<div style="margin-top:20px">${msgbroadVO.msg}</div>
					
				<hr>
				
				<c:forEach var="commentsVO" items="${commentsList}">
					<c:forEach var="empVO" items="${employeeSvc.all}">
						<c:if test="${commentsVO.empno == empVO.empno}">
							<p class="comments">
								<b class="commentsId">&nbsp;${empVO.empname}&nbsp;</b>:&nbsp;${commentsVO.empmsg}
								<span style="float: right">${commentsVO.day}&nbsp;${commentsVO.hour}&nbsp;</span>
							</p>
						</c:if>
					</c:forEach>
				</c:forEach>
				<div style="margin-top:20px">
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addCommemtModal">留言</button>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/msgbroad/msgbroad.do" style="display:inline">
						<input type="submit" value="回上頁" class="btn btn-default">
						<input type="hidden" name="requestURL"	value="<%=request.getParameter("requestURL")%>">
						<input type="hidden" name="whichPage"	value="<%=request.getParameter("whichPage")%>">
						<input type="hidden" name="action" value="backToList">
					</FORM>
				</div>
			</div>
			<div class="bFooter">sby318@gmail.com &copy; 2018 All rightsreserved.</div>
			
			<!-- Modal -->
			<div class="modal fade" id="addCommemtModal" tabindex="-1" role="dialog" aria-labelledby="#addCommemtModal" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h4>
								<b>留言送出後無法修改 請確認後送出</b>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</h4>
						</div>
						<div class="modal-body">
							<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/msgbroad/comments.do">
								<input type="TEXT" name="empmsg" id="empmsg" size="54">
								<input type="hidden" name="action" value="insert"> 
								<input type="hidden" name="msgno" value="${msgbroadVO.msgno}"> 
								<input type="hidden" name="empno" value="${employeeVO.empno}">
								<input type="hidden" name="requestURL"	value="<%=request.getParameter("requestURL")%>">
								<input type="hidden" name="whichPage"	value="<%=request.getParameter("whichPage")%>">
								<button type="submit" class="btn btn-primary btn-sm">送出</button>
								<button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">取消</button>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- Modal end -->
		</div>
	</div>
</div>
</body>
<script>
	$('.comments').hover(function() {
		$(this).toggleClass('touched');
		$(this).find('b').toggleClass('touchedId');
	}, function() {
		$(this).toggleClass('touched');
		$(this).find('b').toggleClass('touchedId');
	}); //end hobver 移到的留言會換色
</script>
</html>