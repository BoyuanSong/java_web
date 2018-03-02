package com.msgbroad.controller;

import com.comments.model.*;
import com.msgbroad.model.*;
import java.io.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MsgbroadServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) { // from msgAdd.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數 參數錯誤處理
				String empno = req.getParameter("empno");
				
				String title = req.getParameter("title").trim();
				String msg = req.getParameter("msg").trim();
				if ((title.isEmpty()) || (msg.isEmpty())) {
					errorMsgs.add("標題內容不可為空白");
				}
				
				MsgbroadVO msgbroadVO = new MsgbroadVO();
				msgbroadVO.setEmpno(empno);
				msgbroadVO.setTitle(title);
				msgbroadVO.setMsg(msg);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("msgbroadVO", msgbroadVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/msgbroad/msgAdd.jsp?target=msg");
					failureView.forward(req, res);
					return;
				}
				// 2.開始新增資料
				MsgbroadService msgSvc = new MsgbroadService();
				msgSvc.addMsg(empno, title, msg);

				// 3.新增完成 準備轉交
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/msgbroad/msgAll.jsp?target=msg");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("新增文章失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/msgbroad/msgAdd.jsp?target=msg");
				failureView.forward(req, res);
			}
		}

		if (("getOne_For_Update".equals(action)) || ("getOne_For_Show".equals(action))) { // from msgAll.jsp, msgResult.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			
			try {
				// 1.接收請求參數
				String msgno = req.getParameter("msgno");

				// 2.開始查詢
				MsgbroadService msgSvc = new MsgbroadService();
				MsgbroadVO msgbroadVO = msgSvc.getOneMsg(msgno);
				
				// 3.查詢完成 準備轉交
				req.setAttribute("msgbroadVO", msgbroadVO);

				String url = null;
				if ("getOne_For_Update".equals(action)) {
					url = "/back-end/msgbroad/msgUpdate.jsp?target=msg";
				}
				if ("getOne_For_Show".equals(action)) {
					CommentsService commentsSvc = new CommentsService();
					List<CommentsVO> commentsList = commentsSvc.getSomeComments(msgno);
					req.setAttribute("commentsList", commentsList);
					url = "/back-end/msgbroad/msgDetail.jsp?target=msg";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("文章選取失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/msgbroad/msgAll.jsp?target=msg");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // from msgAll.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			try {
				// 1.接收請求參數
				String msgno = req.getParameter("msgno");

				// 2.開始刪除文章
				CommentsService commentsSvc = new CommentsService();
				commentsSvc.deleteSomeComments(msgno);  // 先刪文章內的留言
				MsgbroadService msgSvc = new MsgbroadService();
				msgSvc.deleteMsg(msgno);

				// 3.刪除完成 準備轉交
				if(requestURL.equals("/back-end/msgbroad/msgResult.jsp")){
					HttpSession session = req.getSession();
					HashMap<String, String[]> map = (HashMap<String, String[]>)session.getAttribute("map");
					List<MsgbroadVO> list  = msgSvc.getAll(map);
					req.setAttribute("list",list);
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url+"?target=msg");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/msgbroad/msgAll.jsp?target=msg");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // from msgUpdate.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");

			try {
				// 1.接收請求參數
				String msgno = req.getParameter("msgno");

				String title = req.getParameter("title");
				if (title.isEmpty()) {
					errorMsgs.add("標題不可空白");
				}
				String msg = req.getParameter("msg");
				if (msg.isEmpty()) {
					errorMsgs.add("內容不可空白");
				}

				MsgbroadVO msgVO = new MsgbroadVO();
				msgVO.setMsgno(msgno);
				msgVO.setTitle(title);
				msgVO.setMsg(msg);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("msgbroadVO", msgVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/msgbroad/msgUpdate.jsp?target=msg");
					failureView.forward(req, res);
					return;
				}

				// 2.開始修改
				MsgbroadService msgSvc = new MsgbroadService();
				msgSvc.updateMsg(title, msg, msgno);
				
				// 3.修改完成 準備轉交
				
				String successMsg = "修改成功";
				req.setAttribute("successMsg", successMsg);
				
				MsgbroadVO msgbroadVO = msgSvc.getOneMsg(msgno);
				req.setAttribute("msgbroadVO", msgbroadVO);
				
				CommentsService commentsSvc = new CommentsService();
				List<CommentsVO> commentsList = commentsSvc.getSomeComments(msgno);
				req.setAttribute("commentsList", commentsList);
				
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/msgbroad/msgDetail.jsp?target=msg");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/msgbroad/msgUpdate.jsp?target=msg");
				failureView.forward(req, res);
			}
		}

		if ("listMsg_ByCompositeQuery".equals(action)) { // from msgAll.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數
				HashMap<String, String[]> map = (HashMap)req.getParameterMap();
				HashMap<String, String[]> map2 = new HashMap<String, String[]>();
				map2 = (HashMap<String, String[]>)map.clone();
				HttpSession session = req.getSession();
				session.setAttribute("map",map2);
				
				// 2.準備查詢
				
				MsgbroadService msgbroadSvc = new MsgbroadService();
				List<MsgbroadVO> list = msgbroadSvc.getAll(map);
				
				// 3.查詢完成 準備轉交
				session.setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/msgbroad/msgResult.jsp?target=msg");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/msgbroad/msgAll.jsp?target=msg");
				failureView.forward(req, res);
			}
		}
		if ("backToList".equals(action)) { // from msgDetail.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				if(requestURL.equals("/back-end/msgbroad/msgResult.jsp")){
					HttpSession session = req.getSession();
					HashMap<String, String[]> map = (HashMap<String, String[]>)session.getAttribute("map");
					MsgbroadService msgSvc = new MsgbroadService();
					List<MsgbroadVO> list  = msgSvc.getAll(map);
					req.setAttribute("list",list);
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url+"?target=msg");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/msgbroad/msgAll.jsp?target=msg");
				failureView.forward(req, res);
			}
		}
	}
}