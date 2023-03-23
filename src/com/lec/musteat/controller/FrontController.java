package com.lec.musteat.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.service.*;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int overlap = 0; // 새로고침시 중복 방지
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;
		
<<<<<<< HEAD
		if(command.equals("/main.do") || command.equals("/restaurantCcodeList.do")) { // main 화면 - 카테고리별 맛집 출력 | 카테고리 클릭시 페이징처리
			service = new RestaurantCcodeListService();
			service.execute(request, response);
=======
		if(command.equals("/main.do")) { // main 화면
>>>>>>> da84813b46552efaab71568f3a20e6ebe0411bf0
			viewPage = "main/main.jsp";
			
			/************************************************
			 **************** member 관련 요청 *****************
			 ************************************************/
			
		}else if(command.equals("/joinView.do")) { // 회원가입 요청
			viewPage = "main/join.jsp";
			
		}else if(command.equals("/midConfirm.do")) { // id 중복 체크
			// service = new MidConfirmService();
			service.execute(request, response);
			viewPage = "member/midConfirm.jsp";
			
		}else if(command.equals("/memailConfirm.do")) { // email 중복 체크
			// service = new MemailConfirmService();
			service.execute(request, response);
			viewPage = "member/memailConfirm.jsp";
			
		}else if(command.equals("/join.do")) { // 회원가입 submit 버튼 클릭시 서비스에서 처리 후 로그인 페이지로
			if(overlap == 1) {
				// service = new MJoinService();
				service.execute(request, response);
				overlap = 0;
			}
			viewPage = "loginView.do";
			
		}else if(command.equals("/loginView.do")) { // 로그인 View 요청시 로그인 페이지로
			viewPage = "member/login.jsp";
			
		}else if(command.equals("/login.do")){ // 로그인 submit 버튼 클릭시 서비스에서 처리 후 메인 페이지로
			// service = new MLoginService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
			
		}else if(command.equals("/logout.do")) { // 로그아웃 요청시 서비스에서 처리 후 메인 페이지로
			// service = new MLogoutService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
			
		}else if(command.equals("/modifyView.do")) { // 회원 정보수정 요청시 정보수정 페이지로
			viewPage = "member/modify.jsp";
			
		}else if(command.equals("/modify.do")) { // 정보수정 submit 버튼 클릭시 서비스에서 처리 후 메인페이지로
			if(overlap == 1) {
			//	service = new MModifyService();
				service.execute(request, response);
				overlap = 0;
			}
			viewPage = "main/main.jsp";
			
		}else if(command.equals("/withdrawal.do")) { // 회원탈퇴 요청시 서비스에서 처리 후 메인페이지로
			// service = new MWithdrawalService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
			
			/************************************************
			 **************** freeBoard 관련 요청 *****************
			 ************************************************/
			
		}else if(command.equals("/freeWriteView.do")) { // 자유게시판 글쓰기 요청
			viewPage = "freeBoard/boardWrite.jsp";
			
		}else if(command.equals("/freeWrite.do")) { // 자유게시판 글쓰기 버튼 클릭시 서비스에서 처리후 글목록 페이지로
			// service = new FreeWriteService();
			service.execute(request, response);
			viewPage = "freeBoard/boardList.jsp";
			
		}else if(command.equals("/freeList.do")) { // 자유게시판 클릭시 서비스에서 글 목록 출력 처리 후 글목록 페이지로 
			// service = new FreeListService();
			service.execute(request, response);
			viewPage = "freeBoard/boardList.jsp";
			
		}else if(command.equals("/freeContent.do")) { // 자유게시판 특정 글 클릭시 상세보기 페이지로
			// service = new FreeContentService();
			service.execute(request, response);
			viewPage = "freeBoard/boardContent.jsp"; // 파라미터로 fno를 넘김
			
		}else if(command.equals("/freeModifyView.do")) { // 자유게시판 글 수정하기 클릭시 글 수정 페이지로
			// service new FreeModifyViewService();
			viewPage = "freeBoard/boardModify.jsp";
			
		}else if(command.equals("/freeModify.do")) { // 글 수정 버튼 클릭시 서비스에서 처리 후 글 목록 페이지로
			// service = new FreeModifyService();
			service.execute(request, response);
			viewPage = "freeboardList.do";
			
		}else if(command.equals("/freeDelete.do")) { // 글 삭제 버튼 클릭시 서비스에서 처리 후 글 목록 페이지로
			// service = new FreeDeleteService();
			service.execute(request, response);
			viewPage = "freeboardList.do";
			
		}else if(command.equals("/freeReplyView.do")) { // 답변달기 버튼 클릭시 답변글 작성 페이지로
			// service = new FreeReplyViewService();
			service.execute(request, response);
			viewPage = "freeBoard/boardReply.jsp";
			
		}else if(command.equals("/freeReply.do")) { // 답변글 작성 버튼 클릭시 서비스에서 처리 후 글 상세보기 페이지로
			//service = new FreeReplyService();
			service.execute(request, response);
			viewPage = "freeBoard/boardContent.jsp";
			
			/************************************************
			 **************** restaurant 관련 요청 *****************
			 ************************************************/
			
		}else if(command.equals("/restaurantAddView.do")) { // 맛집 등록하기 버튼 클릭시 맛집 등록 페이지로
			viewPage = "restaurant/restaurantAdd.jsp";
			
		}else if(command.equals("/restaurantAdd.do")) { // 맛집 등록 버튼 클릭시 서비스에서 처리 후 맛집 리스트 페이지로
			// service = new RestaurantAddService();
			service.execute(request, response);
			viewPage = "restaurant/restaurantList.jsp";
			
		}else if(command.equals("/restaurantList.do")) { // 맛집 전체보기 클릭시 
			// service = new RestaurantListService();
			service.execute(request, response);
			viewPage = "restaurant/restaurantList.jsp";
			
		}else if(command.equals("/restaurantContent.do")) { // 맛집 상세보기
			// service = new RestaurantContentService();
			service.execute(request, response);
			viewPage = "restaurant/restaurantContent.jsp";
			
		}else if(command.equals("/restaurantModifyView")) { // 맛집 수정하기 버튼 클릭시 맛집 수정 페이지로
			// service = new RestaurantModifyViewService();
			service.execute(request, response);
			viewPage = "restaurant/restaurantModify.jsp";
			
		}else if(command.equals("/restaurantModify.do")) { // 맛집 수정 버튼 클릭시 서비스에서 처리 후 맛집 상세보기 페이지로
			// service = new RestaurantModifyService();
			service.execute(request, response);
			viewPage = "restaurant/restaurantContent.jsp";
			
		}else if(command.equals("/restaurantDelete.do")) { // 맛집 삭제 버튼 클릭시 서비스에서 처리 후 맛집 목록 페이지로
			// service = new RestaurantDeleteService();
			service.execute(request, response);
			viewPage = "restaurant/restaurantList.jsp";
		
		}else if(command.equals("/restaurantSchList.do")) { // 맛집 이름으로 검색시 맛집 목록 출력 페이지로
			service = new restaurantSchListService();
			service.execute(request, response);
			viewPage = "restaurant/restaurantSchList.jsp";
			
<<<<<<< HEAD
=======
		}else if(command.equals("restaurantCcodeList.do")) { // 메인페이지에서 카테고리별 맛집 목록 출력
			service = new RestaurantCcodeListService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
			
>>>>>>> da84813b46552efaab71568f3a20e6ebe0411bf0
			/************************************************
			 **************** oneReview 관련 요청 *****************
			 ************************************************/
			
		}else if(command.equals("/oneReviewAddView.do")) { // 한 줄 평 등록하기 버튼 클릭시 한 줄 평 등록 페이지로
			viewPage = "oneReview/onereviewAdd.jsp";
			
		}else if(command.equals("/oneReviewAdd.do")) { // 한 줄 평 등록 버튼 클릭시 서비스에서 처리 후 맛집 상세보기 페이지로
			// service = new OneReviewAddService();
			service.execute(request, response);
			viewPage = "restaurant/restaurantContent.jsp";
			
		}else if(command.equals("/oneReviewDelete.do")) { // 한 줄 평 삭제하기 버튼 클릭시 서비스에서 처리 후 맛집 상세보기 페이지로
			// service = new OneReviewDeleteService();
			service.execute(request, response);
			viewPage = "restaurant/restaurantContent.jsp";
			
		}else if(command.equals("/addRating.do")) { // 평점 등록하기 버튼 클릭시 서비스에서 처리 후 맛집 상세보기 페이지로
			// service = new addRatingService();
			service.execute(request, response);
			viewPage = "restaurant/restaurantContent.jsp";
			
		}else if(command.equals("/recommandUp.do")) { // 한 줄 평 추천하기 버튼 클릭시 서비스에서 처리
			// service = new recommandUpService();
			service.execute(request, response);
			viewPage = "restaurant/restaurantContent.jsp";
			
		}else if(command.equals("/recommandDown.do")) { // 한 줄 평 비추천하기 버튼 클릭시 서비스에서 처리
			// service = new recommandDownService();
			service.execute(request, response);
			viewPage = "restaurant/restaurantContent.jsp";
			
			/************************************************
			 **************** admin 관련 요청 *****************
			 ************************************************/
			
		}else if(command.equals("/adminLoginView.do")) { // 관리자 모드 클릭시 로그인 페이지로
			viewPage = "admin/adminLogin.jsp";
			
		}else if(command.equals("/adminLogin.do")) { // 관리자 모드 로그인 버튼 클릭시 서비스에서 처리 후 메인페이지로
			// service = new ALoginService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
			
		}else if(command.equals("/allMemberView.do")) { // 전체회원목록 보기 버튼 클릭시 서비스에서 처리 후 회원목록 출력 페이지로
			// service = new MAllViewService();
			service.execute(request, response);
			viewPage = "member/mAllView.jsp";
			
		}else if(command.equals("/withdrawal.do")) { // 등록한 회원 강퇴
			// service = new MWithdrawalService();
			service.execute(request, response);
			viewPage = "allMemberView.do";
			
			/************************************************
			 **************** NoticeBoard 관련 요청 *****************
			 ************************************************/
			
		}else if(command.equals("/noticeWriteView.do")) { // 공지게시판 글쓰기 버튼 클릭시 공지게시판 글작성 페이지로
			viewPage = "noticeWrite.jsp";
			
		}else if(command.equals("/noticeWrite.do")) { // 공지게시판 글작성 버튼 클릭시 서비스에서 처리 후 공지글 목록 출력 페이지로
			// service = new NoticeWriteService();
			service.execute(request, response);
			viewPage = "noticeboardList.do";
			
		}else if(command.equals("/noticModifyView.do")) { // 공지게시판 글수정하기 버튼 클릭시 서비스에서 처리 후 공지글 수정 페이지로
			// service = new NoticeModifyViewService();
			service.execute(request, response);
			viewPage = "noticeBoard/boardModify.jsp";
			
		}else if(command.equals("/noticeModify.do")) { // 공지게시판 글수정 버튼 클릭시 서비스에서 처리 후 공지글 목록 출력 페이지로
			// service = new NoticeModifyService();
			service.execute(request, response);
			viewPage = "noticeboardList.do";
			
		}else if(command.equals("/noticeDelete.do")) { // 공지게시판 글삭제 버튼 클릭시 서비스에서 처리 후 공지글 목록 출력 페이지로
			// service = new NoticeDeleteService();
			service.execute(request, response);
			viewPage = "noticeboardList.do";
			
			/************************************************
			 **************** Ccode 관련 요청 *****************
			 ************************************************/
			
<<<<<<< HEAD
		}else if(command.equals("/ccodeList.do")) { // 카테고리 출력
=======
		}else if(command.equals("/ccodeList.do")) {
>>>>>>> da84813b46552efaab71568f3a20e6ebe0411bf0
			service = new CcodeListService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
