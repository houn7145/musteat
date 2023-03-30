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
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<link href="${conPath }/css/freeBoard/fBoardWrite.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<c:if test="${empty member }">
		<script>
			location.href="${conPath}/loginView.do?next=fBoardWriteView.do";
		</script>
	</c:if>
	<div id="write_warp">
		<div class="write_content">
			<div class="write_title">
				<h2>글쓰기</h2>
				<hr>
			</div>
			<form action="${conPath }/fBoardWrite.do" method="post" enctype="multipart/form-data">
				<input type="hidden" name="mid" value="${member.mid }">
				<input type="hidden" name="mname" value="${member.mname }">
				<p>제목</p>
				<input type="text" name="ftitle" required="required" placeholder="제목을 입력해 주세요." maxlength="33">
				<p>본문</p>
				<div class="textarea">
				<textarea name="fcontent" placeholder="내용을 입력해 주세요." maxlength="1300"></textarea>
				</div>
				<p>사진1</p>
				<input type="file" name="fimage1">
				<p>사진2</p>
				<input type="file" name="fimage2">
				<br>
				<input type="submit" value="등록">
				<input type="reset" value="취소" onclick="history.back()">
			</form>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>