<?php

	$jasonarray = json_decode(file_get_contents('php://input'),true);
	
	$userID = $jasonarray["userID"];
	$userPassword = $jasonarray["userPassword"];

	$con = mysqli_connect("localhost", "qoxodnjs", "zhkrmffldk1!", "qoxodnjs");

	// Check connection
	if (mysqli_connect_errno())
  	{
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
  	}
	$statement = mysqli_prepare($con, "SELECT * FROM USER WHERE userID = ? AND userPassword = ?");
	mysqli_stmt_bind_param($statement, "ss", $userID, $userPassword);
	mysqli_stmt_execute($statement);
	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement, $userID);

	$response = array();
	$response["success"] = false;

	while (mysqli_stmt_fetch($statement)) {
		$response["success"] = true;
		$response["userID"] = $userID;
	}

	echo json_encode($response);
?>