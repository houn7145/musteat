package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.MemberDao;

public class MemailConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String memail = request.getParameter("memail");
		MemberDao mDao = new MemberDao();
		int result = mDao.confirmEmail(memail);
		if(result == MemberDao.MEMBER_EXISTENT) {
			request.setAttribute("memailConfirmResult", "<b>중복된 메일입니다.</b>");
		}else {
			request.setAttribute("memailConfirmResult", "사용 가능한 메일입니다.");
		}
	}
}
