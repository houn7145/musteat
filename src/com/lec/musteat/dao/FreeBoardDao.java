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
	public int insertFreeBoard(FreeBoardDto dto) {
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
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT M.MNAME, F.* FROM FREEBOARD F, MEMBER M WHERE M.MID = F.MID ORDER BY FGROUP DESC, FSTEP)A)" + 
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
				String mname = rs.getNString("mname");
				String ftitle = rs.getString("ftitle");
				String fcontent = rs.getString("fcontent");
				String fimage1 = rs.getString("fimage1");
				String fimage2 = rs.getString("fimage2");
				Timestamp frdate = rs.getTimestamp("frdate");
				int fhit = rs.getInt("fhit");
				int fgroup = rs.getInt("fgroup");
				int fstep = rs.getInt("fstep");
				int findent = rs.getInt("findent");
				dtos.add(new FreeBoardDto(fno, mid, ftitle, fcontent, fimage1, fimage2, frdate, fhit, fgroup, fstep, findent, mname));
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
	
	// -- 2-2. 페이징시 필요한 자유게시판 글목록 갯수
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
		String sql = "SELECT F.*, M.MNAME FROM FREEBOARD F, MEMBER M WHERE FNO = ? AND F.MID = M.MID";
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
				int    fgroup = rs.getInt("fgroup");
				int    fstep = rs.getInt("fstep");
				int    findent = rs.getInt("findent");
				String mname = rs.getString("mname");
				dto = new FreeBoardDto(fno, mid, ftitle, fcontent, fimage1, fimage2, frdate, fhit, fgroup, fstep, findent, mname);
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
		String sql = "SELECT F.*, M.MNAME FROM FREEBOARD F, MEMBER M WHERE FNO = ? AND F.MID = M.MID";
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
				int    fgroup = rs.getInt("fgroup");
				int    fstep = rs.getInt("fstep");
				int    findent = rs.getInt("findent");
				String mname = rs.getString("mname");
				dto = new FreeBoardDto(fno, mid, ftitle, fcontent, fimage1, fimage2, frdate, fhit, fgroup, fstep, findent, mname);
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
	
	// -- 5. 자유게시판 글 수정
	public int modifyFreeBoard(FreeBoardDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FREEBOARD SET FTITLE = ?," + 
				"                     FCONTENT = ?," + 
				"                     FIMAGE1 = ?," + 
				"                     FIMAGE2 = ?" + 
				"    WHERE FNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getFtitle());
			pstmt.setString(2, dto.getFcontent());
			pstmt.setString(3, dto.getFimage1());
			pstmt.setString(4, dto.getFimage2());
			pstmt.setInt(5, dto.getFno());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("정보수정 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "정보수정 실패");
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
	
	// -- 6-1. 자유게시판 글 삭제
	public int deleteBoard(int fgroup, int fstep, int findent) {
		int deleteCnt = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM FREEBOARD WHERE FGROUP = ? AND (FSTEP>=? AND FSTEP<(select NVL(MIN(FSTEP),9999) FROM FREEBOARD WHERE FGROUP=? AND FSTEP>? AND FINDENT<=?))";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fgroup);
			pstmt.setInt(2, fstep);
			pstmt.setInt(3, fgroup);
			pstmt.setInt(4, fstep);
			pstmt.setInt(5, findent);
			deleteCnt = pstmt.executeUpdate();
			postDelete(deleteCnt, fgroup, fstep); // 글삭제시 fstep 재조정
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return deleteCnt;
	}
	
	//6-2 delete 수행후 fstep 재배열
	private void postDelete(int deleteCnt, int fgroup, int fstep) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FREEBOARD SET fSTEP = fSTEP-? WHERE fGROUP=? AND fSTEP>?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deleteCnt);
			pstmt.setInt(2, fgroup);
			pstmt.setInt(3, fstep);
			int cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
	}
	
	// -- 7-1. 답변글 달기 선행답변글 쓰기 선행 단계(원글의 fgroup과 같고 원글의 fstep보다 크면 fstep을 1 증가)
	private void preReplyBoardStep(int fgroup, int fstep) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FREEBOARD SET FSTEP = FSTEP + 1" + 
				"    WHERE FGROUP = ? AND FSTEP > ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fgroup);
			pstmt.setInt(2, fstep);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " preReplyStep에서 오류");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
	}
	
	// -- 7-2. 답변글 등록
	public int replyFreeBoard(FreeBoardDto dto) {
		int result = FAIL;
		preReplyBoardStep(dto.getFgroup(), dto.getFstep()); // 답변글 선행 실행
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FREEBOARD (FNO, MID, FTITLE, FCONTENT, FIMAGE1, FIMAGE2, FGROUP, FSTEP, FINDENT)" + 
				"    VALUES (FREE_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setString(2, dto.getFtitle());
			pstmt.setString(3, dto.getFcontent());
			pstmt.setString(4, dto.getFimage1());
			pstmt.setString(5, dto.getFimage2());
			pstmt.setInt(6, dto.getFgroup());
			pstmt.setInt(7, dto.getFstep() + 1);
			pstmt.setInt(8, dto.getFindent() + 1);
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("답변글쓰기 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " 답변글쓰기 실패 ");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return result;
	}
	
	// -- 8-1. 자유게시판 글 검색
	public ArrayList<FreeBoardDto> getSchFreeBoard(String ftitle, int startRow, int endRow) {
		ArrayList<FreeBoardDto> dtos = new ArrayList<FreeBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT M.MNAME, F.* FROM FREEBOARD F, MEMBER M WHERE M.MID = F.MID AND F.FTITLE LIKE '%' || ? || '%' ORDER BY FGROUP DESC, FSTEP)A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ftitle);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int fno = rs.getInt("fno");
				String mid = rs.getString("mid");
				ftitle = rs.getString("ftitle");
				String fcontent = rs.getString("fcontent");
				String fimage1 = rs.getString("fimage1");
				String fimage2 = rs.getString("fimage2");
				Timestamp frdate = rs.getTimestamp("frdate");
				int fhit = rs.getInt("fhit");
				int fgroup = rs.getInt("fgroup");
				int fstep = rs.getInt("fstep");
				int findent = rs.getInt("findent");
				String mname = rs.getString("mname");
				dtos.add(new FreeBoardDto(fno, mid, ftitle, fcontent, fimage1, fimage2, frdate, fhit, fgroup, fstep, findent, mname));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs  != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return dtos;
	}
	
	// -- 8-2. 자유게시판 검색시 조회된 글 갯수
	public int getFreeBoardSchTotCnt(String ftitle) {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM FREEBOARD WHERE FTITLE LIKE '%' || ? || '%'";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ftitle);
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
