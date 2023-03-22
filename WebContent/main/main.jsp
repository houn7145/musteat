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
<link rel="stylesheet" href="${conPath }/css/flexslider.css" type="text/css">
<script type="text/javascript" src="${conPath }/js/modernizr.custom.28468.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="${conPath }/js/jquery.flexslider.js"></script>
<script>
	$(function() {
		$('#mslider').flexslider({
			animation : "slide"
		});
		
		$('.category ul li').click(function() {
			var cno = $('.category ul li').index(this) + 1;
			if(!isNaN(cno)){
				location.href = '${conPath }/restaurantCcodeList.do?cno=' + cno;
			}
		});
	});
</script>
	<style>
		@font-face {
			font-family: 'pop-Light';
			src: url('${conPath}/fonts/ONE Mobile Light.ttf') format('woff');
		}
		* {
			margin: 0;
			padding: 0;
			font-family: 'pop-Light';
		}
		
		li {
			list-style: none;
		}
		
		#main_wrap {
			width: 100%;
			border: 1px solid gray;
			margin: 0 auto;
		}
		
		#main_wrap .section1 {
			width: 1000px;
			height: 500px;
			border: 1px solid gray;
			margin: 0 auto;
		}
		
		.section1 .category	{
			whidth: 1000px;
			height: 50px;
			border: 1px solid gray;
			text-align: center;
		}
		
		.category ul{
			overflow: hidden;
			height: 50px;
			line-height: 50px;
			display: inline-block;
		}
		
		.category ul li{
			float: left;
			margin:0 10px;
			cursor:pointer;
		}
		
		.section1 .restaurant {
			width: 700px;
			height: 400px;
			text-align:center;
			border: 1px solid gray;
			box-sizing: border-box;
			margin: 0 auto;
		}
		
		.slides {
			margin-top: 30px;
		}
		
	</style>
</head>
<body>
	<div id="main_wrap">
		<div class="section1">
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
			<div class="restaurant">
				<div id="slider"> <!-- 맛집 배너 슬라이드 -->
					<div id="mslider" class="flexslider">
						<ul class="slides">
							<c:forEach var="dto" items="${restaurants }">
							<c:if test="${empty dto }">
								<li><p>등록된 맛집이 없습니다.</p></li>
							</c:if>
								<li>
									<img src="${conPath }/img/${dto.mainimg}" />
									<p>이름 ${dto.rname }</p>
									<p>장소 ${dto.rplace }</p>
									<p>메뉴 ${dto.rmenu }</p>
									<p>가격대 ${dto.rprice }</p>
									<p>전화번호 ${dto.rtel }</p>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div> <!-- 맛집 배너 슬라이드 -->
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>
