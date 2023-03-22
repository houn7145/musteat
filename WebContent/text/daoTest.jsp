<%@page import="com.lec.musteat.dto.RestaurantDto"%>
<%@page import="com.lec.musteat.dao.RestaurantDao"%>
<%@page import="com.lec.musteat.dto.CcodeDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.lec.musteat.dao.CcodeDao"%>
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
	<%
		CcodeDao cDao = new CcodeDao();
		ArrayList<RestaurantDto> res = cDao.getCcodeRestaurantList(1, 1, 3);
		for(RestaurantDto r : res){
			out.println(r + "<br>");
		}
		
	%>
</body>
</html>