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

import com.lec.musteat.dto.RavgDto;
import com.lec.musteat.dto.RestaurantDto;

public class RestaurantDao {
	public static final int SUCCESS = 1; // 등록 성공;
	public static final int FAIL = 0; // 등록 실패;
	private DataSource ds;

	public RestaurantDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}

	// -- 1-1. 맛집 리스트 보기 - 페이징
	public ArrayList<RestaurantDto> getRestaurantList(int startRow, int endRow) {
		ArrayList<RestaurantDto> Restaurants = new ArrayList<RestaurantDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM RESTAURANT ORDER BY RRDATE DESC)A)"
				+ "    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rno = rs.getInt("rno");
				String mid = rs.getString("mid");
				int cno = rs.getInt("cno");
				String rname = rs.getString("rname");
				String rcontent = rs.getString("rcontent");
				String rplace = rs.getString("rcontent");
				String mainimg = rs.getString("mainimg");
				String rtel = rs.getString("rtel");
				String rmenu = rs.getString("rmenu");
				String rprice = rs.getString("rprice");
				int rhit = rs.getInt("rhit");
				Timestamp rrdate = rs.getTimestamp("rrdate");
				int avghit = rs.getInt("avghit");
				Restaurants.add(new RestaurantDto(rno, mid, cno, rname, rcontent, rplace, mainimg, rtel, rmenu, rprice, rhit, rrdate, avghit));
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

	// -- 1-2. 페이징시 필요한 등록한 맛집 갯수
	public int getRestaurantTotCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM RESTAURANT";
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

	// -- 2. 맛집 등록
	public int insertRestaurant(RestaurantDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)"
				+ "    VALUES (RES_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setInt(2, dto.getCno());
			pstmt.setString(3, dto.getRplace());
			pstmt.setString(4, dto.getRname());
			pstmt.setString(5, dto.getRcontent());
			pstmt.setString(6, dto.getMainimg());
			pstmt.setString(7, dto.getRtel());
			pstmt.setString(8, dto.getRmenu());
			pstmt.setString(9, dto.getRprice());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("등록 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "등록 실패");
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

	// -- 3. 등록한 맛집 수정
	public int modifyRestaurant(RestaurantDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE RESTAURANT SET CNO = ?," + "                      RPLACE = ?,"
				+ "                      RNAME = ?," + "                      RCONTENT = ?,"
				+ "                      MAINIMG = ?," + "                     RTEL = ?,"
				+ "					   RMENU = ?," + "                      RPRICE = ?" + "    WHERE RNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getCno());
			pstmt.setString(2, dto.getRplace());
			pstmt.setString(3, dto.getRname());
			pstmt.setString(4, dto.getRcontent());
			pstmt.setString(5, dto.getMainimg());
			pstmt.setString(6, dto.getRtel());
			pstmt.setString(7, dto.getRmenu());
			pstmt.setString(8, dto.getRprice());
			pstmt.setInt(9, dto.getRno());
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

	// -- 4. 등록한 맛집 삭제
	public int DeleteRestaurant(int rno) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM RESTAURANT WHERE RNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("맛집삭제 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "맛집삭제 실패");
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

	// -- 5-1. 평점 등록
	public int insertRavg(RavgDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO RAVG (ANO, RNO, RATING)" + "    VALUES (RAVG_SEQ.NEXTVAL, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getRno());
			pstmt.setDouble(2, dto.getrating());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("평점등록 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "평점등록 실패");
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

	// -- 5-2. 평점 등록 횟수 업데이트
	public void avgHitUp(int rno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE RESTAURANT SET AVGHIT = AVGHIT + 1" + "    WHERE RNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			pstmt.executeUpdate();
			System.out.println("횟수 업데이트 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "횟수 업데이트 실패");
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

	// -- 6. 평점 출력
	public int avg(int rno) {
		int avgResult = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT (AVG(RATING) FROM RAVG WHERE RNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			pstmt.setInt(1, rno);
			rs.next();
			avgResult = rs.getInt(1);
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
		return avgResult;
	}

	// -- 7. 조회수 업
	private void hitUp(int rno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE RESTAURANT SET RHIT = RHIT + 1" + "    WHERE RNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
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

	// -- 8. 맛집 클릭시 상세보기 - 맛집 수정View
	public RestaurantDto modifyViewRestaurant(int rno) {
		RestaurantDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM RESTAURANT WHERE RNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mid = rs.getString("mid");
				int cno = rs.getInt("cno");
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
				dto = new RestaurantDto(rno, mid, cno, rname, rcontent, rplace, mainimg, rtel, rmenu,
						rprice, rhit, rrdate, avghit);
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

	// -- 8. 맛집 클릭시 상세보기 - 조회수 1 up
	public RestaurantDto getRestaurant(int rno) {
		hitUp(rno); // 조회수 1 up
		RestaurantDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT R.*, C.CNAME, (SELECT AVG(RATING) FROM RAVG A WHERE A.RNO = R.RNO)RAVG FROM RESTAURANT R, CCODE C WHERE C.CNO = R.CNO AND RNO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mid = rs.getString("mid");
				int cno = rs.getInt("cno");
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
				String cname = rs.getString("cname");
				double ravg = rs.getDouble("ravg");
				dto = new RestaurantDto(rno, mid, cno, rname, rcontent, rplace, mainimg, rtel, rmenu, rprice, rhit, rrdate, avghit, ravg, cname);
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

	// -- 9-1. 맛집 지역으로 검색(페이징)
	public ArrayList<RestaurantDto> getSchRestaurant(String rplace, int startRow, int endRow) {
		ArrayList<RestaurantDto> dtos = new ArrayList<RestaurantDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT RESTAURANT.*, (SELECT AVG(RATING) FROM RAVG WHERE RNO=RESTAURANT.RNO)RAVG FROM RESTAURANT WHERE RPLACE LIKE '%' || ? || '%' ORDER BY RAVG) A)"
				+ " WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rplace);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rno = rs.getInt("rno");
				String mid = rs.getString("mid");
				int cno = rs.getInt("cno");
				String rname = rs.getString("rname");
				String rcontent = rs.getString("rcontent");
				rplace = rs.getString("rplace");
				String mainimg = rs.getString("mainimg");
				String rtel = rs.getString("rtel");
				String rmenu = rs.getString("rmenu");
				String rprice = rs.getString("rprice");
				int rhit = rs.getInt("rhit");
				Timestamp rrdate = rs.getTimestamp("rrdate");
				int avghit = rs.getInt("avghit");
				double ravg = rs.getDouble("ravg");
				dtos.add(new RestaurantDto(rno, mid, cno, rname, rcontent, rplace, mainimg, rtel,
						rmenu, rprice, rhit, rrdate, avghit, ravg));
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

	// -- 9-2.  맛집 지역으로 검색(페이징)시 필요한 맛집 갯수
	public int getRestaurantSchTotCnt(String rplace) {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM RESTAURANT WHERE RPLACE LIKE '%' || ? || '%'";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rplace);
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
	
	// 10. 평점을 5명 이상 등록한 맛집 출력(메인페이지)
	public ArrayList<RestaurantDto> getRavgRestaurant() {
		ArrayList<RestaurantDto> dtos = new ArrayList<RestaurantDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT RESTAURANT.*, C.CNAME, RAVG.RAVG FROM RESTAURANT, CCODE C, (SELECT R.RNAME, AVG(RATING)RAVG FROM RAVG, RESTAURANT R WHERE RAVG.RNO = R.RNO GROUP BY R.RNAME HAVING AVG(RATING) > 4)RAVG WHERE RESTAURANT.RNAME = RAVG.RNAME AND RESTAURANT.AVGHIT >= 5 AND RESTAURANT.CNO = C.CNO";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rno = rs.getInt("rno");
				String mid = rs.getString("mid");
				int cno = rs.getInt("cno");
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
				double ravg = rs.getDouble("ravg");
				dtos.add(new RestaurantDto(rno, mid, cno, rname, rcontent, rplace, mainimg, rtel,
						rmenu, rprice, rhit, rrdate, avghit, ravg));
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
}
