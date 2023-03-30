package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.MemberDao;

public class MidConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		MemberDao mDao = new MemberDao();
		int result = mDao.confirmId(mid);
		if(result == MemberDao.MEMBER_EXISTENT) {
			request.setAttribute("midConfirmResult", "<b>중복된 ID입니다.</b>");
		}else{
			request.setAttribute("midConfirmResult", "사용 가능한 ID입니다.");
		}
	}
}
