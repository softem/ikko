<c:forEach var="item" items="${commuterTicketPage.list}" varStatus="status">
	<c:if test="${status.first}">
	<table class="dataTable">
		<tr>
			<th>ID</th>
			<th>定期代年月</th>
			<th>定期代連番</th>
			<th>使用開始年月日</th>
			<th>路線名</th>
			<th>駅名（始点）</th>
			<th>駅名（終点）</th>
			<th>金額</th>
			<th>操作</th>
		</tr>
	</c:if>
		<tr>
			<td id="id${item.id}">${item.id}</td>
			<td id="commuterTicketMonth${item.id}">${item.commuterTicketMonth}</td>
			<td id="commuterTicketNo${item.id}">${item.commuterTicketNo}</td>
			<td id="startDate${item.id}">${item.startDate}</td>
			<td id="lineName${item.id}">${item.lineName}</td>
			<td id="stationNameStart${item.id}">${item.stationNameStart}</td>
			<td id="stationNameEnd${item.id}">${item.stationNameEnd}</td>
			<td id="ticketPrice${item.id}">${item.ticketPrice}</td>
			<td><input type="button" class="editButton" value="編集" onclick="inputForm.showEditForm(${item.id})" /></td>
		</tr>
	<c:if test="${status.last}">
	</table>
	</c:if>
</c:forEach>
