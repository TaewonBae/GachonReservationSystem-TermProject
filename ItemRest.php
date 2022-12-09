<?php 
	
	$jasonarray = json_decode(file_get_contents('php://input'),true);
	
	$list1 = "마우스";
	$list2 = "노트북충전기";
	$list3 = "우산";
	$list4 = "휴대폰충전기";

	$con = mysqli_connect("localhost", "qoxodnjs", "zhkrmffldk1!", "qoxodnjs");
	
	//받아온 id를 검색 조건으로 회원의 정보를 불러오는 쿼리문을 작성합니다.
	$sql = "SELECT * FROM ITEM WHERE itemName = '". $list1."'";
	$r = mysqli_query($con,$sql);
	
	// r 변수의 내용을 array 형식으로 내보내 result 변수에 저장합니다.
	$result = array();

	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"list1"=>$row['itemTotalNum']
		));

	$sql = "SELECT * FROM ITEM WHERE itemName = '". $list2."'";
	$r = mysqli_query($con,$sql);

	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"list2"=>$row['itemTotalNum']
		));
	

	$sql = "SELECT * FROM ITEM WHERE itemName = '". $list3."'";
	$r = mysqli_query($con,$sql);

	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"list3"=>$row['itemTotalNum']
		));
	

	$sql = "SELECT * FROM ITEM WHERE itemName = '". $list4."'";
	$r = mysqli_query($con,$sql);

	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"list4"=>$row['itemTotalNum']
		));
	

	//저장된 result 변수를 json 형식으로 출력합니다.
	echo json_encode(array('result'=>$result),JSON_UNESCAPED_UNICODE);
?>
