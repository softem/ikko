/*
 * マスタメンテナンス共通JavaScript
 * 
 * 事前にconfig/xxx.jsを用意してtargetItemsという名称のグローバル変数を用意する必要があります。
 * targetItemsは編集対象項目名の配列です。
 * 
 * 例:
 * var targetItem = new Array('sectionId', 'sectionName');
 */

/**
 * 設定処理
 * 
 * <p>
 * Ajax再描画後にリセットされてしまう設定をメソッド化。
 * </p>
 */
function setupPage() {
	$('#inputForm').corner();
	$('table#f\\:dataTable tr:even').css('background-color', '#eee');
}

/**
 * 初期処理
 * 
 * <p>
 * ページ表示直前に実行したいイベントなどの設定を実施。
 * </p>
 */
$(function() {

	setupPage();
	teardownForm();

	// Ajax通信後の再描画先
	var targetRender = "f:dataTable";
	for (i = 0; i < targetItems.length; i++) {
		targetRender += ' f:' + targetItems[i] + 'Message';
	}

	$("#f\\:saveButton").live("click", function(event) {
		jsf.ajax.request(event.target, event, {
			execute : "@form",
			render : targetRender,
			onevent : onStatusChange
		});
		event.preventDefault();
	});

	$("#f\\:deleteButton").live("click", function(event) {
		if (confirm('本当に削除しますか？')) {
			jsf.ajax.request(event.target, event, {
				execute : "@form",
				render : targetRender,
				onevent : onStatusChange
			});
		}
		event.preventDefault();
	});

	$('#close').live("click", function(event) {
		$('#inputForm').css('display', 'none');
		$('.editButton').removeAttr('disabled');
		$('.error').text('');
		return false;
	});
});

/**
 * 入力フォーム表示時処理
 */
function setupForm() {
	$('#inputForm').css('display', 'block');
	$('#inputForm input:first').focus();
	$('.editButton').attr('disabled', true);
}

/**
 * 入力フォーム非表示時処理
 */
function teardownForm() {
	$('.editButton').removeAttr('disabled');
	$('#inputForm').css('display', 'none');
}

/**
 * 入力フォーム表示(新規登録)
 */
function showAddForm() {
	setupForm();
	$('#f\\:deleteButton').css('display', 'none');

	// 項目値の設定
	$('#f\\:id').val(0);
	var i = 0;
	for (i = 0; i < targetItems.length; i++) {
		$('#f\\:' + targetItems[i]).val('');
	}
}

/**
 * 入力フォーム表示(編集)
 * 
 * @param id
 */
function showEditForm(id) {
	setupForm();
	$('#f\\:deleteButton').css('display', 'inline');

	// 項目値の設定
	$('#f\\:id').val(id);
	var i = 0;
	for (i = 0; i < targetItems.length; i++) {
		$('#f\\:' + targetItems[i]).val($('#' + targetItems[i] + id).text());
	}
}

/**
 * Ajax通信状態監視
 * 
 * @param data
 */
function onStatusChange(data) {
	if (data.type == 'event') {
		if (data.status == 'begin') {
			// 通信前にボタンを無効化
			$('.formButton').attr('disabled', true);
		}
		if (data.status == 'success') {
			if ($('.error').html() == null) {
				// 処理成功
				teardownForm();
			} else {
				// 入力チェックエラー時
				$('.error').css('color', '#f00');
				$('.editButton').attr('disabled', true);
			}
			setupPage();
		}
		if (data.status == 'complete') {
			// 通信後にボタンを有効化
			$('.formButton').removeAttr('disabled');
		}
	}
}
