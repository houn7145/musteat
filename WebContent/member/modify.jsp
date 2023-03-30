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
	<link href="${conPath}/css/member/modify.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
  	<script>
  	</script>
  		<script>
	  	$(function(){
	  		var patternMemail = /^[a-zA-Z0-9_\.]+@[a-zA-Z0-9_]+(\.\w+){1,2}$/;
	  		$('input[name="memail"]').keyup(function(){
	  			let memail = $(this).val();
	  			if( (!memail) || (memail == '${member.memail}')){
	  				$('#memailConfirmResult').html(' &nbsp; ');
	  			}else if(patternMemail.test(memail)){
	  				$.ajax({
	  					url : '${conPath}/memailConfirm.do',
	  					type : 'get',
	  					data : 'memail='+memail,
	  					dataType : 'html',
	  					success : function(data){
	  						$('#memailConfirmResult').html(data);
	  					},
	  				});
	  			}else if(!patternMemail.test(memail)){
	  				$('#memailConfirmResult').html('<b>메일 형식을 지켜 주세요.</b>');
	  			}
	  		});
	  		
	  		$('form').submit(function(){
	  			// 현비밀번호확인과 사용불가한 중복된 메일일 경우 submit 제한
	  			var oldMpw = $('input[name="oldMpw"]').val();
	  			var memailConfirmResult = $('#memailConfirmResult').text().trim();
	  			if(oldMpw != '${member.mpw}'){
	  				alert('현재 비밀번호를 확인하세요.');
	  				$('input[name="oldMpw"]').focus();
	  				return false; // submit 제한
	  			}else if( (memailConfirmResult =='메일 형식을 지켜 주세요.') || 
	  				(memailConfirmResult == '사용 불가능한 메일입니다.')){
	  				alert('메일을 확인하세요.');
	  				$('input[name="memail"]').focus();
	  				return false;
	  			}
	  		});
	  		
	  		$('#withdrawal').click(function(){
	  			location.href = '${conPath}/memberwithdrawalView.do';
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
	<c:if test="${not empty modifyResult }">
		<script>
			alert('${modifyResult}');
		</script>
	</c:if>
	<c:if test="${empty member }">
		<script>
			location.href = '${conPath}/loginView.do?next=modifyView.do';
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp" />
	<div id="modify_wrap">
		<div id="modify_form">
			<form action="${conPath }/modify.do" method="post">
			<input type="hidden" name="dbMpw" value="${member.mpw }"> <!-- 현 비밀번호를 넘김 -->
				<h2>회원정보</h2>
				<table>
					<tr>
						<th>이름 </th>
						<td><input type="text" name="mname" value="${member.mname }" readonly="readonly"></td>
					</tr>
					<tr style="height:20px"><td></td></tr>
					<tr>
						<th>아이디 </th>
						<td>
							<input type="text" name="mid" value="${member.mid }" readonly="readonly">
							<div id="midConfirmResult"></div>
						</td>
					</tr>
					<tr style="height:20px"><td></td></tr>
					<tr>
						<th>현재 비밀번호 <b>*</b></th>
						<td>
							<input type="password" name="oldMpw" id="mpw" required="required">
						</td>
					</tr>
					<tr style="height:20px"><td></td></tr>
					<tr>
						<th>새 비밀번호  </th>
						<td>
							<input type="password" name="mpw">
							<div id="mpwChkResult"></div>
						</td>
					</tr>
					<tr style="height:20px"><td></td></tr>
					<tr>
						<th>이메일 </th>
						<td>
							<input type="text" name="memail" id="memail" placeholder="info@musteat.com" value="${member.memail }">
							<div id="memailConfirmResult"></div>
						</td>
					</tr>
					<tr style="height:20px"><td></td></tr>
					<tr>
						<th>전화번호 </th>
						<td><input type="text" name="mtel" placeholder="02-393-4321" value="${member.mtel }"></td>
					</tr>
					<tr style="height:20px"><td></td></tr>
					<tr>
						<th>생년월일 </th>
						<td><input type="text" name="mbirth" id="datepicker" value="${member.mbirth }"></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="수정하기">
							<input type="button" id="withdrawal" value="탈퇴하기">
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