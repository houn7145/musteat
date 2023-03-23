package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.CcodeDao;

public class RestaurantCcodeListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNm");
		if(pageNum == null) pageNum = "1";
<<<<<<< HEAD
		String cnoStr = request.getParameter("cno");
		if(cnoStr == null) cnoStr = "1";
		int cno = Integer.parseInt(cnoStr);
=======
		int cno = Integer.parseInt(request.getParameter("cno"));
>>>>>>> da84813b46552efaab71568f3a20e6ebe0411bf0
		int CurrentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 4, BLOCKSIZE = 5;
		int startRow = (CurrentPage - 1) * PAGESIZE + 1;
		int endRow = startRow + PAGESIZE - 1;
		CcodeDao cDao = new CcodeDao();
		request.setAttribute("restaurants", cDao.getCcodeRestaurantList(cno, startRow, endRow));
		int totCnt = cDao.getRestaurantTotCnt(cno);
		int pageCnt = (int)Math.ceil((double)totCnt / PAGESIZE);
		int startPage = ((CurrentPage - 1) / BLOCKSIZE) * BLOCKSIZE + 1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE); 
		request.setAttribute("startPage", startPage); 
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt); 
		request.setAttribute("pageNum", CurrentPage); 
	}
}
