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
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link href="${conPath }/css/noticeBoard/nBoardContent.css"
	rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script>
	$(function() {
		$('tr').click(
				function() {
					var nno = $(this).children().eq(0).text();
					if (!isNaN(nno)) {
						location.href = '${conPath }/nBoardContent.do?nno='
								+ nno + '&pageNum=${pageNum}&ntitle=${ntitle}';
					}
				});
	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_warp">
		<div class="content_title">
			<h3>${nBoardContent.ntitle }</h3>
			<ul>
				<li>${nBoardContent.aname }</li>
				<li><fmt:formatDate pattern="yy/MM/dd hh:mm:ss"
						value="${nBoardContent.nrdate }" /></li>
				<li>조회 수 ${nBoardContent.nhit}</li>
			</ul>
			<hr>
		</div>
		<div class="content_main">
			<div class="fcontent">
				<pre>${nBoardContent.ncontent }</pre>
			</div>
			<hr>
			<div class="btn">
				<c:if test="${not empty admin && admin.aid eq nBoardContent.aid}">
					<button
						onclick="location.href='${conPath}/nBoardModifyView.do?nno=${param.nno }&pageNum=${param.pageNum }'">수정</button>
					<button
						onclick="location.href='${conPath}/nBoardDelete.do?nno=${param.nno }&pageNum=${param.pageNum }'">삭제</button>
					<button
						onclick="location.href='${conPath}/nBoardList.do?pageNum=${param.pageNum }'">목록</button>
				</c:if>
				<c:if test="${empty admin && empty member}">
					<button
						onclick="location.href='${conPath}/nBoardList.do?pageNum=${param.pageNum }'">목록</button>
				</c:if>
				<c:if test="${not empty member && empty admin}">
					<button
						onclick="location.href='${conPath}/nBoardList.do?pageNum=${param.pageNum }'">목록</button>
				</c:if>
			</div>
		</div>
	</div>
	<div id="board_warp">
		<div class="list_content">
			<div class="list_title">
				<h2>공지사항</h2>
			</div>
			<div class="write">
				<c:if test="${not empty admin }">
					<a href="${conPath }/nBoardWriteView.do"><span>글쓰기</span><img
						src="${conPath }/icon/write.png"
						style="width: 20px; float: right;"></a>
				</c:if>
			</div>
			<hr>
			<table>
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>작성시간</th>
					<th>조회수</th>
				</tr>
				<c:if test="${schNoitceBoardList.size() eq 0}">
					<tr>
						<td colspan="5">검색된 글이 없거나 해당 페이지의 글이 없습니다</td>
					</tr>
				</c:if>
				<c:if test="${schNoitceBoardList.size() ne 0 }">
					<c:forEach var="dto" items="${schNoitceBoardList }">
						<tr>
							<td>${dto.nno }</td>
							<td class="left">${dto.ntitle }</td>
							<td>${dto.aname }</td>
							<td><fmt:formatDate pattern="yy/MM/dd hh:mm:ss"
									value="${dto.nrdate }" /></td>
							<td>${dto.nhit }</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
		<div class="boardFooter">
			<div class="search">
				<form action="${conPath }/nBoardSch.do">
					<input type="text" name="ntitle" class="inputSch"
						placeholder="제목 검색" value="${ntitle }"> <input type="submit"
						class="submitImg" value="">
				</form>
			</div>
			<div class="paging">
				<ul>
					<c:if test="${startPage > BLOCKSIZE }">
						<li><a
							href="${conPath }/nBoardList.do?pageNum=${startPage - 1}&ntitle=${ntitle}">◀</a></li>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:if test="${i eq pageNum }">
							<li class="now"><a>${i }</a></li>
						</c:if>
						<c:if test="${i ne pageNum }">
							<li><a href="${conPath}/nBoardList.do?pageNum=${i}&ntitle=${ntitle}">${i }</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${endPage < pageCnt }">
						<li><a
							href="${conPath }/nBoardList.do?pageNum=${endPage + 1 }&ntitle=${ntitle}">▶</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>