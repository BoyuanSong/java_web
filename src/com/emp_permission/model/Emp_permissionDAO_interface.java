package com.emp_permission.model;

import java.util.Set;

public abstract interface Emp_permissionDAO_interface {

	public Set<Emp_permissionVO> getAllByEmpno(String empno);

	public void update(String empno, String[] perm);
}
