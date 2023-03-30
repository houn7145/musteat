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
	<link href="${conPath }/css/admin/mAllView.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	<script>
		$(function() {
			$('.mKickOut').click(function() {
				var mid = $(this).attr('id');
				location.href = '${conPath }/aKickOutView.do?mid=' + mid;
			});
		});
	</script>
</head>
<c:if test="${not empty kickOutResult }">
	<script>
		alert('${kickOutResult}');
	</script>
</c:if>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="board_warp">
		<div class="list_content">
			<div class="list_title">
				<h2>회원목록</h2>
			</div>
			<hr>
			<table>
				<tr>
					<th>이름</th><th>ID</th><th>PW</th><th>E-mail</th><th>전화번호</th><th>생년월일</th><th>가입일</th><th>회원추방</th>
				</tr>
				<c:if test="${memberList.size() eq 0}">
					<tr><td colspan="8">해당 페이지의 회원이 없습니다.</td></tr>
				</c:if>
				<c:if test="${memberList.size() ne 0 }">
					<c:forEach var="dto" items="${memberList }">
						<tr>
							<td>${dto.mname }</td>
							<td>${dto.mid }</td>
							<td>${dto.mpw }</td>
							<c:if test="${empty dto.memail }">
								<td>-</td>
							</c:if>
							<c:if test="${not empty dto.memail }">
								<td>${dto.memail }</td>
							</c:if>
							<c:if test="${empty dto.mtel }">
								<td>-</td>
							</c:if>
							<c:if test="${not empty dto.mtel }">
								<td>${dto.mtel }</td>
							</c:if>
							<c:if test="${empty dto.mbirth }">
								<td>-</td>
							</c:if>
							<c:if test="${not empty dto.mbirth }">
								<td><fmt:formatDate pattern="yy/MM/dd hh:mm:ss" value="${dto.mbirth }"/></td>
							</c:if>
							<td><fmt:formatDate pattern="yy/MM/dd hh:mm:ss" value="${dto.mrdate }"/></td>
							<td><img src="${conPath }/icon/x1.png" class="mKickOut" style="width: 25px; height: 25px;" id="${dto.mid }"></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
		<div class="boardFooter">
			<div class="paging">
				<ul>
				<c:if test="${startPage > BLOCKSIZE }">
					 <li><a href="${conPath }/allMemberView.do?pageNum=${startPage - 1}">◀</a></li>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum }">		
						<li class="now"><a>${i }</a></li>
					</c:if>
					<c:if test="${i ne pageNum }">
						<li><a href="${conPath}/allMemberView.do?pageNum=${i}">${i }</a></li>
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageCnt }"> 
					<li><a href="${conPath }/allMemberView.do?pageNum=${endPage + 1 }">▶</a></li>
				</c:if>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>