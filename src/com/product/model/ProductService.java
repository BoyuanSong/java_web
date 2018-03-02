package com.product.model;

import java.util.List;

import com.msgbroad.model.MsgbroadDAO_interface;
import com.msgbroad.model.MsgbroadVO;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		this.dao = new ProductDAO();
	}

	public List<ProductVO> getAllForShop() { // 有上架的照分類排序
		return dao.getAllForShop();
	}

	public List<ProductVO> getAll() { // 全部照perno排序
		return dao.getAll();
	}

	public void delete(String prono) {
		dao.delete(prono);
	}

	public void updateState(String prostate, String prono) {
		dao.updateState(prostate, prono);
	}

	public void update(String proname, String classno, int proprice, String prodesc, byte[] propic, String prono) {
		ProductVO productVO = new ProductVO();
		productVO.setProname(proname);
		productVO.setClassno(classno);
		productVO.setProprice(proprice);
		productVO.setProdesc(prodesc);
		productVO.setPropic(propic);
		productVO.setProno(prono);
		dao.update(productVO);
	}

	public ProductVO getOneProduct(String prono) {
		return dao.findByPrimaryKey(prono);
	}
	
	public List<ProductVO> getSomeByClassno(String classno) {
		return dao.getSomeByClassno(classno);
	}

	public ProductVO addProduct(String proname,String classno,int proprice,String prodesc,String prostate,byte[] propic,String empno) {
		ProductVO productVO = new ProductVO();
		productVO.setProname(proname);
		productVO.setClassno(classno);
		productVO.setProprice(proprice);
		productVO.setProdesc(prodesc);
		productVO.setProstate(prostate);
		productVO.setPropic(propic);
		productVO.setEmpno(empno);
		dao.insert(productVO);
		return productVO;
	}
}
