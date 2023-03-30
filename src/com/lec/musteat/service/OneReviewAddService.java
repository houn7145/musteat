package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.musteat.dao.OneReviewDao;
import com.lec.musteat.dto.OneReviewDto;

public class OneReviewAddService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rno = Integer.parseInt(request.getParameter("rno"));
		String mid = request.getParameter("mid");
		String ocontent = request.getParameter("ocontent");
		OneReviewDao oDao = new OneReviewDao();
		OneReviewDto dto = new OneReviewDto(0, rno, mid, ocontent, 0);
		oDao.insertOneReview(dto);
		request.setAttribute("rno", rno);
	}
}
