package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.RestaurantDao;

public class restaurantSchListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNm");
		if(pageNum == null) pageNum = "1";
		String rplace = request.getParameter("rplace");
		int CurrentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 5, BLOCKSIZE = 5;
		int startRow = (CurrentPage - 1) * PAGESIZE + 1;
		int endRow = startRow + PAGESIZE - 1;
		RestaurantDao rDao = new RestaurantDao();
		request.setAttribute("restaurantSchList", rDao.getSchRestaurant(rplace, startRow, endRow));
		int totCnt = rDao.getRestaurantSchTotCnt(rplace);
		int pageCnt = (int)Math.ceil((double)totCnt / PAGESIZE);
		int startPage = ((CurrentPage - 1) / BLOCKSIZE) * BLOCKSIZE + 1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("rpalce", rplace);
		request.setAttribute("BLOCKSIZE", BLOCKSIZE); 
		request.setAttribute("startPage", startPage); 
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt); 
		request.setAttribute("pageNum", CurrentPage);
	}
}
