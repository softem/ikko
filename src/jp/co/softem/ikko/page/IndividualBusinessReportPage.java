package jp.co.softem.ikko.page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import jp.co.softem.ikko.bean.CalendarBean;
import jp.co.softem.ikko.core.BasePage;
import jp.co.softem.ikko.eis.BusinessReportSummary;
import jp.co.softem.ikko.service.BusinessReportSummaryService;
import jp.co.softem.ikko.service.EmployeeService;

import org.t2framework.commons.annotation.core.Singleton;
import org.t2framework.t2.annotation.composite.GET;
import org.t2framework.t2.annotation.core.ActionPath;
import org.t2framework.t2.annotation.core.Ajax;
import org.t2framework.t2.annotation.core.Default;
import org.t2framework.t2.annotation.core.Page;
import org.t2framework.t2.annotation.core.Var;
import org.t2framework.t2.contexts.Request;
import org.t2framework.t2.navigation.Forward;
import org.t2framework.t2.spi.Navigation;

@Singleton
@Named
@Page("individual_business_report")
public class IndividualBusinessReportPage extends BasePage {

	@EJB
	BusinessReportSummaryService service;

	@EJB
	EmployeeService employeeService;

	@Inject
	CalendarBean cal;

	@Default
	@ActionPath("{year}/{month}")
	public Navigation index(@Var("year") Integer year,
			@Var("month") Integer month, Request request) {
		cal.setCurrentCal(Calendar.getInstance());
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
			cal.getCurrentCal().set(year, month - 1, 1);
		}
		pageInfo.setPage("individual_business_report");
		return Forward.to(TEMPLATE);
	}

	@GET
	@Ajax
	public Navigation table() {
		return Forward
				.to("/WEB-INF/pages/individual_business_report_table.jsp");
	}

	public Date getTitleDate() {
		return cal.getCurrentCal().getTime();
	}

	public List<BusinessReportSummary> getList() throws ParseException {
		List<BusinessReportSummary> list = new ArrayList<BusinessReportSummary>();

		BusinessReportSummary report = new BusinessReportSummary();
		report.setEmployee(employeeService.findById(1));
		report.setStandardTimeSummary(999.99);
		report.setActualWorkTime(999.99);
		report.setOvertimeWorkSummary(999.99);
		report.setHarfHolidayTimeSummary(999.99);
		report.setMidnightOvertimeWorkSummary(999.99);
		report.setLegalHolidayWorkSummary(999.99);
		report.setAllOvertimeWorkSummary(999.99);
		report.setMonthlySeparateFlag(true);
		list.add(report);
		return list;
	}

}
