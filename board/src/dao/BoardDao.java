package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.BoardModel;


public class BoardDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://14.32.7.49:1122/boarddb";
	private final String DB_ID = "board";
	private final String DB_PWD = "board";
	
	public List<BoardModel> selectList(BoardModel boardModel) {
		List<BoardModel> boardList = new ArrayList<BoardModel>();
		
		int pageNumInteger = 1;
		int listCount = 10;
		int pagePerList = 10;
		String searchSQL = "";

		String pageNum = boardModel.getPageNum();
		String searchType = boardModel.getSearchType();
		String searchText = boardModel.getSearchText();

		if (searchText == null) {
			searchType = "";
			searchText = "";
		}
		if (pageNum != null) {
			pageNumInteger = Integer.parseInt(pageNum);
		}

		// 검색어 한글 처리
		//searchText = new String(searchText.getBytes("ISO-8859-1"), "UTF-8") ;

		// 검색어 쿼리문 준비
		if (!"".equals(searchText)) {
			if ("ALL".equals(searchType)) {
				searchSQL = " WHERE SUBJECT LIKE CONCAT('%',?,'%') OR WRITER LIKE CONCAT('%',?,'%') OR CONTENTS LIKE CONCAT('%',?,'%') ";
			} else if ("SUBJECT".equals(searchType)) {
				searchSQL = " WHERE SUBJECT LIKE CONCAT('%',?,'%')";
			} else if ("WRITER".equals(searchType)) {
				searchSQL = " WHERE WRITER LIKE CONCAT('%',?,'%')";
			} else if ("CONTENTS".equals(searchType)) {
				searchSQL = " WHERE CONTENTS LIKE CONCAT('%',?,'%')";
			}
		}

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
			
			pstmt = conn.prepareStatement("SELECT COUNT(NUM) AS TOTAL FROM BOARD" + searchSQL);
			if (!"".equals(searchSQL)) {
				if ("ALL".equals(searchType)) {
					pstmt.setString(1, searchText);
					pstmt.setString(2, searchText);
					pstmt.setString(3, searchText);
				} else {
					pstmt.setString(1, searchText);
				}
			}
			rs = pstmt.executeQuery();
			rs.next();
			int totalCount  = rs.getInt("TOTAL");
			
			if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
			
			// 목록 쿼리
			pstmt = conn.prepareStatement("SELECT NUM, SUBJECT, WRITER, HIT, REG_DATE FROM BOARD " + searchSQL + " ORDER BY NUM DESC LIMIT ?,?" );
			
			if (!"".equals(searchSQL)) {
				// 전체 검색
				if ("ALL".equals(searchType)) {
					pstmt.setString(1, searchText);
					pstmt.setString(2, searchText);
					pstmt.setString(3, searchText);
					pstmt.setInt(4, listCount * (pageNumInteger - 1));
					pstmt.setInt(5, listCount);
				} else {
					pstmt.setString(1, searchText);
					pstmt.setInt(2, listCount * (pageNumInteger - 1));
					pstmt.setInt(3, listCount);
				}
				
			} else {
				pstmt.setInt(1, listCount * (pageNumInteger - 1));
				pstmt.setInt(2, listCount);
			}
			rs = pstmt.executeQuery();
			
			
			BoardModel bm;
			while (rs.next()) {
				bm = new BoardModel();
				bm.setNum(rs.getInt("NUM"));
				bm.setSubject(rs.getString("SUBJECT"));
				bm.setWriter(rs.getString("WRITER"));
				bm.setHit(rs.getInt("HIT"));
				bm.setRegDate(rs.getString("REG_DATE"));
				boardList.add(bm);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
			if (conn != null) try {conn.close();} catch (Exception e) {}
		}
		return boardList;
	}
	
	
	
	public int selectCount(BoardModel boardModel) {
		int totalCount = 0;
		int pageNumInteger = 1;
		//int listCount = 3;
		//int pagePerList = 3;
		String searchSQL = "";
		
		String pageNum = boardModel.getPageNum();
		String searchType = boardModel.getSearchType();
		String searchText = boardModel.getSearchText();
		
		
		
		if (searchText == null) {
			searchType = "";
			searchText = "";
		}
		if (pageNum != null) {
			pageNumInteger = Integer.parseInt(pageNum);
		}

		// 검색어 한글 처리
		//searchText = new String(searchText.getBytes("ISO-8859-1"), "UTF-8") ;

		// 검색어 쿼리문 준비
		if (!"".equals(searchText)) {
			if ("ALL".equals(searchType)) {
				searchSQL = " WHERE SUBJECT LIKE CONCAT('%',?,'%') OR WRITER LIKE CONCAT('%',?,'%') OR CONTENTS LIKE CONCAT('%',?,'%') ";
			} else if ("SUBJECT".equals(searchType)) {
				searchSQL = " WHERE SUBJECT LIKE CONCAT('%',?,'%')";
			} else if ("WRITER".equals(searchType)) {
				searchSQL = " WHERE WRITER LIKE CONCAT('%',?,'%')";
			} else if ("CONTENTS".equals(searchType)) {
				searchSQL = " WHERE CONTENTS LIKE CONCAT('%',?,'%')";
			}
		}


		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
			
			pstmt = conn.prepareStatement("SELECT COUNT(NUM) AS TOTAL FROM BOARD" + searchSQL);
			if (!"".equals(searchSQL)) {
				if ("ALL".equals(searchType)) {
					pstmt.setString(1, searchText);
					pstmt.setString(2, searchText);
					pstmt.setString(3, searchText);
				} else {
					pstmt.setString(1, searchText);
				}
			}
			rs = pstmt.executeQuery();
			rs.next();
			totalCount  = rs.getInt("TOTAL");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
			if (conn != null) try {conn.close();} catch (Exception e) {}
		}
		
		return totalCount;
	}
	
	public BoardModel select(BoardModel boardModel) {
		return null;
	}
	
	public void insert(BoardModel boardModel) {
		
	}
	
	public void update(BoardModel boardModel) {
		
	}
	
	public void updateHit(BoardModel boardModel) {
		
	}
	
	public void delete(BoardModel boardModel) {
		
	}
	
}
