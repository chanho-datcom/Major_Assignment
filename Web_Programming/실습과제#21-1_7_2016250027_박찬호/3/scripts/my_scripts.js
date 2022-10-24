$(document).ready(function(){

	//1. Build a date picker for users to enter the date of the sighting.
	$("#datepicker").datepicker({ changeMonth: true, changeYear: true});
	
	$("#type_select").buttonset();

	//3. Build number entry sliders for users to enter distance from creature, creature weight, and creature height.

		//3a. slide_dist
		$( "#slide_dist" ).slider({
			value:0,
			min: 0,
			max: 500,
			step: 10,
			slide: function( event, ui ) {
				$( "#distance" ).val( ui.value);
			}
		});

		
		//3b. slide_weight
		
		$( "#slide_weight" ).slider({
			value:0,
			min: 0,
			max: 5000,
			step: 5,
			slide: function( event, ui ) {
				$( "#weight" ).val( ui.value);
			}
		});

		//3c.  slide_height
		$( "#slide_height" ).slider({
			value:0,
			min: 0,
			max: 20,
			step: 1,
			slide: function( event, ui ) {
				$( "#height" ).val( ui.value);
			}
		});



});//end doc ready