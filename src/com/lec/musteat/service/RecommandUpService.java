package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.OneReviewDao;

public class RecommandUpService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int ono = Integer.parseInt(request.getParameter("ono"));
		int rno = Integer.parseInt(request.getParameter("rno"));
		OneReviewDao oDao = new OneReviewDao();
		oDao.recommandUp(ono);
		request.setAttribute("recommand", oDao.getRecommandTotCnt(ono));
		request.setAttribute("rno", rno);
	}
}
