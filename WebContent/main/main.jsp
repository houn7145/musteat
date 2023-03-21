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
</head>
<body>
	<form action="">
		<table>
			<caption>카테고리</caption>
			<tr>
				<c:forEach var="dto" items="${Ccodes }">
					<td>
						${dto.cname }
					</td>
				</c:forEach>
			</tr>
		</table>
	</form>
</body>
</html>