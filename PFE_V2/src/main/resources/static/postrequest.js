$(document).ready(
		function() {

			// SUBMIT FORM
			$("#ajoutForm").click(function(event) {
				// Prevent the form from submitting via the browser.
				//event.preventDefault();

				console.log($("input[name='utilisateurType']:checked").val());
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					utilisateurNom : $("#utilisateurNom").val(),
					utilisateurPrenom : $("#utilisateurPrenom").val(),
					utilisateurPassword : $("#utilisateurPassword").val(),
					utilisateurEmail : $("#utilisateurEmail").val(),
					utilisateurType : $("input[name='utilisateurType']:checked").val()
				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/saveUser",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
							$("#ajoutResult").html(
											"Post Successfully! <br>"
											+ "---> Congrats !!" + "</p>");
						} else {
							$("#ajoutResult").html("<strong>Error</strong>");
						}
						console.log(result);
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});

			}

		})