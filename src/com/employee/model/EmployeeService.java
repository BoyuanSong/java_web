package com.employee.model;

import java.sql.Date;
import java.util.List;

public class EmployeeService {

	private EmployeeDAO_interface dao;

	public EmployeeService() {
		dao = new EmployeeDAO();
	}

	public EmployeeVO getOneEmp(String empno) {
		return dao.findByPrimaryKey(empno);
	}

	public EmployeeVO getOneByEmpid(String empid) {
		return dao.findByEmpid(empid);
	}

	public EmployeeVO addEmployee(String empid, String emppw, String empname, String empemail, Date empbirth, String empstate) {

		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmpid(empid);
		employeeVO.setEmppw(emppw);
		employeeVO.setEmpname(empname);
		employeeVO.setEmpemail(empemail);
		employeeVO.setEmpbirth(empbirth);
		employeeVO.setEmpstate(empstate);
		dao.insert(employeeVO);
		return employeeVO;
	}

	public void deleteEmployee(String empno) {
		dao.delete(empno);
	}

	public EmployeeVO updateEmp(String empid, String emppw, String empname, String empemail, Date empbirth, String empstate, String empno) {

		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmpid(empid);
		employeeVO.setEmppw(emppw);
		employeeVO.setEmpname(empname);
		employeeVO.setEmpemail(empemail);
		employeeVO.setEmpbirth(empbirth);
		employeeVO.setEmpstate(empstate);
		employeeVO.setEmpno(empno);
		dao.update(employeeVO);
		return employeeVO;
	}

	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}
}