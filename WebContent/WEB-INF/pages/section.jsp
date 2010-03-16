<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script language="javascript">
var inputForm = new InputForm('section');
$(function() {
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

<hr size="2" width="100%" align="left" ></hr>
<h3>概要</h3>
<p>　部署マスタ一覧画面、及び、部署マスタ入力画面より部署マスタの管理（登録・更新・削除）を行う。</p>
<br></br>

<h3>項目定義</h3>
<table border="1">
	<caption>部署マスタ一覧画面</caption>
	<tr bgcolor="#99ffff">
		<th>No</th><th>表示部</th><th>項目名</th><th>タイプ</th><th>I/O</th><th>桁数</th><th>備考</th>
	</tr>
	<tr>
		<td>1</td><td>一覧部</td><td>ID</td><td>テキスト</td><td>O</td><td>11</td><td></td>
	</tr>
	<tr>
		<td>2</td><td>一覧部</td><td>部署名</td><td>テキスト</td><td>O</td><td>40</td><td></td>
	</tr>
	<tr>
		<td>3</td><td>一覧部</td><td>操作</td><td>ボタン</td><td>O</td><td>-</td>	<td></td>
	</tr>
</table>
<p>※ヘッダ部、フッタ部については、マスタの一覧・入力画面の共通仕様を参照。</p>
<br></br>

<table border="1">
	<caption>部署マスタ入力画面</caption>
	<tr bgcolor="#99ffff">
		<th>No</th><th>表示部</th><th>項目名</th><th>タイプ</th><th>Ｉ/Ｏ</th><th>桁数</th><th>備考</th>
	</tr>
	<tr>
		<td>1</td><td>入力部</td><td>部署名</td><td>ラベル</td><td>O</td><td>-</td><td></td>
	</tr>
	<tr>
		<td>2</td><td>入力部</td><td>部署名入力エリア</td><td>テキストボックス</td><td>I</td><td>40</td><td></td>
	</tr>
</table>
<p>※保存、削除、閉じるボタンについては、マスタの一覧・入力画面の共通仕様を参照。</p>
<br></br>

<h3>入力チェック</h3>
<table border="1">
	<tr bgcolor="#99ffff">
		<th>No</th><th width="100">契機</th><th width="150">項目名</th><th width="50">分類</th><th>仕様</th><th>エラー時の動作</th>
	</tr>
	<tr>
		<td>1</td><td>保存ボタン</td><td>部署名入力エリア</td><td>必須</td><td>入力されていない場合</td><td>部署名の横に「必須入力です。」と表示する</td>
	</tr>
	<tr>
		<td>2</td><td>保存ボタン</td><td>部署名入力エリア</td><td>桁数</td><td>入力された文字が40文字を越える場合</td><td>部署名の横に「40文字以内で入力して下さい。」と表示する</td>
	</tr>
	<tr>
		<td>3</td><td>保存ボタン</td><td>部署名入力エリア</td><td>重複</td><td>登録・更新時に該当データが存在した場合</td><td>部署名の横に「すでに登録されています。」と表示する</td>
	</tr>
</table>
<p>※存在チェックについては、マスタの一覧・入力画面を参照。</p>
<br></br>

<h3>アクション</h3>
<p>　イベント一覧、画面制御については、マスタの一覧・入力画面の共通仕様を参照。</p>
<br></br>

<h3>その他</h3>
<p>　特になし。</p>
<br></br>

<h3>検討事項</h3>
<p>　特になし。</p>
	