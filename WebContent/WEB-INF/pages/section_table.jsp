<c:forEach var="item" items="${sectionPage.list}" varStatus="status">
	<c:if test="${status.first}">
	<table class="dataTable">
		<tr>
			<th>ID</th>
			<th>部署名</th>
			<th>操作</th>
		</tr>
	</c:if>
		<tr>
			<td id="id${item.id}">${item.id}</td>
			<td id="sectionName${item.id}">${item.sectionName}</td>
			<td><input type="button" class="editButton" value="編集" onclick="inputForm.showEditForm(${item.id})" /></td>
		</tr>
	<c:if test="${status.last}">
	</table>
	</c:if>
</c:forEach>
