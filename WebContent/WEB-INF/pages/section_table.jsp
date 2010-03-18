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
			<td>
				<c:choose>
					<c:when test="${item.deleteFlag}">済</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
				<input type="hidden" id="deleteFlag${item.id}" value="${item.deleteFlag}"/>
			</td>
			<td><input type="button" class="editButton" value="編集" onclick="inputForm.showEditForm(${item.id})" /></td>
		</tr>
	<c:if test="${status.last}">
		</tbody>
	</table>
	</c:if>
</c:forEach>
