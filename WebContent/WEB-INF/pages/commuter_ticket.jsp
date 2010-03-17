<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script language="javascript">
var inputForm = new InputForm('commuter_ticket');
$(function() {
	inputForm.init();
});
</script>

<div id="local-menu">
	<a href="${t:url("/welcome/main")}">メインメニューへ戻る</a>
</div>

<form id="inputForm" method="post" onsubmit="return false;">
	<div>
		<label for="commuterTicketMonth">定期代年月<span id="commuterTicketMonthMessage" class="message"></span></label>
		<input type="text" id="commuterTicketMonth" name="commuterTicketMonth" size="6" maxlength="6" />
	</div>
	<div>
		<label for="commuterTicketNo">定期代連番<span id="commuterTicketNoMessage" class="message"></span></label>
		<input type="text" id="commuterTicketNo" name="commuterTicketNo" size="11" maxlength="11" />
	</div>
	<div>
 		<label for="startDate">使用開始年月日（西暦8桁）<span id="startDateMessage" class="message"></span></label>
		<input type="text" id="startDate" name="startDate" size="8" maxlength="8" />
	</div>
	<div>
		<label for="lineName">路線名<span id="lineNameMessage" class="message"></span></label>
		<input type="text" id="lineName" name="lineName" size="35" maxlength="40" />
	</div>
	<div>
		<label for="stationNameStart">駅名（始点）<span id="stationNameStartMessage" class="message"></span></label>
		<input type="text" id="stationNameStart" name="stationNameStart" size="35" maxlength="40" />
	</div>
	<div>
		<label for="stationNameEnd">駅名（終点）<span id="stationNameEndMessage" class="message"></span></label>
		<input type="text" id="stationNameEnd" name="stationNameEnd" size="35" maxlength="40" />
	</div>
	<div>
		<label for="ticketPrice">金額<span id="ticketPriceMessage" class="message"></span></label>
		<input type="text" id="ticketPrice" name="ticketPrice" size="6" maxlength="6" />
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
<jsp:include page="commuter_ticket_table.jsp"></jsp:include>
</div>


<hr size="2" width="100%" align="left" ></hr>
<h3>概要</h3>
<p>　プロジェクトマスタ一覧画面、及び、プロジェクトマスタ入力画面よりプロジェクトマスタの管理（登録・更新・削除）を行う。</p>
<br></br>

<h3>項目定義</h3>
<table border="1">
	<caption>プロジェクトマスタ一覧画面</caption>
	<tr bgcolor="#99ffff">
		<th>No</th><th>表示部</th><th>項目名</th><th>タイプ</th><th>I/O</th><th>桁数</th><th>備考</th>
	</tr>
	<tr>
		<td>1</td><td>一覧部</td><td>ID</td><td>テキスト</td><td>O</td><td>11</td><td></td>
	</tr>
	<tr>
		<td>2</td><td>一覧部</td><td>プロジェクトコード</td><td>テキスト</td><td>O</td><td>20</td><td></td>
	</tr>
	<tr>
		<td>3</td><td>一覧部</td><td>プロジェクト名</td><td>テキスト</td><td>O</td><td>20</td><td></td>
	</tr>
	<tr>
		<td>4</td><td>一覧部</td><td>作業場所</td><td>テキスト</td><td>O</td><td>20</td><td></td>
	</tr>
	<tr>
		<td>5</td><td>一覧部</td><td>Flex</td><td>テキスト</td><td>O</td><td>1</td><td></td>
	</tr>
	<tr>
		<td>6</td><td>一覧部</td><td>コアタイム開始</td><td>テキスト</td><td>O</td><td>4</td><td></td>
	</tr>
	<tr>
		<td>7</td><td>一覧部</td><td>コアタイム終了</td><td>テキスト</td><td>O</td><td>4</td><td></td>
	</tr>
	<tr>
		<td>8</td><td>一覧部</td><td>単位時間（分）</td><td>テキスト</td><td>O</td><td>2</td><td></td>
	</tr>
	<tr>
		<td>9</td><td>一覧部</td><td>定時間開始</td><td>テキスト</td><td>O</td><td>4</td><td></td>
	</tr>
	<tr>
		<td>10</td><td>一覧部</td><td>定時間終了</td><td>テキスト</td><td>O</td><td>4</td><td></td>
	</tr>
	<tr>
		<td>11</td><td>一覧部</td><td>定時間後勤務開始</td><td>テキスト</td><td>O</td><td>4</td><td></td>
	</tr>
	<tr>
		<td>12</td><td>一覧部</td><td>前日勤務終了</td><td>テキスト</td><td>O</td><td>4</td><td></td>
	</tr>
	<tr>
		<td>13</td><td>一覧部</td><td>操作</td><td>ボタン</td><td>O</td><td>-</td><td></td>
	</tr>
</table>
<p>※ヘッダ部、フッタ部については、マスタの一覧・入力画面の共通仕様を参照。</p>
<br></br>

<table border="1">
	<caption>プロジェクトマスタ入力画面</caption>
	<tr bgcolor="#99ffff">
		<th>No</th><th>表示部</th><th>項目名</th><th>タイプ</th><th>Ｉ/Ｏ</th><th>桁数</th><th>備考</th>
	</tr>
	<tr>
		<td>1</td><td>入力部</td><td>プロジェクトコード</td><td>ラベル</td><td>O</td><td>-</td><td></td>
	</tr>
	<tr>
		<td>2</td><td>入力部</td><td>プロジェクトコード入力エリア</td><td>テキストボックス</td><td>I</td><td>20</td><td></td>
	</tr>
	<tr>
		<td>3</td><td>入力部</td><td>プロジェクト名</td><td>ラベル</td><td>O</td><td>-</td><td></td>
	</tr>
	<tr>
		<td>4</td><td>入力部</td><td>プロジェクト名入力エリア</td><td>テキストボックス</td><td>I</td><td>20</td><td></td>
	</tr>
	<tr>
		<td>5</td><td>入力部</td><td>作業場所</td><td>ラベル</td><td>O</td><td>-</td><td></td>
	</tr>
	<tr>
		<td>6</td><td>入力部</td><td>作業場所入力エリア</td><td>テキストボックス</td><td>I</td><td>20</td><td></td>
	</tr>
	<tr>
		<td>7</td><td>入力部</td><td>フレックスフラグチェックエリア</td><td>チェックボックス</td><td>I</td><td>-</td><td></td>
	</tr>
	<tr>
		<td>8</td><td>入力部</td><td>フレックスフラグ</td><td>ラベル</td><td>O</td><td>-</td><td></td>
	</tr>
	<tr>
		<td>9</td><td>入力部</td><td>コアタイム開始～終了</td><td>ラベル</td><td>O</td><td>-</td><td></td>
	</tr>
	<tr>
		<td>10</td><td>入力部</td><td>コアタイム開始入力エリア</td><td>テキストボックス</td><td>I</td><td>4</td><td></td>
	</tr>
	<tr>
		<td>12</td><td>入力部</td><td>～</td><td>ラベル</td><td>O</td><td>-</td><td></td>
	</tr>
	<tr>
		<td>13</td><td>入力部</td><td>コアタイム終了入力エリア</td><td>テキストボックス</td><td>I</td><td>4</td><td></td>
	</tr>
	<tr>
		<td>14</td><td>入力部</td><td>出勤／休日指定</td><td>ラベル</td><td>O</td><td>-</td><td></td>
	</tr>
	<tr>
		<td>15</td><td>入力部</td><td>（0:出勤　1:その他休日　2:法定休日）</td><td>ラベル</td><td>O</td><td>-</td><td></td>
	</tr>
	<tr>
		<td>16</td><td>入力部</td><td>曜日</td><td>ラベル</td><td>O</td><td>-</td><td>月～日曜日を表示する</td>
	</tr>
	<tr>
		<td>17</td><td>入力部</td><td>出勤／休日指定入力エリア</td><td>テキストボックス</td><td>I</td><td>1</td><td>月～日曜日分を表示する</td>
	</tr>
	<tr>
		<td>18</td><td>入力部</td><td>定時間開始～終了</td><td>ラベル</td><td>O</td><td>-</td><td></td>
	</tr>
	<tr>
		<td>19</td><td>入力部</td><td>定時間開始入力エリア</td><td>テキストボックス</td><td>I</td><td>4</td><td></td>
	</tr>
	<tr>
		<td>20</td><td>入力部</td><td>～</td><td>ラベル</td><td>O</td><td>-</td><td></td>
	</tr>
	<tr>
		<td>21</td><td>入力部</td><td>定時間終了入力エリア</td><td>テキストボックス</td><td>I</td><td>4</td><td></td>
	</tr>
	<tr>
		<td>22</td><td>入力部</td><td>定時間後勤務開始～前日継続終了</td><td>ラベル</td><td>O</td><td>-</td><td></td>
	</tr>
	<tr>
		<td>23</td><td>入力部</td><td>定時間後開始入力エリア</td><td>テキストボックス</td><td>I</td><td>4</td><td></td>
	</tr>
	<tr>
		<td>24</td><td>入力部</td><td>～</td><td>ラベル</td><td>O</td><td>-</td><td></td>
	</tr>
	<tr>
		<td>25</td><td>入力部</td><td>前日継続終了入力エリア</td><td>テキストボックス</td><td>I</td><td>4</td><td></td>
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
		<td>1</td><td>保存ボタン</td><td>プロジェクトコード入力エリア</td><td>必須</td><td>入力されていない場合</td><td>プロジェクトコードの横に「必須入力です。」と表示する</td>
	</tr>
	<tr>
		<td>2</td><td>保存ボタン</td><td>プロジェクトコード入力エリア</td><td>桁数</td><td>入力された文字が20文字を越える場合</td><td>プロジェクトコードの横に「20文字以内で入力して下さい。」と表示する</td>
	</tr>
	<tr>
		<td>3</td><td>保存ボタン</td><td>プロジェクトコード入力エリア</td><td>重複</td><td>登録・更新時に該当データが存在した場合</td><td>プロジェクト名の横に「すでに存在しています。」と表示する</td>
	</tr>
	<tr>
		<td>4</td><td>保存ボタン</td><td>プロジェクト名入力エリア</td><td>必須</td><td>入力されていない場合</td><td>プロジェクト名の横に「必須入力です。」と表示する</td>
	</tr>
	<tr>
		<td>5</td><td>保存ボタン</td><td>プロジェクト名入力エリア</td><td>桁数</td><td>入力された文字が20文字を越える場合</td><td>プロジェクト名の横に「20文字以内で入力して下さい。」と表示する</td>
	</tr>
	<tr>
		<td>6</td><td>保存ボタン</td><td>プロジェクト名入力エリア</td><td>重複</td><td>登録・更新時に該当データが存在した場合</td><td>プロジェクト名の横に「すでに存在しています。」と表示する</td>
	</tr>
	<tr>
		<td>7</td><td>保存ボタン</td><td>作業場所入力エリア</td><td>必須</td><td>入力されていない場合</td><td>作業場所の横に「必須入力です。」と表示する</td>
	</tr>
	<tr>
		<td>8</td><td>保存ボタン</td><td>作業場所入力エリア</td><td>桁数</td><td>入力された文字が20文字を越える場合</td><td>作業場所の横に「20文字以内で入力して下さい。」と表示する</td>
	</tr>
	<tr>
		<td>9</td><td>保存ボタン</td><td>作業場所入力エリア</td><td>必須</td><td>入力されていない場合</td><td>作業場所の横に「必須入力です。」と表示する</td>
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
	