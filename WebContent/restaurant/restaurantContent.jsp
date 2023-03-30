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
<link href="${conPath }/css/restaurant/restaurantContent.css"
	rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script>
	$(function() {
		$('#rsDelete').click(function() {
			var rno = $('.rContentRno').val();
			if (!isNaN(rno)) {
				location.href = '${conPath }/restaurantDeleteView.do?rno=' + rno;
			}
		}); // 맛집 삭제
		
		$('#rsEdit').click(function() {
			var rno = $('.rContentRno').val();
			if (!isNaN(rno)) {
				location.href = '${conPath }/restaurantModifyView.do?rno=' + rno;
			}
		}); // 맛집 수정

		$('.orDelete').click(function() {
			var ono = $(this).siblings('.ono').val();
			var rno = $('.oneReviewRno').val();
			if (!isNaN(ono)) {
				location.href = '${conPath }/oneReviewDeleteView.do?ono=' + ono + '&rno=' + rno;
			}
		}); 

		$('.up').click(function() {
			var ono = $(this).siblings('.ono').val();
			var rno = $('.oneReviewRno').val();
			$.ajax({
				url : '${conPath}/recommandUp.do',
				type : 'get',
				data : 'ono=' + ono + '&rno=' + rno,
				dataType : 'html',
				success : function(data) {
					$('.recommandCount' + ono).html(data);
				},
			}); // $.ajax()
		}); // up

		$('.down').click(function() {
			var ono = $(this).siblings('.ono').val();
			var rno = $('.oneReviewRno').val();
			$.ajax({
				url : '${conPath}/recommandDown.do',
				type : 'get',
				data : 'ono=' + ono + '&rno=' + rno,
				dataType : 'html',
				success : function(data) {
					$('.recommandCount' + ono).html(data);
				},
			}); // $.ajax()
		}); // down

		$('.writeOr').click(function() {
			$('.addOneReview_form').toggle('fast');
		});
	});	
	
	function clip(){
		var url = '';
		var textarea = document.createElement("textarea");
		document.body.appendChild(textarea);
		url = window.document.location.href;
		textarea.value = url;
		textarea.select();
		document.execCommand("copy");
		document.body.removeChild(textarea);
		alert("URL이 복사되었습니다.")
	};
	
</script>
<c:if test="${not empty orResult}">
	<script>
		alert('${orResult}');
	</script>
</c:if>
<c:if test="${not empty addRatingResult}">
	<script>
		alert('${addRatingResult}');
	</script>
</c:if>
<c:if test="${not empty rsModifyResult}">
	<script>
		alert('${rsModifyResult}');
	</script>
</c:if>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="rContent_warp">
		<div class="rtitle">
			<img src="${conPath }/restaurantPhotoUp/${rContent.mainimg}">
			<h1>${rContent.rname }</h1>
			<ul>
				<li>${rContent.rplace }</li>
				<li>${rContent.cname }</li>
			</ul>
			<img src="${conPath }/icon/star.png" id="star"
				style="width: 25px; height: 25px;" />
			<p><b><fmt:formatNumber value="${rContent.ravg}" pattern="0.#"/>점</b>(${rContent.avghit }명의 평가)</p>
			<hr>
			<div class="btn">
				<p>${rContent.rhit }회</p>
				<img src="${conPath }/icon/hit2.png" id="hit" style="width:25px; height:25px;">
				<button onclick="clip(); return false;">&nbsp; &nbsp; &nbsp;공유</button>
				<img src="${conPath }/icon/share.png" id="share" style="width:18px; height:18px;">
			</div>
		</div>
		<div class="rcontent">
			<h2>맛집 정보</h2>
			<hr>
			<pre>${rContent.rcontent }</pre>
			<ul>
				<li><img src="${conPath }/icon/location2.png" style="width: 25px; height: 25px;"></li>
				<li><img src="${conPath }/icon/phone2.png" style="width: 25px; height: 25px;"></li>
				<li><img src="${conPath }/icon/menu3.png" style="width: 25px; height: 25px;"></li>
				<li><img src="${conPath }/icon/price2.png" style="width: 25px; height: 25px;"></li>
			</ul>
			<ul>
				<li><span>${rContent.rplace }</span></li>
				<li><span>${rContent.rtel }</span></li>
				<li><span>${rContent.rmenu }</span></li>
				<li><span>${rContent.rprice }</span></li>
			</ul>
			<c:if test="${rContent.mid eq member.mid }">
				<input type="hidden" class="rContentRno" value="${rContent.rno }">
				<img src="${conPath }/icon/vector.png" id="rsDelete"
					style="width: 30px; height: 30px;">
				<img src="${conPath }/icon/edit.png" id="rsEdit"
					style="width: 28px; height: 28px;">
			</c:if>
			<c:if test="${not empty admin }">
				<input type="hidden" class="rContentRno" value="${rContent.rno }">
				<img src="${conPath }/icon/vector.png" id="rsDelete"
					style="width: 30px; height: 30px;">
			</c:if>
		</div>
		<c:if test="${not empty member }">
		<div class="addstarRating_form">
			<div class="addstarRating">
				<h2>평점 달기</h2>
				<hr>
				<div class="starRating">
					<form action="${conPath }/addRating.do" class="mb-3" name="myform" id="myform">
					<input type="hidden" name="rno" value="${rContent.rno }">
					<span>별을 클릭하여 맛집을 평가해주세요.</span>
					<fieldset>
						<input type="radio" name="rating" value="5" id="rate1"><label for="rate1">★</label>
						<input type="radio" name="rating" value="4" id="rate2"><label for="rate2">★</label> 
						<input type="radio" name="rating" value="3" id="rate3"><label for="rate3">★</label> 
						<input type="radio" name="rating" value="2" id="rate4"><label for="rate4">★</label> 
						<input type="radio" name="rating" value="1" id="rate5"><label for="rate5">★</label>
					</fieldset>
					<br>
					<input type="submit" class="srBtn" value="등록">
					</form>
				</div>
			</div>
		</div>
		</c:if>
		<div class="addOneReview_form">
			<div class="WriteOneReview">
				<h2>한 줄 평 작성</h2>
				<hr>
				<form action="${conPath }/oneReviewAdd.do">
					<input type="hidden" name="mid" id="mid" value="${member.mid }">
					<input type="hidden" name="rno" value="${rContent.rno }">
					<textarea name="ocontent" id="ocontent"
						placeholder="60자 이하로 한줄평을 작성해주세요." maxlength="60"
						required="required"></textarea>
					<input type="submit" class="orBtn" value="등록">
				</form>
			</div>
		</div>
		<div class="oneReview_form">
			<div class="btn">
				<h2>방문자 한 줄 평</h2>
				<c:if test="${not empty member }">
					<img src="${conPath }/icon/write.png" id="write"
						style="width: 20px; height: 20px;">
					<button class="writeOr">&nbsp; &nbsp; &nbsp; 한줄평쓰기</button>
				</c:if>
			</div>
			<hr>
			<c:forEach var="dto" items="${oneReview }">
				<div class="orList">
					<input type="hidden" class="ono" value="${dto.ono }"> 
					<input type="hidden" class="oneReviewRno" value="${dto.rno }">
					<p>${dto.mname }</p>
					<pre>${dto.ocontent }</pre>
					<c:if test="${member.mid eq dto.mid || not empty admin }">
						<img src="${conPath }/icon/x3.png" class="orDelete"
							style="width: 25px; height: 25px;">
					</c:if>
					<img src="${conPath }/icon/up.png" class="up"style="width: 25px; height: 25px;">
					<p class="recommandCount${dto.ono }">${dto.orecommand }</p>
					<img src="${conPath }/icon/down.png" class="down" style="width: 25px; height: 25px;">
				</div>
			</c:forEach>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>