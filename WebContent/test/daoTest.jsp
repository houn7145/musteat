<%@page import="com.lec.musteat.dto.OneReviewDto"%>
<%@page import="com.lec.musteat.dao.OneReviewDao"%>
<%@page import="com.lec.musteat.dto.FreeBoardDto"%>
<%@page import="com.lec.musteat.dao.FreeBoardDao"%>
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
		OneReviewDao oDao = new OneReviewDao();
		int rno = 1;
		ArrayList<OneReviewDto> dto = oDao.getOneReviewList(rno);
		for(int i=0 ; i<dto.size() ; i++){
		out.println(dto.get(i) + "<br>");
		}
	%>
</body>
</html>