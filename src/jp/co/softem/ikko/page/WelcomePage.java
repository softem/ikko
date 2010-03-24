package jp.co.softem.ikko.page;

import javax.ejb.EJB;
import javax.inject.Named;

import jp.co.softem.ikko.core.BasePage;
import jp.co.softem.ikko.eis.Employee;
import jp.co.softem.ikko.service.EmployeeService;
import jp.co.softem.ikko.service.EventInfoService;

import org.t2framework.commons.annotation.composite.RequestScope;
import org.t2framework.commons.util.Logger;
import org.t2framework.t2.action.ErrorInfo;
import org.t2framework.t2.annotation.composite.GET;
import org.t2framework.t2.annotation.composite.POST;
import org.t2framework.t2.annotation.core.ActionParam;
import org.t2framework.t2.annotation.core.ActionPath;
import org.t2framework.t2.annotation.core.Default;
import org.t2framework.t2.annotation.core.Form;
import org.t2framework.t2.annotation.core.Page;
import org.t2framework.t2.contexts.Request;
import org.t2framework.t2.navigation.Forward;
import org.t2framework.t2.spi.Navigation;

@RequestScope
@Named
@Page("welcome")
public class WelcomePage extends BasePage {

	@EJB
	EmployeeService service;

	@EJB
	EventInfoService eventInfoService;

	@Default
	public Navigation index() {
		pageInfo.setPage("welcome");
		return Forward.to(TEMPLATE);
	}

	@POST
	@ActionParam
	public Navigation login(@Form Employee input, ErrorInfo info, Request req) {
		req.setAttribute("employee", input);
		String loginId = input.getLoginId();
		String password = input.getPassword();
		Employee e = service.find(loginId, password);
		if (e == null) {
			pageInfo.setMessage("message.login.failed");
			return index();
		}
		Logger logger = Logger.getLogger(WelcomePage.class);
		logger.info(loginId + "," + password);
		
		if (eventInfoService.find(e.getId()) == null) {
			pageInfo.setPage("event_attendance");
		} else {
			pageInfo.setPage("main");
		}
		return Forward.to(TEMPLATE);
	}

	@GET
	@ActionPath
	public Navigation main() {
		pageInfo.setPage("main");
		return Forward.to(TEMPLATE);
	}

	@GET
	@ActionPath
	public Navigation logout() {
		// TODO:セッション破棄
		return index();
	}

}
