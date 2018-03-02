package com.comments.model;

import java.util.List;

public class CommentsService {
	
	CommentsDAO_interface dao;
	public CommentsService() {
		dao = new CommentsDAO();
	}

	public List<CommentsVO> getSomeComments(String msgno) {
		return dao.getSomeByMsgno(msgno);
	}

	public void addComments(String msgno, String empno, String empmsg) {
		CommentsVO commentsVO = new CommentsVO();
		commentsVO.setMsgno(msgno);
		commentsVO.setEmpno(empno);
		commentsVO.setEmpmsg(empmsg);
		dao.insert(commentsVO);
	}

	public void deleteSomeComments(String msgno) {
		dao.deleteSomeByMsgno(msgno);
	}
}
