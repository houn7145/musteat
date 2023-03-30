<%@page import="com.lec.musteat.dto.FreeBoardDto"%>
<%@page import="com.lec.musteat.dao.FreeBoardDao"%>
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
	FreeBoardDao dao = new FreeBoardDao();
	FreeBoardDto dto = new FreeBoardDto();
	for(int i=0 ; i<60 ; i++){
		dto.setFtitle("제목 " + i);
		dto.setFcontent(i+"번째 본문");
		if(i%5!=0){
			dto.setMid("AAA");
			dto.setFimage1(null);
			dao.insertFreeBoard(dto);			
		}else if(i%5==0){
			dto.setMid("BBB");
			dto.setFimage1("1.png");
			dao.insertFreeBoard(dto);		
		}	
	}
%>
</body>
</html>