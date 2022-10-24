<?php
$link = mysqli_connect('localhost', 'cksgh9206', 'qch20102010!')
            OR die(fail('Could not connect to database.'));
        mysqli_select_db($link, 'cksgh9206');

        echo "2016250027 connected!";
?>