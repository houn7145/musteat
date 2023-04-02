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
	<link rel="stylesheet" href="${conPath }/css/ex/flexslider.css" type="text/css">
	<link href="${conPath }/css/main/main.css" rel="stylesheet" type="text/css">
	<script type="text/javascript"src="${conPath }/js/modernizr.custom.28468.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="${conPath }/js/jquery.flexslider.js"></script>
	<script>
		$(function() {
			$('#mslider').flexslider({
				animation : "slide"
			});
	
			$('.category ul li').click(function() {
				var cno = $('.category ul li').index(this) + 1;
				if (!isNaN(cno)) {
					location.href = '${conPath }/restaurantCcodeList.do?cno='+ cno;
				}
			});
			$('.schList').click(function() {
				var rno = $(this).children('#rno').val();
				if (!isNaN(rno)) {
					location.href = '${conPath }/restaurantContent.do?rno='+ rno;
				}
			});
			$('.flexslider .slides > li').click(function() {
				var rno = $(this).children('#rno').val();
				if (!isNaN(rno)) {
					location.href = '${conPath }/restaurantContent.do?rno='+ rno;
				}
			});
		});
	</script>
</head>
<body>
	<c:if test="${not empty next }">
		<script>
			location.href = '${conPath}/${next}';
		</script>
	</c:if>
	<c:if test="${not empty loginErrorMsg }">
		<script>
			alert('${loginErrorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty restaurantDeleteResult }">
		<script>
			alert('${restaurantDeleteResult}');
		</script>
	</c:if>
	<c:if test="${not empty withdrawalResult }">
		<script>
			alert('${withdrawalResult}');
		</script>
	</c:if>
	<c:if test="${not empty addRestaurantResult }">
		<script>
			alert('${addRestaurantResult}');
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp" />
	<div id="main_wrap">
		<div class="category">
			<ul>
				<li>한식</li>
				<li>일식</li>
				<li>중식</li>
				<li>양식</li>
				<li>카페/디저트</li>
				<li>기타</li>
			</ul>
		</div>
		<div class="mainSection">
			<div class="ccodeRestaurants">
				<div id="slider"><!-- 맛집 배너 슬라이드 -->
					<div id="mslider" class="flexslider">
						<div class="restaurantCategory">
							<h2>${cname } 카테고리 추천 맛집</h2>
						</div>
						<ul class="slides">
							<c:forEach var="dto" items="${restaurants }">
								<li>
									<input type=hidden value="${dto.rno }" id="rno">
									<img src="${conPath }/restaurantPhotoUp/${dto.mainimg}"/>
									<p>${dto.rname }</p>
									<p><fmt:formatNumber value="${dto.ravg }" pattern="0.#"/>점</p>
									<p>${dto.rplace }</p>
									<p>${dto.rmenu }</p>
									<p>${dto.rtel }</p>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div><!-- 맛집 배너 슬라이드 -->
			</div>
		</div>
		<div class="ravgRestaurants">
			<div class="restaurantCategory">
				<h2>평점 TOP 인기 맛집</h2>
				<c:forEach var="dto" items="${restauranRavgList }" varStatus="idx">
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
							<span><fmt:formatNumber value="${dto.ravg }" pattern="0.#"/>점(${dto.avghit }명)</span>
							<p>${dto.rcontent }</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>

