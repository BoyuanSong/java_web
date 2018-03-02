package com.product.model;

import java.util.List;

public interface ProductDAO_interface {

	public void insert(ProductVO productVO);

	public void update(ProductVO productVO);

	public void updateState(String prostate, String prono);// 改上下架

	public void delete(String prono);

	public ProductVO findByPrimaryKey(String prono);

	public List<ProductVO> getAllForShop();//有上架的 照分類排  前端用

 	public List<ProductVO> getSomeByClassno(String classno);

	public List<ProductVO> getAll();//照編號排 後端用


}
