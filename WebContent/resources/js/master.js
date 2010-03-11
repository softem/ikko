	$(function() {
		$('#inputForm').css('display', 'none');
		$('.editButton').removeAttr('disabled');

		$("#f\\:saveButton").live("click", function(event) {
			jsf.ajax.request(event.target , event, {
				execute:"@form", render:"f:dataTable f:sectionCodeMessage f:sectionNameMessage", onevent:onStatusChange
			});
			event.preventDefault();
		});

		$("#f\\:deleteButton").live("click", function(event) {
			if (confirm('本当に削除しますか？')) {
				jsf.ajax.request(event.target , event, {
					execute:"@form", render:"f:dataTable f:sectionCodeMessage f:sectionNameMessage", onevent:onStatusChange
				});
			}
			event.preventDefault();
		});

		$('#close').live("click", function(event) {
			$('#inputForm').css('display', 'none');
			$('.editButton').removeAttr('disabled');
			$('.errors').text('');
			return false;
		});
	});

	var edit = false;

	function showAddForm() {
		edit = false;
		$('#f\\:id').val(0);
		$('#f\\:sectionCode').val('');
		$('#f\\:sectionName').val('');
		$('#inputForm').css('display', 'block');
		$('#inputForm input:first').focus();
		$('#f\\:deleteButton').css('display', 'none');
		$('.editButton').attr('disabled', true);
	}

	function showEditForm(id) {
		edit = true;
		$('#f\\:id').val(id);
		$('#f\\:sectionCode').val($('#sectionCode'+id).text());
		$('#f\\:sectionName').val($('#sectionName'+id).text());
		$('#inputForm').css('display', 'block');
		$('#inputForm input:first').focus();
		$('#f\\:deleteButton').css('display', 'inline');
		$('.editButton').attr('disabled', true);
	}

	function onStatusChange(data) {
		if (data.type == 'event') {
			if (data.status == 'begin') {
				$('.formButton').attr('disabled', true);
			}
			if (data.status == 'success') {
				if ($('.errors').html() == null) {
					$('#inputForm').css('display', 'none');
					$('.editButton').removeAttr('disabled');
				} else {
					$('.errors').css('color', '#f00');
					$('.editButton').attr('disabled', true);
				}
			}
			if (data.status == 'complete') {
				$('.formButton').removeAttr('disabled');
			}
		}
	}
