package com.lec.musteat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lec.musteat.dto.CcodeDto;

public class CcodeDao {
	public static final int SUCCESS = 1; // 등록 성공;
	public static final int FAIL = 0; // 등록 실패;
	private DataSource ds;

	public CcodeDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}

	// -- 1. 카테고리 추가
	public int insertCcode(CcodeDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO CCODE (CNO, CNAME)" + "    VALUES (CCODE_SEQ.NEXTVAL, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCname());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("카테고리 등록 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "카테고리 등록 실패");
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

	// -- 2. 카테고리 삭제
	public int deleteCcode(String cname) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM CCODE WHERE CNAME = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cname);
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("카테고리 삭제 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "카테고리 삭제 실패");
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

	// -- 3. 카테고리 출력
	public ArrayList<CcodeDto> listMember() {
		ArrayList<CcodeDto> codes = new ArrayList<CcodeDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM CCODE";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cno = rs.getInt("cno");
				String cname = rs.getString("cname");
				codes.add(new CcodeDto(cno, cname));
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
		return codes;
	}
}
