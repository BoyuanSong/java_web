package com.emp_permission.model;

import java.util.Set;

public class Emp_permissionService {

	private Emp_permissionDAO_interface dao;
	public Emp_permissionService() {
		dao = new Emp_permissionDAO();
	}

	public Set<Emp_permissionVO> getAllByEmpno(String empno) {
		return dao.getAllByEmpno(empno);
	}

	public void updatePermission(String empno, String[] perm) {
		dao.update(empno, perm);
	}
}