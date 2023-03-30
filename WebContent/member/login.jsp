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
	<link href="${conPath }/css/member/login.css" rel="stylesheet" type="text/css">
	
</head>
<body>
	<c:if test="${not empty joinResult }">
		<script>
			alert('${joinResult}');
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp" />
	<div id="login_warp">
		<div id="login_form">
			<h4>로그인 후 나만 아는 대한민국 맛집을 공유해보세요!</h4>
			<h4>로그인하지 않아도 맛집검색은 가능합니다!</h4>
			<form action="${conPath }/login.do" method="post">
			<input type="hidden" name="next" value="${param.next }">
				<table>
					<tr>
						<td><input type="text" name="mid" required="required" placeholder="아이디" value="${mid }"></td>
					</tr>
					<tr>
						<td><input type="password" name="mpw" required="required" placeholder="비밀번호"></td>
					</tr>
					<tr>
						<td><input type="submit" value="로그인"></td>
					</tr>
				</table>
				<p></p>
			</form>
			<h5>아직 회원이 아니라면</h5>
			<a href="${conPath }/joinView.do"><button>회원가입하기</button></a>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>