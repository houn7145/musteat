package com.lec.musteat.service;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.RestaurantDao;
import com.lec.musteat.dto.RestaurantDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class RestaurantModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getSession().getServletContext().getRealPath("restaurantPhotoUp");
		int maxSize = 1024*1024*10; // 
		String mainimg = null, dbmainimg = null;
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			mainimg = mRequest.getFilesystemName(param);
			dbmainimg = mRequest.getParameter("dbmainimg");
			if(mainimg==null) {
				mainimg = dbmainimg;
			}
			int rno = Integer.parseInt(mRequest.getParameter("rno"));
			int cno = Integer.parseInt(mRequest.getParameter("cno"));
			String rname = mRequest.getParameter("rname");
			String rcontent = mRequest.getParameter("rcontent");
			String rplace = mRequest.getParameter("rplace");
			String rmenu = mRequest.getParameter("rmenu");
			String rprice = mRequest.getParameter("rprice");
			String rtel = mRequest.getParameter("rtel");
			RestaurantDao rDao = new RestaurantDao();
			RestaurantDto dto = new RestaurantDto(rno, null, cno, rname, rcontent, rplace, mainimg, rtel, rmenu, rprice, 0, null, 0);
			int result = rDao.modifyRestaurant(dto);
			if(result == RestaurantDao.SUCCESS) {
				request.setAttribute("rsModifyResult", "맛집 수정을 완료했습니다.");
			}else {
				request.setAttribute("rsModifyResult", "맛집 수정에 실패했습니다.");
			}
			request.setAttribute("rno", mRequest.getParameter("rno"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
