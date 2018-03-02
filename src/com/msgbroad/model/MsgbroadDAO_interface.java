package com.msgbroad.model;

import java.util.List;
import java.util.Map;

public abstract interface MsgbroadDAO_interface {
	
	public void insert(MsgbroadVO msgbroadVO);

	public void delete(String msgno);

	public void update(MsgbroadVO msgbroadVO);

	public MsgbroadVO findByPrimaryKey(String msgno);

	public List<MsgbroadVO> getAll();

	public List<MsgbroadVO> getAll(Map<String, String[]> map);//½Æ¦X¬d¸ß¥Î
}