package jp.co.softem.ikko.page;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import jp.co.softem.ikko.core.BasePage;
import jp.co.softem.ikko.core.JsonResult;
import jp.co.softem.ikko.eis.BusinessReport;
import jp.co.softem.ikko.eis.CommuterTicket;
import jp.co.softem.ikko.service.CommuterTicketService;

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
@Page("commuter_ticket")
public class CommuterTicketPage extends BasePage {

	@EJB
	CommuterTicketService service;

	@Default
	public Navigation index() {
		pageInfo.setPage("commuter_ticket");
		return Forward.to(TEMPLATE);
	}

	@POST
	@ActionPath
	@Ajax
	public Navigation save(@Form CommuterTicket commuterTicket, ErrorInfo info) {
		JsonResult result = service.save(commuterTicket, info);
		return Json.convert(result);
	}

	@POST
	@ActionPath
	@Ajax
	public Navigation delete(@Form CommuterTicket commuterTicket, ErrorInfo info) {
		JsonResult result = new JsonResult();
		service.delete(commuterTicket.getId());
		return Json.convert(result);
	}

	@GET
	@Ajax
	public Navigation table() {
		return Forward.to("/WEB-INF/pages/commuter_ticket_table.jsp");
	}

	public List<CommuterTicket> getList() {
		List<CommuterTicket> list = new ArrayList<CommuterTicket>();
		Calendar a = Calendar.getInstance();

		// TODO:Mock用仮データ
		CommuterTicket commuterTicket = new CommuterTicket();
		commuterTicket.setId(1);
		commuterTicket.setCommuterTicketMonth(a.getTime());
		commuterTicket.setCommuterTicketNo(1);
		commuterTicket.setStartDate(a.getTime());
		commuterTicket.setLineName("京浜急行");
		commuterTicket.setStationNameStart("北久里浜");
		commuterTicket.setStationNameEnd("上大岡");
		commuterTicket.setTicketPrice(11550);
		list.add(commuterTicket);

		commuterTicket.setId(2);
		commuterTicket.setCommuterTicketMonth(a.getTime());
		commuterTicket.setCommuterTicketNo(2);
		commuterTicket.setStartDate(a.getTime());
		commuterTicket.setLineName("横浜市営地下鉄");
		commuterTicket.setStationNameStart("上大岡");
		commuterTicket.setStationNameEnd("関内");
		commuterTicket.setTicketPrice(8970);
		list.add(commuterTicket);
		
		return list;
	}

	public CommuterTicket getCommuterTicket() {
		return new CommuterTicket();
	}

}
