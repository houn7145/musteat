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
	<link href="${conPath }/css/header.css" rel="stylesheet" type="text/css">
</head>
<body>
	<header>
		<div id="header_wrap">
			<div class="sch">
				<form action="">
				<img src="${conPath }/img/Mlogo.png" width="250px" height="70px"/><input type="text" value="${rname }"><input type="submit">
				</form>
			</div>
			<div class="join">
				<span>회원가입</span>
				<span>로그인</span>
			</div>
			<div class="lnb">
				<ul>
					<li>공지사항</li>
					<li>자유게시판</li>
				</ul>
			</div>
		</div>
	</header>
</body>
</html>