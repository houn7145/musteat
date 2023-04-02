package com.lec.musteat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.musteat.dao.CcodeDao;

public class CcodeListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		CcodeDao cDao = new CcodeDao();
		request.setAttribute("Ccodes", cDao.listCcode());
	}
}
