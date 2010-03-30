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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import jp.co.softem.ikko.bean.SessionItemBean;
import jp.co.softem.ikko.core.BasePage;
import jp.co.softem.ikko.core.JsonResult;
import jp.co.softem.ikko.eis.Employee;
import jp.co.softem.ikko.eis.EventAttendance;
import jp.co.softem.ikko.service.EmployeeService;
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
@Page("event_detail")
public class EventDetailPage extends BasePage {

	@EJB
	EmployeeService employeeService;

	@EJB
	EventAttendanceService eventAttendanceService;

	@Inject
	SessionItemBean sessionItem;

//	int absenceCount = 0;
//	int reserveCount = 0;

	@Default
	@ActionPath("{id}")
	public Navigation index(@Var("id") int id, Request request) {
		List eventDetailList = employeeService.findByEventId(id);
		int absenceCount = eventAttendanceService.findCountByEventId(id, 0);
		int reserveCount = eventAttendanceService.findCountByEventId(id);
		request.setAttribute("eventDetailList", eventDetailList);
		request.setAttribute("absenceCount", absenceCount);
		request.setAttribute("reserveCount", reserveCount);
		pageInfo.setPage("event_detail");
		return Forward.to(TEMPLATE);
	}

	@POST
	@ActionPath
	@Ajax
	public Navigation save(@Form EventAttendance eventAttendance, ErrorInfo info) {
// TOarg1)DO
		pageInfo.setPage("main");
		return Forward.to(TEMPLATE);
	}

	@GET
	@Ajax
	public Navigation table() {
		return Forward.to("/WEB-INF/pages/event_detail_table.jsp");
	}

//	@ActionPath("{id}")
//	public List<Employee> getList(@Var("id") Integer id) {
	public List<Employee> getList(@Var("id") Integer id) {
		List<Employee> list = new ArrayList<Employee>();
		List<Employee> employeeList = employeeService.findAll();
		List<EventAttendance> attendanceList = eventAttendanceService.findByEventId(id);
		Iterator<Employee> it1 = employeeList.iterator();
		while (it1.hasNext()) {
			Employee employee = it1.next();
			Iterator<EventAttendance> it2 = attendanceList.iterator();
			employee.setEventAttendances(new ArrayList<EventAttendance>());
			boolean isDataExisits = false;
			while (it2.hasNext()) {
				EventAttendance eventAttendance = it2.next();
				if (employee.getId() == eventAttendance.getEmployee().getId()) {
					List<EventAttendance> attendanceLt = new ArrayList<EventAttendance>();
					attendanceLt.add(eventAttendance);
					employee.setEventAttendances(attendanceLt);
					if (!eventAttendance.getEventAttendanceFlag()) {
//						absenceCount++;
					}
					isDataExisits = true;
					break;
				}
			}
			list.add(employee);
			if (!isDataExisits) {
//				reserveCount++;
			}
		}
		return list;
	}

//	public int getEventId() {
//		return eventId;
//	}
//	
//	public int setEventId(int id) {
//		return eventId = id;
//	}
//	
//	public int getAbsenceCount() {
//		return absenceCount;
//	}
//	
//	public int setAbsenceCount(int count) {
//		return absenceCount = count;
//	}
//	
//	public int getReserveCount() {
//		return reserveCount;
//	}
//	
//	public int setReserveCount(int count) {
//		return reserveCount = count;
//	}
	
//	@SuppressWarnings("unchecked")
//	public Map getCount() {
//		List<Employee> list = new ArrayList<Employee>();
//		List<Employee> employeeList = employeeService.findAll();
//		List<EventAttendance> attendanceList = eventAttendanceService.findByEventId(2);
//		Iterator<Employee> it1 = employeeList.iterator();
//		while (it1.hasNext()) {
//			Employee employee = it1.next();
//			Iterator<EventAttendance> it2 = attendanceList.iterator();
//			employee.setEventAttendances(new ArrayList<EventAttendance>());
//			boolean isDataExisits = false;
//			while (it2.hasNext()) {
//				EventAttendance eventAttendance = it2.next();
//				if (employee.getId() == eventAttendance.getEmployee().getId()) {
//					List<EventAttendance> attendanceLt = new ArrayList<EventAttendance>();
//					attendanceLt.add(eventAttendance);
//					employee.setEventAttendances(attendanceLt);
//					if (!eventAttendance.getEventAttendanceFlag()) {
//						absenceCount++;
//					}
//					isDataExisits = true;
//					break;
//				}
//			}
//			list.add(employee);
//			if (!isDataExisits) {
//				reserveCount++;
//			}
//		}
//		
//		Map map = new HashMap();
//		map.put("absenceCount", Integer.toString(absenceCount));
//		map.put("reserveCount", Integer.toString(reserveCount));
//		
//		return map;
//	}

	public EventAttendance getEventAttendance() {
		return new EventAttendance();
	}

}
