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
				<a href="${conPath }/main.do"><img src="${conPath }/img/Mlogo.png" id="logo" width="230px" height="70px"/></a>
				<div class="gnb">
					<div class="search">
						<form action="${conPath }/restaurantSchList.do">
							<input type="text" name="rplace" id="inputSch" placeholder="지역명 검색" value="${param.rplace }">
							<input type="submit" id="submitImg" value="">
						</form>	
					</div>
				</div>
				<div class="join">
					<b><a href="${conPath }/joinView.do">회원가입</a></b>
					<b><a href="${conPath }/loginView.do">로그인</a></b>
				</div>
				<div class="lnb">
					<ul>
						<li><a href="${conPath }/nBoardList.do">공지사항</a></li>
						<li><a href="${conPath }/fBoardList.do">자유게시판</a></li>
						<li><a href="${conPath }/restaurantAddView.do">맛집 등록</a></li>
					</ul>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty member and empty admin }">
			<div id="header_wrap">
				<a href="${conPath }/main.do"><img src="${conPath }/img/Mlogo.png" id="logo" width="230px" height="70px"/></a>
				<div class="gnb">
					<div class="search">
						<form action="${conPath }/restaurantSchList.do">
							<input type="text" name="rplace" id="inputSch" placeholder="지역명 검색" value="${param.rplace }">
							<input type="submit" id="submitImg" value="">
						</form>	
					</div>
				</div>
				<div class="join">
					<b><a href="${conPath }/modifyView.do">${member.mname }님</a></b>
					<b><a href="${conPath }/logout.do">로그아웃</a></b>
				</div>
				<div class="lnb">
					<ul>
						<li><a href="${conPath }/nBoardList.do">공지사항</a></li>
						<li><a href="${conPath }/fBoardList.do">자유게시판</a></li>
						<li><a href="${conPath }/restaurantAddView.do">맛집 등록</a></li>
					</ul>
				</div>
			</div>
		</c:if>
		<c:if test="${empty member and not empty admin }">
			<div id="header_wrap">
				<a href="${conPath }/main.do"><img src="${conPath }/img/Mlogo.png" id="logo" width="230px" height="70px"/></a>
				<div class="gnb">
					<div class="search">
						<form action="${conPath }/restaurantSchList.do">
							<input type="text" name="rplace" id="inputSch" placeholder="지역명 검색" value="${param.rplace }">
							<input type="submit" id="submitImg" value="">
						</form>	
					</div>
				</div>
				<div class="join">
					<b><a href="${conPath }/allMemberView.do">회원관리</a></b>
					<b><a href="${conPath }/logout.do">로그아웃</a></b>
				</div>
				<div class="lnb">
					<ul>
						<li><a href="${conPath }/nBoardList.do">공지사항</a></li>
						<li><a href="${conPath }/fBoardList.do">자유게시판</a></li>
					</ul>
				</div>
			</div>
		</c:if>
	</header>
</body>
</html>