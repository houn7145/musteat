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
	<link href="${conPath }/css/error/404error.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../main/header.jsp" />
		<div id="error_form">
			<h2>요청하신 경로를 찾을 수 없거나 없는 페이지 입니다.</h2>
		</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>