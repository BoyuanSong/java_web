package com.product_class.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Product_classJNDIDAO implements Product_classDAO_interface {

	private static final String GET_ALL_STMT = "SELECT classno,classname FROM product_class";
	private static final String GET_CLASSNAME = "SELECT classname FROM product_class WHERE classno=?";

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Product_classVO> getAll() {

		List<Product_classVO> list = new ArrayList<Product_classVO>();
		Product_classVO product_classVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_classVO = new Product_classVO();
				product_classVO.setClassno(rs.getString("classno"));
				product_classVO.setClassname(rs.getString("classname"));
				list.add(product_classVO);
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public String getClassname(String classno) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String classname = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CLASSNAME);
			pstmt.setString(1, classno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				classname = rs.getString("classname");
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return classname;
	}
}