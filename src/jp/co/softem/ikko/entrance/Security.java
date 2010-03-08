package jp.co.softem.ikko.entrance;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import jp.co.softem.ikko.core.eis.Employee;
import jp.co.softem.ikko.service.EmployeeService;

@ManagedBean
@RequestScoped
public class Security implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{employeeService}")
	private EmployeeService service;

	private Employee employee;

	private String message;

	@PostConstruct
	public void init() {
		employee = new Employee();
	}

	public Employee getEmployee() {
		return employee;
	}

	public String getMessage() {
		return message;
	}

	public boolean isError() {
		return (message != null && message.length() > 0);
	}

	public void setService(EmployeeService dao) {
		this.service = dao;
	}

	public String login() {
		String loginId = employee.getLoginId();
		String password = employee.getPassword();
		Employee emp = service.find(loginId, password);
		if (emp == null) {
			message = "ログインできません。";
			return "index";
		} else {
			return "main?faces-redirect=true";
		}
	}

}
