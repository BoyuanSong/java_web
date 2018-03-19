package com.emp_permission.model;

import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Emp_permissionJNDIDAO implements Emp_permissionDAO_interface {

	private static final String DELETE_ALL_BY_EMPNO = "DELETE FROM emp_permission WHERE empno=?";

	private static final String GET_ALL_BY_EMPNO = "SELECT empno,permno FROM emp_permission WHERE empno=?";

	private static final String INSERT_STMT = "INSERT INTO emp_permission (empno, permno) VALUES (?, ?)";

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
	public Set<Emp_permissionVO> getAllByEmpno(String empno) {

		Set<Emp_permissionVO> set = new HashSet<Emp_permissionVO>();
		Emp_permissionVO emp_permissionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_EMPNO);
			pstmt.setString(1, empno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				emp_permissionVO = new Emp_permissionVO();
				emp_permissionVO.setEmpno(rs.getString("empno"));
				emp_permissionVO.setPermno(rs.getString("permno"));
				set.add(emp_permissionVO);
			}

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
		return set;
	}

	@Override
	public void update(String empno, String[] perm) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除員工所有權限
			pstmt = con.prepareStatement(DELETE_ALL_BY_EMPNO);
			pstmt.setString(1, empno);
			pstmt.executeUpdate();

			// 再把選定的權限 一個一個新增
			if(perm != null){
				for (String permno : perm) {
					pstmt = con.prepareStatement(INSERT_STMT);
					pstmt.setString(1, empno);
					pstmt.setString(2, permno);
					pstmt.executeUpdate();
				}
			}
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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