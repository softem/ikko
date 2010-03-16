<c:forEach var="item" items="${projectPage.list}" varStatus="status">
	<c:if test="${status.first}">
	<table class="dataTable">
		<tr>
			<th rowspan="2">ID</th>
			<th rowspan="2">プロジェクトコード</th>
			<th rowspan="2">プロジェクト名</th>
			<th rowspan="2">作業場所</th>
			<th rowspan="2">Flex</th>
			<th colspan="2">コアタイム</th>
			<th rowspan="2">単位時間（分）</th>
			<th colspan="2">定時間</th>
			<th colspan="2">定時間後</th>
			<th rowspan="2">操作</th>
		</tr>
		<tr>
			<th>開始</th>
			<th>終了</th>
			<th>開始</th>
			<th>終了</th>
			<th>勤務開始</th>
			<th>前日継続終了</th>
		</tr>
	</c:if>
		<tr>
			<td id="id${item.id}">${item.id}</td>
			<td id="projectCode${item.id}">${item.projectCode}</td>
			<td id="projectName${item.id}">${item.projectName}</td>
			<td id="workPlace${item.id}">${item.workPlace}</td>
			<td>
				<c:choose>
					<c:when test="${item.flexFlag}">有</c:when>
					<c:otherwise>無</c:otherwise>
				</c:choose>
			</td>
			<td id="coreTimeStart${item.id}">${item.coreTimeStart}</td>
			<td id="coreTimeFinish${item.id}">${item.coreTimeFinish}</td>
			<td id="unitMinute${item.id}">${item.unitMinute}</td>
			<td id="fixStartTime${item.id}">${item.fixStartTime}</td>
			<td id="fixFinishTime${item.id}">${item.fixFinishTime}</td>
			<td id="afterFixStartTime${item.id}">${item.afterFixStartTime}</td>
			<td id="afterFixFinishTime${item.id}">${item.afterFixFinishTime}</td>
			<td><input type="button" class="editButton" value="編集" onclick="inputForm.showEditForm(${item.id})" /></td>
		</tr>
	<c:if test="${status.last}">
	</table>
	<input type="hidden" id="flexFlag${item.id}" value="${item.flexFlag}"/>
	<div style="display:none" id="monday${item.id}">${item.monday}</div>
	<div style="display:none" id="tuesday${item.id}">${item.tuesday}</div>
	<div style="display:none" id="wednesday${item.id}">${item.wednesday}</div>
	<div style="display:none" id="thursday${item.id}">${item.thursday}</div>
	<div style="display:none" id="friday${item.id}">${item.friday}</div>
	<div style="display:none" id="saturday${item.id}">${item.saturday}</div>
	<div style="display:none" id="sunday${item.id}">${item.sunday}</div>
	</c:if>
</c:forEach>
