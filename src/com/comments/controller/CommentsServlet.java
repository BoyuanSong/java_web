package com.comments.controller;

import com.comments.model.*;
import com.msgbroad.model.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CommentsServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) { // from msgDetail.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數
				String msgno = req.getParameter("msgno");
				String empno = req.getParameter("empno");
				String empmsg = req.getParameter("empmsg").trim();
				
				// 2.開始新增資料
				CommentsService commentsSvc = new CommentsService();
				if(!empmsg.isEmpty()){
					commentsSvc.addComments(msgno, empno, empmsg);
				}
				
				// 3.新增完成 準備轉交
				MsgbroadService msgbroadSvc = new MsgbroadService();
				MsgbroadVO msgbroadVO = msgbroadSvc.getOneMsg(msgno);
				req.setAttribute("msgbroadVO", msgbroadVO);
				
				List<CommentsVO> commentsList = commentsSvc.getSomeComments(msgno);
				req.setAttribute("commentsList", commentsList);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/msgbroad/msgDetail.jsp?target=msg");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/msgbroad/msgDetail.jsp?target=msg");
				failureView.forward(req, res);
			}
		}
	}
}
