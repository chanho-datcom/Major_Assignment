<?php
	date_default_timezone_set('Asia/Seoul');
if($_POST){
	if ($_POST['action'] == 'addSighting') {
	
		$date = $_POST['sighting_date'] ;
		$type = htmlspecialchars($_POST['creature_type']);
		$distance = htmlspecialchars($_POST['creature_distance']);
		$weight = htmlspecialchars($_POST['creature_weight']);
		$height = htmlspecialchars($_POST['creature_height']);
		$color = htmlspecialchars($_POST['creature_color_rgb']);
		$lat = htmlspecialchars($_POST['sighting_latitude']);
		$long = htmlspecialchars($_POST['sighting_longitude']);
		
		$my_date = date('Y-m-d', strtotime($date));
		
		if($type == ''){
			$type = "Other";
		}
		
		$query = "INSERT INTO sightings (sighting_date, creature_type, creature_distance, creature_weight, creature_height, creature_color, sighting_latitude, sighting_longitude) ";
		$query .= "VALUES ('$my_date', '$type', '$distance', '$weight', '$height', '$color', '$lat', '$long') ";

		$result = db_connection($query);
		
		if ($result) {
			$msg = "Creature added successfully";
			success($msg);
		} else {
			fail('Insert failed.');
		}
		exit;
	}
}

if($_GET){
	if($_GET['action'] == 'getAllSightings'){
		$query = "SELECT sighting_id, sighting_date, creature_type FROM sightings order by sighting_date ASC ";
		$result = db_connection($query);
		
		$sightings = array();

		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			array_push($sightings, array('id' => $row['sighting_id'], 'date' => $row['sighting_date'], 'type' => $row['creature_type'] ));
		}
		echo json_encode(array("sightings" => $sightings));
		exit;	
	}elseif($_GET['action'] == 'getSingleSighting'){
		$id = htmlspecialchars($_GET['id']);
		$query = "SELECT sighting_date, creature_type, creature_distance, creature_weight, creature_height, creature_color, sighting_latitude, ";
		$query .= " sighting_longitude FROM sightings where sighting_id = '$id' ";
		$result = db_connection($query);
		
		$sightings = array();

		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			array_push($sightings, array('date' => $row['sighting_date'], 'type' => $row['creature_type'], 'distance' => $row['creature_distance'], weight => $row['creature_weight'],
					'height' => $row['creature_height'], 'color' => $row['creature_color'], 'lat' => $row['sighting_latitude'], 'long' => $row['sighting_longitude']
				)
			);
		}
		echo json_encode(array("sightings" => $sightings));
		exit;	
	}elseif($_GET['action'] == 'getSightingsByType'){
		$type = htmlspecialchars($_GET['type']);
		$query = "SELECT sighting_id, sighting_date, creature_type, creature_distance, creature_weight, creature_height, creature_color, sighting_latitude, ";
		$query .= " sighting_longitude FROM sightings where creature_type = '$type' order by sighting_date ASC ";
		$result = db_connection($query);
		
		$sightings = array();

		while ($row = mysqli_fetch_array($result, MYSQL_ASSOC)) {
			array_push($sightings, array('id' => $row['sighting_id'], 'date' => $row['sighting_date'], 'type' => $row['creature_type'], 'distance' => $row['creature_distance'], weight => $row['creature_weight'], 
					'height' => $row['creature_height'], 'color' => $row['creature_color'], 'lat' => $row['sighting_latitude'], 'long' => $row['sighting_longitude']
				)
			);
		}
		echo json_encode(array("sightings" => $sightings));
		exit;
	}elseif($_GET['action'] == 'getSightingsTypes'){
		$query = "SELECT distinct(creature_type) FROM sightings order by creature_type ASC ";
		$result = db_connection($query);
		
		$types = array();

		while ($row = mysqli_fetch_array($result)) {
			array_push($types, array('type' => $row['creature_type']));
		}
		echo json_encode(array("creature_types" => $types));
		exit;
	}else{}
}	
    function db_connection($query) {
        $link = mysqli_connect('localhost', 'cksgh9206', 'qch20102010!')
            OR die(fail('Could not connect to database.'));
        mysqli_select_db($link, 'cksgh9206');

        return mysqli_query($link, $query);
    }
	
	function fail($message) {
		die(json_encode(array('status' => 'fail', 'message' => $message)));
	}
	function success($message) {
		die(json_encode(array('status' => 'success', 'message' => $message)));
	}
?>
