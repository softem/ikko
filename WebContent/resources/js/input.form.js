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
			top:'30px',
			height:'22em',
			width:'auto',
			overflow:'auto',
			border:'2px solid #666',
			padding:'1em 2em 1em 1em',
			color:'#333',
			backgroundColor:'#fff',
			opacity: .9
		}
	});
}

/**
 * 入力フォームクラスです。
 * 
 * @param table
 * 	          編集対象のテーブル名
 */
function InputForm(name) {
	this.tableName = name;
};

InputForm.prototype = {

	'init' : function() {
		$('table.dataTable tr:even').css('background-color', '#eee');

		$('#inputForm').ajaxComplete(function(event, XMLHttpRequest, options){
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
		$('#inputForm :input').filter(':enabled').each(function() {
			var name = $(this).attr('name');
			var type = $(this).attr('type');
			if (type == 'checkbox') {
				$(this).removeAttr('checked');
			} else if (type != 'button') {
				$(this).val('');
			}
		});
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
		$('#inputForm :input').filter(':enabled').each(function() {
			var name = $(this).attr('name');
			var type = $(this).attr('type');
			var val = $(this).val();
			var srcType = $('#' + name + id).attr('type');
			var srcVal = (srcType == undefined) ? $('#' + name + id).text() : $('#' + name + id).val();
			if (type == 'checkbox') {
				if (val == srcVal) {
					$(this).attr('checked', 'checked');
				} else {
					$(this).removeAttr('checked');
				}
			} else if (type != 'button') {
				$(this).val(srcVal);
			}
		});
		execBlockUI();
	}

};