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
				<h1>서버 장애로 인해 서비스할 수 없습니다.</h1>
				<img src="${conPath }/img/error.png">
				<p>죄송합니다. 서버 장애로 인해 서비스할 수 없습니다.</p>
				<p>관리자에게 문의하시거나 나중에 다시 접속해주세요.</p>
				<button onclick="location.href='${conPath}/main.do'">홈으로</button>
			</div>
		</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>