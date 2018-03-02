package com.employee.model;

import java.io.Serializable;
import java.sql.Date;

public class EmployeeVO implements Serializable {

	private String empno;
	private String empid;
	private String emppw;
	private String empname;
	private String empemail;
	private Date empbirth;
	private String empstate;

	public Date getEmpbirth() {
		return empbirth;
	}

	public void setEmpbirth(Date empbirth) {
		this.empbirth = empbirth;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmppw() {
		return emppw;
	}

	public void setEmppw(String emppw) {
		this.emppw = emppw;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getEmpemail() {
		return empemail;
	}

	public void setEmpemail(String empemail) {
		this.empemail = empemail;
	}

	public String getEmpstate() {
		return empstate;
	}

	public void setEmpstate(String empstate) {
		this.empstate = empstate;
	}
}
