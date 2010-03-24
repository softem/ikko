<c:forEach var="item" items="${eventInfoPage.list}" varStatus="status">
	<c:if test="${status.first}">
	<table class="dataTable">
		<tr>
			<th>ID</th>
			<th>イベント名</th>
			<th>開催年月日</th>
			<th>開始時刻</th>
			<th>終了時刻</th>
			<th>開催場所</th>
			<th>内容</th>
			<th>出欠席チェック年月</th>
			<th>操作</th>
		</tr>
	</c:if>
		<tr<c:if test="${status.index mod 2 != 0}"> class="odd"</c:if>>
			<td id="id${item.id}">${item.id}</td>
			<td id="eventName${item.id}">${item.eventName}</td>
			<td><span><fmt:formatDate value="${item.eventDate}" pattern="yyyy/MM/dd"/></span></td>
			<td id="eventStart${item.id}">${item.eventStart}</td>
			<td id="eventEnd${item.id}">${item.eventEnd}</td>
			<td id="eventPlace${item.id}">${item.eventPlace}</td>
			<td><span><fmt:formatDate value="${item.eventCheckMonth}" pattern="yyyy/MM"/></span></td>
			<td id="eventComment${item.id}">${item.eventComment}</td>
			<td><input type="button" class="editButton" value="編集" onclick="inputForm.showEditForm(${item.id})" /></td>
		</tr>
	<c:if test="${status.last}">
	</table>
	<input type="hidden" id="eventDate${item.id}" value="<fmt:formatDate value="${item.eventDate}" pattern="yyyyMMdd"/>">
	<input type="hidden" id="eventCheckMonth${item.id}" value="<fmt:formatDate value="${item.eventCheckMonth}" pattern="yyyyMM"/>">
	</c:if>
</c:forEach>
