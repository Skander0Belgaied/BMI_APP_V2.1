GET: $(document).ready(
		function() {

/*----------------------------------------------------recharge du tab avec tout les utilisateur -------------------------------------------------------*/

				$.ajax({
					type : "GET",
					url : "Users/",
					
					success : function(result) {
						if (result.status == "success") {
							$('#utilisateurtable').empty();
							$('#utilisateurs').empty();
							var custList = "";
							$.each(result.data,
									function(i, users) {
										var tableInit= "<tr><th scope='row'>"+users.utilisateurId+"</th><td>"
												+ users.utilisateurNom+"</td><td>"
												+ users.utilisateurPrenom+"</td><td>"
												+ users.utilisateurEmail+"</td><td>"
												+ users.utilisateurType+"</td><td>"
												+ "<a class='btn btn-danger btn-circle' onclick='ajaxGets("+users.utilisateurId+")'><i class='fas fa-trash'></i></a></td>"
                   										 
                 										 
												+ "</tr>";
											var	datalistInit="<option value='"+users.utilisateurEmail+"' name='"+users.utilisateurId+"'>"+users.utilisateurNom+" "+users.utilisateurPrenom+"</option>";


										$('#utilisateurtable').append(
												tableInit)
										$('#utilisateurs').append(
												datalistInit)
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

/*----------------------------------------------------debut des event -------------------------------------------------------*/

/*---------------------------------------search call------------------------------------------------------------*/

			$("#filter").keyup(function(event) {

				//event.preventDefault();
				console.log("key donw ");
				ajaxGet();
			});
			/*---------------------------------------search call------------------------------------------------------------*/
			$("#updatefilter").change(function(event) {

				//event.preventDefault();
				console.log("change update");
				ajaxGetUpdate();
			});
			
		});
/*---------------------------------------fin Ready------------------------------------------------------------*/

function ajaxGet() {
				var email=$("#filter").val();
				$.ajax({
					type : "GET",
					url : "Users/"+email,
					
					success : function(result) {
						showUsers(result);
					},
					error : function(e) {
						$("#getResultDiv").html("<strong>Error</strong>");
						console.log("ERROR: ", e);
					}
				});
			}

/*---------------------------------------function delete define------------------------------------------------------------*/
function ajaxGets(id) {
	$.ajax({
					type : "GET",
					url : "/delete/"+id,
					
					success : function(result) {},
					error : function(e) {}
					}
				);

				var email=$("#filter").val();
				$.ajax({
					type : "GET",
					url : "Users/"+email,
					
					success : function(result) {
						showUsers(result);
					},
					error : function(e) {
						$("#getResultDiv").html("<strong>Error</strong>");
						console.log("ERROR: ", e);
					}
				});
			}
/*-----------------------------------------------------------------------------------------------------------------*/
function showUsers(result)
{

						if (result.status == "success") {
							$('#utilisateurtable').empty();
							var custList = "";
							$.each(result.data,
									function(i, users) {
										var user = "<tr><th scope='row'>"+users.utilisateurId+"</th><td>"
												+ users.utilisateurNom+"</td><td>"
												+ users.utilisateurPrenom+"</td><td>"
												+ users.utilisateurEmail+"</td><td>"
												+ users.utilisateurType+"</td><td>"
												+ "<a class='btn btn-danger btn-circle' onclick='ajaxGets("+users.utilisateurId+")'><i class='fas fa-trash'></i></a></td>"
                   										 
                 										 
												+ "</tr>";
										$('#utilisateurtable').append(
												user)
									});
							console.log("Success: ", result);
						} else {
							$("#error").html("<strong>Error</strong>");
							console.log("Fail: ", result);
						}
}
/*---------------------------------------------------------------------------------------------------------------------------------------------------------*/
function ajaxGetUpdate() {
				var email=$("#updatefilter").val();
				$.ajax({
					type : "GET",
					url : "Users/"+email,
					
					success : function(result) {
						showUpdate(result);
					},
					error : function(e) {
						$("#getResultDiv").html("<strong>Error</strong>");
						console.log("ERROR: ", e);
					}
				});
			}
/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
function showUpdate(result)
{

						if (result.status == "success") {
							$('#utilisateurtable').empty();
							var custList = "";
							$.each(result.data,
									function(i, users) {
										$("#utilisateurNom1").val(users.utilisateurNom);
										$("#utilisateurPrenom1").val(users.utilisateurPrenom);
										$("#utilisateurEmail1").val(users.utilisateurEmail);
										console.log(users.utilisateurType);
										if(users.utilisateurType=="ADMIN")
										{
											$("#utilisateurType1").prop('checked', true);
										}
										else{$("#utilisateurType1").prop('checked', false); }
									});
							console.log("Success: ", result);
						} else {
							$("#error").html("<strong>Error</strong>");
							console.log("Fail: ", result);
						}
}

