package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.NoticeBoardDao;

public class NboardDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nno = Integer.parseInt(request.getParameter("nno"));
		NoticeBoardDao nDao = new NoticeBoardDao();
		int result = nDao.deleteNoticeBoard(nno);
		if(result == NoticeBoardDao.SUCCESS) {
			request.setAttribute("boaredResult", "삭제가 완료되었습니다.");
		}
	}
}
