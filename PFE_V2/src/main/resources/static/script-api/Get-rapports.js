GET: $(document).ready(
		function() {
			$("#selectrapport").empty();
			$("#selectrapport").append("<option value='none'>selectionez un rapport</option>");
			$("#filtre").empty();
			getallrapports($('#Sujet').val());

			$("#selectrapport").change(function(event) {
				$("#filtre").empty();
				if($('#selectrapport').val!="none"){
				$.ajax({
					type : "GET",
					url : "/filters/"+$("#selectrapport").val(),
					
					success : function(result) {
						$('#filtre').append(" <div class='card shadow mb-4'>" +
								"<div class='card-header py-3'>" +
								"<h6 class='m-0 font-weight-bold text-primary'>Filters Disponible</h6>" +
								"</div>" +
								"<div class='card-body'>" +
								"<div class='row' id='row'>");
						if (result.status == "success") {
							$.each(result.data,
									function(i, filters) {
								var div="<div class='custom-control custom-checkbox col-md-3 mb-2'>"+"<input type='checkbox' value='"+filters.filterId+"' name='Filters-rapport' class='custom-control-input' id='"+filters.filterId+"'>"+"<label class='custom-control-label' for='"+filters.filterId+"'>"+filters.filterNom+"</label></div>";
				                  $('#row').append(div);

								
									}
							);
							//$('#filtre').append("</div></div></div>");
							console.log("Success: ", result);
						} else {
							$("#error").html("<strong>Error</strong>");
							console.log("Fail: ", result);
						}
					},
					error : function(e) {
						$("#getResultDiv").html("<strong>Error</strong>");
						console.log("ERROR: ", e);
					}
				});}
				else{
					$("#filtre").empty();
				}

		

			});
			
		});
function getallrapports(sujet)
{
	$.ajax({
		type : "GET",
		url : "/"+sujet,
		
		success : function(result) {
		
			if (result.status == "success") {
				$.each(result.data,
						function(i, rapports) {
				var options="<option value='"+rapports.rapportid+"'>"+rapports.rapportNom+"</option>"
				$('#selectrapport').append(options);
						});
				console.log("Success: ", result);
			} else {
				$("#error").html("<strong>Error</strong>");
				console.log("Fail: ", result);
			}
		},
		error : function(e) {
			$("#getResultDiv").html("<strong>Error</strong>");
			console.log("ERROR: ", e);
		}
	});
}

