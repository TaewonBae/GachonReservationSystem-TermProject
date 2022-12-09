<?php 
	
	$jasonarray = json_decode(file_get_contents('php://input'),true);

	$userID = $jasonarray["userID"];
	$itemName = $jasonarray["itemName"];
	$itemRentalDate = $jasonarray["itemRentalDate"];
	$itemReturnDate = $jasonarray["itemReturnDate"];
	
	$con = mysqli_connect("localhost", "qoxodnjs", "zhkrmffldk1!", "qoxodnjs");
	
	#$sql = "UPDATE * FROM ITEM WHERE userID = '' and itemName ='". $itemName."'";
	#$con,"UPDATE Persons SET Age=36 WHERE FirstName='Peter' AND LastName='Griffin'"

	$sql = "SELECT * FROM ITEM WHERE itemName = '".$itemName."'";
	$r = mysqli_query($con,$sql);
	$row = mysqli_fetch_array($r);

	$cnt = $row['itemTotalNum'] - 1;

	$statement = mysqli_prepare($con, "UPDATE ITEM SET itemTotalNum = '".$cnt."' WHERE itemName ='".$itemName."'");
	mysqli_stmt_execute($statement);
	mysqli_stmt_store_result($statement);

	$statement = mysqli_prepare($con, "UPDATE ITEM SET userID ='".$userID."', itemRentalDate ='".$itemRentalDate."', itemReturnDate ='".$itemReturnDate."' WHERE userID = '' and itemName ='".$itemName."'ORDER BY userID LIMIT 1");
	mysqli_stmt_execute($statement);
	mysqli_stmt_store_result($statement);

	

	#####
	$response = array();
	$response["success"] = true;

	while(mysqli_stmt_fetch($statement)){
		$response["success"] = false;
	}
	
	echo json_encode($response);
?>
