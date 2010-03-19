<c:forEach var="item" items="${individualBusinessReportPage.list}" varStatus="status">
	<c:if test="${status.first}">
	<table class="dataTable">
		<caption><fmt:formatDate value="${individualBusinessReportPage.titleDate}" pattern="yyyy年MM月度" /></caption>
		<thead>
			<tr>
				<th rowspan="2">社員No</th>
				<th rowspan="2">社員名</th>
				<th rowspan="2">所属部署</th>
				<th rowspan="2">稼働日数</th>
				<th colspan="9">合計時間</th>
			</tr>
			<tr>
				<th>F稼働</th>
				<th>非F稼働</th>
				<th>非F残業</th>
				<th>法定休出</th>
				<th>遅早外</th>
				<th>実働</th>
				<th>残業</th>
				<th>深夜残業</th>
				<th>総残業</th>
			</tr>
		</thead>
	</c:if>
		<tbody>
			<tr<c:if test="${status.index mod 2 != 0}"> class="odd"</c:if>>
				<td><fmt:formatNumber value="${item.employee.employeeNo}" pattern="0000"/></td>
				<td><a href="${t:url("/business_report")}">${item.employee.employeeName}</a></td>
				<td>${item.employee.section.sectionName}</td>
				<td>${item.workSummaryDay}</td>
				<td>${item.flexWorkTimeSummary}</td>
				<td>${item.normalWorkTimeSummary}</td>
				<td>${item.normalOvertimeWorkSummary}</td>
				<td>${item.legalHolidayWorkSummary}</td>
				<td>${item.lateEarlyLeftOutSummary}</td>
				<td>${item.actualWorkTime}</td>
				<td>${item.overtimeWorkSummary}</td>
				<td>${item.midnightOvertimeWorkSummary}</td>
				<td>${item.allOvertimeWorkSummary}</td>
			</tr>
		</tbody>
	<c:if test="${status.last}">
	</table>
	</c:if>
</c:forEach>
