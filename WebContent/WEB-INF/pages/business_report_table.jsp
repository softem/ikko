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
	<c:if test="${status.last}">
		</tbody>
	</table>
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
				<td><fmt:formatDate value="${item.startDate}" pattern="yyyy/MM/dd(E)" /></td>
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
