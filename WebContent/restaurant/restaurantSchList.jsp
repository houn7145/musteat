<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath }/css/restaurant/restaurantSch.css"
		rel="stylesheet" type="text/css">
	<script type="text/javascript"src="${conPath }/js/modernizr.custom.28468.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script>
		$(function() {
			$('.schList').click(function() {
				var rno = $(this).children('#rno').val();
				if (!isNaN(rno)) {
					location.href = '${conPath }/restaurantContent.do?rno='+ rno;
				}
			});
		});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<c:if test="${not empty restaurantSchList }">
		<div id="search_warp">
			<h2>${rplace } 맛집 인기 순위</h2>
			<c:forEach var="dto" items="${restaurantSchList }" varStatus="idx">
				<div class="schList">
					<input type=hidden value="${dto.rno }" id="rno">
					<div class="imgInfo">
						<img src="${conPath }/restaurantPhotoUp/${dto.mainimg }" width="180px"
							height="180px" />
					</div>
					<div class="restaurantInfo">
						<p>${dto.rname }</p>
						<p>${dto.rmenu }</p>
						<p>${dto.rprice }</p>
						<p>${dto.rtel }</p>
						<img src="${conPath }/icon/star.png" width="20px" height="20px" />
						<span><fmt:formatNumber value="${dto.ravg}" pattern="0.#"/>점(${dto.avghit }명)</span>
						<p>${dto.rcontent }</p>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${empty restaurantSchList }">
		<div id="noList_warp">
			<div id="noList">
				<h1>검색된 맛집이 없습니다.</h1>
				<h2>새로운 맛집을 등록해 주세요.</h2>
				<button onclick="location.href='${conPath}/restaurantAddView.do'">맛집 등록하러 가기</button>
			</div>
		</div>
	</c:if>
	<div class="paging">
			<ul>
			<c:if test="${startPage > BLOCKSIZE }">
				 <li><a href="${conPath }/restaurantSchList.do?pageNum=${startPage - 1}">◀</a></li>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i eq pageNum }">		
					<li class="now"><a>${i }</a></li>
				</c:if>
				<c:if test="${i ne pageNum }">
					<li><a href="${conPath}/restaurantSchList.do?rplace=${rplace}&pageNum=${i}">${i }</a></li>
				</c:if>
			</c:forEach>
			<c:if test="${endPage < pageCnt }"> 
				<li><a href="${conPath }/restaurantSchList.do?rplace=${rplace}&pageNum=${endPage + 1 }">▶</a></li>
			</c:if>
			</ul>
		</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>