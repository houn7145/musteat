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
	<link href="${conPath}/css/member/join.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
  	<script>
		$(function() {
			$('input[name="mid"]').keyup(function() {
				var mid = $(this).val();
				if(mid.length < 2){
					$('#midConfirmResult').text('아이디는 2글자 이상 입력하세요.');
				}else{
					$.ajax({
						url : '${conPath}/midConfirm.do',
						type : 'get',
						data : 'mid=' + mid,
						dataType : 'html',
						success : function(data) {
							$('#midConfirmResult').html(data);
						},
					});// ajax함수
				}// if
			});// keyup event - id 
			
			$('#mpw, #mpwChk').keyup(function() {
				var pw = $('#mpw').val();
				var pwChk = $('#mpwChk').val();
				if(pw == pwChk){
					$('#mpwChkResult').text('비밀번호가 일치합니다.');
				}else{
					$('#mpwChkResult').html('<b>비밀번호가 불일치합니다.</b>');
				}
			});// keyup event - pw
			
			var patternMemail = /^[a-zA-Z0-9_\.]+@[a-zA-Z0-9_]+(\.\w+){1,2}$/;
			$('#memail').keyup(function() {
				var memail = $('#memail').val();
				if(!memail){
	  				$('#memailConfirmResult').html(' &nbsp; ');
	  			}else if(!memail.match(patternMemail)){
	  				$('#memailConfirmResult').html('<b>메일 형식을 지켜 주세요</b>');
	  			}else{
	  				$.ajax({
						url : '${conPath}/memailConfirm.do',
						type : 'get',
						data : 'memail=' + memail,
						dataType : 'html',
						success : function(data) {
							$('#memailConfirmResult').html(data);
						},
					});
	  			}
			});// keup event - email
			
			$('form').submit(function() {
				var idConfirmResult = $('#midConfirmResult').text().trim();
				var pwChkResult = $('#mpwChkResult').text().trim();
				var emailChkResult = $('#memailConfirmResult').text().trim();
				if(idConfirmResult != '사용 가능한 ID입니다.'){
					alert('사용 가능한 ID가 아닙니다.');
					return false; 
				}else if(pwChkResult != '비밀번호가 일치합니다.'){
					alert('비밀번호를 확인하세요.');
					$('#mpw').focus();
					return false;
				}else if(emailChkResult != "" && emailChkResult != '사용 가능한 메일입니다.'){
					alert('사용 가능한 메일이 아닙니다.');
					$('#memail').focus();
					return false;
				}
			});
		});
  	</script>
	<script>
	  	$( function() {
	    	$( "#datepicker" ).datepicker({
	    		dateFormat: "yy-mm-dd",
	    		dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
	    		monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	    		changeMonth: true, // 월을 바꿀 수 있는 셀렉트 박스를 표시
	    		changeYear: true, // 년을 바꿀 수 있는 셀렉트 박스를 표시
	    		showMonthAfterYear: true,
	        	yearSuffix: '년',
	        	showOtherMonths: true, // 현재 달이 아닌 달의 날짜도 회색으로 표시
	        	// minDate: '-100y', // 현재 날짜로부터 100년 이전까지 년을 표시
	        	minDate: new Date(1900, 1 - 1, 1), // 1950년 1월 1일을 최소 날짜로 셋팅
	        	yearRange: 'c-100:c+10', // 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할 지
	    	});
	  	});
  	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="join_wrap">
		<div id="join_form">
			<form action="${conPath }/join.do" method="post">
				<h2>회원가입</h2>
				<table>
					<tr>
						<th>이름 <b>*</b></th>
						<td><input type="text" name="mname" required="required" placeholder="이름을 입력해 주세요"></td>
					</tr>
					<tr style="height:20px"><td></td></tr>
					<tr>
						<th>아이디 <b>*</b></th>
						<td>
							<input type="text" name="mid" required="required" placeholder="아이디를 입력해 주세요">
							<div id="midConfirmResult"></div>
						</td>
					</tr>
					<tr style="height:20px"><td></td></tr>
					<tr>
						<th>비밀번호 <b>*</b></th>
						<td>
							<input type="password" name="mpw" id="mpw" required="required" placeholder="비밀번호를 입력해 주세요">
						</td>
					</tr>
					<tr style="height:20px"><td></td></tr>
					<tr>
						<th>비밀번호 확인 <b>*</b></th>
						<td>
							<input type="password" name="mpwChk" id="mpwChk" required="required">
							<div id="mpwChkResult"></div>
						</td>
					</tr>
					<tr style="height:20px"><td></td></tr>
					<tr>
						<th>이메일</th>
						<td>
							<input type="text" name="memail" id="memail" placeholder="info@musteat.com">
							<div id="memailConfirmResult"></div>
						</td>
					</tr>
					<tr style="height:20px"><td></td></tr>
					<tr>
						<th>전화번호</th>
						<td><input type="text" name="mtel" placeholder="02-393-4321"></td>
					</tr>
					<tr style="height:20px"><td></td></tr>
					<tr>
						<th>생년월일</th>
						<td><input type="text" name="mbirth" id="datepicker"></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="가입하기">
							<input type="reset" value="초기화">
							<input type="button" value="뒤로가기" onclick="history.back()">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>