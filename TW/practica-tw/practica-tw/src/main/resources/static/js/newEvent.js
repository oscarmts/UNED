$(document).ready(function() {
	$("button").click(function() {
		var emptyDates = $("input[type='date']").filter(function() {
			return !$(this).val();
		}).length;
		if (emptyDates == 0) {
			$("form").submit(); // Submit the form
		} else {
			alert("Complete todas las fechas");
		}
	});
});
$(document)
		.ready(
				function() {
					var max_fields = 10; // maximum input boxes allowed
					var wrapper = $(".input_fields_wrap"); // Fields wrapper
					var add_button = $(".add_field_button"); // Add button ID

					var x = 1; // initlal text box count
					$(add_button)
							.click(
									function(e) { // on add input button click
										e.preventDefault();
										if (x < max_fields) { // max input box
											// allowed
											x++; // text box increment
											$(wrapper)
													.append(
															'<div class="col-sm-2"><input type="date" name="dates" id="dates" /><a href="#" class="remove_field"><i class="fas fa-minus"></i></a></div>'); // add
											// input
											// box
										}
									});

					$(wrapper).on("click", ".remove_field", function(e) { // user
						// click
						// on
						// remove
						// text
						e.preventDefault();
						$(this).parent('div').remove();
						x--;
					})
				});
$(document)
		.ready(
				function() {
							$('#usersTable')
									.dataTable(
											{
												"language" : {
													"sProcessing" : "Procesando...",
													"sLengthMenu" : "Mostrar _MENU_ registros",
													"sZeroRecords" : "No se encontraron resultados",
													"sEmptyTable" : "Ningún dato disponible en esta tabla",
													"sInfo" : "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
													"sInfoEmpty" : "Mostrando registros del 0 al 0 de un total de 0 registros",
													"sInfoFiltered" : "(filtrado de un total de _MAX_ registros)",
													"sInfoPostFix" : "",
													"sSearch" : "Buscar:",
													"sUrl" : "",
													"sInfoThousands" : ",",
													"sLoadingRecords" : "Cargando...",
													"oPaginate" : {
														"sFirst" : "Primero",
														"sLast" : "Último",
														"sNext" : "Siguiente",
														"sPrevious" : "Anterior"
													},
													"oAria" : {
														"sSortAscending" : ": Activar para ordenar la columna de manera ascendente",
														"sSortDescending" : ": Activar para ordenar la columna de manera descendente"
													}
												}
											}),
							$('#waitingListTable')
									.dataTable(
											{
												"language" : {
													"sProcessing" : "Procesando...",
													"sLengthMenu" : "Mostrar _MENU_ registros",
													"sZeroRecords" : "No se encontraron resultados",
													"sEmptyTable" : "Ningún dato disponible en esta tabla",
													"sInfo" : "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
													"sInfoEmpty" : "Mostrando registros del 0 al 0 de un total de 0 registros",
													"sInfoFiltered" : "(filtrado de un total de _MAX_ registros)",
													"sInfoPostFix" : "",
													"sSearch" : "Buscar:",
													"sUrl" : "",
													"sInfoThousands" : ",",
													"sLoadingRecords" : "Cargando...",
													"oPaginate" : {
														"sFirst" : "Primero",
														"sLast" : "Último",
														"sNext" : "Siguiente",
														"sPrevious" : "Anterior"
													},
													"oAria" : {
														"sSortAscending" : ": Activar para ordenar la columna de manera ascendente",
														"sSortDescending" : ": Activar para ordenar la columna de manera descendente"
													}
												}
											});
				});
$(document).on(
		'change',
		'.detectUsersChange',
		function() {
			var self = $(this);
			if (self.is(":checked")) {
				$('#users').append(
						'<input type="hidden" name="usersSelected"  id="usersSelected" value='
								+ self.attr("value") + ' />');
			} else {
				$("#users :input[value='" + self.attr("value") + "']").remove()
			}
		});
$(document)
		.on(
				'change',
				'.detectWaitingListChange',
				function() {
					var self = $(this);
					if (self.is(":checked")) {
						$('#usersWaitingList')
								.append(
										'<input type="hidden" name="waitingListSelected"  id="waitingListSelected" value='
												+ self.attr("value") + ' />');
					} else {
						$(
								"#usersWaitingList :input[value='"
										+ self.attr("value") + "']").remove()
					}
				});