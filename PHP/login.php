<?php
    $con = mysqli_connect("localhost", "닷홈 아이디", "닷홈 비밀번호", "닷홈 아이디");
    mysqli_query($con,'SET NAMES utf8');

    $userID = $_POST["userID"];
    $userPW = $_POST["userPW"];

    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE userID = ? AND userPW = ?");
    mysqli_stmt_bind_param($statement, "s3", $userID, $userPW);
    mysqli_stmt_execute($statement);


    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $userPW);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
        $response["userID"] = $userID;
        $response["userPW"] = $userPW;
    }

    echo json_encode($response);



?>