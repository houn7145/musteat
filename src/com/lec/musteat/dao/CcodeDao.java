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

	// -- 1. 메인페이지 카테고리 별 맛집 출력 - 페이징
	public ArrayList<RestaurantDto> getCcodeRestaurantList(int cno) {
		ArrayList<RestaurantDto> Restaurants = new ArrayList<RestaurantDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT R.*, C.CNAME, (SELECT AVG(RATING) FROM RAVG A WHERE A.RNO = R.RNO)RAVG FROM RESTAURANT R, CCODE C WHERE C.CNO = ? AND C.CNO = R.CNO ORDER BY RAVG) A)" + 
				" WHERE RN BETWEEN 1 AND 5";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rno = rs.getInt("rno");
				String mid = rs.getString("mid");
				String rname = rs.getString("rname");
				String rcontent = rs.getString("rcontent");
				String rplace = rs.getString("rplace");
				String mainimg = rs.getString("mainimg");
				String rtel = rs.getString("rtel");
				String rmenu = rs.getString("rmenu");
				String rprice = rs.getString("rprice");
				int rhit = rs.getInt("rhit");
				Timestamp rrdate = rs.getTimestamp("rrdate");
				int avghit = rs.getInt("avghit");
				int ravg = rs.getInt("ravg");
				String cname = rs.getString("cname");
				Restaurants.add(new RestaurantDto(rno, mid, cno, rname, rcontent, rplace, mainimg,
						rtel, rmenu, rprice, rhit, rrdate, avghit, ravg, cname));
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
	
	// -- 2. 카테고리 별 맛집 출력시 필요한 맛집 갯수
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
