package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.NoticeBoardDao;

public class NboardModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nno = Integer.parseInt(request.getParameter("nno"));
		NoticeBoardDao nDao = new NoticeBoardDao();
		request.setAttribute("nboard", nDao.modifyViewBoard(nno));
	}
}
