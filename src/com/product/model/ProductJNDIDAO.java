package com.product.model;

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

import com.msgbroad.model.MsgbroadVO;

public class ProductJNDIDAO implements ProductDAO_interface {

	private static final String GET_ONE_STMT = "SELECT prono, proname, classno, proprice, prostate, prodesc, propic, empno FROM product WHERE prono=?";

	// 用classno排序 顯示上架中的 前端商城用
	private static final String GET_ALL_FOR_SHOP = 
			"SELECT prono, proname, classno, proprice, prostate, prodesc, propic, empno FROM product WHERE prostate=1 ORDER BY classno";
	
	// 選取特定classno 顯示上架中的 前端商城用
	private static final String GET_SOME_BY_CLASSNO =
			"SELECT prono, proname, classno, proprice, prostate, prodesc, propic, empno FROM product WHERE classno=?";

	// 用prono排序 後端商品管理用
	private static final String GET_ALL_ORDER_BY_PRONO = 
			"SELECT prono, proname, classno, proprice, prostate, prodesc, propic, empno FROM product ORDER BY prono DESC";

	private static final String INSERT_STMT = 
			"INSERT INTO product (prono, proname, classno, proprice, prostate, prodesc, propic, empno) "
			
		  + "VALUES (lpad(to_char(product_SEQ .NEXTVAL),6 ,'0'), ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE_STATE = "UPDATE product SET prostate=? WHERE prono=?";

	private static final String UPDATE = "UPDATE product SET proname=?, classno=?, proprice=?, prodesc=?, propic=? WHERE prono=?";

	private static final String DELETE = "DELETE FROM product WHERE prono=?";

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
	public void updateState(String prostate, String prono) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATE);
			pstmt.setString(1, prostate);
			pstmt.setString(2, prono);
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	}

	@Override
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, productVO.getProname());
			pstmt.setString(2, productVO.getClassno());
			pstmt.setInt(3, productVO.getProprice());
			pstmt.setString(4, productVO.getProdesc());
			pstmt.setBytes(5, productVO.getPropic());
			pstmt.setString(6, productVO.getProno());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	}

	@Override
	public ProductVO findByPrimaryKey(String prono) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, prono);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProno(rs.getString("prono"));
				productVO.setProname(rs.getString("proname"));
				productVO.setClassno(rs.getString("classno"));
				productVO.setProprice(rs.getInt("proprice"));
				productVO.setProstate(rs.getString("prostate"));
				productVO.setProdesc(rs.getString("prodesc"));
				productVO.setPropic(rs.getBytes("propic"));
				productVO.setEmpno(rs.getString("empno"));
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
		return productVO;
	}

	@Override
	public List<ProductVO> getAllForShop() {

		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_FOR_SHOP);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProno(rs.getString("prono"));
				productVO.setProname(rs.getString("proname"));
				productVO.setClassno(rs.getString("classno"));
				productVO.setProprice(rs.getInt("proprice"));
				productVO.setProstate(rs.getString("prostate"));
				productVO.setProdesc(rs.getString("prodesc"));
				productVO.setPropic(rs.getBytes("propic"));
				productVO.setEmpno(rs.getString("empno"));
				list.add(productVO);
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
	public List<ProductVO> getSomeByClassno(String classno) {

		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SOME_BY_CLASSNO);
			pstmt.setString(1, classno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProno(rs.getString("prono"));
				productVO.setProname(rs.getString("proname"));
				productVO.setClassno(rs.getString("classno"));
				productVO.setProprice(rs.getInt("proprice"));
				productVO.setProstate(rs.getString("prostate"));
				productVO.setProdesc(rs.getString("prodesc"));
				productVO.setPropic(rs.getBytes("propic"));
				productVO.setEmpno(rs.getString("empno"));
				list.add(productVO);
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
	public List<ProductVO> getAll() {

		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ORDER_BY_PRONO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProno(rs.getString("prono"));
				productVO.setProname(rs.getString("proname"));
				productVO.setClassno(rs.getString("classno"));
				productVO.setProprice(rs.getInt("proprice"));
				productVO.setProstate(rs.getString("prostate"));
				productVO.setProdesc(rs.getString("prodesc"));
				productVO.setPropic(rs.getBytes("propic"));
				productVO.setEmpno(rs.getString("empno"));
				list.add(productVO);
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
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, productVO.getProname());
			pstmt.setString(2, productVO.getClassno());
			pstmt.setInt(3, productVO.getProprice());
			pstmt.setString(4, productVO.getProstate());
			pstmt.setString(5, productVO.getProdesc());
			pstmt.setBytes(6, productVO.getPropic());
			pstmt.setString(7, productVO.getEmpno());
			pstmt.executeUpdate();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	}

	@Override
	public void delete(String prono) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, prono);
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	}
}