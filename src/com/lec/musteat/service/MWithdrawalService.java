package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.musteat.dao.MemberDao;
import com.lec.musteat.dto.MemberDto;

public class MWithdrawalService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String mid = null;
		MemberDto sessionMember = (MemberDto)session.getAttribute("member");
		if(sessionMember != null) {
			mid = sessionMember.getMid();
		}
		MemberDao mDao = new MemberDao();
		int result = mDao.withdrawalMember(mid);
		session.invalidate();
		if(result == MemberDao.SUCCESS) {
			request.setAttribute("withdrawalResult", "회원 탈퇴가 완료되었습니다.");
		}else {
			request.setAttribute("withdrawalResult", "로그인 상태가 아닙니다");
		}
	}
}
