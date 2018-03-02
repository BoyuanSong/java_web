package com.comments.model;

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

public class CommentsDAO implements CommentsDAO_interface {

	private static final String GET_SAME_MSGNO = "SELECT empno, empmsg, to_char(time,'MM/DD') day, to_char(time,'HH24:MI') hour FROM comments WHERE msgno=?";

	private static final String INSERT_STMT = "INSERT INTO comments VALUES (lpad(to_char(comments_SEQ .NEXTVAL),6 ,'0'),?,?,?,sysdate)";

	private static final String DELETE = "DELETE FROM comments WHERE msgno=?";

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
	public List<CommentsVO> getSomeByMsgno(String msgno) {
		List<CommentsVO> list = new ArrayList<CommentsVO>();
		CommentsVO commentsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SAME_MSGNO);
			pstmt.setString(1, msgno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				commentsVO = new CommentsVO();
				commentsVO.setEmpno(rs.getString("empno"));
				commentsVO.setEmpmsg(rs.getString("empmsg"));
				commentsVO.setDay(rs.getString("day"));
				commentsVO.setHour(rs.getString("hour"));
				list.add(commentsVO);
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
	public void insert(CommentsVO commentsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, commentsVO.getMsgno());
			pstmt.setString(2, commentsVO.getEmpno());
			pstmt.setString(3, commentsVO.getEmpmsg());
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
	public void deleteSomeByMsgno(String msgno) {
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
}