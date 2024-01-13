<?php
    $con = mysqli_connect('localhost', '닷홈 아이디', '닷홈 비밀번호', '닷홈 아이디');
    mysqli_query($con, 'SET NAMES utf8'); 

    $userID = $_POST["userID"];

    $statement = mysqli_prepare($con, "SELECT userID FROM  WHERE userID = ?");

    mysqli_stmt_bind_param($statement, "s4", $userID);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID);

    $response = array();
    $response["success"] = true;

    while(mysqli_stmt_fetch($statement)){
      $response["success"] = false;
      $response["userID"] = $userID;
    }

    echo json_encode($response);
?>