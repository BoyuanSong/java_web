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
				// 1.�����ШD�Ѽ� �Ѽƿ��~�B�z
				String oldEmppw = req.getParameter("oldEmppw");
				String typedOldEmppw = req.getParameter("typedOldEmppw").trim();
				String newEmppw1 = req.getParameter("newEmppw1").trim();
				String newEmppw2 = req.getParameter("newEmppw2").trim();

				if ((typedOldEmppw.isEmpty()) || (newEmppw1.isEmpty()) || (newEmppw2.isEmpty())) {
					errorMsgs.add("��줣�i����");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empChangePw.jsp?target=emp");
					failureView.forward(req, res);
					return;
				}

				if (!typedOldEmppw.equals(oldEmppw)) {
					errorMsgs.add("�±K�X��J���~");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empChangePw.jsp?target=emp");
					failureView.forward(req, res);
					return;
				}

				if (!newEmppw1.equals(newEmppw2)) {
					errorMsgs.add("�s�K�X���ۦP");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empChangePw.jsp?target=emp");
					failureView.forward(req, res);
					return;
				}
				if ((typedOldEmppw.indexOf(" ") != -1) || (newEmppw1.indexOf(" ") != -1) || (newEmppw2.indexOf(" ") != -1)) {
					errorMsgs.add("���঳�ťզr��");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login.jsp");
					failureView.forward(req, res);
					return;
				}

				// 2.�}�l�ק���
				HttpSession session = req.getSession();
				EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("employeeVO");

				EmployeeService empSvc = new EmployeeService();
				empSvc.updateEmp(employeeVO.getEmpid(), newEmppw1, employeeVO.getEmpname(), employeeVO.getEmpemail(),
						employeeVO.getEmpbirth(), employeeVO.getEmpstate(), employeeVO.getEmpno());

				// 3.�ק粒�� �ǳ����
				String successMsg = "�ק�K�X���\";
				req.setAttribute("successMsg", successMsg);
				EmployeeVO updatedEmp = empSvc.getOneEmp(employeeVO.getEmpno());
				session.setAttribute("employeeVO", updatedEmp);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/employee/empChangePw.jsp?target=emp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�ק�K�X����: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empChangePw.jsp?target=emp");
				failureView.forward(req, res);
			}
		}
		
		if ("changeEmail".equals(action)) { // from empChangeEmail.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.�����ШD�Ѽ� �Ѽƿ��~�B�z
				String empemail = req.getParameter("empemail").trim();
				if ((empemail == null) || (empemail.trim().length() == 0)) {
					errorMsgs.add("�ж�q�l�H�c");
				} else if (!empemail.matches("^[0-9A-Za-z_.]+@[0-9A-Za-z_.]+[.]+[0-9A-Za-z_.]+")) {
					errorMsgs.add("�H�c�榡�����T");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empChangeEmail.jsp?target=emp");
					failureView.forward(req, res);
					return;
				}
				// 2.�}�l�ק�
				HttpSession session = req.getSession();
				EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("employeeVO");
				
				EmployeeService empSvc = new EmployeeService();
				empSvc.updateEmp(employeeVO.getEmpid(), employeeVO.getEmppw(), employeeVO.getEmpname(), empemail,
						employeeVO.getEmpbirth(), employeeVO.getEmpstate(), employeeVO.getEmpno());

				// 3.�ק粒�� �ǳ����
				String successMsg = "���\�ק�H�c�� " + empemail;
				req.setAttribute("successMsg", successMsg);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/employee/empChangeEmail.jsp?target=emp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�ק�H�c����: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empChangeEmail.jsp?target=emp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // from empAdd.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.�����ШD�Ѽ� �Ѽƿ��~�B�z
				String empid = req.getParameter("empid").trim();
				String empname = req.getParameter("empname").trim();
				String empemail = req.getParameter("empemail").trim();
				
				if ((empid.isEmpty()) || (empname.isEmpty()) || (empemail.isEmpty())) {
					errorMsgs.add("��짡����g");
				}
				if ((!empemail.isEmpty()) && (!empemail.matches("^[0-9A-Za-z_.]+@[0-9A-Za-z_.]+[.]+[0-9A-Za-z_.]+"))) {
					errorMsgs.add("�q�l�H�c�榡�����T");
				}
				if (new EmployeeService().getOneByEmpid(empid) != null) {
					errorMsgs.add("�b�����L�H���ƤF");
				}
				
				Date empbirth = null;
				try {
					empbirth = Date.valueOf(req.getParameter("empbirth"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("�п�ܤ��");
				}

				EmployeeVO errorEmpVO = new EmployeeVO();
				errorEmpVO.setEmpid(empid);
				errorEmpVO.setEmpname(empname);
				errorEmpVO.setEmpemail(empemail);
				errorEmpVO.setEmpbirth(empbirth);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("errorEmpVO", errorEmpVO); // ���~�ɪ����A
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

				// 2.�}�l�s�W
				EmployeeService empSvc = new EmployeeService();
				empSvc.addEmployee(empid, randomEmppw, empname, empemail, empbirth, empstate);

				// 3.�N�b���K�X�ιq�l�l��H�X
				String to = empemail;
				String subject = "���u�n�J���";
				String messageText = "Hello! " + empname + "\n���԰O�H�U�n�J��T" + "\n�b��: " + empid + "\n�K�X: " + randomEmppw;

				MailService mailService = new MailService();
				mailService.sendMail(to, subject, messageText);

				// 4.�s�W���� �ǳ����
				String successMsg = "�s�W���u���\";
				req.setAttribute("successMsg", successMsg);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/employee/empListAll.jsp?target=emp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�s�W���u����: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empAdd.jsp?target=emp");
				failureView.forward(req, res);
			}
		}

		if ("login".equals(action)) { // from login.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.�����ШD�Ѽ� �Ѽƿ��~�B�z
				String empid = req.getParameter("empid");
				String emppw = req.getParameter("emppw");

				if ((empid.trim().length() == 0) || (emppw.trim().length() == 0)) {
					errorMsgs.add("�п�J�b���K�X");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login.jsp");
					failureView.forward(req, res);
					return;
				}
				if ((empid.indexOf(" ") != -1) || (emppw.indexOf(" ") != -1)) {
					errorMsgs.add("���঳�ťզr��");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login.jsp");
					failureView.forward(req, res);
					return;
				}

				// 2 �d�߿�J���b���K�X�O�_�ŦX���u���
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneByEmpid(empid);

				if (employeeVO == null) {
					errorMsgs.add("�L���b��");
				} else if (!emppw.equals(employeeVO.getEmppw())) {
					errorMsgs.add("�K�X���~");
				} else if (employeeVO.getEmpstate().equals("0")) {
					errorMsgs.add("�w��¾�L�k�n�J");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login.jsp");
					failureView.forward(req, res);
					return;
				}

				// 3.�n�J���\  ���o�n�J���u���v�� �ǳ����ܧG�i�歺��
				
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
				errorMsgs.add("�n�J����: " + e.getMessage());
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
				// 1.�����ШD�Ѽ� �Ѽƿ��~�B�z
				String empno = req.getParameter("empno");
				
				String empstate = req.getParameter("empstate");
				String newState = null;
				if (empstate.equals("1")) {
					newState = "0";
				} else {
					newState = "1";
				}

				// 2.�}�l�ק���
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmp(empno);

				employeeSvc.updateEmp(employeeVO.getEmpid(), employeeVO.getEmppw(), employeeVO.getEmpname(),
						employeeVO.getEmpemail(), employeeVO.getEmpbirth(), newState, empno);

				// 3.�ק令�\ �ǳ����
				String successMsg = "���A�ק令�\";
				req.setAttribute("successMsg", successMsg);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/employee/empListAll.jsp?target=emp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("���A�ק異��:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empListAll.jsp?target=emp");
				failureView.forward(req, res);
			}
		}
	}
}