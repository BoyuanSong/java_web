package com.msgbroad.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;
import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Msg;

public class MsgbroadJNDIDAO implements MsgbroadDAO_interface {

	private static final String GET_ONE_STMT =

			"SELECT msgno, empno, title, msg, to_char(time,'HH24:MI') hour, to_char(time,'YYYY') year, to_char(time,'MM/DD') day "

		  + "FROM msgbroad WHERE msgno=?";

	private static final String GET_ALL_STMT =

			"SELECT msgno, empno, title, msg, to_char(time,'HH24:MI') hour, to_char(time,'YYYY') year, to_char(time,'MM/DD') day "

		  + "FROM msgbroad ORDER BY msgno DESC";

	private static final String INSERT_STMT = "INSERT INTO msgbroad VALUES (lpad(to_char(msgbroad_SEQ.NEXTVAL),6 ,'0'),?,?,?,sysdate)";

	private static final String UPDATE = "UPDATE msgbroad SET title=?, msg=? WHERE msgno=?";

	private static final String DELETE = "DELETE FROM msgbroad WHERE msgno=?";

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
	public MsgbroadVO findByPrimaryKey(String msgno) {

		MsgbroadVO msgbroadVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, msgno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				msgbroadVO = new MsgbroadVO();
				msgbroadVO.setMsgno(rs.getString("msgno"));
				msgbroadVO.setEmpno(rs.getString("empno"));
				msgbroadVO.setTitle(rs.getString("title"));
				msgbroadVO.setMsg(rs.getString("msg"));
				msgbroadVO.setHour(rs.getString("hour"));
				msgbroadVO.setYear(rs.getString("year"));
				msgbroadVO.setDay(rs.getString("day"));
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
		return msgbroadVO;
	}

	@Override
	public void delete(String msgno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, msgno);
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
	public void update(MsgbroadVO msgbroadVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, msgbroadVO.getTitle());
			pstmt.setString(2, msgbroadVO.getMsg());
			pstmt.setString(3, msgbroadVO.getMsgno());
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
	public List<MsgbroadVO> getAll() {

		List<MsgbroadVO> list = new ArrayList<MsgbroadVO>();
		MsgbroadVO msgbroadVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				msgbroadVO = new MsgbroadVO();
				msgbroadVO.setMsgno(rs.getString("msgno"));
				msgbroadVO.setEmpno(rs.getString("empno"));
				msgbroadVO.setTitle(rs.getString("title"));
				msgbroadVO.setMsg(rs.getString("msg"));
				msgbroadVO.setHour(rs.getString("hour"));
				msgbroadVO.setYear(rs.getString("year"));
				msgbroadVO.setDay(rs.getString("day"));
				list.add(msgbroadVO);
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
	public void insert(MsgbroadVO msgbroadVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, msgbroadVO.getEmpno());
			pstmt.setString(2, msgbroadVO.getTitle());
			pstmt.setString(3, msgbroadVO.getMsg());
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
	public List<MsgbroadVO> getAll(Map<String, String[]> map) {
		List<MsgbroadVO> list = new ArrayList<MsgbroadVO>();
		MsgbroadVO msgbroadVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			
			// 在 employee和msgbroad join後的表格 用empname,title來查詢
			String finalSQL =

					"SELECT m.msgno, m.empno, m.title, m.msg, to_char(time,'HH24:MI') hour,to_char(time,'YYYY') year, to_char(time,'MM/DD') day "

				  + "FROM msgbroad m JOIN employee e ON m.empno = e.empno "

				  +  jdbcUtil_CompositeQuery_Msg.get_WhereCondition(map) 
				  
				  + "ORDER BY msgno DESC";
			
			pstmt = con.prepareStatement(finalSQL);
			//System.out.println("●●finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				msgbroadVO = new MsgbroadVO();
				msgbroadVO.setMsgno(rs.getString("msgno"));
				msgbroadVO.setEmpno(rs.getString("empno"));
				msgbroadVO.setTitle(rs.getString("title"));
				msgbroadVO.setMsg(rs.getString("msg"));
				msgbroadVO.setHour(rs.getString("hour"));
				msgbroadVO.setYear(rs.getString("year"));
				msgbroadVO.setDay(rs.getString("day"));
				list.add(msgbroadVO);
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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