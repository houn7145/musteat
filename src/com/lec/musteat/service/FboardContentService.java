package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.FreeBoardDao;
import com.lec.musteat.dto.FreeBoardDto;

public class FboardContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		String ftitle = request.getParameter("ftitle");
		if(pageNum == null) {
			if(request.getAttribute("pageNum") != null) {
				pageNum = (String) request.getAttribute("pageNum");
			}else {
				pageNum = "1";
			}
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 10, BLOCKSIZE = 5;
		int startRow = (currentPage - 1) * PAGESIZE + 1;
		int endRow 	 = startRow + PAGESIZE - 1;
		FreeBoardDao fDao = new FreeBoardDao();
		request.setAttribute("schFreeBoardList", fDao.getSchFreeBoard(ftitle, startRow, endRow));
		int totCnt = fDao.getFreeBoardSchTotCnt(ftitle);
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage - 1) / BLOCKSIZE) * BLOCKSIZE + 1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage > pageCnt) endPage = pageCnt;
		request.setAttribute("pageNum", currentPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("ftitle", ftitle);
		
		int fno = Integer.parseInt(request.getParameter("fno"));
		FreeBoardDto dto = fDao.getFreeBoard(fno);
		request.setAttribute("fBoardContent", dto);
	}
}
