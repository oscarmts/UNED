$(document).ready(function() {
	$("button").click(function() {
		var emptyDates = $("input[type='date']").filter(function() {
			return !$(this).val();
		}).length;
		if (emptyDates == 0) {
			$("form").submit(); // Submit the form
		} else {
			alert("No puede dejar la fecha vacía");
		}
	});
});