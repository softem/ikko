<c:forEach var="item" items="${eventDetailPage.List}" varStatus="status">
	<c:if test="${status.first}">
	<table class="dataTable">
		<tr>
			<th>社員No</th>
			<th>社員名</th>
			<th>出欠席</th>
			<th>操作</th>
		</tr>
	</c:if>
		<tr<c:if test="${status.index mod 2 != 0}"> class="odd"</c:if>>
			<td id="employeeNo${item.id}">${item.employeeNo}</td>
			<td id="employeeName${item.id}">${item.employeeName}</td>
			<td>
				<c:choose>
					<c:when test="${item.eventAttendances[0].id == null}"><b>保留</b></c:when>
					<c:when test="${item.eventAttendances[0].eventAttendanceFlag}">出席</c:when>
					<c:otherwise>欠席</c:otherwise>
				</c:choose>
			</td>
			<td>
				<c:choose>
					<c:when test="${item.eventAttendances[0].id == null}"><input type="button" class="editButton" value="出欠席入力" onclick="inputForm.showAddForm()" /></c:when>
					<c:otherwise><input type="button" class="editButton" value="出欠席変更" onclick="inputForm.showEditForm(${item.id})" /></c:otherwise>
				</c:choose>
			</td>
		</tr>
	<c:if test="${status.last}">
	</table>
	<input type="hidden" id="id${item.id}" value="${item.eventAttendances[0].id}"/>
	<input type="hidden" id="eventAttendanceFlag${item.id}" value="${item.eventAttendances[0].eventAttendanceFlag}"/>
	</c:if>
</c:forEach>
