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
<link rel="stylesheet" href="${conPath }/css/flexslider.css"
	type="text/css">
<script type="text/javascript"
	src="${conPath }/js/modernizr.custom.28468.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="${conPath }/js/jquery.flexslider.js"></script>
<script>
	$(function() {
		$('#mslider').flexslider({
			animation : "slide"
		});

		$('.category ul li')
				.click(
						function() {
							var cno = $('.category ul li').index(this) + 1;
							if (!isNaN(cno)) {
								location.href = '${conPath }/restaurantCcodeList.do?cno='
										+ cno;
							}
							$(this).css('color', '#f7c95e');
						});
	});
</script>
<link href="${conPath }/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
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
				<div id="slider">
					<!-- 맛집 배너 슬라이드 -->
					<div id="mslider" class="flexslider">
						<ul class="slides">
							<c:forEach var="dto" items="${restaurants }">
								<li>
									<img src="${conPath }/img/${dto.mainimg}"/>
									<p>${dto.rname }</p>
									<p>${dto.rplace }</p>
									<p>${dto.rmenu }</p>
									<p>${dto.rtel }</p>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<!-- 맛집 배너 슬라이드 -->
			</div>
			<div class="restaurantReview"></div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>

