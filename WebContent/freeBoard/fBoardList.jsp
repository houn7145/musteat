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
	<link href="${conPath }/css/freeBoard/fBoardList.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
  	<script>
		$(function() {
			$('tr').click(function() {
				var fno = $(this).children().eq(0).text();
				if(!isNaN(fno)){
					location.href = '${conPath }/fBoardContent.do?fno=' + fno + '&pageNum=${pageNum}';
				}
			});
		});
	</script>
	<c:if test="${not empty fBoardResult }">
		<script>
			alert('${fBoardResult}');
		</script>
	</c:if>
		<c:if test="${not empty fBoardModifyResult }">
		<script>
			alert('${fBoardModifyResult}');
		</script>
	</c:if>
	<c:if test="${not empty fBoaredDeleteResult }">
		<script>
			alert('${fBoaredDeleteResult}');
		</script>
	</c:if>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="board_warp">
		<div class="list_content">
			<div class="list_title">
				<h2>자유게시판</h2>
			</div>
				<div class="write">
				<c:if test="${not empty member }">
					<a href="${conPath }/fBoardWriteView.do"><span>글쓰기</span><img src="${conPath }/icon/write.png" style="width:20px; float: right;"></a>
				</c:if>
				</div>
			<hr>
			<table>
				<tr>
					<th>No</th><th>제목</th><th>글쓴이</th><th>작성시간</th><th>조회수</th>
				</tr>
				<c:if test="${freeBoardList.size() eq 0}">
					<tr><td colspan="5">해당 페이지의 글이 없습니다.</td></tr>
				</c:if>
				<c:if test="${freeBoardList.size() ne 0 }">
					<c:forEach var="dto" items="${freeBoardList }">
						<tr>
							<td>${dto.fno }</td>
							<td class="left">
							<c:forEach var="i" begin="1" end="${dto.findent }">
								<c:if test="${i ne dto.findent }">
									&nbsp; &nbsp;
								</c:if>
								<c:if test="${i eq dto.findent }">
									┕ 
								</c:if>
							</c:forEach>
							<c:if test="${not empty dto.fimage1 || not empty dto.fimage2 }">
								${dto.ftitle } <img src="${conPath }/icon/file.png" style="width:10px;">
							</c:if>
							<c:if test="${empty dto.fimage1 && empty dto.fimage2 }">
								${dto.ftitle }
							</c:if>
							</td>
							<td>${dto.mname }</td>
							<td><fmt:formatDate pattern="yy/MM/dd hh:mm:ss" value="${dto.frdate }"/> </td>
							<td>${dto.fhit }</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
		<div class="boardFooter">
			<div class="search">
				<form action="${conPath }/fBoardSch.do?pageNum2=${pageNum}">
					<input type="text" name="ftitle" class="inputSch" placeholder="제목 검색">
					<input type="submit" class="submitImg" value="">
				</form>	
			</div>
			<div class="paging">
				<ul>
				<c:if test="${startPage > BLOCKSIZE }">
					 <li><a href="${conPath }/fBoardList.do?pageNum=${startPage - 1}">◀</a></li>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum }">		
						<li class="now"><a>${i }</a></li>
					</c:if>
					<c:if test="${i ne pageNum }">
						<li><a href="${conPath}/fBoardList.do?pageNum=${i}">${i }</a></li>
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageCnt }"> 
					<li><a href="${conPath }/fBoardList.do?pageNum=${endPage + 1 }">▶</a></li>
				</c:if>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>