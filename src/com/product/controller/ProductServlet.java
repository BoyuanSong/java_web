package com.product.controller;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.product.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) { // from productAdd.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.�����ШD�Ѽ� ��J�榡�����~�B�z
				String proname = req.getParameter("proname").trim();
				if (proname == null || proname.length() == 0) {
					errorMsgs.add("�п�J�ӫ~�W��");
				}

				String classno = req.getParameter("classno");
				if (classno.equals("X")){
					errorMsgs.add("�п�����O");
				}

				Integer proprice = null;
				try {
					proprice = new Integer(req.getParameter("proprice"));
				} catch (NumberFormatException e) {
					proprice = 0;
					errorMsgs.add("�п�J�ӫ~����");
				}
				proprice = (int) proprice;

				String prodesc = req.getParameter("prodesc").trim();
				if (prodesc == null || prodesc.length() == 0) {
					errorMsgs.add("�п�J�ӫ~����");
				}

				String prostate = "1";

				byte[] propic = null;
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					if (part.getContentType() != null) {
						InputStream in = part.getInputStream();
						propic = new byte[in.available()];
						in.read(propic);
						in.close();
					}
				}
				
				String empno = req.getParameter("empno");

				ProductVO productVO = new ProductVO();
				productVO.setProname(proname);
				productVO.setClassno(classno);
				productVO.setProprice(proprice);
				productVO.setProdesc(prodesc);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/front-web/productAdd.jsp?target=front");
					failureView.forward(req, res);
					return;
				}

				// 2.�}�l�s�W���
				ProductService productSvc = new ProductService();
				productSvc.addProduct(proname, classno, proprice, prodesc, prostate, propic, empno);

				// 3.�s�W����,�ǳ����
				String url = "/back-end/front-web/product.jsp?target=front";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/front-web/productAdd.jsp?target=front");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // from product.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.�����ШD�Ѽ�
				String prono = req.getParameter("prono");

				// 2.�}�l�R���峹
				ProductService productService = new ProductService();
				productService.delete(prono);
				
				// 3.�R������ �ǳ����
				String url = "/back-end/front-web/product.jsp?target=front";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("�R������: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/front-web/product.jsp?target=front");
				failureView.forward(req, res);
			}
		}

		if ("changeState".equals(action)) { // from product.jsp

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.�����ШD�Ѽ�
				String prono = req.getParameter("prono");
				String prostate = req.getParameter("prostate");
				
				// 2.�}�l�ק�, 1=�W�[ 0=�U�[
				String newstate = null;
				if (prostate.equals("1")) {
					newstate = "0";
				} else {
					newstate = "1";
				}
				ProductService productService = new ProductService();
				productService.updateState(newstate, prono);

				// 3.�ק粒�� �ǳ����
				String url = "/back-end/front-web/product.jsp?target=front";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("�ק�W�U�[����: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/front-web/product.jsp?target=front");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // from product.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.�����ШD�Ѽ�
				String prono = req.getParameter("prono");

				// 2.�}�l�d�߸��
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(prono);

				// 3.�d�ߧ��� �ǳ����
				req.setAttribute("productVO", productVO);
				String url = "/back-end/front-web/productEdit.jsp?target=front-web";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���~:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/front-web/product.jsp?target=front-web");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // from productEdit.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.�����ШD�Ѽ� ��J�榡�����~�B�z
				String prono = req.getParameter("prono");

				String proname = req.getParameter("proname").trim();
				if (proname == null || proname.length() == 0) {
					errorMsgs.add("�п�J�ӫ~�W��");
				}

				String classno = req.getParameter("classno");
				if (classno.equals("X")){
					errorMsgs.add("�п�����O");
				}
				
				Integer proprice = null;
				try {
					proprice = new Integer(req.getParameter("proprice"));
				} catch (NumberFormatException e) {
					proprice = 0;
					errorMsgs.add("����ж�g�Ʀr");
				}
				proprice = (int) proprice;

				String prodesc = req.getParameter("prodesc").trim();
				if (prodesc == null || prodesc.length() == 0) {
					errorMsgs.add("�п�J�ӫ~����");
				}

				byte[] propic = null;
				int hasin = 0;
				Part part = req.getPart("propic");
				if (part.getContentType() != null) {
					InputStream in = part.getInputStream();
					hasin = in.available();
					propic = new byte[in.available()];
					in.read(propic);
					in.close();
				}

				ProductVO productVO = new ProductVO();
				productVO.setProname(proname);
				productVO.setClassno(classno);
				productVO.setProprice(proprice);
				productVO.setProdesc(prodesc);
				productVO.setProno(prono);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/front-web/productEdit.jsp?target=front");
					failureView.forward(req, res);
					return;
				}
				// 2.�}�l�ק���
				ProductService productService = new ProductService();
				if (hasin == 0) {
					ProductVO proVO = productService.getOneProduct(prono);
					propic = proVO.getPropic();
				}
				productService.update(proname, classno, proprice, prodesc, propic, prono);
				
				// 3.�ק粒��,�ǳ����
				String url = "/back-end/front-web/product.jsp?target=front";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/front-web/productEdit.jsp?target=front");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // from product.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.�����ШD�Ѽ�
				String prono = new String(req.getParameter("prono"));

				// 2.�}�l�R�����
				ProductService productService = new ProductService();
				productService.delete(prono);
				
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/front-web/productEdit.jsp?target=front");
				failureView.forward(req, res);
				
			}
		}

		if ("view".equals(action)) { // from �e��shop��Ƨ���������
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.�����ШD�Ѽ�
				String prono = req.getParameter("prono");

				// 2.�}�l�d�߸��
				ProductService proSvc = new ProductService();
				ProductVO productVO = proSvc.getOneProduct(prono);

				// 3.�d�ߧ���,�ǳ����
				req.setAttribute("productVO", productVO);

				String url = "/front-end/shop/productDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�s�������:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/news/news.jsp?target=web");
				failureView.forward(req, res);
			}
		}
	}

	// ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
