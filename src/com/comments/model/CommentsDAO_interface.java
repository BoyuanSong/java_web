package com.comments.model;

import java.util.List;

public abstract interface CommentsDAO_interface {

	public List<CommentsVO> getSomeByMsgno(String msgno);

	public void insert(CommentsVO commentsVO);

	public void deleteSomeByMsgno(String msgno);
}
