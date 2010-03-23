<c:forEach var="item" items="${sectionPage.list}" varStatus="status">
	<c:if test="${status.first}">
	<table class="dataTable">
		<thead>
			<tr>
				<th>ID</th>
				<th>部署名</th>
				<th>削除</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
	</c:if>
		<tr<c:if test="${status.index mod 2 != 0}"> class="odd"</c:if>>
			<td id="id${item.id}">${item.id}</td>
			<td id="sectionName${item.id}">${item.sectionName}</td>
			<td><span><fmt:formatDate value="${item.termValidStart}" pattern="yyyy/MM/dd"/></span></td>
			<td><span><fmt:formatDate value="${item.termValidEnd}" pattern="yyyy/MM/dd"/></span></td>
			<td><input type="button" class="editButton" value="編集" onclick="inputForm.showEditForm(${item.id})" /></td>
		</tr>
	<c:if test="${status.last}">
		</tbody>
	</table>
	<input type="hidden" id="termValidStart${item.id}" value="${item.termValidStart}" pattern="yyyyMMdd"/>">
	<input type="hidden" id="termValidEnd${item.id}" value="${item.termValidEnd}" pattern="yyyyMMdd"/>">
	</c:if>
</c:forEach>
