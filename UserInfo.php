<?php 
	
	$jasonarray = json_decode(file_get_contents('php://input'),true);

	$userID = $jasonarray["userID"];
	$itemName = $jasonarray["itemName"];
	
	$con = mysqli_connect("localhost", "qoxodnjs", "zhkrmffldk1!", "qoxodnjs");
	
	//받아온 id를 검색 조건으로 회원의 정보를 불러오는 쿼리문을 작성합니다.
	$sql = "SELECT * FROM USER WHERE userID = '". $userID."'";
	
	//쿼리문을 실행합니다. 결과를 r 변수에 저장합니다.
	$r = mysqli_query($con,$sql);
	
	// r 변수의 내용을 array 형식으로 내보내 result 변수에 저장합니다.
	$result = array();
	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"userName"=>$row['userName'],
			"userStudentID"=>$row['userStudentID']
		));

	$sql = "SELECT * FROM ITEM WHERE userID = '". $userID."'";
	
	//쿼리문을 실행합니다. 결과를 r 변수에 저장합니다.
	$r = mysqli_query($con,$sql);
	
	// r 변수의 내용을 array 형식으로 내보내 result 변수에 저장합니다.
	$result2 = array();
	while($row = mysqli_fetch_array($r))
	{
		array_push($result2,array(
				"itemName"=>$row['itemName'],
				"itemRentalDate"=>$row['itemRentalDate'],
				"itemReturnDate"=>$row['itemReturnDate']
			));
	}
	//저장된 result 변수를 json 형식으로 출력합니다.
	echo json_encode(array('result'=>$result, 'result2'=>$result2),JSON_UNESCAPED_UNICODE);
?>
