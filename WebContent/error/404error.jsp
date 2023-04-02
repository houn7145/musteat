<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<%
	response.setStatus(HttpServletResponse.SC_OK);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath }/css/error/error.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../main/header.jsp" />
		<div id="error_form">
			<div class="error_content">
				<h1>요청하신 페이지를 찾을 수 없습니다.</h1>
				<img src="${conPath }/img/error.png">
				<p>죄송합니다. 요청하신 페이지를 찾을 수 없습니다.</p>
				<p>존재하지 않는 주소를 입력하셨거나</p>
				<p>요청하신 페이지의 주소가 변경, 삭제되어 찾을 수 없습니다.</p>
				<button onclick="location.href='${conPath}/main.do'">홈으로</button>
			</div>
		</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>