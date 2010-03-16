/*
 * 入力フォーム用JavaScript
 */
function randerTable(name) {
	$.ajax({
		url: '/ikko/' + name + '/table',
	  	cache: false,
	  	success: function(html){
			$("#results").html('').append(html);
			$('table.dataTable tr:even').css('background-color', '#eee');
		}
	});
}

function success(json, name) {
	if (json.error) {
		for (key in json.messages) {
			$('#' + key + 'Message').text(json.messages[key]);
		}
	} else {
		randerTable(name);
		$.unblockUI();
	}
}

function execBlockUI() {
	$.blockUI({
		message: $('#inputForm'),
		css: {
			top: '1em',
			width: 'auto',
			border: '2px solid #666',
			padding: '1em',
			backgroundColor: '#fff',
			'-webkit-border-radius': '10px',
			'-moz-border-radius': '10px',
			opacity: .9
		}
	});
}

/**
 * 入力フォームクラスです。
 * 
 * @param table
 * 	          編集対象のテーブル名
 * @param fields
 *            編集対象のフィールド名の配列
 */
function InputForm(name, fields) {
	this.tableName = name;
	this.fields = fields;
};

InputForm.prototype = {

	'init' : function() {
		$('table.dataTable tr:even').css('background-color', '#eee');

		$("#inputForm").ajaxComplete(function(event, XMLHttpRequest, options){
			$('.formButton').removeAttr('disabled');
		});

		var name = this.tableName;
	    $('#saveButton').click(function() {
			$('.formButton').attr('disabled', true);
			$('#inputForm').ajaxSubmit({
				url: '/ikko/' + name + '/save',
				dataType: 'json',
				success: function(json) {
					success(json, name);
				}
			});
			return false;
		});

		$('#deleteButton').click(function() {
			$('.formButton').attr('disabled', true);
			if (confirm('本当に削除しますか？')) {
				$('#inputForm').ajaxSubmit({
					url: '/ikko/' + name + '/delete',
					dataType: 'json',
					success: function(json) {
						success(json, name);
					}
				});
				return false;
			}
		});
	},

	/**
	 * 入力フォーム表示(新規登録)
	 */
	'showAddForm' : function() {
		$('#deleteButton').css('display', 'none');
		$('.message').text('');
		var i = 0;
		for(i = 0; i < this.fields.length; i++) {
			$('#' + this.fields[i]).val('');
		}
		execBlockUI();
	},

	/**
	 * 入力フォーム表示(編集)
	 * 
	 * @param id
	 */
	'showEditForm' : function(id) {
		$('#deleteButton').css('display', 'inline');
		$('.message').text('');
		var value = '';
		var i = 0;
		for(i = 0; i < this.fields.length; i++) {
			value = $('#' + this.fields[i] + id).text();
			if ($('#' + this.fields[i] + id).attr('type') != undefined) {
				value = $('#' + this.fields[i] + id).val();
			}
			$('#' + this.fields[i]).val(String(value));
		}
		execBlockUI();
	}

};