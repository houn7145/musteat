package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.RestaurantDao;
import com.lec.musteat.dto.RavgDto;

public class RestaurantAddRatingService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rno = Integer.parseInt(request.getParameter("rno"));
		String ratingStr = request.getParameter("rating");
		if(ratingStr == null) ratingStr = "0";
		int rating = Integer.parseInt(ratingStr);
		RestaurantDao rDao = new RestaurantDao();
		rDao.avgHitUp(rno);
		RavgDto dto = new RavgDto(0, rno, rating);
		int result = rDao.insertRavg(dto);
		if(result == RestaurantDao.SUCCESS) {
			request.setAttribute("addRatingResult", rating + "점을 등록하였습니다.");
		}else {
			request.setAttribute("addRatingResult", "평점 등록에 실패했습니다.");
		}
		request.setAttribute("rno", rno);
	}
}
