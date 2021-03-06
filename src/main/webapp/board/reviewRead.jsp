<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Document</title>
       <jsp:include page="../common/header.jsp"/>
       <!-- 폰트 링크 -->
	    <link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
        <style>
            .review-view{
                width: 1200px; margin: auto; padding: 50px 0;
            }

			.review-content-title {
				padding: 25px 30px; margin: 20px 0; border-top: 2px black solid; border-bottom: 1px grey solid;
			}

			.review-content-title > div {margin: 20px 0 0; display: flex; justify-content: space-between;}
            
            .review-content-title > div > span > span:last-child {margin-left: 30px;}
            
			.review-contentview {
				margin: 50px auto; padding: 0 25px;
                
			}
			
			.review-Image {
				box-sizing: border-box;
                width: 800px;
                height: 100%;
                
			}
            #reviewImg, #reveiwImg-when-empty{
                box-sizing: border-box;
                width: 800px;
                height: 100%;
                object-fit: contain;
                background-color: rgb(241, 241, 241);
                
            }
            .review-content-area{
                padding: 20px 0px 20px;
            }
           

            img.starRateImg{
            	box-sizing: border-box;
                width:130px;
                height: auto;
                padding-bottom: 5px;                
            }
            
            .base-btn {display: flex; justify-content: space-between; padding: 25px;}
            
            .reply-each {padding: 10px 0;}
            
            .reply-num { border-top: 1px #eeeeee solid; border-bottom: 1px #eeeeee solid; padding: 25px;}
            
            #goodsImg{
                width: 20px;
                height: auto;
            }
            
            .review-reply{
                width: 800px;
            }
            
            .user-write-wrap{
                width: 800px;
                padding: 10px;
                border: 1px solid gray;
            }
            
            .review_reply_wrap{
            	background-color: #eeeeee;
                padding: 20px 30px 20px 30px;
            }
            
            img.reply-user-icon{
            	width: 30px;
            	margin-right: 10px;
            }
            
            pre{
            	white-space: pre-wrap;
            	font-family: 'Nanum Gothic', sans-serif;
            }
            
            .reply-user-info{
                padding-bottom: 5px;
            }
            
            .reply-content-text{
                width: fit-content;
                padding: 10px;
                border-radius: 15px;
                background-color: rgb(248, 249, 250);
                border: 1px solid rgb(196, 196, 196);
            }
            
            .reply-content{
            	padding:10px 10px 10px 0px;
            }
            
            #reply-delete-bnt{
            	color:gray;
            }
            
            
            
        </style>
       <!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
		integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        
		<!-- JavaScript Bundle with Popper -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" 
		integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
        
        <!-- jQuery -->        
        <script type="text/javascript" src="${path}/util/js/jquery-3.6.0.min.js"></script>
        
        <script>
        $(function(){
            var target ='${reviewDetail.reviewNo}'
            var loginUser='${sessionScope.loginUser.userId}' //세션으로 확인한 현재 로그인한 유저
            var loginManager='${sessionScope.loginManager.managerId}'
            //var loginUser='frog123'
            //alert("로그인 유저 아이디:"+loginUser)

            //전체 댓글 검색
        	function selectAllReply(){
                $.ajax({
				url: "${path}/ajax" , //서버요청주소
				type: "post" , //요청방식 (get,post...)
				dataType: "json" , //서버가 보내온 데이터(응답)type(text | html | xml | json)
				data: {key:"reviewreply", methodName:"select", reviewNo: target} , //서버에게 보낼 데이터정보(parameter정보)
				
				success: function(result){
					//alert("검색성공~")
					let str="";
					count = 0;
					if(JSON.stringify(result) == "[]"){

						str += "<div class='reply-each'>"
                        str+=`<span class="reply-content-text">댓글이 없습니다</span>`;
                        str+=`</div>`;
					} else {
						$.each(result,function(index,reply){
							str += "<div class='reply-each'>"
	                        str+=`<div class="reply-user-info">`;
	                            str+=`<span class="badge rounded-pill text-dark">\${reply.userId}</span>&nbsp;`
	                           	str+=`<span class="badge rounded-pill bg-primary">\${reply.managerId}</span>`
	                            str+=`<span class="badge rounded-pill text-dark">\${reply.replyRegdate}</span>`
	                        str+=`</div>`;
	                        str+=`<div class="reply-content">`;
	                            str+=`<span class="reply-content-text">\${reply.replyContent}</span>`
	                            //str+=`<span class="badge rounded-pill bg-light text-dark"><a href="javascript:void(0);" id="reply-update-bnt" name=${'${reply.userId}'} reply_No="${'${reply.replyNo}'}>수정</a></span>`
	                            str+=`<span class="badge rounded-pill text-dark"><a href="javascript:void(0);" id="reply-delete-bnt" name=${'${reply.userId}'} reply_No="${'${reply.replyNo}'}">삭제</a></span>`
	                        str+=`</div>`;
	        				str += "</div>"
	        				count++;
						})
                    }
                   	$("#review_reply_output").html(review_reply_output);
                   	$("#review_reply_output").append(str);
                   	$(".reply-num-count").text(count);
				},

				error: function(err){//실패했을 때 콜백함수
				  alert(err+"오류가 발생했습니다.")
				} 

			    })
            }
        	
            $("#reply-insert-btn").click(function(){
                let status =true;
                //alert(loginUser)
                //댓글 유효성체크
                if(!loginUser){
                    alert("댓글 기능은 회원만 가능합니다.")
                    status=false;
                    return;
                }

                if($("#exampleFormControlTextarea1").val()==""){
                    alert("댓글을 입력해 주십시오.")
                    $(this).focus();
                    status=false;
                    return;
                }
                //댓글 달기
                if(status){
                    $.ajax({
                        url: "${path}/ajax" , //서버요청주소
                        type: "post" , //요청방식 (get,post...)
                        dataType: "text" , //서버가 보내온 데이터(응답)type(text | html | xml | json)
                        data: $("#reply-loginUser-insert").serialize() , //서버에게 보낼 데이터정보(parameter정보)
                        
                        success: function(result){
                            //alert("댓글등록성공~")
                            if(result==0){
                                alert("댓글을 등록하지 못 했습니다.")
                            }else{
                                //textarea부분 지우고 
                                $("textarea.form-control").val("")
                                //화면갱신한다
                                selectAllReply();
                            }
                        },

                        error: function(err){//실패했을 때 콜백함수
                        alert(err+"오류가 발생했습니다.")
                        } 

			        })
                }
            })
            $(document).on("click","#reply-delete-bnt",function(){
                
                var replyId = $(this).attr("name")
                //alert("댓글유저아이디~~"+replyId)
                var replyNo =$(this).attr("reply_No")
                //alert(replyNo)

                //세션에서 로그인한 유저와 댓글 작성자 id가 일치하는지 확인
                if(loginUser==replyId||loginManager==replyId){
                    $.ajax({
                        url: "${path}/ajax" , //서버요청주소
                        type: "post" , //요청방식 (get,post...)
                        dataType: "text" , //서버가 보내온 데이터(응답)type(text | html | xml | json)
                        data: {key:"reviewreply", methodName:"delete", reply_No: replyNo } , //서버에게 보낼 데이터정보(parameter정보)
                        
                        success: function(result){
                            if(result==0){
                                alert("댓글이 삭제되지 않았습니다.")
                            }else{
                                selectAllReply(); //화면갱신
                            }
                        
                        },

                        error: function(err){//실패했을 때 콜백함수
                        alert(err+"오류가 발생했습니다.")
                        } 

			        })
                }else{
                    alert("댓글은 자신이 단 댓글만 삭제 가능합니다.")
                }
            })
            $(document).on("click","#delete-btn",function(){
                var reivewId = $(this).attr("name")
                //alert(reivewId)
                if(loginUser!=reivewId || !loginUser){
                    alert("게시물은 자신이 작성한 게시물만 삭제 가능합니다.")
                }else{
                    let url = `${path}/front?key=review&methodName=delete&reviewNo=${reviewDetail.reviewNo}`
        			location.replace(url);
                }
            })

            $(document).on("click","#update-btn",function(){
                var reivewId = $(this).attr("name")
                if(loginUser!=reivewId|| !loginUser){
                    alert("게시물은 자신이 작성한 게시물만 수정 가능합니다.")
                }else{
                    let url = `${path}/front?key=review&methodName=updateForm&reviewNo=${reviewDetail.reviewNo}`
        			location.replace(url);
                }
            })

            $(document).on("click","#back-list-btn",function(){
                
                let url = `${path}/front?key=review&methodName=selectAll&pageNum=${pageNum}`
        		location.replace(url);
            })

            selectAllReply();
        })
        </script>
    </head>
    <body>
        <section class="review-view">
		<h1>후기 게시판</h1>
		<div class="review-content-title">
			<h4>${reviewDetail.reviewTitle}</h4>
			<div>
				<span>
					<strong class="userName">${reviewDetail.userId.substring(0,4)}****</strong>
					<span class="review-rate">
						<c:choose>
							<c:when test="${reviewDetail.reviewRate==1}">
								<span> <img src="${path}/img/ui/starRate1.jpg" class="starRateImg"></span>
							</c:when>
							<c:when test="${reviewDetail.reviewRate==2}">
								<span><img src="${path}/img/ui/starRate2.jpg" class="starRateImg"></span>
							</c:when>
							<c:when test="${reviewDetail.reviewRate==3}">
								<span><img src="${path}/img/ui/starRate3.jpg" class="starRateImg"></span>
							</c:when>
							<c:when test="${reviewDetail.reviewRate==4}">
								<span><img src="${path}/img/ui/starRate4.jpg" class="starRateImg"></span>
							</c:when>
							<c:when test="${reviewDetail.reviewRate==5}">
								<span><img src="${path}/img/ui/starRate5.jpg" class="starRateImg"></span>
							</c:when>
						</c:choose>
					</span>
				</span>
				<span>
					<span>조회수</span> <span>${reviewDetail.reviewViews}</span>
					<span>${reviewDetail.reviewRegdate}</span>
				</span>
				
			</div>
		</div>
        <div class="review-contentview">
			<div class="review-Image">
				<c:choose>
					<c:when test ="${not empty reviewDetail.reviewAttach}">
						<img src="${path}/img/save/${reviewDetail.reviewAttach}" alt="회원이 올린 후기 사진입니다." name="reviewImg" id="reviewImg">
					</c:when>
					<c:otherwise>
						<span class="review-Image-isEmpty">
							<img src="${path}/img/goods/${reviewDetail.goodsDTO.goodsThumbnail}" alt="후기를 작성한 상품의 썸네일 이미지입니다." name="reveiwImg-when-empty" id="reveiwImg-when-empty">
						</span>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="review-content-area">
				<div class="review-content">
					<pre class="">${reviewDetail.reviewContent}</pre>
				</div>
			</div>
            <!--리뷰 정보 하단-->
            <div class="base-btn">
                <a href="#" id="back-list-btn" class="btn btn-outline-dark shadow-none">목록으로 돌아가기</a>
                <span>
	                <a href="#" id="delete-btn"  class="btn btn-outline-dark shadow-none" name="${reviewDetail.userId}">삭제</a>
	                <a href="#" id="update-btn"  class="btn btn-outline-dark shadow-none" name="${reviewDetail.userId}">수정</a>
                </span>
            </div> 
                <!--댓글 조회하기 -->
                <div class="reply-num">
                	댓글 수: <span class="reply-num-count"></span>개
                </div>
                <div class="review_reply_wrap">
                    <div class="review_reply_area">
                       	<div id="review_reply_output"></div>
                    </div>
                </div>
                
            </div>

                <!--댓글 등록하기-->
                <div class="card">
                    <div class="card-body">
                    	<form name="reply-loginUser-insert" method="post" id="reply-loginUser-insert">
                            <div class="form-inline mb-2">
                                <label for="replyId"><img src="${path}/img/ui/user.png" class="reply-user-icon"><img></label>
                                <span><strong>${sessionScope.loginUser.userId}</strong></span>
                                <span><strong>${sessionScope.loginManager.managerId}</strong></span>
                                <input type="hidden" name="reply_id" value="${sessionScope.loginUser.userId}"><!-- 나중에 세션으로 아이디 받기 -->
                                <input type="hidden" name="reply_manager_id" value="${sessionScope.loginManager.managerId}">
                            </div>
                            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="reply_content"></textarea>
                            <input type="hidden" name="key" value="reviewreply">
                            <input type="hidden" name="methodName" value="insert">
                            <input type="hidden" name="reviewNo" value="${reviewDetail.reviewNo}">
                            <button type="button" class="btn btn-dark mt-3" id="reply-insert-btn" >댓글 등록하기</button>
                    	</form>
                    </div>
                </div>
        </section>
    </body>
    <jsp:include page="../common/footer.jsp"/>
</html>