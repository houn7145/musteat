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
	<link href="${conPath }/css/main/header.css" rel="stylesheet" type="text/css">
</head>
<body>
	<header>
		<c:if test="${empty member and empty admin }">
			<div id="header_wrap">
				<img src="${conPath }/img/Mlogo.png" width="250px" height="70px"/>
				<div class="gnb">
					<div class="search">
						<form action="${conPath }/restaurantSchList.do">
							<input type="text" name="rplace" id="inputSch" placeholder="지역 검색">
							<input type="submit" id="submitImg" value="">
						</form>	
					</div>
				</div>
				<div class="join">
					<b>회원가입</b>
					<b>로그인</b>
				</div>
				<div class="lnb">
					<ul>
						<li>공지사항</li>
						<li>자유게시판</li>
						<li>맛집 등록</li>
					</ul>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty member and not empty admin }">
			<div id="header_wrap">
				<img src="${conPath }/img/Mlogo.png" width="250px" height="70px"/>
				<div class="gnb">
					<div class="search">
						<form action="${conPath }/restaurantSchList.do">
							<input type="text" name="rplace" id="inputSch" placeholder="지역 검색">
							<input type="submit" id="submitImg" value="">
						</form>	
					</div>
				</div>
				<div class="join">
					<b>정보수정</b>
					<b>로그아웃</b>
				</div>
				<div class="lnb">
					<ul>
						<li>공지사항</li>
						<li>자유게시판</li>
						<li>맛집 등록</li>
					</ul>
				</div>
			</div>
		</c:if>
		<c:if test="${empty member and not empty admin }">
			<div id="header_wrap">
				<img src="${conPath }/img/Mlogo.png" width="250px" height="70px"/>
				<div class="gnb">
					<div class="search">
						<form action="${conPath }/restaurantSchList.do">
							<input type="text" name="rplace" id="inputSch" placeholder="지역 검색">
							<input type="submit" id="submitImg" value="">
						</form>	
					</div>
				</div>
				<div class="join">
					<b>회원관리</b>
					<b>로그아웃</b>
				</div>
				<div class="lnb">
					<ul>
						<li>공지사항</li>
						<li>자유게시판</li>
					</ul>
				</div>
			</div>
		</c:if>
	</header>
</body>
</html>