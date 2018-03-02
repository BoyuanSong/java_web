package com.emp_permission.model;

import java.io.Serializable;

public class Emp_permissionVO implements Serializable {
	
	private String empno;
	private String permno;

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getPermno() {
		return permno;
	}

	public void setPermno(String permno) {
		this.permno = permno;
	}
}