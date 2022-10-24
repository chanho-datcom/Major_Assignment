<?php
    if ($_POST['action'] == 'addRunner') {
        $fname = htmlspecialchars($_POST['txtFirstName']);
        $lname = htmlspecialchars($_POST['txtLastName']);
        $gender = htmlspecialchars($_POST['ddlGender']);
        $minutes = htmlspecialchars($_POST['txtMinutes']);
        $seconds = htmlspecialchars($_POST['txtSeconds']);
        if(preg_match('/[^\w\s]/i', $fname) || preg_match('/[^\w\s]/i', $lname)) {
            fail('Invalid name provided.');
        }

    if(empty($fname) || empty($lname)) {
        fail('Please enter a first and last name.');
    }

    if(empty($gender)) {
        fail('Please select a gender.');
    }

    $time = $minutes.":".$seconds;
    $query = "INSERT INTO runners SET first_name='$fname', last_name='$lname', gender='$gender', finish_time='$time'";
    $result = db_connection($query);
    
    if($result) {
        $msg = "Runner: ".$fname." ".$lname." added successfully" ;
        success($msg);
    } else { fail('Insert failed.'); } exit;
    
    } elseif($_GET['action'] == 'getRunners') {
        $query = "SELECT first_name, last_name, gender, finish_time FROM runners order by finish_time ASC ";
        $result = db_connection($query);
        $runners = array();
        while ($row = mysqli_fetch_array($result)) {
            array_push($runners, array('fname' => $row['first_name'], 'lname' => $row['last_name'], 'gender' => $row['gender'], 'time' => $row['finish_time']));
        }
        echo json_encode(array("runners" => $runners));
        exit;
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