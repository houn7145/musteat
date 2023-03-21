package com.lec.musteat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lec.musteat.dto.CcodeDto;
import com.lec.musteat.dto.RestaurantDto;

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
	public ArrayList<CcodeDto> listCcode() {
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
	
	// -- 4. 메인페이지 카테고리 별 맛집 출력 - 페이징
	public ArrayList<RestaurantDto> getCcodeRestaurantList(int cno, int startRow, int endRow) {
		ArrayList<RestaurantDto> Restaurants = new ArrayList<RestaurantDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM RESTAURANT WHERE CNO = ? ORDER BY RRDATE DESC)A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cno);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rno = rs.getInt("rno");
				String mid = rs.getString("mid");
				String rname = rs.getString("rname");
				String rcontent = rs.getString("rcontent");
				String rplace = rs.getString("rcontent");
				String mainimg = rs.getString("mainimg");
				String subimg1 = rs.getString("subimg1");
				String subimg2 = rs.getString("subimg2");
				String rtel = rs.getString("rtel");
				String rmenu = rs.getString("rmenu");
				String rprice = rs.getString("rprice");
				int rhit = rs.getInt("rhit");
				Timestamp rrdate = rs.getTimestamp("rrdate");
				int avghit = rs.getInt("avghit");
				Restaurants.add(new RestaurantDto(rno, mid, cno, rname, rcontent, rplace, mainimg, subimg1, subimg2,
						rtel, rmenu, rprice, rhit, rrdate, avghit));
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
		return Restaurants;
	}
	
	// -- 4-2. 카테고리 별 맛집 출력시 필요한 맛집 갯수
	public int getRestaurantTotCnt(int cno) {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM RESTAURANT WHERE CNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cno);
			rs = pstmt.executeQuery();
			rs.next();
			totCnt = rs.getInt(1);
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
		return totCnt;
	}
}
