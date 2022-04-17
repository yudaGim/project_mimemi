<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
  <head>
        <meta charset="UTF-8">
		<title>공지사항 관리 - 미미미</title>
		<style type="text/css">
			section {width: 1200px; margin: auto;}
			table {width: 1200px;}
			th, td {border: 1px solid black;}
		</style> 			
		<!-- CSS only -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<script type="text/javascript" src="${path}/util/js/jquery-3.6.0.min.js"></script>
		<!-- JavaScript Bundle with Popper -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	  	
	  		<style>
			.orderList-main {width: 1200px; margin: auto;}
			table {width: 100%;}
			.order_sort{text-align: right;}
		</style>
		
		<script type="text/javascript">
			$(function(){
				$("select").change(function() {
					if($(this).val() != "0"){
						let url = `${path}/front?key=notice&methodName=selectNoticeAllNotice&field=` + $(this).val();
						location.replace(url);
					}
				})
			})
		</script>
  </head>
   <body>
	<section class="noticeList-main">
			<h1><a href="${path}/front?key=notice&methodName=selectAllNotice">공지사항 관리</a></h1>
			<section class="notice_sort">
				<select name="notice_sort" id="">
					<option value="0">--정렬방식--</option>
					<option value="notice_title">제목</option>
					<option value="notice_content">내용</option>
					<option value="notice_regdate">작성날짜</option>
				</select>
			</section>
			<table class="table table-hover" id="noticeList">
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>내용</th>
						<th>첨부파일명</th>
						<th>작성날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty NoticeList}">
							<tr>
								<td colspan="6">공지사항이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${NoticeList}" var="notice">
							<tr>
								<td>${notice.noticeNo}</td>
								<td>${notice.noticeTitle}</td>
								<td>${notice.noticeContent}</td>
								<td>${notice.noticeAttach}</td>
								<td>${notice.noticeRegdate}</td>
							</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<nav aria-label="Page navigation example">
				<jsp:useBean class="mimemi.mvc.paging.NoticeListPageCnt" id="p"/> 
				<c:set var="isLoop" value="false"/>
				<c:set var="temp" value="${(pageNum - 1) % p.blockcount}"/>
				<c:set var="startPage" value="${pageNum - temp}"/>
				<ul class="pagination justify-content-center">
					<c:if test="${(startPage - p.blockcount) > 0}">
						<li class="page-item"><a class="page-link" href="${path}/front?key=notice&methodName=selectAllNotice&pageNum=${startPage-1}">이전</a></li>
					</c:if>
					<c:forEach var='i' begin='${startPage}' end='${(startPage-1) + p.blockcount}'> 
						<c:if test="${(i-1) >= p.pageCnt}">
							<c:set var="isLoop" value="true"/>
						</c:if> 
						<c:if test="${not isLoop}" >
							<li class="page-item ${i == pageNum ? ' active' : ''}"><a class="page-link page_num" href="${path}/front?key=notice&methodName=selectAllNotice&pageNum=${i}">${i}</a></li> 
						</c:if>
					</c:forEach>
					<c:if test="${(startPage + p.blockcount) <=p.pageCnt}">
						<li class="page-item"><a class="page-link" href="${path}/front?key=notice&methodName=selectAllNotice&pageNum=${startPage+p.blockcount}">이후</a></li>
					</c:if>
				</ul>
			</nav>
		</section>
	</body>
</html>