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
<<<<<<< HEAD
	<link href="${conPath }/css/footer.css" rel="stylesheet" type="text/css">
</head>
<body>
	<footer>
		<div id="wrap">
			<ul>
				<li><a href="#">회사 소개</a><span class="border"></span></li>
				<li><a href="#">서비스 소개</a><span class="border"></span></li>
				<li><a href="#">광고 및 제휴</a><span class="border"></span></li>
				<li><a href="#"><b>개인정보처리방침</b></a><span class="border"></span></li>
				<li><a href="#">이용약관</a><span class="border"></span></li>
				<li><a href="#">도움말</a><span class="border"></span></li>
				<li><a href="#"><b>공지사항</b></a></li>
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
		</div>
		<span>Copyright (c) Must-Eat. All Rights Reserved.</span>
=======
	<style>
		@font-face {
			font-family: 'yangjin';
			src: url('${conPath}/fonts/양진체v0.9_ttf.woff') format('woff');
		}
		
		@font-face {
			font-family: 'pop-bold';
			src: url('${conPath}/fonts/ONE Mobile Bold.ttf') format('woff');
		}
		
		@font-face {
			font-family: 'pop-Light';
			src: url('${conPath}/fonts/ONE Mobile Light.ttf') format('woff');
		}
		
		* {
			margin: 0;
			padding: 0;
		}
		
		a {
			text-decoration: none;
			color: black;
			font-family: 'pop-Light';
			font-size: 0.9em;
		}
		
		li {
			list-style: none;
		}
		
		footer {
			margin: 0 auto;
			width: 1000px;
			height: 200px;
			border: 1px solid gray;
    		box-sizing: border-box;
		}
		
		footer ul{
		    overflow: hidden;
		    margin: 20px 50px;
		}
		
		footer ul li{
		    float: left;
		    margin-left: 20px;
		}	
		
		footer ul:first-child{
		   font-family: 'pop-bold';
		}	
	</style>
</head>
<body>
	<footer>
		<ul>
			<li><a href="#">데이터 제휴 문의</a></li>
			<li><a href="#">개인정보처리방침</a></li>
			<li><a href="#">이용약관</a></li>
			<li><a href="#">공지사항</a></li>
		</ul>
		<ul>
			<li><a href="#">(주)Must-Eat</a></li>
			<li><a href="#">대표이사 : 김훈</a></li>
			<li><a href="#">소재지 : 서울특별시 서대문구</a></li>
		</ul>
>>>>>>> da84813b46552efaab71568f3a20e6ebe0411bf0
	</footer>
</body>
</html>