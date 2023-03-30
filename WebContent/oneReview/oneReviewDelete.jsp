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
	<link href="${conPath }/css/restaurant/restaurantDelete.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
		<div id="delete_wrap">
			<div class="delete_form">
				<div class="delete_confirm">
					<form action="${conPath }/oneReviewDelete.do">
						<input type="hidden" name="ono" value="${param.ono }">
						<input type="hidden" name="rno" value="${param.rno }">
						<h1>등록한 한줄평을 삭제하시겠습니까?</h1>
						<h2>삭제를 누르시면 복구가 불가능합니다.</h2>
						<input type="submit" value="삭제">
						<input type="button" value="취소" onclick="history.back()">
					</form>
				</div>
			</div>
		</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>