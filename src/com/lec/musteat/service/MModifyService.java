package com.lec.musteat.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.musteat.dao.MemberDao;
import com.lec.musteat.dto.MemberDto;

public class MModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		String mname = request.getParameter("mname");
		String dbMpw = request.getParameter("dbMpw");
		String mpw = request.getParameter("mpw");
		if(mpw.equals("")) { // 새 비밀번호를 입력하지 않았을 경우 db에 있는 현 비밀번호로 교체
			mpw = dbMpw;
		}
		String memail = request.getParameter("memail");
		String mtel = request.getParameter("mtel");
		Date mbirth = null;
		String birth = request.getParameter("mbirth");
		if(!birth.equals("")) {
			mbirth = Date.valueOf(birth);
		}
		MemberDao mDao = new MemberDao();
		MemberDto member = new MemberDto(mid, mpw, mname, memail, mtel, mbirth, null);
		int result = mDao.modifyMember(member);
		if(result == MemberDao.SUCCESS) { // 수정 성공시 session도 수정
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			request.setAttribute("modifyResult", "회원정보 수정 성공");
		}else { // 수정 실패
			request.setAttribute("modifyResult", "회원정보 수정 실패");
		}
	}
}
