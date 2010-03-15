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
		var name = this.tableName;
		$('#saveButton').click(function() {
			$('#inputForm').ajaxSubmit({
				url: '/ikko/' + name + '/save',
				dataType: 'json',
				success: function(json) {
					if (json.error) {
						for (key in json.messages) {
							$('#' + key + 'Message').text(json.messages[key]);
						}
					} else {
						randerTable(name);
						$.unblockUI();
					}
				}
			});
			return false;
		});
	},

	'execBlockUI' : function() {
		$.blockUI({
			message: $('#inputForm'),
			css: {
				width: 'auto',
				border: '2px solid #666',
				padding: '1em',
				backgroundColor: '#fff',
				'-webkit-border-radius': '10px',
				'-moz-border-radius': '10px',
				opacity: .9
			}
		});
	},

	/**
	 * 入力フォーム表示(新規登録)
	 */
	'showAddForm' : function() {
		var i = 0;
		for(i = 0; i < this.fields.length; i++) {
			$('#' + this.fields[i]).val('');
		}
		this.execBlockUI();
	},

	/**
	 * 入力フォーム表示(編集)
	 * 
	 * @param id
	 */
	'showEditForm' : function(id) {
		var i = 0;
		for(i = 0; i < this.fields.length; i++) {
			$('#' + this.fields[i]).val($('#' + this.fields[i] + id).text());
		}
		this.execBlockUI();
	}

};