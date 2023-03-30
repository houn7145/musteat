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
  	<c:if test="${empty member }">
  		<script>
  			location.href = '${conPath}/loginView.do?next=restaurantAddView.do';
  		</script>
  	</c:if>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="add_wrap">
		<div class="add_form">
			<div class="addTitle">
				<h2>맛집 등록</h2>
				<h4>나만 아는 대한민국 맛집을 공유해주세요!</h4>
				<hr>
			</div>
			<div class="addContent">
				<form action="${conPath }/restaurantAdd.do" method="post" enctype="multipart/form-data">
					<h3>맛집 이름<b>*</b></h3>
					<input type="text" name="rname" required="required">
					<h3>맛집 소개<b>*</b></h3>
					<textarea name="rcontent" placeholder="맛집에 대한 상세정보나 다녀왔던 후기를 적어주세요." maxlength="1300"></textarea>
					<h3>위치<b>*</b></h3>
					<input type="text" name="rplace" required="required" placeholder="서울시 마포구" maxlength="10">
					<h3>카테고리<b>*</b></h3>
					<select name="cno">
						<option value="1">한식</option>
						<option value="2">일식</option>
						<option value="3">중식</option>
						<option value="4">양식</option>
						<option value="5">카페/디저트</option>
						<option value="6">기타</option>
					</select>
					<h3>대표메뉴<b>*</b></h3>
					<input type="text" name="rmenu" required="required" maxlength="30">
					<h3>가격대<b>*</b></h3>
					<input type="text" name="rprice" required="required" placeholder="만원~2만원">
					<h3>맛집 전화번호</h3>
					<input type="text" name="rtel" placeholder="02-234-5678">
					<h3>음식 사진<b>*</b></h3>
					<input type="file" name="mainimg" required="required">
					<div class="btn">
						<input type="submit" value="등록">
						<input type="reset" value="취소" onclick="history.back()">
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>