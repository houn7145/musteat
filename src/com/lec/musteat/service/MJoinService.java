package com.lec.musteat.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.musteat.dao.MemberDao;
import com.lec.musteat.dto.MemberDto;

public class MJoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Date mbirth = null; 
		String mname = request.getParameter("mname");
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String memail = request.getParameter("memail");
		String mtel = request.getParameter("mtel");
		String birth = request.getParameter("mbirth");
		if(!birth.equals("")) {
			mbirth = Date.valueOf(birth);
		}
		MemberDao mDao = new MemberDao();
		int result = mDao.confirmId(mid);
		if (result == MemberDao.MEMBER_NONEEXISTENT) {
			MemberDto dto = new MemberDto(mid, mpw, mname, memail, mtel, mbirth, null);
			result = mDao.joinMember(dto);
			HttpSession session = request.getSession();
			session.setAttribute("mid", mid);
			request.setAttribute("joinResult", "회원가입이 완료되었습니다.");
			
		}
	}
}
