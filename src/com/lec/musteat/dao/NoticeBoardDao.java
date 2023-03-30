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

import com.lec.musteat.dto.NoticeBoardDto;

public class NoticeBoardDao {
	public static final int SUCCESS = 1; // 등록 성공;
	public static final int FAIL = 0; // 등록 실패;
	private DataSource ds;

	public NoticeBoardDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}

	// -- 1. 공지게시판 글 등록
	public int insertNotice(NoticeBoardDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO NOTICEBOARD (NNO, AID, NTITLE, NCONTENT)"
				+ "    VALUES (NOTICE_SEQ.NEXTVAL, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAid());
			pstmt.setString(2, dto.getNtitle());
			pstmt.setString(3, dto.getNcontent());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("공지게시판 글 등록 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "공지게시판 글 등록 실패");
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

	// -- 2-1. 공지게시판 페이징
	public ArrayList<NoticeBoardDto> getNoticeBoardList(int startRow, int endRow) {
		ArrayList<NoticeBoardDto> dtos = new ArrayList<NoticeBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT N.*, A.ANAME FROM NOTICEBOARD N, ADMIN A WHERE N.AID = A.AID ORDER BY NRDATE DESC)A)\r\n" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int nno = rs.getInt("nno");
				String aid = rs.getString("aid");
				String ntitle = rs.getString("ntitle");
				String ncontent = rs.getString("ncontent");
				Timestamp nrdate = rs.getTimestamp("nrdate");
				int nhit = rs.getInt("nhit");
				String aname = rs.getString("aname");
				dtos.add(new NoticeBoardDto(nno, aid, ntitle, ncontent, nrdate, nhit, aname));
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

	// -- 2-2. 페이징시 필요한 등록한 공지글 수
	public int getNoticeBoardTotCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM NOTICEBOARD";
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
	private void hitUp(int nno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICEBOARD SET NHIT = NHIT + 1" + "    WHERE NNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " 조회수 up 실패");
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

	// -- 4-1. 공지글 상세보기 - 조회수 1업
	public NoticeBoardDto getNoticeBoard(int nno) {
		hitUp(nno); // 조회수 1업
		NoticeBoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT N.*, A.ANAME FROM NOTICEBOARD N, ADMIN A WHERE N.AID = A.AID AND NNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aid = rs.getString("aid");
				String ntitle = rs.getString("ntitle");
				String ncontent = rs.getString("ncontent");
				Timestamp nrdate = rs.getTimestamp("nrdate");
				int nhit = rs.getInt("nhit");
				String aname = rs.getString("aname");
				dto = new NoticeBoardDto(nno, aid, ntitle, ncontent, nrdate, nhit, aname);
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

	// -- 4-2. 공지글 상세보기 - 수정View
	public NoticeBoardDto modifyViewBoard(int nno) {
		NoticeBoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT N.*, A.ANAME FROM NOTICEBOARD N, ADMIN A WHERE N.AID = A.AID AND NNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aid = rs.getString("aid");
				String ntitle = rs.getString("ntitle");
				String ncontent = rs.getString("ncontent");
				Timestamp nrdate = rs.getTimestamp("nrdate");
				int nhit = rs.getInt("nhit");
				String aname = rs.getString("aname");
				dto = new NoticeBoardDto(nno, aid, ntitle, ncontent, nrdate, nhit, aname);
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

	// -- 5. 공지글 수정
	public int modifyNoticeBoard(NoticeBoardDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICEBOARD SET NTITLE = ?," + "                       NCONTENT = ?" + "    WHERE NNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNtitle());
			pstmt.setString(2, dto.getNcontent());
			pstmt.setInt(3, dto.getNno());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("공지글 수정 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "공지글 수정 실패");
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
	
	// -- 6. 공지글 삭제
	public int deleteNoticeBoard(int nno) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM NOTICEBOARD WHERE NNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("공지게시판 글 삭제 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "공지게시판 글 삭제 실패");
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
	
	// -- 7. 공지게시판 검색
	public ArrayList<NoticeBoardDto> getSchNoticeBoard(String ntitle, int startRow, int endRow) {
		ArrayList<NoticeBoardDto> dtos = new ArrayList<NoticeBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT N.*, A.ANAME FROM NOTICEBOARD N, ADMIN A WHERE N.AID = A.AID AND NTITLE LIKE '%' || ? || '%' ORDER BY NRDATE DESC)A)\r\n" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ntitle);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int nno = rs.getInt("nno");
				String aid = rs.getString("aid");
				ntitle = rs.getString("ntitle");
				String ncontent = rs.getString("ncontent");
				Timestamp nrdate = rs.getTimestamp("nrdate");
				int nhit = rs.getInt("nhit");
				String aname = rs.getString("aname");
				dtos.add(new NoticeBoardDto(nno, aid, ntitle, ncontent, nrdate, nhit, aname));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
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
	
	// --8. 공지게시판 검색 페이징시 필요한 글 갯수
	
	public int getNoticeBoardSchTotCnt(String ntitle) {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM NOTICEBOARD WHERE NTITLE LIKE '%' || ? || '%'";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ntitle);
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
