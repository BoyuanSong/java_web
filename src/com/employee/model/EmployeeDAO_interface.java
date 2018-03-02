package com.employee.model;

import java.util.List;

public abstract interface EmployeeDAO_interface {

	public void insert(EmployeeVO employeeVO);

	public void update(EmployeeVO employeeVO);

	public void delete(String empno);

	public EmployeeVO findByPrimaryKey(String empno);

	public EmployeeVO findByEmpid(String empid);

	public List<EmployeeVO> getAll();
}
