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

import com.lec.musteat.dto.OneReviewDto;

public class OneReviewDao {
	public static final int SUCCESS = 1; // 등록 성공;
	public static final int FAIL = 0; // 등록 실패;
	private DataSource ds;
	
	public OneReviewDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// -- 1. 한줄평 등록
	public int insertOneReview(OneReviewDto dto) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ONEREVIEW (ONO, RNO, MID, OCONTENT)" + 
				"    VALUES (ONE_SEQ.NEXTVAL, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getRno());
			pstmt.setString(2, dto.getMid());
			pstmt.setString(3, dto.getOcontent());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("한줄평 등록 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "한줄평 등록 실패");
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
	
	// -- 2. 한줄평 삭제
	public int deleteOneReview(int ono) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM ONEREVIEW WHERE ONO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ono);
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("한줄평 삭제 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "한줄평 삭제 실패");
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
	
	// -- 3. 맛집 한줄평 출력
	public ArrayList<OneReviewDto> getOneReviewList(int rno){
		ArrayList<OneReviewDto> dtos = new ArrayList<OneReviewDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT O.*, M.MNAME FROM ONEREVIEW O, MEMBER M WHERE O.MID = M.MID AND RNO = ? ORDER BY ORECOMMAND DESC";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int ono = rs.getInt("ono");
				String mid = rs.getString("mid");
				String ocontent = rs.getString("ocontent");
				int orecommand = rs.getInt("orecommand");
				String mname = rs.getString("mname");
				dtos.add(new OneReviewDto(ono, rno, mid, ocontent, orecommand, mname));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return dtos;
	}
	
	// -- 4. 좋아요 클릭
	public void recommandUp(int ono) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ONEREVIEW SET ORECOMMAND = ORECOMMAND + 1" + 
				"    WHERE ONO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ono);
			pstmt.executeUpdate();
			System.out.println("추천 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "추천 실패");
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
	}
	
	// -- 5. 싫어요 클릭
	public void recommandDown(int ono) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ONEREVIEW SET ORECOMMAND = ORECOMMAND - 1" + 
				"    WHERE ONO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ono);
			pstmt.executeUpdate();
			System.out.println("비추천 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "비추천 실패");
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
	}
	
	// -- 6. ajax로 보낼 추천 수
	public int getRecommandTotCnt(int ono) {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT ORECOMMAND FROM ONEREVIEW WHERE ONO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ono);
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
