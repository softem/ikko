<c:forEach var="item" items="${individualBusinessReportPage.list}" varStatus="status">
	<c:if test="${status.first}">
	<table class="dataTable">
		<tr>
			<th rowspan="2">社員No</th>
			<th rowspan="2">社員名</th>
			<th rowspan="2">所属部署</th>
			<th colspan="7">合計時間</th>
			<th rowspan="2">月末締め</th>
		</tr>
		<tr>
			<th>標準</th>
			<th>実働</th>
			<th>残業</th>
			<th>深夜残業</th>
			<th>半休取得</th>
			<th>法定休出</th>
			<th>総残業</th>
		</tr>
	</c:if>
		<tr>
			<td>${item.employee.employeeNo}</td>
			<td><a href="${t:url("/business_report")}">${item.employee.employeeName}</a></td>
			<td>${item.employee.section.sectionName}</td>
			<td>${item.standardTimeSummary}</td>
			<td>${item.actualWorkTime}</td>
			<td>${item.overtimeWorkSummary}</td>
			<td>${item.midnightOvertimeWorkSummary}</td>
			<td>${item.harfHolidayTimeSummary}</td>
			<td>${item.legalHolidayWorkSummary}</td>
			<td>${item.allOvertimeWorkSummary}</td>
			<td></td>
		</tr>
	<c:if test="${status.last}">
	</table>
	</c:if>
</c:forEach>
