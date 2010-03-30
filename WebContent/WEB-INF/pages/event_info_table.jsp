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
			<th>適用年月日</th>
			<th>操作</th>
		</tr>
	</c:if>
		<tr<c:if test="${status.index mod 2 != 0}"> class="odd"</c:if>>
			<td id="id${item.id}">${item.id}</td>
			<td id="eventName${item.id}"><a href="${t:url("/event_detail/")}${item.id}">${item.eventName}</a></td>
			<td>
				<span><fmt:formatDate value="${item.eventDate}" pattern="yyyy/MM/dd"/></span>
				<span><input type="hidden" id="eventDate${item.id}" value="<fmt:formatDate value="${item.eventDate}" pattern="yyyyMMdd"/>"></span>
			</td>
			<td id="eventStart${item.id}">${item.eventStart}</td>
			<td id="eventEnd${item.id}">${item.eventEnd}</td>
			<td id="eventPlace${item.id}">${item.eventPlace}</td>
			<td id="eventComment${item.id}">${item.eventComment}</td>
			<td>
				<span><fmt:formatDate value="${item.eventApplyDate}" pattern="yyyy/MM/dd"/></span>
				<span><input type="hidden" id="eventApplyDate${item.id}" value="<fmt:formatDate value="${item.eventApplyDate}" pattern="yyyyMMdd"/>"></span>
			</td>
			<td><input type="button" class="editButton" value="編集" onclick="inputForm.showEditForm(${item.id})" /></td>
		</tr>
	<c:if test="${status.last}">
	</table>
	</c:if>
</c:forEach>
