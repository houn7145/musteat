package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.CcodeDao;
import com.lec.musteat.dao.RestaurantDao;

public class RestaurantCcodeListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNm");
		if(pageNum == null) pageNum = "1";
		String cname = null;
		String cnoStr = request.getParameter("cno");
		if(cnoStr == null) cnoStr = "1";
		int cno = Integer.parseInt(cnoStr);
		switch(cno) {
		case 1 : cname = "한식"; break;
		case 2 : cname = "일식"; break;
		case 3 : cname = "중식"; break;
		case 4 : cname = "양식"; break;
		case 5 : cname = "카페/디저트"; break;
		case 6 : cname = "기타"; break;
		}
		CcodeDao cDao = new CcodeDao();
		request.setAttribute("restaurants", cDao.getCcodeRestaurantList(cno));
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("cname", cname);
		RestaurantDao rDao = new RestaurantDao();
		request.setAttribute("restauranRavgList", rDao.getRavgRestaurant());
	}
}
