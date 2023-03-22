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
	<link rel="stylesheet" href="../css/flexslider.css" type="text/css">
	<script type="text/javascript" src="../js/modernizr.custom.28468.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="../js/jquery.flexslider.js"></script>
	<script>
		$(function(){
			$('#mslider').flexslider({animation: "slide"});
		});
	</script>
	<style>
		.wrap {
			width: 1500px;
			height: 700px;
			border: 1px solid gray;
		}
	</style>
</head>
<body>
	<div class="wrap">
		<div id="slider">
			<div id="mslider" class="flexslider">
				<ul class="slides">
					<li><img src="../img/img1.jpg"></li>
					<li><img src="../img/img2.jpg"></li>
					<li><img src="../img/img3.jpg"></li>
					<li><img src="../img/img4.jpg"></li>
					<li><img src="../img/img5.jpg"></li>
				</ul>
			</div>
		</div>
	</div>
</head>
<body>
	
</body>
</html>