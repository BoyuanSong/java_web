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
				// 1.�����ШD�Ѽ�
				Map<String, String[]> map = req.getParameterMap();
				String empno = map.get("empno")[0];
				String empname = map.get("empname")[0];
				String[] perm = map.get("perm");

				// 2.�}�l�ק���
				Emp_permissionService emp_permissionSvc = new Emp_permissionService();
				emp_permissionSvc.updatePermission(empno, perm);

				// 3.�ק粒�� �ǳ����
				String successMsg = "���\�ק� " + empname + " ���v��";
				req.setAttribute("successMsg", successMsg);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/employee/empListAll.jsp?target=emp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�ק��v������: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empListAll.jsp?target=emp" );
				failureView.forward(req, res);
			}
		}

		if ("getOneEmp_perm".equals(action)) { // from empListAll.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.�����ШD�Ѽ�
				String empno = req.getParameter("empno");
				String empname = req.getParameter("empname");

				// 2.�}�l�d�߭��u�v��
				Emp_permissionService emp_permSvc = new Emp_permissionService();
				Set<Emp_permissionVO> set = emp_permSvc.getAllByEmpno(empno);
				
				List<String> perm = new ArrayList();
				for (Emp_permissionVO permVO : set) {
					perm.add(permVO.getPermno());
				}

				// 3.�d�ߧ��� �ǳ����
				req.setAttribute("empname", empname);
				req.setAttribute("empno", empno);
				req.setAttribute("perm", perm);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/employee/empChangePerm.jsp?target=emp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("����v������: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/empListAll.jsp?target=emp");
				failureView.forward(req, res);
			}
		}
	}
}