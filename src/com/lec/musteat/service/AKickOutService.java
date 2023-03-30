package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.MemberDao;

public class AKickOutService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		MemberDao mDao = new MemberDao();
		int result = mDao.withdrawalMember(mid);
		if(result == MemberDao.SUCCESS) {
			request.setAttribute("kickOutResult", "회원 추방이 완료되었습니다.");
		}
		request.setAttribute("mid", mid);
	}
}
