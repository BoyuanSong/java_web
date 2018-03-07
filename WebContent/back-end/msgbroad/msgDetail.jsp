<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<!DOCTYPE>
<html>
<head>
<title>�峹���e</title>
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
			<div class="bListTitle">�峹���e</div>
			
			<div class="bListConent">
			
				<div style="float:right">
					<c:if test="${employeeVO.empno == msgbroadVO.empno}">
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/msgbroad/msgbroad.do" style="display:inline">
							<input type="submit" value="�ק�" class="btn btn-default">
							<input type="hidden" name="msgno" value="${msgbroadVO.msgno}">
							<input type="hidden" name="requestURL"	value="<%=request.getParameter("requestURL")%>">
							<input type="hidden" name="whichPage"	value="<%=request.getParameter("whichPage")%>">
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</c:if>
						
					<c:if test="${employeeVO.empno == msgbroadVO.empno}">							
						<button type="button" class="btn btn btn-default" data-toggle="modal" data-target="#deleteMsgModal">�R��</button>
							
						<!-- Modal -->
						<div class="modal fade" id="deleteMsgModal" tabindex="-1" role="dialog" aria-labelledby="#deleteMsgModal" aria-hidden="true">
							<div class="modal-dialog" role="document" style="width=200">
								<div class="modal-content">
									<div class="modal-body">
										<h4>
											<span>�R����L�k�_��,�нT�{��R��</span>
											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</h4>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/msgbroad/msgbroad.do">
											<input type="hidden" name="msgno" value="${msgbroadVO.msgno}">
											<input type="hidden" name="requestURL"	value="<%=request.getParameter("requestURL")%>">
											<input type="hidden" name="whichPage"	value="<%=request.getParameter("whichPage")%>">
											<input type="hidden" name="action" value="delete">
											<button type="submit" class="btn btn-primary btn-sm">�T�{</button>
											<button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">����</button>
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
						<h4 style="display:inline"><span>�@��:&nbsp;</span>;&nbsp;${empVO.empname}</h4>
						<c:if test="${not empty successMsg}">
							<span style="color:green">&nbsp;&nbsp;${successMsg}</span>
						</c:if>
					</c:if>
				</c:forEach>
				<h4><span>���D:&nbsp;</span>&nbsp;${msgbroadVO.title}</h4>
				<h4><span>�ɶ�:&nbsp;</span>&nbsp;${msgbroadVO.year}${msgbroadVO.day}&nbsp;${msgbroadVO.hour}</h4>
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
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addCommemtModal">�d��</button>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/msgbroad/msgbroad.do" style="display:inline">
						<input type="submit" value="�^�W��" class="btn btn-default">
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
								<b>�d���e�X��L�k�ק� �нT�{��e�X</b>
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
								<button type="submit" class="btn btn-primary btn-sm">�e�X</button>
								<button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">����</button>
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
	}); //end hobver ���쪺�d���|����
</script>
</html>