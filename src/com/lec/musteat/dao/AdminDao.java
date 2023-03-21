package com.lec.musteat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lec.musteat.dto.AdminDto;

public class AdminDao {
	public static final int LOGIN_SUCCESS = 1; // 로그인 성공
	public static final int LOGIN_FAIL = 0; // 로그인 실패
	public static final int SUCCESS = 1; // 생성 성공
	public static final int FAIL = 0; // 생성 실패
	private DataSource ds;
	
	public AdminDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// -- 1. ADMIN 로그인 체크
	public int loginChk(String aid, String apw) {
		int result = LOGIN_FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT * FROM ADMIN WHERE AID = ? AND APW = ?";
		try {
			conn 	= ds.getConnection();
			pstmt 	= conn.prepareStatement(sql);
			pstmt.setString(1, aid);
			pstmt.setString(2, apw);
			rs		= pstmt.executeQuery();
			if (rs.next()) {
				result = LOGIN_SUCCESS;
			} else {
				result = LOGIN_FAIL;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	// -- 2. 관리자 로그인 후 세션에 넣을 용도 (DTO 가져오기)
	public AdminDto getAdmin(String aid) {
		AdminDto 			dto   = null;
		Connection 			conn  = null;
		PreparedStatement 	pstmt = null;
		ResultSet 			rs 	  = null;
		String sql = "SELECT * FROM  ADMIN WHERE AID = ?";
		try {
			conn  = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aid);
			rs    = pstmt.executeQuery();
			if (rs.next()) {
				String apw = rs.getString("apw");
				String aname = rs.getString("aname");
				dto = new AdminDto(aid, apw, aname);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}
	
	// -- 3. 관리자 모드에서 관리자 계정 추가
	public int createAdmin(AdminDto dto) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ADMIN (AID, APW, ANAME)" + 
				"    VALUES (?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAid());
			pstmt.setString(2, dto.getApw());
			pstmt.setString(3, dto.getAname());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("관리자 계정 추가 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " 관리자 계정 추가 실패");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
}
