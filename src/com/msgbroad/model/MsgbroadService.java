package com.msgbroad.model;

import java.util.HashMap;
import java.util.List;

public class MsgbroadService {
	
	private MsgbroadDAO_interface dao;

	public MsgbroadService() {
		this.dao = new MsgbroadDAO();
	}

	public MsgbroadVO addMsg(String empno, String title, String msg) {

		MsgbroadVO msgbroadVO = new MsgbroadVO();
		msgbroadVO.setEmpno(empno);
		msgbroadVO.setTitle(title);
		msgbroadVO.setMsg(msg);
		dao.insert(msgbroadVO);
		return msgbroadVO;
	}

	public MsgbroadVO updateMsg(String title, String msg, String msgno) {
		
		MsgbroadVO msgbroadVO = new MsgbroadVO();
		msgbroadVO.setTitle(title);
		msgbroadVO.setMsg(msg);
		msgbroadVO.setMsgno(msgno);
		dao.update(msgbroadVO);
		return msgbroadVO;
	}

	public void deleteMsg(String msgno) {
		dao.delete(msgno);
	}

	public MsgbroadVO getOneMsg(String msgno) {
		return dao.findByPrimaryKey(msgno);
	}

	public List<MsgbroadVO> getAll() {
		return dao.getAll();
	}
	
	//½Æ¦X¬d¸ß
	public List<MsgbroadVO> getAll(HashMap<String, String[]> map) {
		return dao.getAll(map);
	}
}