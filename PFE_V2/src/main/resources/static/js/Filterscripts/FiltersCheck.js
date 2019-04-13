GET: $(document).ready(
		function() 
		{	
		$('#selectrapport').change(function(){
			if($('#selectrapport').val()=="none")
			{
				$('#suivant').prop('disabled', true);
			}
			else
			{
				$('#suivant').prop('disabled', false);

			}
		});


					
		}
);