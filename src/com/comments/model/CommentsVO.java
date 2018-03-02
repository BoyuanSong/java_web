package com.comments.model;

import java.io.Serializable;

public class CommentsVO implements Serializable {

	private String commentno;
	private String msgno;
	private String empno;
	private String empmsg;
	private String day;
	private String hour;

	public String getCommentno() {
		return commentno;
	}

	public void setCommentno(String commentno) {
		this.commentno = commentno;
	}

	public String getMsgno() {
		return msgno;
	}

	public void setMsgno(String msgno) {
		this.msgno = msgno;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getEmpmsg() {
		return empmsg;
	}

	public void setEmpmsg(String empmsg) {
		this.empmsg = empmsg;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}
}
