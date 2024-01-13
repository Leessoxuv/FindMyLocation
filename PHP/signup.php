<?php
    $con = mysqli_connect("localhost", "닷홈 아이디", "닷홈 비밀번호", "닷홈 아이디");
    mysqli_query($con, 'SET NAMES utf8'); 
    
    $userID = $_POST["userID"]; //입력받음
    $userPW = $_POST["userPW"]; //입력받음

    $statement = mysqli_prepare($con, "INSERT INTO USER VALUES (?, ?)"); //DB에 값 저장
    mysqli_stmt_bind_param($statement, "s1", $userID, $userPW); 
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>