/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.softem.ikko.page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import jp.co.softem.ikko.bean.SessionItemBean;
import jp.co.softem.ikko.core.BasePage;
import jp.co.softem.ikko.core.JsonResult;
import jp.co.softem.ikko.eis.AttendanceKind;
import jp.co.softem.ikko.eis.BusinessReport;
import jp.co.softem.ikko.eis.BusinessReportProjectSummary;
import jp.co.softem.ikko.eis.BusinessReportSummary;
import jp.co.softem.ikko.eis.CommuterTicket;
import jp.co.softem.ikko.eis.Project;
import jp.co.softem.ikko.service.AttendanceKindService;
import jp.co.softem.ikko.service.BusinessReportProjectSummaryService;
import jp.co.softem.ikko.service.BusinessReportService;
import jp.co.softem.ikko.service.BusinessReportSummaryService;
import jp.co.softem.ikko.service.CommuterTicketService;
import jp.co.softem.ikko.service.ProjectService;

import org.t2framework.commons.annotation.core.Singleton;
import org.t2framework.t2.action.ErrorInfo;
import org.t2framework.t2.annotation.composite.GET;
import org.t2framework.t2.annotation.composite.POST;
import org.t2framework.t2.annotation.core.ActionPath;
import org.t2framework.t2.annotation.core.Ajax;
import org.t2framework.t2.annotation.core.Default;
import org.t2framework.t2.annotation.core.Form;
import org.t2framework.t2.annotation.core.Page;
import org.t2framework.t2.annotation.core.Var;
import org.t2framework.t2.contexts.Request;
import org.t2framework.t2.navigation.Forward;
import org.t2framework.t2.navigation.Json;
import org.t2framework.t2.spi.Navigation;

@Singleton
@Named
@Page("business_report")
public class BusinessReportPage extends BasePage {

	@EJB
	BusinessReportService service;

	@Inject
	SessionItemBean sessionItem;

	@Default
	@ActionPath("{year}/{month}")
	public Navigation index(@Var("year") Integer year,
			@Var("month") Integer month, Request request) {
		sessionItem.setCurrentCal(Calendar.getInstance());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
		if (year == null || month == null) {
			Calendar nextCal = Calendar.getInstance();
			nextCal.add(Calendar.MONTH, 1);
			request.setAttribute("next", sdf.format(nextCal.getTime()));
			Calendar prevCal = Calendar.getInstance();
			prevCal.add(Calendar.MONTH, -1);
			request.setAttribute("prev", sdf.format(prevCal.getTime()));
		} else {
			Calendar nextCal = Calendar.getInstance();
			nextCal.set(year, month - 1, 1);
			nextCal.add(Calendar.MONTH, 1);
			request.setAttribute("next", sdf.format(nextCal.getTime()));
			Calendar prevCal = Calendar.getInstance();
			prevCal.set(year, month - 1, 1);
			prevCal.add(Calendar.MONTH, -1);
			request.setAttribute("prev", sdf.format(prevCal.getTime()));
			sessionItem.getCurrentCal().set(year, month - 1, 1);
		}
		pageInfo.setPage("business_report");
		return Forward.to(TEMPLATE);
	}

	@POST
	@ActionPath
	@Ajax
	public Navigation save(@Form BusinessReport entity, ErrorInfo info) {
		JsonResult result = new JsonResult();
		return Json.convert(result);
	}

	@POST
	@ActionPath
	@Ajax
	public Navigation delete(@Form BusinessReport entity, ErrorInfo info) {
		JsonResult result = new JsonResult();
		service.deletePhysical(entity.getId());
		return Json.convert(result);
	}

	@GET
	@Ajax
	public Navigation table() {
		return Forward.to("/WEB-INF/pages/business_report_table.jsp");
	}

	public Date getTitleDate() {
		return sessionItem.getCurrentCal().getTime();
	}

	public List<BusinessReport> getList() throws ParseException {
		List<BusinessReport> list = new ArrayList<BusinessReport>();
		Calendar start = Calendar.getInstance();
		start.setTime(sessionItem.getCurrentCal().getTime());
		int max = start.getActualMaximum(Calendar.DATE);
		start.set(Calendar.DATE, 20);
		for (int i = 0; i < max; i++) {
			start.add(Calendar.DATE, 1);
			// TODO:仮データ
			BusinessReport report = new BusinessReport();
			report.setId(i * -1);
			report.setBusinessReportDate(start.getTime());
			report.setStartTime("0900");
			report.setFinishTime("1700");
			report.setProject(projectService.findById(1));
			report.setAttendanceKind(attendanceKindService.findById(2));
			list.add(report);
		}
		return list;
	}

	@EJB
	BusinessReportSummaryService businessReportSummaryService;

	public List<BusinessReportSummary> getSummaryList() {
		return businessReportSummaryService.findAll();
	}

	@EJB
	BusinessReportProjectSummaryService businessReportProjectSummaryService;

	public List<BusinessReportProjectSummary> getProjectSummaryList() {
		return businessReportProjectSummaryService.findAll();
	}

	@EJB
	CommuterTicketService commuterTicketServiceService;

	public List<CommuterTicket> getCommuterTicketList() {
		return commuterTicketServiceService.findAll();
	}

	@EJB
	ProjectService projectService;

	public Map<Integer, String> getProject() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(0, "");
		List<Project> list = projectService.findAll();
		for (Project project : list) {
			map.put(project.getId(), project.getProjectName());
		}
		return map;
	}

	@EJB
	AttendanceKindService attendanceKindService;

	public Map<Integer, String> getAttendanceKind() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(0, "");
		List<AttendanceKind> list = attendanceKindService.findAll();
		for (AttendanceKind kind : list) {
			map.put(kind.getId(), kind.getAttendanceKindName());
		}
		return map;
	}

}
