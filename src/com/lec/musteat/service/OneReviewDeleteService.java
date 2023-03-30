package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.OneReviewDao;

public class OneReviewDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int ono = Integer.parseInt(request.getParameter("ono"));
		int rno = Integer.parseInt(request.getParameter("rno"));
		OneReviewDao oDao = new OneReviewDao();
		oDao.deleteOneReview(ono);
		request.setAttribute("orResult", "한줄평을 삭제했습니다.");
		request.setAttribute("rno", rno);
	}
}
