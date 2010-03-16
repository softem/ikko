<c:forEach var="item" items="${businessReportPage.list}" varStatus="status">
	<c:if test="${status.first}">
	<table class="dataTable">
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
	</c:if>
		<tr>
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
	</table>
	</c:if>
</c:forEach>