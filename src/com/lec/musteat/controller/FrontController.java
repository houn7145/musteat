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

		if(command.equals("/main.do") || command.equals("/restaurantCcodeList.do")) { // main 화면 - 카테고리별 맛집 출력 | 카테고리 클릭시 페이징처리
			service = new RestaurantCcodeListService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
			
			/************************************************
			 **************** member 관련 요청 *****************
			 ************************************************/
			
		}else if(command.equals("/joinView.do")) { // 회원가입 요청
			viewPage = "member/join.jsp";
			overlap = 1;
			
		}else if(command.equals("/midConfirm.do")) { // id 중복 체크
			service = new MidConfirmService();
			service.execute(request, response);
			viewPage = "member/midConfirm.jsp";
			
		}else if(command.equals("/memailConfirm.do")) { // email 중복 체크
			service = new MemailConfirmService();
			service.execute(request, response);
			viewPage = "member/memailConfirm.jsp";
			
		}else if(command.equals("/join.do")) { // 회원가입 submit 버튼 클릭시 서비스에서 처리 후 로그인 페이지로
			if(overlap == 1) {
				service = new MJoinService();
				service.execute(request, response);
				overlap = 0;
			}	
			viewPage = "loginView.do";
			
		}else if(command.equals("/loginView.do")) { // 로그인 View 요청시 로그인 페이지로
			viewPage = "member/login.jsp";
			
		}else if(command.equals("/login.do")){ // 로그인 submit 버튼 클릭시 서비스에서 처리 후 메인 페이지로
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "main.do";
			
		}else if(command.equals("/logout.do")) { // 로그아웃 요청시 서비스에서 처리 후 메인 페이지로
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "main.do";
			
		}else if(command.equals("/modifyView.do")) { // 회원 정보수정 요청시 정보수정 페이지로
			viewPage = "member/modify.jsp";
			overlap = 1;
			
		}else if(command.equals("/modify.do")) { // 정보수정 submit 버튼 클릭시 서비스에서 처리 후 메인페이지로
			if(overlap == 1) {
				service = new MModifyService();
				service.execute(request, response);
				overlap = 0;
			}
			viewPage = "modifyView.do";
			
		}else if(command.equals("/memberwithdrawalView.do")) { // 회원탈퇴 클릭시 확인페이지로
			viewPage = "member/mwithdrawal.jsp";
			overlap = 1;
			
		}else if(command.equals("/memberwithdrawal.do")) { // 회원탈퇴 요청시 서비스에서 처리 후 메인페이지로
			if(overlap == 1) {
				service = new MWithdrawalService();
				service.execute(request, response);
				overlap = 0;
			}
			viewPage = "main.do";
			
			/************************************************
			 **************** freeBoard 관련 요청 *****************
			 ************************************************/
			
		}else if(command.equals("/fBoardWriteView.do")) { // 자유게시판 글쓰기 요청
			viewPage = "freeBoard/fBoardWrite.jsp";
			overlap = 1;
			
		}else if(command.equals("/fBoardWrite.do")) { // 자유게시판 글쓰기 버튼 클릭시 서비스에서 처리후 글목록 페이지로
			if(overlap == 1) {
				service = new FboardWriteService();
				service.execute(request, response);
				overlap = 0;
			}
			viewPage = "fBoardList.do";
			
		}else if(command.equals("/fBoardList.do")) { // 자유게시판 클릭시 서비스에서 글 목록 출력 처리 후 글목록 페이지로 
			service = new FboardListService();
			service.execute(request, response);
			viewPage = "freeBoard/fBoardList.jsp";
			
		}else if(command.equals("/fBoardContent.do")) { // 자유게시판 특정 글 클릭시 상세보기 페이지로
			service = new FboardContentService();
			service.execute(request, response);
			viewPage = "freeBoard/fBoardContent.jsp"; // 파라미터로 fno를 넘김
			
		}else if(command.equals("/fBoardModifyView.do")) { // 자유게시판 글 수정하기 클릭시 글 수정 페이지로
			service = new FboardModifyViewService();
			service.execute(request, response);
			viewPage = "freeBoard/fBoardModify.jsp";
			
		}else if(command.equals("/fBoardModify.do")) { // 글 수정 버튼 클릭시 서비스에서 처리 후 글 목록 페이지로
			service = new FboardModifyService();
			service.execute(request, response);
			viewPage = "fBoardList.do";
			
		}else if(command.equals("/fBoardDelete.do")) { // 글 삭제 버튼 클릭시 서비스에서 처리 후 글 목록 페이지로
			service = new FboardDeleteService();
			service.execute(request, response);
			viewPage = "fBoardList.do";
			
		}else if(command.equals("/fBoardReplyView.do")) { // 답변달기 버튼 클릭시 답변글 작성 페이지로
			service = new FboardReplyViewService();
			service.execute(request, response);
			viewPage = "freeBoard/fBoardReply.jsp";
			
		}else if(command.equals("/fBoardReply.do")) { // 답변글 작성 버튼 클릭시 서비스에서 처리 후 글 상세보기 페이지로
			service = new FboardReplyService();
			service.execute(request, response);
			viewPage = "fBoardList.do";
		
		}else if(command.equals("/fBoardSch.do")) { // 자유게시판 검색시 서비스에서 처리 후 글 목록 페이지로
			service = new FboardSchService();
			service.execute(request, response);
			viewPage = "freeBoard/fBoardSchList.jsp";
			
			/************************************************
			 **************** restaurant 관련 요청 *****************
			 ************************************************/
			
		}else if(command.equals("/restaurantAddView.do")) { // 맛집 등록하기 버튼 클릭시 맛집 등록 페이지로
			viewPage = "restaurant/restaurantAdd.jsp";
			
		}else if(command.equals("/restaurantAdd.do")) { // 맛집 등록 버튼 클릭시 서비스에서 처리 후 맛집 리스트 페이지로
			service = new RestaurantAddService();
			service.execute(request, response);
			viewPage = "main.do";
			
		}else if(command.equals("/restaurantContent.do")) { // 맛집 상세보기
			service = new RestaurantContentService();
			service.execute(request, response);
			viewPage = "restaurant/restaurantContent.jsp";
			
		}else if(command.equals("/restaurantModifyView.do")) { // 맛집 수정하기 버튼 클릭시 맛집 수정 페이지로
			service = new RestaurantModifyViewService();
			service.execute(request, response);
			viewPage = "restaurant/restaurantModify.jsp";
			overlap = 1;
			
		}else if(command.equals("/restaurantModify.do")) { // 맛집 수정 버튼 클릭시 서비스에서 처리 후 맛집 상세보기 페이지로
			if(overlap == 1) {
				service = new RestaurantModifyService();
				service.execute(request, response);
				overlap = 0;
			}
			viewPage = "restaurantContent.do";
			
		}else if(command.equals("/restaurantDeleteView.do")) { // 맛집 삭제 버튼 클릭시 삭제 확인 페이지로
			viewPage = "restaurant/restaurantDelete.jsp";
			overlap = 1;
			
		}else if(command.equals("/restaurantDelete.do")) { // 맛집 삭제 버튼 클릭시 서비스에서 처리 후 맛집 목록 페이지로
			if(overlap == 1) {
				service = new RestaurantDeleteService();
				service.execute(request, response);
				overlap = 0;
			}
			viewPage = "main.do";
		
		}else if(command.equals("/restaurantSchList.do")) { // 맛집 지역 이름으로 검색시 맛집 목록 출력 페이지로
			service = new RestaurantSchListService();
			service.execute(request, response);
			viewPage = "restaurant/restaurantSchList.jsp";

		}else if(command.equals("restaurantCcodeList.do")) { // 메인페이지에서 카테고리별 맛집 목록 출력
			service = new RestaurantCcodeListService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
			
			/************************************************
			 **************** oneReview 관련 요청 *****************
			 ************************************************/
			
		}else if(command.equals("/oneReviewAdd.do")) { // 한 줄 평 등록 버튼 클릭시 서비스에서 처리 후 맛집 상세보기 페이지로
			service = new OneReviewAddService();
			service.execute(request, response);
			viewPage = "restaurantContent.do";
		
		}else if(command.equals("/addRating.do")) { // 평점 등록하기 버튼 클릭시 서비스에서 처리 후 맛집 상세보기 페이지로
			service = new RestaurantAddRatingService();
			service.execute(request, response);
			viewPage = "restaurantContent.do";
			
		}else if(command.equals("/oneReviewDeleteView.do")) { // 한줄평 삭제 버튼 클릭시 확인 페이지로
			viewPage = "oneReview/oneReviewDelete.jsp";
			overlap = 1;
			
		}else if(command.equals("/oneReviewDelete.do")) { // 한 줄 평 삭제하기 버튼 클릭시 서비스에서 처리 후 맛집 상세보기 페이지로
			if(overlap == 1) {
				service = new OneReviewDeleteService();
				service.execute(request, response);
				overlap = 0;
			}
			viewPage = "restaurantContent.do";
			
		}else if(command.equals("/recommandUp.do")) { // 한 줄 평 추천하기 버튼 클릭시 서비스에서 처리
			service = new RecommandUpService();
			service.execute(request, response);
			viewPage = "oneReview/recommand.jsp";
			
		}else if(command.equals("/recommandDown.do")) { // 한 줄 평 비추천하기 버튼 클릭시 서비스에서 처리
			service = new RecommandDownService();
			service.execute(request, response);
			viewPage = "oneReview/recommand.jsp";
			
			/************************************************
			 **************** admin 관련 요청 *****************
			 ************************************************/
			
		}else if(command.equals("/adminLoginView.do")) { // 관리자 모드 클릭시 로그인 페이지로
			viewPage = "admin/aLogin.jsp";
			
		}else if(command.equals("/adminLogin.do")) { // 관리자 모드 로그인 버튼 클릭시 서비스에서 처리 후 메인페이지로
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "main.do";
			
		}else if(command.equals("/allMemberView.do")) { // 전체회원목록 보기 버튼 클릭시 서비스에서 처리 후 회원목록 출력 페이지로
			service = new MAllViewService();
			service.execute(request, response);
			viewPage = "admin/mAllView.jsp";
			
		}else if(command.equals("/aKickOutView.do")) { // 회원추방 버튼 클릭시 확인 페이지로
			viewPage = "admin/aKickOut.jsp";
			overlap = 1;
			
		}else if(command.equals("/aKickOut.do")) { // 회원추방 확인버튼 클릭시 서비스에서 처리 후 멤버 리스트 페이지로
			if(overlap == 1) {
				service = new AKickOutService();
				service.execute(request, response);
				overlap = 0;
			}
			viewPage = "allMemberView.do";
			
			/************************************************
			 **************** NoticeBoard 관련 요청 *****************
			 ************************************************/
			
		}else if(command.equals("/nBoardList.do")) { // 공지사항 클릭시 서비스에서 처리후 글목록 출력
			service = new NboardListService();
			service.execute(request, response);
			viewPage = "noticeBoard/nBoardList.jsp";
			
		}else if(command.equals("/nBoardContent.do")) { // 공지사항 글 클릭시 상세보기
			service = new NboardContentService();
			service.execute(request, response);
			viewPage = "noticeBoard/nBoardContent.jsp";
			
		}else if(command.equals("/nBoardWriteView.do")) { // 공지게시판 글쓰기 버튼 클릭시 공지게시판 글작성 페이지로
			viewPage = "noticeBoard/nBoardWrite.jsp";
			
		}else if(command.equals("/nBoardWrite.do")) { // 공지게시판 글작성 버튼 클릭시 서비스에서 처리 후 공지글 목록 출력 페이지로
			service = new NboardWriteService();
			service.execute(request, response);
			viewPage = "nBoardList.do";
			
		}else if(command.equals("/nBoardModifyView.do")) { // 공지게시판 글수정하기 버튼 클릭시 서비스에서 처리 후 공지글 수정 페이지로
			service = new NboardModifyViewService();
			service.execute(request, response);
			viewPage = "noticeBoard/nBoardModify.jsp";
			
		}else if(command.equals("/nBoardModify.do")) { // 공지게시판 글수정 버튼 클릭시 서비스에서 처리 후 공지글 목록 출력 페이지로
			service = new NboardModifyService();
			service.execute(request, response);
			viewPage = "nBoardList.do";
			
		}else if(command.equals("/nBoardDelete.do")) { // 공지게시판 글삭제 버튼 클릭시 서비스에서 처리 후 공지글 목록 출력 페이지로
			service = new NboardDeleteService();
			service.execute(request, response);
			viewPage = "nBoardList.do";
			
		}else if(command.equals("/nBoardSch.do")) { // 공지게시판 검색시 서비스에서 처리 후 공지글 목록 출력 페이지로
			service = new NboardSchService();
			service.execute(request, response);
			viewPage = "noticeBoard/nBoardSchList.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
