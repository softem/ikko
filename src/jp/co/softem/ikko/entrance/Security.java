package jp.co.softem.ikko.entrance;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import jp.co.softem.ikko.common.Utils;
import jp.co.softem.ikko.core.eis.Employee;
import jp.co.softem.ikko.service.EmployeeService;

@ManagedBean
@RequestScoped
public class Security implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final ResourceBundle RB = ResourceBundle
			.getBundle("messages");

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

	public void setService(EmployeeService service) {
		this.service = service;
	}

	public String login() {
		Utils.putSession("employee", employee);
		return "main?faces-redirect=true";
	}

	public void validate(ComponentSystemEvent e) {
		UIForm form = (UIForm) e.getComponent();
		String loginId = (String) Utils.getUIInputValue(form, "loginId");
		String password = (String) Utils.getUIInputValue(form, "password");
		Employee emp = service.find(loginId, password);
		if (emp == null) {
			FacesContext fc = FacesContext.getCurrentInstance();
			String message = RB.getString("errors.login.failed");
			fc.addMessage(form.getClientId(), new FacesMessage(message));
			fc.renderResponse();
		}
	}

}
