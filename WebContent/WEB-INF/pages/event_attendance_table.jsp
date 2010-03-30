<c:forEach var="item" items="${eventAttendancePage.list}" varStatus="status">
	<c:if test="${status.first}">
	<table class="dataTable">
		<tr>
			<th>出欠席</th>
			<th>イベント名</th>
			<th>開催年月日</th>
			<th>開始時刻</th>
			<th>終了時刻</th>
			<th>開催場所</th>
			<th>内容</th>
			<th>操作</th>
		</tr>
	</c:if>
		<tr<c:if test="${status.index mod 2 != 0}"> class="odd"</c:if>>
			<td>
				<c:choose>
					<c:when test="${item.eventAttendances[0].id == null}"><b>保留</b></c:when>
					<c:when test="${item.eventAttendances[0].eventAttendanceFlag}">出席</c:when>
					<c:otherwise>欠席</c:otherwise>
				</c:choose>
				<span><input type="hidden" id="id${item.id}" value="${item.id}"/></span>
				<span><input type="hidden" id="eventAttendanceFlag${item.id}" value="${item.eventAttendances[0].eventAttendanceFlag}"/></span>
			</td>
			<td id="eventName${item.id}">${item.eventName}</td>
			<td><span><fmt:formatDate value="${item.eventDate}" pattern="yyyy/MM/dd"/></span></td>
			<td id="eventStart${item.id}">${item.eventStart}</td>
			<td id="eventEnd${item.id}">${item.eventEnd}</td>
			<td id="eventPlace${item.id}">${item.eventPlace}</td>
			<td id="eventComment${item.id}">${item.eventComment}</td>
			<td>
				<c:choose>
					<c:when test="${item.eventAttendances[0].id == null}"><input type="button" class="editButton" value="出欠席入力" onclick="inputForm.showAddForm()" /></c:when>
					<c:otherwise><input type="button" class="editButton" value="出欠席変更" onclick="inputForm.showEditForm(${item.id})" /></c:otherwise>
				</c:choose>
			</td>
		</tr>
	<c:if test="${status.last}">
	</table>
	</c:if>
</c:forEach>
