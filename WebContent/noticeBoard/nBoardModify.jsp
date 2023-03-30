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
	<link href="${conPath }/css/noticeBoard/nBoardWrite.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<c:if test="${empty admin }">
		<script>
			location.href="${conPath}/aLoginView.do?next=nBoardListView.do";
		</script>
	</c:if>
	<c:if test="${not empty admin }">
	<div id="write_warp">
		<div class="write_content">
			<div class="write_title">
				<h2>글수정</h2>
				<hr>
			</div>
			<form action="${conPath }/nBoardModify.do" method="post">
				<input type="hidden" name="aid" value="${admin.aid }">
				<input type="hidden" name="aname" value="${admin.aname }">
				<input type="hidden" name="nno" value="${param.nno }">
				<p>제목</p>
				<input type="text" name="ntitle" required="required" placeholder="제목을 입력해 주세요." value="${nboard.ntitle}">
				<p>본문</p>
				<div class="textarea">
				<textarea name="ncontent" placeholder="내용을 입력해 주세요.">${nboard.ncontent}</textarea>
				</div>
				<br>
				<input type="submit" value="수정">
				<input type="reset" value="취소" onclick="history.back()">
			</form>
		</div>
	</div>
	</c:if>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>