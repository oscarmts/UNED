$(function() {
	var $tabButtonItem = $('#tab-button li'), $tabSelect = $('#tab-select'), $tabContents = $('.tab-contents'), activeClass = 'is-active';
	$tabButtonItem.first().addClass(activeClass);
	$tabContents.not(':first').hide();
	var successContainer = $('.sucess-container');
	$tabButtonItem.find('a').on('click', function(e) {
		var target = $(this).attr('href');

		$tabButtonItem.removeClass(activeClass);
		$(this).parent().addClass(activeClass);
		$tabSelect.val(target);
		$tabContents.hide();
		$(target).show();
		e.preventDefault();
		successContainer.hide();
	});

	$tabSelect.on('change', function() {
		var target = $(this).val(), targetSelectNum = $(this).prop(
				'selectedIndex');

		$tabButtonItem.removeClass(activeClass);
		$tabButtonItem.eq(targetSelectNum).addClass(activeClass);
		$tabContents.hide();
		$(target).show();
	});
});