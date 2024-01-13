<?php
    $con = mysqli_connect("localhost", "닷홈 아이디", "닷홈 비밀번호", "닷홈 아이디");
    mysqli_query($con, 'SET NAMES utf8'); 
    
    $userName = $_POST["userName"]; //입력받음
    $userBirth = $_POST["userBirth"]; //입력받음
    $userGender = $_POST["userGender"];
    $userNumber = $_POST["userNumber"];

    $statement = mysqli_prepare($con, "INSERT INTO USER VALUES (?, ?, ?, ?)"); //DB에 값 저장
    mysqli_stmt_bind_param($statement, "s2", $userName, $userBirth, $userGender, $userNumber); 
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>