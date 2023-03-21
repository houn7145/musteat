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

import com.lec.musteat.dto.FreeBoardDto;

public class FreeBoardDao {
	public static final int SUCCESS = 1; // 등록 성공;
	public static final int FAIL = 0; // 등록 실패;
	private DataSource ds;
	
	public FreeBoardDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// -- 1. 자유게시판 글 등록(원글)
	public int insertOneReview(FreeBoardDto dto) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FREEBOARD (FNO, MID, FTITLE, FCONTENT, FIMAGE1, FIMAGE2, FGROUP, FSTEP, FINDENT)" + 
				"    VALUES (FREE_SEQ.NEXTVAL, ?, ?, ?, ?, ?, FREE_SEQ.CURRVAL, 0, 0)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setString(2, dto.getFtitle());
			pstmt.setString(3, dto.getFcontent());
			pstmt.setString(4, dto.getFimage1());
			pstmt.setString(5, dto.getFimage2());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("자유게시판 글 등록 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "자유게시판 글 등록 실패");
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
	
	// -- 2-1. 자유게시판 페이징
	public ArrayList<FreeBoardDto> getFreeBoardList(int startRow, int endRow) {
		ArrayList<FreeBoardDto> dtos = new ArrayList<FreeBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM FREEBOARD ORDER BY FGROUP DESC, FSTEP)A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int fno = rs.getInt("fno");
				String mid = rs.getString("mid");
				String ftitle = rs.getString("ftitle");
				String fcontent = rs.getString("fcontent");
				String fimage1 = rs.getString("fimage1");
				String fimage2 = rs.getString("fimage2");
				Timestamp frdate = rs.getTimestamp("frdate");
				int fhit = rs.getInt("fhit");
				int fgroup = rs.getInt("fgroup");
				int fstep = rs.getInt("fstep");
				int findent = rs.getInt("findent");
				dtos.add(new FreeBoardDto(fno, mid, ftitle, fcontent, fimage1, fimage2, frdate, fhit, fgroup, fstep, findent));
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
		return dtos;
	}
	
	// -- 2-2. 페이징시 필요한 자유게시판 글목록
	public int getFreeBoardTotCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT (*) FROM FREEBOARD";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
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
	
	// -- 3. 조회수 1 올리기
	private void hitUp(int fno) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FREEBOARD SET FHIT = FHIT + 1" + 
				"    WHERE FNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " 조회수 up 실패");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
	}
	
	// -- 4-1. 자유게시판 글 상세보기
	public FreeBoardDto getFreeBoard(int fno) {
		FreeBoardDto dto = null;
		hitUp(fno); // 글 상세보기 시 조회수 1 올리기
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM FREEBOARD WHERE FNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String mid = rs.getString("mid");
				String ftitle = rs.getString("ftitle");
				String fcontent = rs.getString("fcontent");
				String fimage1 = rs.getString("fimage1");
				String fimage2 = rs.getString("fimage2");
				Timestamp frdate = rs.getTimestamp("frdate");
				int    fhit = rs.getInt("fhit");
				int    fgroup= rs.getInt("fgroup");
				int    fstep= rs.getInt("fstep");
				int    findent= rs.getInt("findent");
				dto = new FreeBoardDto(fno, mid, ftitle, fcontent, fimage1, fimage2, frdate, fhit, fgroup, fstep, findent);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return dto;
	}
	
	// -- 4-2. 자유게시판 글 상세보기 - 글수정VIEW, 답변글VIEW 용
	public FreeBoardDto modifyViewBoard_replyViewBoard(int fno) {
		FreeBoardDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM FREEBOARD WHERE FNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String mid = rs.getString("mid");
				String ftitle = rs.getString("ftitle");
				String fcontent = rs.getString("fcontent");
				String fimage1 = rs.getString("fimage1");
				String fimage2 = rs.getString("fimage2");
				Timestamp frdate = rs.getTimestamp("frdate");
				int    fhit = rs.getInt("fhit");
				int    fgroup= rs.getInt("fgroup");
				int    fstep= rs.getInt("fstep");
				int    findent= rs.getInt("findent");
				dto = new FreeBoardDto(fno, mid, ftitle, fcontent, fimage1, fimage2, frdate, fhit, fgroup, fstep, findent);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return dto;
	}
}
