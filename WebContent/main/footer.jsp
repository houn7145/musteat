<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="${conPath }/css/main/footer.css" rel="stylesheet" type="text/css">
</head>
<body>
	<footer>
		<div id="footer_wrap">
			<ul>
				<li><a href="#">회사 소개</a><span class="border"></span></li>
				<li><a href="#">서비스 소개</a><span class="border"></span></li>
				<li><a href="#">광고 및 제휴</a><span class="border"></span></li>
				<li><a href="#"><b>개인정보처리방침</b></a><span class="border"></span></li>
				<li><a href="#">이용약관</a><span class="border"></span></li>
				<li><a href="#">도움말</a><span class="border"></span></li>
				<li><a href="${conPath }/nBoardList.do" class="click"><b>공지사항</b></a></li>
			</ul>
			<ul>
				<li><a href="#"><b>Must-Eat(주)</b></a><span class="border"></span></li>
				<li><a href="#">대표자 <b>김훈</b></a><span class="border"></span></li>
				<li><a href="#">서울특별시 서대문구 대현동 104-48 1층 (위고인빌딩)</a></li>
			</ul>
			<ul>
				<li><a href="#">사업자등록번호 414-33-12345</a><span class="border"></span></li>
				<li><a href="#">TEL <b>02-393-4321</b></a><span class="border"></span></li>
				<li><a href="#">EMAIL <b>info@musteat.com</b></a></li>
			</ul>
			<br>
			<br>
			<span>Copyright (c) Must-Eat. All Rights Reserved.</span>
		</div>
	</footer>
</body>
</html>