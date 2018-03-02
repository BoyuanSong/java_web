package com.emp_permission.controller;

import com.emp_permission.model.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Emp_permissionServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("changePerm".equals(action)) { // from empChangePerm.jsp
			
			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數
				Map<String, String[]> map = req.getParameterMap();
				String empno = map.get("empno")[0];
				String empname = map.get("empname")[0];
				String[] perm = map.get("perm");

				// 2.開始修改資料
				Emp_permissionService emp_permissionSvc = new Emp_permissionService();
				emp_permissionSvc.updatePermission(empno, perm);

				// 3.修改完成 準備轉交
				String successMsg = "成功修改 " + empname + " 的權限";
				req.setAttribute("successMsg", successMsg);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/employee/empListAll.jsp?target=emp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改權限失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empListAll.jsp?target=emp" );
				failureView.forward(req, res);
			}
		}

		if ("getOneEmp_perm".equals(action)) { // from empListAll.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數
				String empno = req.getParameter("empno");
				String empname = req.getParameter("empname");

				// 2.開始查詢員工權限
				Emp_permissionService emp_permSvc = new Emp_permissionService();
				Set<Emp_permissionVO> set = emp_permSvc.getAllByEmpno(empno);
				
				List<String> perm = new ArrayList();
				for (Emp_permissionVO permVO : set) {
					perm.add(permVO.getPermno());
				}

				// 3.查詢完成 準備轉交
				req.setAttribute("empname", empname);
				req.setAttribute("empno", empno);
				req.setAttribute("perm", perm);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/employee/empChangePerm.jsp?target=emp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("選取權限失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empListAll.jsp?target=emp");
				failureView.forward(req, res);
			}
		}
	}
}