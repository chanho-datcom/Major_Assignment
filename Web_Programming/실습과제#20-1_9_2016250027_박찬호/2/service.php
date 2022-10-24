<?php
    $link = mysqli_connect('localhost', 'dongsuhi', 'Park1728!')
                OR die(fail('Could not connect to database.'));
    mysqli_select_db($link, 'dongsuhi');

    echo "Connected!";
?>