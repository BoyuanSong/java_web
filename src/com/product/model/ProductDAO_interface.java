package com.product.model;

import java.util.List;

public interface ProductDAO_interface {

	public void insert(ProductVO productVO);

	public void update(ProductVO productVO);

	public void updateState(String prostate, String prono);// ��W�U�[

	public void delete(String prono);

	public ProductVO findByPrimaryKey(String prono);

	public List<ProductVO> getAllForShop();//���W�[�� �Ӥ�����  �e�ݥ�

 	public List<ProductVO> getSomeByClassno(String classno);

	public List<ProductVO> getAll();//�ӽs���� ��ݥ�


}
