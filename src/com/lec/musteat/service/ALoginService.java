package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.musteat.dao.AdminDao;
import com.lec.musteat.dto.AdminDto;

public class ALoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String aid = request.getParameter("aid");
		String apw = request.getParameter("apw");
		AdminDao aDao = new AdminDao();
		int result = aDao.loginChk(aid, apw);
		if(result == AdminDao.LOGIN_SUCCESS) {
			AdminDto admin = aDao.getAdmin(aid);
			session.setAttribute("admin", admin);
		}else {
			request.setAttribute("loginErrorMsg", "아이디와 비밀번호를 확인하세요.");
		}
	}
}
