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
	<link href="${conPath }/css/restaurant/restaurantSch.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="search_warp">
		<h2>${rpalce } 맛집 인기 순위</h2>
		<div class="schList">
			<c:forEach var="dto" items="${restaurantSchList }" varStatus="idx">
				<img src="${conPath }/img/${dto.mainimg }" width="150px" height="150px"/>
					<div id="restaurantTable">
						<h3>${idx.count }. ${dto.rname }</h3>
						<h4>${dto.rmenu }</h4>
						<h4>${dto.ravg }(${dto.avghit }명)</h4>
					</div>
			</c:forEach>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>