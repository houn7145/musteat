package com.lec.musteat.service;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.musteat.dao.RestaurantDao;
import com.lec.musteat.dto.MemberDto;
import com.lec.musteat.dto.RestaurantDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class RestaurantAddService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getSession().getServletContext().getRealPath("restaurantPhotoUp");
		int maxSize = 1024 * 1024 * 10; // 최대 업로드 용량 : 10mb
		String mainimg = null; 
		MultipartRequest mRequest = null;
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> paramNames = mRequest.getFileNames();
			String param = paramNames.nextElement();
			mainimg = mRequest.getFilesystemName(param);
			HttpSession session = request.getSession();
			MemberDto member = (MemberDto)session.getAttribute("member");
			if(member != null) {
				String mid = member.getMid();
				String rname = mRequest.getParameter("rname");
				int cno = Integer.parseInt(mRequest.getParameter("cno"));
				String rcontent = mRequest.getParameter("rcontent");
				String rplace = mRequest.getParameter("rplace");
				String rmenu = mRequest.getParameter("rmenu");
				String rprice = mRequest.getParameter("rprice");
				String rtel = mRequest.getParameter("rtel");
				RestaurantDao rDao = new RestaurantDao();
				RestaurantDto dto = new RestaurantDto(0, mid, cno, rname, rcontent, rplace, mainimg, rtel, rmenu, rprice, 0, null, 0);
				int result = rDao.insertRestaurant(dto);
				if(result == RestaurantDao.SUCCESS) {
					request.setAttribute("addRestaurantResult", "맛집을 등록하였습니다.");
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
