package util;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.product.model.ProductService;
import com.product.model.ProductVO;

public class DBJpgReader4 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/jpg");
		ServletOutputStream out = res.getOutputStream();

		try {
			String prono = req.getParameter("prono");
			if (prono != null) {
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(prono);
				out.write(productVO.getPropic());
			}
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/images/nopic.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}
}
