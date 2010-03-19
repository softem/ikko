<c:forEach var="item" items="${businessReportPage.list}" varStatus="status">
	<c:if test="${status.first}">
	<table class="dataTable">
		<caption><fmt:formatDate value="${businessReportPage.titleDate}" pattern="yyyy年MM月度" /></caption>
		<thead>
			<tr>
				<th rowspan="2">日付</th>
				<th rowspan="2">プロジェクト名</th>
				<th rowspan="2">区分</th>
				<th colspan="2">勤務時間</th>
				<th colspan="6">稼働</th>
				<th rowspan="2">備考</th>
				<th colspan="2">外出</th>
				<th rowspan="2">操作</th>
			</tr>
			<tr>
				<th>開始</th>
				<th>終了</th>
				<th>F稼働</th>
				<th>非F稼働</th>
				<th>非F残業</th>
				<th>深夜</th>
				<th>法定休出</th>
				<th>遅早外</th>
				<th>開始</th>
				<th>終了</th>
			</tr>
		</thead>
		<tbody>
	</c:if>
			<tr<c:if test="${status.index mod 2 != 0}"> class="odd"</c:if>>
				<td><span id="businessReportDate${item.id}">
				<fmt:formatDate value="${item.businessReportDate}" pattern="MM/dd(E)" /></span>
				<input type="hidden" id="id${item.id}" value="${item.id}" /></td>
				<td><span>${item.project.projectName}</span>
				<input type="hidden" id="project${item.id}" value="${item.project.id}" /></td>
				<td><span>${item.attendanceKind.attendanceKindName}</span>
				<input type="hidden" id="attendanceKind${item.id}" value="${item.attendanceKind.id}" /></td>
				<td><span id="startTime${item.id}">${item.startTime}</span></td>
				<td><span id="finishTime${item.id}">${item.finishTime}</span></td>
				<td>${item.flexWorkTime}</td>
				<td>${item.normalWorkTime}</td>
				<td>${item.normalOvertimeWork}</td>
				<td>${item.midnightOvertimeWork}</td>
				<td>${item.legalHolidayWorkTime}</td>
				<td>${item.lateEarlyLeftOutTime}</td>
				<td><span id="outStartTime${item.id}">${item.outStartTime}</span></td>
				<td><span id="outFinishTime${item.id}">${item.outFinishTime}</span></td>
				<td><span id="comment${item.id}">${item.comment}</span></td>
				<td><input type="button" class="editButton" value="編集" onclick="inputForm.showEditForm(${item.id})" /></td>
			</tr>
	<c:if test="${status.last}">				<td>${item.lateEarlyLeftOutSummary}</td>
	
		</tbody>
	</table>
	</c:if>
</c:forEach>

<c:forEach var="item" items="${businessReportPage.summaryProjectList}" varStatus="status">
	<c:if test="${status.first}">
	<table class="dataTable">
		<caption>当月度合計（プロジェクト単位）</caption>
		<thead>
			<tr>
				<th class="odd" rowspan="2">標準時間</th>
				<th class="odd" rowspan="2">半休取得時間</th>
				<th class="odd" colspan="4">フレックス合計</th>
			</tr>
			<tr>
				<th class="odd">F稼働日数</th>
				<th class="odd">仮F標準</th>
				<th class="odd">F標準時間</th>
				<th class="odd">F残業</th>
			</tr>
		</thead>
		<tbody>
	</c:if>
			<tr>
				<td>${item.standardTimeSummary}</td>
				<td>${item.harfHolidayTimeSummary}</td>
				<td>${item.flexWorkSummaryDay}</td>
				<td>${item.temporaryFlexTimeSummary}</td>
				<td>${item.flexStandardTimeSummary}</td>
				<td>${item.flexOvertimeWorkSummary}</td>
			</tr>
	<c:if test="${status.last}">
		</tbody>
	</table>
	</c:if>
</c:forEach>

<c:forEach var="item" items="${businessReportPage.summaryList}" varStatus="status">
	<c:if test="${status.first}">
	</c:if>
	<table class="dataTable">
		<caption>当月度合計（各種合計）</caption>
		<tbody>
			<tr>
				<th class="odd" rowspan="2">稼働日数</th>
				<th class="odd" colspan="9">合計時間</th>
			</tr>
			<tr>
				<th class="odd">F稼働</th>
				<th class="odd">非F稼働</th>
				<th class="odd">非F残業</th>
				<th class="odd">法定休出</th>
				<th class="odd">遅早外</th>
				<th class="odd">実働</th>
				<th class="odd">残業</th>
				<th class="odd">深夜残業</th>
				<th class="odd">総残業</th>
			</tr>
			<tr>
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
	</table>
	<table class="dataTable">
		<caption>当月度合計（各種勤怠区分回数）</caption>
		<tbody>
			<tr>
				<th class="odd">有休</th>
				<th class="odd">特別休暇</th>
				<th class="odd">徹休</th>
				<th class="odd">振替</th>
				<th class="odd">休出代休</th>
				<th class="odd">出張</th>
				<th class="odd">徹夜残業</th>
				<th class="odd">欠勤</th>
				<th class="odd">残業代休</th>
				<th class="odd">シフト</th>
				<th class="odd">早出</th>
				<th class="odd">半休</th>
				<th class="odd">休出</th>
				<th class="odd">遅早外</th>
			</tr>
			<tr>
				<td>${item.paidHolidayCount}</td>
				<td>${item.specialHolidayCount}</td>
				<td>${item.allNightHolidayCount}</td>
				<td>${item.transferHolidayCount}</td>
				<td>${item.holidayWorkCompensatoryHolidayCount}</td>
				<td>${item.businessTripCount}</td>
				<td>${item.allNightWorkCount}</td>
				<td>${item.absenceCount}</td>
				<td>${item.overtimeWorkCompensatoryHolidayCount}</td>
				<td>${item.shiftCount}</td>
				<td>${item.earlyMorningWorkCount}</td>
				<td>${item.harfHolidayCount}</td>
				<td>${item.holidayWorkCount}</td>
				<td>${item.lateEarlyLeftOutCount}</td>
			</tr>
		</tbody>
	</table>
	<table class="dataTable">
		<caption>当月度合計（平日・土曜日稼働合計時間）</caption>
		<tbody>
			<tr>
				<th class="odd" colspan="3">平日稼働</th>
				<th class="odd" colspan="3">土曜日稼働</th>
			</tr>
			<tr>
				<th class="odd">21日～末まで</th>
				<th class="odd">1日～20日まで</th>
				<th class="odd">合計</th>
				<th class="odd">21日～末まで</th>
				<th class="odd">1日～20日まで</th>
				<th class="odd">合計</th>
			</tr>
			<tr>
				<td>${item.firstHarfUsualWorkTimeSummary}</td>
				<td>${item.latterHarfUsualWorkTimeSummary}</td>
				<td>${item.usualWorkTimeSummary}</td>
				<td>${item.firstHarfSaturdayWorkTimeSummary}</td>
				<td>${item.latterHarfSaturdayWorkTimeSummary}</td>
				<td>${item.saturdayWorkTimeSummary}</td>
			</tr>
		</tbody>
	</table>
	<c:if test="${status.last}">
	</c:if>
</c:forEach>

<c:forEach var="item" items="${businessReportPage.commuterTicketList}" varStatus="status">
	<c:if test="${status.first}">
	<table class="dataTable">
		<caption>定期代</caption>
		<thead>
			<tr>
				<th rowspan="2">使用開始年月</th>
				<th rowspan="2">路線名</th>
				<th colspan="2">駅名</th>
				<th rowspan="2">金額</th>
			</tr>
			<tr>
				<th>始点</th>
				<th>終点</th>
			</tr>
		</thead>
		<tbody>
	</c:if>
			<tr<c:if test="${status.index mod 2 != 0}"> class="odd"</c:if>>
				<td><fmt:formatDate value="${item.startDate}" pattern="yyyy/MM/dd" /></td>
				<td>${item.lineName}</td>
				<td>${item.stationNameStart}</td>
				<td>${item.stationNameEnd}</td>
				<td><fmt:formatNumber value="${item.ticketPrice}" pattern="###,###"/></td>
			</tr>
	<c:if test="${status.last}">
		</tbody>
	</table>
	</c:if>
</c:forEach>
