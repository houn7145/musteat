package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.RestaurantDao;
import com.lec.musteat.dto.RestaurantDto;

public class RestaurantModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rno = Integer.parseInt(request.getParameter("rno"));
		RestaurantDao rDao = new RestaurantDao();
		RestaurantDto dto = rDao.modifyViewRestaurant(rno);
		request.setAttribute("restaurantModify", dto);
	}
}
