package com.product_class.model;

import java.io.Serializable;

public class Product_classVO implements Serializable {

	private String classno;
	private String classname;

	public String getClassno() {
		return classno;
	}

	public void setClassno(String classno) {
		this.classno = classno;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
}
