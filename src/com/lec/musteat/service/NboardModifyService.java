package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.NoticeBoardDao;
import com.lec.musteat.dto.NoticeBoardDto;

public class NboardModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nno = Integer.parseInt(request.getParameter("nno"));
		String aid = request.getParameter("aid");
		String aname = request.getParameter("aname");
		String ntitle = request.getParameter("ntitle");
		String ncontent = request.getParameter("ncontent");
		NoticeBoardDao nDao = new NoticeBoardDao();
		NoticeBoardDto dto = new NoticeBoardDto(nno, aid, ntitle, ncontent, null, 0, aname);
		int result = nDao.modifyNoticeBoard(dto);
		if(result == NoticeBoardDao.SUCCESS) {
			request.setAttribute("nBoardModifyResult", "글수정 완료");
		}else {
			request.setAttribute("nBoardModifyResult", "글수정 실패");
		}
		request.setAttribute("nno", nno);
		
	}
}
