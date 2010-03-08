package jp.co.softem.ikko.employee;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import jp.co.softem.ikko.core.eis.Employee;
import jp.co.softem.ikko.service.EmployeeService;

@ManagedBean
@RequestScoped
public class Index implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{employeeService}")
	private EmployeeService service;

	private List<Employee> list;

	@PostConstruct
	public void init() {
		list = service.findAll();
	}

	public List<Employee> getList() {
		return list;
	}

	public void setService(EmployeeService dao) {
		this.service = dao;
	}

}
