package com.lec.musteat.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.OneReviewDao;
import com.lec.musteat.dao.RestaurantDao;
import com.lec.musteat.dto.OneReviewDto;
import com.lec.musteat.dto.RestaurantDto;

public class RestaurantContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String rnoStr = request.getParameter("rno");
		if(rnoStr == null) {
			if(request.getAttribute("rno") != null) {
				rnoStr = (String) request.getAttribute("rno");
			}
		}
		int rno = Integer.parseInt(rnoStr);
		RestaurantDao rDao = new RestaurantDao();
		RestaurantDto dto = rDao.getRestaurant(rno);
		request.setAttribute("rContent", dto);
		
		// oneReview
		OneReviewDao oDao = new OneReviewDao();
		ArrayList<OneReviewDto> oDto = oDao.getOneReviewList(rno);
		request.setAttribute("oneReview", oDto);
	}
}
