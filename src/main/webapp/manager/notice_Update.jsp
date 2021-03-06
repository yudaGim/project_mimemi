<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../common/sidebar.jsp"/>
</head>
<style type="text/css">

	.notice_update{width: 1000px; margin: 80px auto;}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!--JQgrid CDN-->
<script src="../util/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

</script>
<body>
<section class="notice_update">
 <form name="updateNotice" method="post" action="${path}/front?key=notice&methodName=update"
       onsubmit='return checkValid()' enctype="multipart/form-data">
           <input type="hidden" name="noticeNo" value="${notice.noticeNo}">
		<div class="container" role="main">
			<h2>공지사항 수정</h2>
				<div class="mb-3">
					<label for="title">제목</label>
					<textarea class="form-control" rows="1" name="notice_title" id="title" >${notice.noticeTitle}</textarea>
				</div>
				<div class="mb-3">
					<label for="content">내용</label>
					<textarea class="form-control" rows="5" name="notice_content" id="content" >${notice.noticeContent}</textarea>
				</div>
				<div class="mb-3">
					<label for="attach">첨부파일</label>
				 <input type="file" name="notice_attach" maxlength="" size="40">
				 <p id="file-status">${notice.noticeAttach}</p>
				</div>
     			<div >
					<input type="submit" value="작성" />
				    <input type="button" value="목록보기" onclick="location.href='selectNoticeAll.jsp'"/>
			</div>
		</div>
</form>
</section>
</body>
</html>