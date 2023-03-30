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
	<link href="${conPath }/css/admin/aLogin.css" rel="stylesheet" type="text/css">
	
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
			<h2>관리자 로그인 페이지입니다.</h2>
			<form action="${conPath }/adminLogin.do" method="post">
			<input type="hidden" name="next" value="${param.next }">
				<table>
					<tr>
						<td><input type="text" name="aid" required="required" placeholder="아이디" value="${aid }"></td>
					</tr>
					<tr>
						<td><input type="password" name="apw" required="required" placeholder="비밀번호"></td>
					</tr>
					<tr>
						<td><input type="submit" value="로그인"></td>
					</tr>
				</table>
				<p></p>
			</form>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>