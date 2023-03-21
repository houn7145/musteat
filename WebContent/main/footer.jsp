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
	</footer>
</body>
</html>