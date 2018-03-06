package com.employee.controller;

import com.employee.model.*;
import com.emp_permission.model.*;
import java.io.IOException;
import java.sql.Date;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import mail.MailService;

public class EmployeeServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("changeEmppw".equals(action)) { // from empChangePw.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數 參數錯誤處理
				String oldEmppw = req.getParameter("oldEmppw");
				String typedOldEmppw = req.getParameter("typedOldEmppw").trim();
				String newEmppw1 = req.getParameter("newEmppw1").trim();
				String newEmppw2 = req.getParameter("newEmppw2").trim();

				if ((typedOldEmppw.isEmpty()) || (newEmppw1.isEmpty()) || (newEmppw2.isEmpty())) {
					errorMsgs.add("欄位不可為空");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empChangePw.jsp?target=emp");
					failureView.forward(req, res);
					return;
				}

				if (!typedOldEmppw.equals(oldEmppw)) {
					errorMsgs.add("舊密碼輸入錯誤");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empChangePw.jsp?target=emp");
					failureView.forward(req, res);
					return;
				}

				if (!newEmppw1.equals(newEmppw2)) {
					errorMsgs.add("新密碼不相同");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empChangePw.jsp?target=emp");
					failureView.forward(req, res);
					return;
				}
				if ((typedOldEmppw.indexOf(" ") != -1) || (newEmppw1.indexOf(" ") != -1) || (newEmppw2.indexOf(" ") != -1)) {
					errorMsgs.add("不能有空白字元");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login.jsp");
					failureView.forward(req, res);
					return;
				}

				// 2.開始修改資料
				HttpSession session = req.getSession();
				EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("employeeVO");

				EmployeeService empSvc = new EmployeeService();
				empSvc.updateEmp(employeeVO.getEmpid(), newEmppw1, employeeVO.getEmpname(), employeeVO.getEmpemail(),
						employeeVO.getEmpbirth(), employeeVO.getEmpstate(), employeeVO.getEmpno());

				// 3.修改完成 準備轉交
				String successMsg = "修改密碼成功";
				req.setAttribute("successMsg", successMsg);
				EmployeeVO updatedEmp = empSvc.getOneEmp(employeeVO.getEmpno());
				session.setAttribute("employeeVO", updatedEmp);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/employee/empChangePw.jsp?target=emp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改密碼失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empChangePw.jsp?target=emp");
				failureView.forward(req, res);
			}
		}
		
		if ("changeEmail".equals(action)) { // from empChangeEmail.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數 參數錯誤處理
				String empemail = req.getParameter("empemail").trim();
				if ((empemail == null) || (empemail.trim().length() == 0)) {
					errorMsgs.add("請填電子信箱");
				} else if (!empemail.matches("^[0-9A-Za-z_.]+@[0-9A-Za-z_.]+[.]+[0-9A-Za-z_.]+")) {
					errorMsgs.add("信箱格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empChangeEmail.jsp?target=emp");
					failureView.forward(req, res);
					return;
				}
				// 2.開始修改
				HttpSession session = req.getSession();
				EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("employeeVO");
				
				EmployeeService empSvc = new EmployeeService();
				empSvc.updateEmp(employeeVO.getEmpid(), employeeVO.getEmppw(), employeeVO.getEmpname(), empemail,
						employeeVO.getEmpbirth(), employeeVO.getEmpstate(), employeeVO.getEmpno());

				// 3.修改完成 準備轉交
				String successMsg = "成功修改信箱為 " + empemail;
				req.setAttribute("successMsg", successMsg);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/employee/empChangeEmail.jsp?target=emp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改信箱失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empChangeEmail.jsp?target=emp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // from empAdd.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數 參數錯誤處理
				String empid = req.getParameter("empid").trim();
				String empname = req.getParameter("empname").trim();
				String empemail = req.getParameter("empemail").trim();
				
				if ((empid.isEmpty()) || (empname.isEmpty()) || (empemail.isEmpty())) {
					errorMsgs.add("欄位均須填寫");
				}
				if ((!empemail.isEmpty()) && (!empemail.matches("^[0-9A-Za-z_.]+@[0-9A-Za-z_.]+[.]+[0-9A-Za-z_.]+"))) {
					errorMsgs.add("電子信箱格式不正確");
				}
				if (new EmployeeService().getOneByEmpid(empid) != null) {
					errorMsgs.add("帳號跟其他人重複了");
				}
				
				Date empbirth = null;
				try {
					empbirth = Date.valueOf(req.getParameter("empbirth"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請選擇日期");
				}

				EmployeeVO errorEmpVO = new EmployeeVO();
				errorEmpVO.setEmpid(empid);
				errorEmpVO.setEmpname(empname);
				errorEmpVO.setEmpemail(empemail);
				errorEmpVO.setEmpbirth(empbirth);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("errorEmpVO", errorEmpVO); // 錯誤時的狀態
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empAdd.jsp?target=emp");
					failureView.forward(req, res);
					return;
				}
				
				int[] randomNum = new int[7];
				for (int i = 0; i < 7; i++) {
					randomNum[i] = ((int) (Math.random() * 10));
				}
				String randomEmppw = "z";
				for (int i = 0; i < 7; i++) {
					randomEmppw = randomEmppw + randomNum[i];
				}
				
				String empstate = "1";

				// 2.開始新增
				EmployeeService empSvc = new EmployeeService();
				empSvc.addEmployee(empid, randomEmppw, empname, empemail, empbirth, empstate);

				// 3.將帳號密碼用電子郵件寄出
				String to = empemail;
				String subject = "員工登入資料";
				String messageText = "Hello! " + empname + "\n請謹記以下登入資訊" + "\n帳號: " + empid + "\n密碼: " + randomEmppw;

				MailService mailService = new MailService();
				mailService.sendMail(to, subject, messageText);

				// 4.新增完成 準備轉交
				String successMsg = "新增員工成功";
				req.setAttribute("successMsg", successMsg);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/employee/empListAll.jsp?target=emp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("新增員工失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empAdd.jsp?target=emp");
				failureView.forward(req, res);
			}
		}

		if ("login".equals(action)) { // from login.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數 參數錯誤處理
				String empid = req.getParameter("empid");
				String emppw = req.getParameter("emppw");

				if ((empid.trim().length() == 0) || (emppw.trim().length() == 0)) {
					errorMsgs.add("請輸入帳號密碼");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login.jsp");
					failureView.forward(req, res);
					return;
				}
				if ((empid.indexOf(" ") != -1) || (emppw.indexOf(" ") != -1)) {
					errorMsgs.add("不能有空白字元");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login.jsp");
					failureView.forward(req, res);
					return;
				}

				// 2 查詢輸入的帳號密碼是否符合員工資料
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneByEmpid(empid);

				if (employeeVO == null) {
					errorMsgs.add("無此帳號");
				} else if (!emppw.equals(employeeVO.getEmppw())) {
					errorMsgs.add("密碼錯誤");
				} else if (employeeVO.getEmpstate().equals("0")) {
					errorMsgs.add("已離職無法登入");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login.jsp");
					failureView.forward(req, res);
					return;
				}

				// 3.登入成功  取得登入員工的權限 準備轉交至佈告欄首頁
				
				Set<Emp_permissionVO> permissionList = (new Emp_permissionService()).getAllByEmpno(employeeVO.getEmpno());

				Set<String> panel_Heading = new HashSet<String>();
				Set<String> panel_Body = new HashSet<String>();
				for (Emp_permissionVO vo : permissionList) {
					String firstChar = String.valueOf(vo.getPermno().charAt(0));
					panel_Heading.add(firstChar);
					panel_Body.add(vo.getPermno());
				}
				
				HttpSession session = req.getSession();
				session.setAttribute("employeeVO", employeeVO);
				session.setAttribute("panel_Body", panel_Body);
				session.setAttribute("panel_Heading", panel_Heading);
				res.sendRedirect(req.getContextPath() + "/back-end/msgbroad/msgAll.jsp?target=msg");

			} catch (Exception e) {
				errorMsgs.add("登入失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login.jsp");
				failureView.forward(req, res);
			}
		}

		if ("logOut".equals(action)) { // from nav.jsp
			req.getSession().invalidate();
			res.sendRedirect(req.getContextPath() + "/back-end/login.jsp");
			return;
		}

		if ("changeState".equals(action)) { // from empListAll.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數 參數錯誤處理
				String empno = req.getParameter("empno");
				
				String empstate = req.getParameter("empstate");
				String newState = null;
				if (empstate.equals("1")) {
					newState = "0";
				} else {
					newState = "1";
				}

				// 2.開始修改資料
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmp(empno);

				employeeSvc.updateEmp(employeeVO.getEmpid(), employeeVO.getEmppw(), employeeVO.getEmpname(),
						employeeVO.getEmpemail(), employeeVO.getEmpbirth(), newState, empno);

				// 3.修改成功 準備轉交
				String successMsg = "狀態修改成功";
				req.setAttribute("successMsg", successMsg);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/employee/empListAll.jsp?target=emp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("狀態修改失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empListAll.jsp?target=emp");
				failureView.forward(req, res);
			}
		}
	}
}