package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.musteat.dao.NoticeBoardDao;
import com.lec.musteat.dto.AdminDto;
import com.lec.musteat.dto.NoticeBoardDto;

public class NboardWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		AdminDto admin = (AdminDto)httpSession.getAttribute("admin");
		if(admin != null) {
			String aid = request.getParameter("aid");
			String aname = request.getParameter("aname");
			String ntitle = request.getParameter("ntitle");
			String ncontent = request.getParameter("ncontent");
			NoticeBoardDao nDao = new NoticeBoardDao();
			NoticeBoardDto dto = new NoticeBoardDto(0, aid, ntitle, ncontent, null, 0, aname);
			int result = nDao.insertNotice(dto);
			if(result == NoticeBoardDao.SUCCESS){
				request.setAttribute("nBoardResult", "글쓰기 완료");
			}else {
				request.setAttribute("nBoardResult", "글쓰기 실패");
			}
		}else {
			request.setAttribute("nBoardResult", "공지사항 글쓰기는 관리자만 가능합니다.");
		}
	}
}
