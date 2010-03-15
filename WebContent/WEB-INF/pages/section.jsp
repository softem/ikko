<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script language="javascript">
var inputForm = new InputForm('section', new Array('id', 'sectionName'));
$(function() {
	$('table.dataTable tr:even').css('background-color', '#eee');
	inputForm.init();
});
</script>

<div id="local-menu">
	<a href="${t:url("/welcome/main")}">メインメニューへ戻る</a>
</div>

<form id="inputForm" method="post" onsubmit="return false;">
	<div>
		<label for="sectionName">部署名 <span id="sectionNameMessage" class="message"></span></label>
		<input type="text" id="sectionName" name="sectionName" size="42" maxlength="40" />
	</div>
	<div style="text-align:right">
		<input type="hidden" id="id" name="id" />
		<input type="button" id="saveButton" class="formButton" value="保存" style="float:left" />
		<input type="button" id="deleteButton" class="formButton" value="削除" />
		<input type="button" id="closeButton" class="formButton" value="閉じる" onclick="$.unblockUI()" />
	</div>
</form>

<p><input type="button" id="addButton" class="editButton" value="新規登録" onclick="inputForm.showAddForm()" /></p>

<div id="results">
<jsp:include page="section_table.jsp"></jsp:include>
</div>