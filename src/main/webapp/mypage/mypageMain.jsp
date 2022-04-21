<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<jsp:include page="../common/header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
</style>

<script type="text/javascript">
 $(function(){
	
	// 사용자별 쿠폰 조회
	function selectCpByUserId() {
		$.ajax({
			url: "${path}/ajax",
			type: "post",
			dataType: "json",
			data: {key: "coupon", methodName: "selectCpByUserId"},
			success: function(result) {
				
				count = 0;
				$.each(result, function(index, item) {
					if(item.usercouUsable == "N")
					count++;
					
				})
				$("#usableCou").text(count);
				
			}, // 성공 메소드
			error : function(err) {
				alert(err + "\n쿠폰을 불러올 수 없습니다.");
			} // 에러 메소드
		}) // ajax 종료
	}
	selectCpByUserId();
	
	function selectRegdate(){
		const today = new Date();
		const regDate = new Date("${loginUser.userRegdate}");
		
		const diffDate = today.getTime() - regDate.getTime();
		
		const dateDays = Math.abs(diffDate / (1000*3600*24));
// 		alert(dateDays);
		
// 		alert(Math.floor(dateDays));
		var dateFrom = Math.floor(dateDays);
		document.getElementById("dateFrom").innerHTML= "미미미에 가입하신지 <strong>" + dateFrom + "</strong>일 되었습니다.";
	}
	
	selectRegdate();
});

</script>
</head>
<body>
<div class="topBox">
	<div class="firstBox">
	  <div class="txt1"><strong>${loginName}</strong>님 반갑습니다.</div>
	  <div class="txt2" id="dateFrom"></div>
	</div>
	<div class="secondBox">
      <div class="point">가용 적립금
      <a href="orderList.jsp">${loginUser.userPoint}</a>원</div>
    </div>
    <div class="thirdBox">
      <div class="coupon">사용 가능한 쿠폰
      <a href="couponList.jsp" id="usableCou"></a>장</div>
    </div>
</div>
<table class="table table-bordered caption-top" id="deliCalendar">
		<caption>
			<img alt="" src="">
		</caption>
		<thead>
			<tr>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
</table>
<div class="bottomBox">
  <div class="noticeTitle">공지사항</div>
  <div class="noticeList">
    <!-- 아작스 -->
  </div>
</div>

</body>
</html>