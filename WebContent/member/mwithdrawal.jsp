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
	<link href="${conPath }/css/member/withdrawal.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
  	<c:if test="${empty member }">
  		<script>
  			location.href='${conPath}/loginView.do';
  		</script>
  	</c:if>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
		<div id="delete_wrap">
			<div class="delete_form">
				<div class="delete_confirm">
					<form action="${conPath }/memberwithdrawal.do">
						<h1>회원탈퇴를 진행하시겠습니까?</h1>
						<h2>탈퇴를 누르시면 복구가 불가능합니다.</h2>
						<input type="submit" value="탈퇴">
						<input type="button" value="취소" onclick="history.back()">
					</form>
				</div>
			</div>
		</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>