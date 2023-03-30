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
<link href="${conPath }/css/freeBoard/fBoardContent.css"
	rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script>
	$(function() {
		$('tr').click(
				function() {
					var fno = $(this).children().eq(0).text();
					if (!isNaN(fno)) {
						location.href = '${conPath }/fBoardContent.do?fno='
								+ fno + '&pageNum=${pageNum}&ftitle=${ftitle}';
					}
				});
	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_warp">
		<div class="content_title">
			<h3>${fBoardContent.ftitle }</h3>
			<ul>
				<li>${fBoardContent.mname }</li>
				<li><fmt:formatDate pattern="yy/MM/dd hh:mm:ss"
						value="${fBoardContent.frdate }" /></li>
				<li>조회 수 ${fBoardContent.fhit}</li>
			</ul>
			<hr>
		</div>
		<div class="content_main">
			<c:if test="${not empty fBoardContent.fimage1}">
				<div class="img1">
					<img src="${conPath }/freeBoardPhotoUp/${fBoardContent.fimage1}"
						alt="${fBoardContent.fimage1 }사진1"
						style="max-width: 850px; max-height: 250px;">
				</div>
			</c:if>
			<c:if test="${not empty fBoardContent.fimage2}">
				<div class="img2">
					<img src="${conPath }/freeBoardPhotoUp/${fBoardContent.fimage2}"
						alt="${fBoardContent.fimage2 }사진2"
						style="max-width: 850px; max-height: 250px;">
				</div>
			</c:if>
			<div class="fcontent">
				<pre>${fBoardContent.fcontent }</pre>
			</div>
			<hr>
			<div class="btn">
				<c:if test="${empty admin && member.mid eq fBoardContent.mid}">
					<button
						onclick="location.href='${conPath}/fBoardModifyView.do?fno=${param.fno }&pageNum=${param.pageNum }'">수정</button>
					<button
						onclick="location.href='${conPath}/fBoardDelete.do?fgroup=${fBoardContent.fgroup }&fstep=${fBoardContent.fstep }&findent=${fBoardContent.findent }&pageNum=${param.pageNum }'">삭제</button>
					<button
						onclick="location.href='${conPath}/fBoardReplyView.do?fno=${param.fno }&pageNum=${param.pageNum }'">답변</button>
					<button
						onclick="location.href='${conPath}/fBoardList.do?pageNum=${param.pageNum }'">목록</button>
				</c:if>
				<c:if
					test="${empty admin && not empty member && member.mid ne fBoardContent.mid}">
					<button
						onclick="location.href='${conPath}/fBoardReplyView.do?fno=${param.fno }&pageNum=${param.pageNum }'">답변</button>
					<button
						onclick="location.href='${conPath}/fBoardList.do?pageNum=${param.pageNum }'">목록</button>
				</c:if>
				<c:if test="${empty admin && empty member}">
					<button
						onclick="location.href='${conPath}/fBoardList.do?pageNum=${param.pageNum }'">목록</button>
				</c:if>
				<c:if test="${empty member && not empty admin}">
					<button
						onclick="location.href='${conPath}/fBoardDelete.do?fgroup=${fBoardContent.fgroup }&fstep=${fBoardContent.fstep }&findent=${fBoardContent.findent }&pageNum=${param.pageNum }'">삭제</button>
					<button
						onclick="location.href='${conPath}/fBoardList.do?pageNum=${param.pageNum }'">목록</button>
				</c:if>
			</div>
		</div>
	</div>
	<div id="board_warp">
		<div class="list_content">
			<div class="list_title">
				<h2>자유게시판</h2>
			</div>
			<div class="write">
				<c:if test="${not empty member }">
					<a href="${conPath }/fBoardWriteView.do"><span>글쓰기</span><img
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
				<c:if test="${schFreeBoardList.size() eq 0}">
					<tr>
						<td colspan="5">검색된 글이 없거나 해당 페이지의 글이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${schFreeBoardList.size() ne 0 }">
					<c:forEach var="dto" items="${schFreeBoardList }">
						<tr>
							<td>${dto.fno }</td>
							<td class="left"><c:forEach var="i" begin="1"
									end="${dto.findent }">
									<c:if test="${i ne dto.findent }">
									&nbsp; &nbsp;
								</c:if>
									<c:if test="${i eq dto.findent }">
									┕ 
								</c:if>
								</c:forEach> <c:if test="${not empty dto.fimage1 || not empty dto.fimage2 }">
								${dto.ftitle } <img src="${conPath }/icon/file.png"
										style="width: 10px;">
								</c:if> <c:if test="${empty dto.fimage1 && empty dto.fimage2 }">
								${dto.ftitle }
							</c:if></td>
							<td>${dto.mname }</td>
							<td><fmt:formatDate pattern="yy/MM/dd hh:mm:ss"
									value="${dto.frdate }" /></td>
							<td>${dto.fhit }</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
		<div class="boardFooter">
			<div class="search">
				<form action="${conPath }/fBoardSch.do?pageNum=${pageNum}">
					<input type="text" name="ftitle" class="inputSch"
						placeholder="제목 검색" value="${ftitle }"> <input
						type="submit" class="submitImg" value="">
				</form>
			</div>
			<div class="paging">
				<ul>
					<c:if test="${startPage > BLOCKSIZE }">
						<li><a
							href="${conPath }/fBoardSch.do?pageNum=${startPage - 1}&ftitle=${ftitle}">◀</a></li>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:if test="${i eq pageNum }">
							<li class="now"><a>${i }</a></li>
						</c:if>
						<c:if test="${i ne pageNum }">
							<li><a
								href="${conPath}/fBoardSch.do?pageNum=${i}&ftitle=${ftitle}">${i }</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${endPage < pageCnt }">
						<li><a
							href="${conPath }/fBoardSch.do?pageNum=${endPage + 1 }&ftitle=${ftitle}">▶</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>