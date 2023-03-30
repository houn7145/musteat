package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.FreeBoardDao;

public class FboardDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fgroup = Integer.parseInt(request.getParameter("fgroup"));
		int fstep = Integer.parseInt(request.getParameter("fstep"));
		int findent = Integer.parseInt(request.getParameter("findent"));
		FreeBoardDao fDao = new FreeBoardDao();
		int deleteCnt = fDao.deleteBoard(fgroup, fstep, findent);
		if(deleteCnt >= FreeBoardDao.SUCCESS) {
			request.setAttribute("fBoaredDeleteResult", "삭제가 완료되었습니다.");
		}
	}
}
