GET: $(document).ready(
		function() {
				
				$.ajax({
					type : "GET",
					url : "/AllFilters",
					
					success : function(result) {
						$('#filters').append("");
						if (result.status == "success") {
							$("#filterschamps > input").val(result.data[0].filterChamp);
							
							$.each(result.data,
									function(i, filters) {
								var div="<option value='"+filters.filterId+"' champfilter='"+filters.filterChamp+"' id='filtersSelect'>"+filters.filterNom+"</option>";
				                  $('#filters').append(div);
									}
							);
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
			});
			



