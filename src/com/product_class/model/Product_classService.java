package com.product_class.model;

import java.util.List;

public class Product_classService {

	Product_classDAO_interface dao;

	public Product_classService() {
		dao = new Product_classDAO();
	}

	public List<Product_classVO> getAll() {
		return dao.getAll();
	}
	public String getClassname(String classno) {
		return dao.getClassname(classno);
	}
	
}
