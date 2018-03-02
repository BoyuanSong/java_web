package com.employee.model;

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

public class EmployeeDAO implements EmployeeDAO_interface {

	private static final String GET_ONE_BY_EMPNO = "SELECT empno, empid, emppw, empname, empemail, empbirth, empstate FROM employee WHERE empno=? ";

	private static final String GET_ONE_BY_EMPID = "SELECT empno, empid, emppw, empname, empemail, empbirth, empstate FROM employee WHERE empid=? ";

	private static final String INSERT_STMT = 
			
			"INSERT INTO employee (empno,empid,emppw,empname,empemail,empbirth,empstate) VALUES (lpad(to_char(employee_SEQ.NEXTVAL),6,'0'),?,?,?,?,?,?)";

	private static final String GET_ALL_STMT = "SELECT empno, empid, emppw, empname, empemail, empbirth, empstate FROM employee ORDER BY empno DESC";

	private static final String DELETE = "DELETE FROM employee WHERE empno=?";

	private static final String UPDATE = "UPDATE employee SET empid=?, emppw=?, empname=?, empemail=?, empbirth=?, empstate=? WHERE empno=?";

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
	public EmployeeVO findByEmpid(String empid) {

		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_EMPID);
			pstmt.setString(1, empid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				employeeVO = new EmployeeVO();
				employeeVO.setEmpno(rs.getString("empno"));
				employeeVO.setEmpid(rs.getString("empid"));
				employeeVO.setEmppw(rs.getString("emppw"));
				employeeVO.setEmpname(rs.getString("empname"));
				employeeVO.setEmpemail(rs.getString("empemail"));
				employeeVO.setEmpbirth(rs.getDate("empbirth"));
				employeeVO.setEmpstate(rs.getString("empstate"));
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
		return employeeVO;
	}

	@Override
	public void insert(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, employeeVO.getEmpid());
			pstmt.setString(2, employeeVO.getEmppw());
			pstmt.setString(3, employeeVO.getEmpname());
			pstmt.setString(4, employeeVO.getEmpemail());
			pstmt.setDate(5, employeeVO.getEmpbirth());
			pstmt.setString(6, employeeVO.getEmpstate());
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
	public void update(EmployeeVO employeeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, employeeVO.getEmpid());
			pstmt.setString(2, employeeVO.getEmppw());
			pstmt.setString(3, employeeVO.getEmpname());
			pstmt.setString(4, employeeVO.getEmpemail());
			pstmt.setDate(5, employeeVO.getEmpbirth());
			pstmt.setString(6, employeeVO.getEmpstate());
			pstmt.setString(7, employeeVO.getEmpno());
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
	public void delete(String empno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, empno);
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
	public EmployeeVO findByPrimaryKey(String empno) {

		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_EMPNO);
			pstmt.setString(1, empno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				employeeVO = new EmployeeVO();
				employeeVO.setEmpno(rs.getString("empno"));
				employeeVO.setEmpid(rs.getString("empid"));
				employeeVO.setEmppw(rs.getString("emppw"));
				employeeVO.setEmpname(rs.getString("empname"));
				employeeVO.setEmpemail(rs.getString("empemail"));
				employeeVO.setEmpbirth(rs.getDate("empbirth"));
				employeeVO.setEmpstate(rs.getString("empstate"));
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
		return employeeVO;
	}

	@Override
	public List<EmployeeVO> getAll() {

		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {	
				employeeVO = new EmployeeVO();
				employeeVO.setEmpno(rs.getString("empno"));
				employeeVO.setEmpid(rs.getString("empid"));
				employeeVO.setEmppw(rs.getString("emppw"));
				employeeVO.setEmpname(rs.getString("empname"));
				employeeVO.setEmpemail(rs.getString("empemail"));
				employeeVO.setEmpbirth(rs.getDate("empbirth"));
				employeeVO.setEmpstate(rs.getString("empstate"));
				list.add(employeeVO);
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
}