package com.product_class.model;

import java.util.List;

public interface Product_classDAO_interface {
	
	public List<Product_classVO> getAll();
	
	public String getClassname(String classno);
}
