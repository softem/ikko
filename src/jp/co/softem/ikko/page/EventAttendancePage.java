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
import jp.co.softem.ikko.eis.EventInfo;
import jp.co.softem.ikko.eis.EventAttendance;
import jp.co.softem.ikko.eis.Section;
import jp.co.softem.ikko.service.EventAttendanceService;
import jp.co.softem.ikko.service.EventInfoService;

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
@Page("event_attendance")
public class EventAttendancePage extends BasePage {

	@EJB
	EventAttendanceService eventAttendanceService;

	@EJB
	EventInfoService eventInfoService;

	@Inject
	SessionItemBean sessionItem;

	@Default
	public Navigation index() {
		pageInfo.setPage("event_attendance");
		return Forward.to(TEMPLATE);
	}

	@POST
	@ActionPath
	@Ajax
	public Navigation save(@Form EventAttendance eventAttendance, ErrorInfo info) {
		pageInfo.setPage("main");
		return Forward.to(TEMPLATE);
	}

	@GET
	@Ajax
	public Navigation table() {
		return Forward.to("/WEB-INF/pages/event_attendance_table.jsp");
	}

	public List<EventAttendance> getList() {
		List<EventAttendance> list = new ArrayList<EventAttendance>();
		EventInfo eventInfo = eventInfoService.findById(1);
		EventAttendance eventAttendance = eventAttendanceService.findById(eventInfo.getId());
		if (eventAttendance == null) {
			eventAttendance = new EventAttendance();
		}
		eventAttendance.setEventInfo(eventInfo);
		list.add(eventAttendance);
		return list;
	}

	public EventAttendance getEventAttendance() {
		return new EventAttendance();
	}

}
