package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.FreeBoardDao;
import com.lec.musteat.dto.FreeBoardDto;

public class FboardReplyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fno = Integer.parseInt(request.getParameter("fno"));
		FreeBoardDao fDao = new FreeBoardDao();
		FreeBoardDto originfBoard = fDao.modifyViewBoard_replyViewBoard(fno);
		request.setAttribute("originfBoard", originfBoard);
	}
}
