<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트수정 - 미미미</title>
<style>
  table {border: double 5px gray; width:500px}
  td,th{border:1px black solid ;  padding:10px}

  input{border:solid 1px gray;}
  tr td:first-child{width:100px; text-align: center;}
  div{text-align:left;}
  a{text-decoration: none}
   
</style>
<script type="text/javascript">
	function checkValid(){
		
	}
</script>
</head>
<body>

<!-- 
     아래 문장으로 전송하면 post방식으로 전송이되고 현재 파일업로드때문에 enctype="multipart/form-data" 설정되어 있기때문에 
     request로 값을 받을수가 없다. ( MulitpartRequest로 받아야한다.) 그런데 Controller로 가기전에 Controller를 찾기위해서 
     DispatherServlet에서 request로 두개의 값을 받고 있기때문에 key, methodName은 get방식으로 별도로 전송해야한다.
     
	<input type="hidden" name="key" value = "event" />
	<input type="hidden" name="methodName" value = "insert" />  

 -->
<div class="review-writeForm">
	<form name="writeReview" method="post" action="${path}/front?key=event&methodName=updateEvent"
     onSubmit='return checkValid()' enctype="multipart/form-data">
     <input type="hidden" name="eventNo" value="${event.eventNo }"/>
		<table>
		
		    <tr>
		        <td colspan="2">
		            <p><font><b> 이벤트 수정 </b></font></p>
		        </td>
		    </tr>
		    <tr>
		        <td>
		            <p><b><span>제목</span></b></p>
		        </td>
		        <td><b><span>
				<input type=text name="eventTitle" value="${event.eventTitle}" size="50"></span></b></td>
		    </tr>
		    <tr>
		        <td>
		            <p><b><span>내용</span></b></p>
		        </td>
		        <td><b><span>
				<textarea name="eventContent" cols="50" rows="10" >${event.eventContent}</textarea></span></b></td>
		    </tr>
		    <tr>
		        <td>
		            <p><b><span>*첨부파일</span></b></p>
		        </td>
		        <td>
		        <div class="event-attach-insert-area">
                                	<input type="file" id="event-attach-selector" name="eventAttach" accept=".jpg, .jpeg, .png">
                                	<p id="file-status-attach">${event.eventAttach}</p>
                                    <div class="event-attach-preview">
                                        <img id="event-attach-output" src="${path}/img/save/${event.eventAttach}">
                                    </div>
                                    <script>
                                        const status = document.getElementById('file-status-attach')
                                        //const fileSelector =document.getElementById('review-image-selector')
                                        const output = document.getElementById('event-attach-output')
                                        
                                        if(window.FileList && window.File && window.FileReader){
                                            document.getElementById('event-attach-selector').addEventListener('change', event =>{
                                                output.src='';
                                                status.textContent='';
                                                const file = event.target.files[0];
                                                if(!file.type){
                                                    status.textContent = "첨부한 파일 타입이 현재 브라우저에서 지원하지 않습니다."
                                                    return;
                                                }
                                                const reader = new FileReader();
                                                reader.addEventListener('load', event =>{
                                                    output.src = event.target.result;
                                                })
                                                reader.readAsDataURL(file);
                                            })
                                        }
                                    </script>
                                </div>
				</td>
		    </tr>
		    <tr>
		        <td>
		            <p><b><span>썸네일이미지</span></b></p>
		        </td>
		        <td>
		        <div class="event-image-insert-area">
                                	<input type="file" id="event-image-selector" name="eventImg" accept=".jpg, .jpeg, .png">
                                	<p id="file-status">${event.eventImg}</p>
                                    <div class="event-image-preview">
                                        <img id="event-image-output" src="${path}/img/save/${event.eventImg}">
                                    </div>
                                    <script>
                                        const status2 = document.getElementById('file-status')
                                        //const fileSelector =document.getElementById('review-image-selector')
                                        const output2 = document.getElementById('event-image-output')
                                        
                                        if(window.FileList && window.File && window.FileReader){
                                            document.getElementById('event-image-selector').addEventListener('change', event =>{
                                                output2.src='';
                                                status2.textContent='';
                                                const file2 = event.target.files[0];
                                                if(!file2.type){
                                                    status2.textContent = "첨부한 파일 타입이 현재 브라우저에서 지원하지 않습니다."
                                                    return;
                                                }
                                                const reader2 = new FileReader();
                                                reader2.addEventListener('load', event =>{
                                                    output2.src = event.target.result;
                                                })
                                                reader2.readAsDataURL(file2);
                                            })
                                        }
                                    </script>
                                </div>
				
				</td>
		    </tr>
		    <tr>
		        <td>
		            <p><b><span>이벤트 시작일</span></b></p>
		        </td>
		        <td><b><span>
				<input type=text name="eventStartdate" size="50" value="${event.eventStartdate }"></span></b></td>
		    </tr>
		    
		     <tr>
		        <td>
		            <p><b><span>이벤트 종료일</span></b></p>
		        </td>
		        <td>
		        	<b><span>
		        		 <input type=text name="eventEnddate" size="50" value="${event.eventEnddate }">
		        	   </span></b>
		        </td>
		    </tr>
		    <tr>
		        <td colspan="2"><b><span><input type=submit value=수정하기> 
		        <input type=reset value=취소하기></span></b></td>
		    </tr>
		</table>
	</form>
</div>
<p>
<div>
	<a href="${path}/front?key=event&methodName=selectAll">&lt;리스트로 돌아가기&gt;</a>
</div>


</body>
</html>