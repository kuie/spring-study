<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>

<title>뉴스 관리 앱</title>
</head>
<body>
	<div class="container w-75 mt-5 mx-auto">
		<h2>뉴스목록</h2>
		<hr>
		<ul class="list-group"> <!-- status 인덱스 값 갖고있음 -->
			<c:forEach var="news" items="${newslist}" varStatus="status">
		    <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
		    <a href="/news/${news.aid}" class="text-decoration-none">[${status.count}] ${news.title}, ${news.time} </a>
			  <!-- 새화면 뜸,겟뉴스 데려와서 부르고 aid도 같이 부름  -->
			<a href="/news/delete/${news.aid}"><span class="badge bg-secondary">&times;</span></a></li>
		   <!-- 액션에 델레트 뉴스 데려와서 부르고 aid도 같이 부름 / &times; x표시 기호  -->
		    </c:forEach>
		</ul>
		<hr>
		<c:if test="${error !=null}"> <!-- 에러가 있으면 보이고 없으면 안보이는  -->
		<div class="alert alert-danger alert-dismissible fade show mt-3">
			 에러발생: ${error}
			<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
		</div>
		</c:if>
		<button class="btn btn-outline-info mb-3" type="button" data-bs-toggle="collapse" data-bs-target="#addForm" aria-expanded="false" aria-controls="addForm">뉴스등록</button>
	    <div class="callapse" id="addForm">
	    	<div class="card card-body">
	    		<form method="post" action="/news/add" enctype="multipart/form-data"> <!-- action 바꿈  -->
			<label class="form-label">제목</label>
			<input type="text" name="title" class="form-control">
			<label class="form-label">이미지</label>
			<input type="file" name="file" class="form-control">
			<label class="form-label">기사내용</label>
			<textarea cols="50" rows="5" name="content" class="form-control"></textarea>
			<button type="submit" class="btn btn-success mt-3">저장</button>
		</form>
	    	</div>
	    </div>
	</div>
</body>
</html>