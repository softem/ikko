package jp.co.softem.ikko.page;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import jp.co.softem.ikko.core.BasePage;
import jp.co.softem.ikko.core.JsonResult;
import jp.co.softem.ikko.eis.EventInfo;
import jp.co.softem.ikko.service.EventInfoService;

import org.t2framework.commons.annotation.composite.RequestScope;
import org.t2framework.t2.action.ErrorInfo;
import org.t2framework.t2.annotation.composite.GET;
import org.t2framework.t2.annotation.composite.POST;
import org.t2framework.t2.annotation.core.ActionPath;
import org.t2framework.t2.annotation.core.Ajax;
import org.t2framework.t2.annotation.core.Default;
import org.t2framework.t2.annotation.core.Form;
import org.t2framework.t2.annotation.core.Page;
import org.t2framework.t2.navigation.Forward;
import org.t2framework.t2.navigation.Json;
import org.t2framework.t2.spi.Navigation;

@RequestScope
@Named
@Page("event_info")
public class EventInfoPage extends BasePage {

	@EJB
	EventInfoService service;

	@Default
	public Navigation index() {
		pageInfo.setPage("event_info");
		return Forward.to(TEMPLATE);
	}

	@POST
	@ActionPath
	@Ajax
	public Navigation save(@Form EventInfo eventInfo, ErrorInfo info) {
		JsonResult result = service.save(eventInfo, info);
		return Json.convert(result);
	}

	@GET
	@Ajax
	public Navigation table() {
		return Forward.to("/WEB-INF/pages/event_table.jsp");
	}

	public List<EventInfo> getList() {
		return service.findAll();
	}

	public EventInfo getEventInfo() {
		return new EventInfo();
	}

}
