package com.lec.musteat.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.musteat.dao.FreeBoardDao;
import com.lec.musteat.dto.FreeBoardDto;
import com.lec.musteat.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FboardWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getSession().getServletContext().getRealPath("freeBoardPhotoUp");
		int maxSize = 1024 * 1024 * 5;
		String[] fimage = {"", ""};
		MultipartRequest mRequest = null;
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> paramNames = mRequest.getFileNames();
			int i = 0;
			while(paramNames.hasMoreElements()) {
				String param = paramNames.nextElement();
				fimage[i] = mRequest.getFilesystemName(param);
				i++;
			}
			HttpSession httpSession = request.getSession();
			MemberDto member = (MemberDto)httpSession.getAttribute("member");
			if(member != null) {
				String mid = mRequest.getParameter("mid");
				String ftitle = mRequest.getParameter("ftitle");
				String fcontent = mRequest.getParameter("fcontent");
				String mname = mRequest.getParameter("mname");
				FreeBoardDao bDao = new FreeBoardDao();
				FreeBoardDto dto = new FreeBoardDto(0, mid, ftitle, fcontent, fimage[1], fimage[0], null, 0, 0, 0, 0, mname);
				int result = bDao.insertFreeBoard(dto);
				if(result == FreeBoardDao.SUCCESS){
					request.setAttribute("fBoardResult", "글쓰기 완료");
				}else {
					request.setAttribute("fBoardResult", "글쓰기 실패");
				}
			}else {
				request.setAttribute("fBoardResult", "글쓰기는 로그인한 회원만 가능합니다.");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		// 파일 복사 
		for(String file : fimage){
			if(file!=null) {
				InputStream  is = null;
				OutputStream os = null;
				try {
					File serverFile = new File(path+"/"+file);
					is = new FileInputStream(serverFile);
					os = new FileOutputStream("C:/houn/1st_project/Must-Eat/MustEat/WebContent/freeBoardPhotoUp/"+ file);
					byte[] bs = new byte[(int)serverFile.length()];
					while(true) {
						int nByteCnt = is.read(bs);
						if(nByteCnt==-1) break;
						os.write(bs, 0, nByteCnt);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {
					try {
						if(os!=null) os.close();
						if(is!=null) is.close();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} // try
			}// 파일 복사 if
		}
	}
}
