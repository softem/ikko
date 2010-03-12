/*
 * 入力フォーム用JavaScript
 * 
 * '#inputForm' : 入力フォーム
 * '.formButton' : 入力フォーム上のボタン
 * '.editButton' : 一覧画面上のボタン
 * '#dataTable' : 一覧テーブル
 * '#f\\:saveButton' : 保存ボタン
 * '#f\\:deleteButton' : 削除ボタン
 * '#close' : 閉じるボタン
 */

/**
 * Ajax通信状態監視
 * 
 * @param data
 */
function onStatusChange(data) {
	if (data.type == 'event') {
		if (data.status == 'begin') {
			$('.formButton').attr('disabled', true);
		}
		if (data.status == 'success') {
			if ($('.error').html() == null) {
				$('.editButton').removeAttr('disabled');
				$('#inputForm').css('display', 'none');
			} else {
				$('.editButton').attr('disabled', true);
				$('.error').css('color', '#f00');
			}
			$('#inputForm').corner();
			$('table#f\\:dataTable tr:even').css('background-color', '#eee');
		}
		if (data.status == 'complete') {
			$('.formButton').removeAttr('disabled');
		}
	}
};

/**
 * 入力フォームクラスです。
 * 
 * @param fields
 *            編集対象のフィールド名の配列
 */
function InputForm(fields) {
	this.fields = fields;
};

InputForm.prototype = {

	/**
	 * 設定処理
	 */
	'setupPage' : function() {
		$('#inputForm').corner();
		$('table#f\\:dataTable tr:even').css('background-color', '#eee');
	},

	/**
	 * 入力フォーム表示時処理
	 */
	'setupForm' : function() {
		$('#inputForm').css('display', 'block');
		$('#inputForm input:first').focus();
		$('.editButton').attr('disabled', true);
	},

	/**
	 * 入力フォーム非表示時処理
	 */
	'teardownForm' : function() {
		$('.editButton').removeAttr('disabled');
		$('#inputForm').css('display', 'none');
	},

	/**
	 * 初期処理
	 * 
	 * <p>
	 * ページ表示直前に実行したいイベントなどの設定を実施。
	 * </p>
	 */
	'init' : function() {
		this.setupPage();
		this.teardownForm();

		var targetRender = "f:dataTable";
		for (i = 0; i < this.fields.length; i++) {
			targetRender += ' f:' + this.fields[i] + 'Message';
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
	},

	/**
	 * 入力フォーム表示(新規登録)
	 */
	'showAddForm' : function() {
		this.setupForm();
		$('#f\\:deleteButton').css('display', 'none');

		$('#f\\:id').val(0);
		var i = 0;
		for (i = 0; i < this.fields.length; i++) {
			$('#f\\:' + this.fields[i]).val('');
		}
	},

	/**
	 * 入力フォーム表示(編集)
	 * 
	 * @param id
	 */
	'showEditForm' : function(id) {
		this.setupForm();
		$('#f\\:deleteButton').css('display', 'inline');

		$('#f\\:id').val(id);
		var i = 0;
		for (i = 0; i < this.fields.length; i++) {
			$('#f\\:' + this.fields[i])
					.val($('#' + this.fields[i] + id).text());
		}
	}

};