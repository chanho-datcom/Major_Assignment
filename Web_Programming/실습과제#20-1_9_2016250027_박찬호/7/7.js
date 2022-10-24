$(document).ready(function(){

	var FREQ = 10000 ;
	var repeat = true;
	
	function showFrequency(){
		$("#freq").html( "Page refreshes every " + FREQ/1000 + " second(s).");
	}
	
	function startAJAXcalls(){
	
		if(repeat){
			setTimeout( function() {
					getDBRacers();
					startAJAXcalls();
				}, 	
				FREQ
			);
		}
	}
	
	function getXMLRacers(){
		$.getJSON("service.php", function(json) {
			alert(json.runners.length);
		});
		getTimeAjax();
	}

	function getDBRacers(){
		$.getJSON("service.php", function(json) {
			alert(json.runners.length);

		});
		getTimeAjax();
	}

	function getTimeAjax(){
		var time = "";
		$.ajax({
			url: "time.php",
			cache: false,
			success: function(data){
				$('#updatedTime').html(data);
			}
		});
	}
	
	$("#btnStop").click(function(){
		repeat = false;
		$("#freq").html( "Updates paused." );
	});

	$("#btnStart").click(function(){
		repeat = true;
		startAJAXcalls();
		showFrequency();
	});	

	$('#btnSave').click(function() {

		var data = $("#addRunner :input").serializeArray();

		$.post($("#addRunner").attr('action'), data, function(json){
			
			if (json.status == "fail") {
				alert(json.message);
			}
			if (json.status == "success") {
				alert(json.message);
				clearInputs();
			}
		}, "json");

	});	

	function clearInputs(){
		$("#addRunner :input").each(function(){
			$(this).val('');
		});
	}	

	$("#addRunner").submit(function() {
		return false;
	});

	showFrequency();
	getDBRacers();
	startAJAXcalls();
});