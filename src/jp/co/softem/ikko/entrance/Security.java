package jp.co.softem.ikko.entrance;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import jp.co.softem.ikko.core.eis.Employee;
import jp.co.softem.ikko.service.EmployeeService;

@ManagedBean
@RequestScoped
public class Security implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private EmployeeService service;

	private Employee employee;

	@PostConstruct
	public void init() {
		employee = new Employee();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setService(EmployeeService dao) {
		this.service = dao;
	}

	public String login() {
		String loginId = employee.getLoginId();
		String password = employee.getPassword();
		Employee emp = service.find(loginId, password);
		if (emp == null) {
			addFlush("e", "ログインできません。");
			return null;
		} else {
			return "main?faces-redirect=true";
		}
	}

	private void addFlush(String key, String value) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		flash.put(key, value);
	}

}
