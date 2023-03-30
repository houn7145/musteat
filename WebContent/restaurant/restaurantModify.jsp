<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath }/css/restaurant/restaurantAdd.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="add_wrap">
		<div class="add_form">
			<div class="addTitle">
				<h2>맛집 수정 등록</h2>
				<h4>수정할 맛집 정보를 입력해주세요.</h4>
				<hr>
			</div>
			<div class="addContent">
				<form action="${conPath }/restaurantModify.do" method="post" enctype="multipart/form-data">
					<input type="hidden" name="rno" value="${restaurantModify.rno }">
					<input type="hidden" name="dbmainimg" value="${restaurantModify.mainimg }">
					<h3>맛집 이름<b>*</b></h3>
					<input type="text" name="rname" required="required" value="${restaurantModify.rname }">
					<h3>맛집 소개<b>*</b></h3>
					<textarea name="rcontent" placeholder="맛집에 대한 상세정보나 다녀왔던 후기를 적어주세요." maxlength="1300">${restaurantModify.rcontent }</textarea>
					<h3>위치<b>*</b></h3>
					<input type="text" name="rplace" required="required" placeholder="서울시 마포구" maxlength="10" value="${restaurantModify.rplace}">
					<h3>카테고리<b>*</b></h3>
					<select name="cno"> 
						<option value="1" <c:if test="${restaurantModify.cno eq 1}">selected</c:if>>한식</option>
						<option value="2" <c:if test="${restaurantModify.cno eq 2}">selected</c:if>>일식</option>
						<option value="3" <c:if test="${restaurantModify.cno eq 3}">selected</c:if>>중식</option>
						<option value="4" <c:if test="${restaurantModify.cno eq 4}">selected</c:if>>양식</option>
						<option value="5" <c:if test="${restaurantModify.cno eq 5}">selected</c:if>>카페/디저트</option>
						<option value="6" <c:if test="${restaurantModify.cno eq 6}">selected</c:if>>기타</option>
					</select>
					<h3>대표메뉴<b>*</b></h3>
					<input type="text" name="rmenu" required="required" maxlength="30" value="${restaurantModify.rmenu }">
					<h3>가격대<b>*</b></h3>
					<input type="text" name="rprice" required="required" placeholder="만원~2만원" value="${restaurantModify.rprice}">
					<h3>맛집 전화번호</h3>
					<input type="text" name="rtel" placeholder="02-234-5678" value="${restaurantModify.rtel}">
					<h3>음식 사진</h3>
					<input type="file" name="mainimg">
					<p>${restaurantModify.mainimg}</p>
					<div class="btn">
						<input type="submit" value="수정">
						<input type="reset" value="취소" onclick="history.back()">
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>