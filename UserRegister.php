<?php

	$jasonarray = json_decode(file_get_contents('php://input'),true);

	$userID = $jasonarray["userID"];
	$userPassword = $jasonarray["userPassword"];
	$userGender = $jasonarray["userGender"];
	$userMajor = $jasonarray["userMajor"];
	$userEmail = $jasonarray["userEmail"];
	$userStudentID = $jasonarray["userStudentID"];
	$userName = $jasonarray["userName"];
	$userPhone = $jasonarray["userPhone"];

	$con = mysqli_connect("localhost", "qoxodnjs", "zhkrmffldk1!", "qoxodnjs");
	// Check connection
	if (mysqli_connect_errno())
  	{
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
  	}

	$statement = mysqli_prepare($con, "INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
	mysqli_stmt_bind_param($statement, "ssssssss", $userID, $userPassword, $userGender, $userMajor, $userEmail, $userStudentID, $userName, $userPhone);
	mysqli_stmt_execute($statement);

	mysqli_close($con);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);
?>