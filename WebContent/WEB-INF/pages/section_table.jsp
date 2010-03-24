<c:forEach var="item" items="${sectionPage.list}" varStatus="status">
	<c:if test="${status.first}">
	<table class="dataTable">
		<thead>
			<tr>
				<th rowspan="2">ID</th>
				<th rowspan="2">部署名</th>
				<th colspan="2">有効期間</th>
				<th rowspan="2">操作</th>
			</tr>
			<tr>
				<th>開始</th>
				<th>終了</th>
			</tr>
		</thead>
		<tbody>
	</c:if>
		<tr<c:if test="${status.index mod 2 != 0}"> class="odd"</c:if>>
			<td id="id${item.id}">${item.id}</td>
			<td id="sectionName${item.id}">${item.sectionName}</td>
			<td id="termValidStart${item.id}">${item.termValidStart}</td>
			<td id="termValidEnd${item.id}">${item.termValidEnd}</td>
			<td><input type="button" class="editButton" value="編集" onclick="inputForm.showEditForm(${item.id})" /></td>
		</tr>
	<c:if test="${status.last}">
		</tbody>
	</table>
	</c:if>
</c:forEach>
