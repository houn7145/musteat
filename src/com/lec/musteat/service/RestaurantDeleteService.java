package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.RestaurantDao;

public class RestaurantDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rno = Integer.parseInt(request.getParameter("rno"));
		RestaurantDao rDao = new RestaurantDao();
		int result = rDao.DeleteRestaurant(rno);
		if(result == RestaurantDao.SUCCESS) {
			request.setAttribute("restaurantDeleteResult", "삭제가 완료되었습니다.");
		}else {
			request.setAttribute("restaurantDeleteResult", "삭제를실패했습니다.");
		}
	}
}
