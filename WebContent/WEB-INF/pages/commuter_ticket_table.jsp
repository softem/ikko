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
			<td>
				<span><fmt:formatDate value="${item.commuterTicketMonth}" pattern="yyyy/MM"/></span>
			</td>
			<td id="commuterTicketNo${item.id}">${item.commuterTicketNo}</td>
			<td>
				<span><fmt:formatDate value="${item.startDate}" pattern="yyyy/MM/dd"/></span>
			</td>
			<td id="lineName${item.id}">${item.lineName}</td>
			<td id="stationNameStart${item.id}">${item.stationNameStart}</td>
			<td id="stationNameEnd${item.id}">${item.stationNameEnd}</td>
			<td>
				<span><fmt:formatNumber value="${item.ticketPrice}" pattern="###,###"/></span>
			</td>
			<td><input type="button" class="editButton" value="編集" onclick="inputForm.showEditForm(${item.id})" /></td>
		</tr>
	<c:if test="${status.last}">
	</table>
	<input type="hidden" id="commuterTicketMonth${item.id}"
		value="<fmt:formatDate value="${item.commuterTicketMonth}" pattern="yyyyMM"/>">
	<input type="hidden" id="startDate${item.id}"
		value="<fmt:formatDate value="${item.commuterTicketMonth}" pattern="yyyyMMdd"/>">
	<input type="hidden" id="ticketPrice${item.id}"	value="${item.ticketPrice}">
	</c:if>
</c:forEach>
