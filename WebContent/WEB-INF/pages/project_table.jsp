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
			<th colspan="2">有効期間</th>
			<th rowspan="2">操作</th>
		</tr>
		<tr>
			<th>開始</th>
			<th>終了</th>
			<th>開始</th>
			<th>終了</th>
			<th>勤務開始</th>
			<th>前日継続終了</th>
			<th>開始</th>
			<th>終了</th>
		</tr>
	</c:if>
		<tr<c:if test="${status.index mod 2 != 0}"> class="odd"</c:if>>
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
			
			<td id="termValidStart${item.id}">${item.termValidStart}</td>
			<td id="termValidEnd${item.id}">${item.termValidEnd}</td>
			<td><input type="button" class="editButton" value="編集" onclick="inputForm.showEditForm(${item.id})" /></td>
		</tr>
	<c:if test="${status.last}">
	</table>
	<input type="hidden" id="flexFlag${item.id}" value="${item.flexFlag}"/>
	<input type="hidden" id="monday${item.id}" value="${item.monday}" />
	<input type="hidden" id="tuesday${item.id}" value="${item.tuesday}" />
	<input type="hidden" id="wednesday${item.id}" value="${item.wednesday}" />
	<input type="hidden" id="thursday${item.id}" value="${item.thursday}" />
	<input type="hidden" id="friday${item.id}" value="${item.friday}" />
	<input type="hidden" id="saturday${item.id}" value="${item.saturday}" />
	<input type="hidden" id="sunday${item.id}" value="${item.sunday}" />
	</c:if>
</c:forEach>
