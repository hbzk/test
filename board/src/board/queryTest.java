package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class queryTest {
	
	// ========================= Delete
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://14.32.7.49:1122/boarddb", "board", "board");
			pstmt = conn.prepareStatement("DELETE FROM BOARD WHERE NUM=?");
			
			int contentNumber = 2;
			
			pstmt.setInt(1, contentNumber);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
			if (conn != null) try {conn.close();} catch (Exception e) {}
		}
	}

	// ========================= Edit
	public static void mainEdit(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://14.32.7.49:1122/boarddb", "board", "board");
			pstmt = conn.prepareStatement("UPDATE BOARD SET SUBJECT=?, WRITER=?, MOD_DATE=NOW() WHERE NUM=?" );
			
			int contentNumber = 16;
			
			pstmt.setString(1, "수정제목");
			pstmt.setString(2, "수정작성자");
			pstmt.setInt(3, contentNumber);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
			if (conn != null) try {conn.close();} catch (Exception e) {}
		}
	}
	
	// ========================= View
	public static void mainView(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://14.32.7.49:1122/boarddb", "board", "board");
			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM=?" );
			
			int contentNumber = 16;
			pstmt.setInt(1, contentNumber);
			rs = pstmt.executeQuery();
			rs.next();
			
			int num = rs.getInt("NUM");
			String subject = rs.getString("SUBJECT");
			String writer = rs.getString("WRITER");
			String contents = rs.getString("CONTENTS");
			int hit = rs.getInt("HIT");
			String ip = rs.getString("IP");
			String reg_date = rs.getString("REG_DATE");
			String mod_date = rs.getString("MOD_DATE");
			
			System.out.println("num: " + num + ", subject: " + subject + ", writer: " + writer + ", contents: " + contents
				+ ", hit: " + hit + ", ip: " + ip + ", reg_date " + reg_date + ", mod_date: " + mod_date);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
			if (conn != null) try {conn.close();} catch (Exception e) {}
		}
	}
	
	// ========================= LIST
	public static void mainList(String[] args) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://14.32.7.49:1122/boarddb", "board", "board");
			pstmt = conn.prepareStatement("SELECT COUNT(NUM) AS TOTAL FROM BOARD");
			rs = pstmt.executeQuery();
			rs.next();
			int totalCount  = rs.getInt("TOTAL");
			System.out.println("totalCount : " + totalCount);
			if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
			
			int listSize = 100;
			int listNum = 1;
			
			pstmt = conn.prepareStatement("SELECT NUM, SUBJECT, WRITER, HIT, REG_DATE FROM BOARD ORDER BY NUM DESC LIMIT ?,?" );
			pstmt.setInt(1, listSize * (listNum - 1));
			pstmt.setInt(2, listSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("NUM");
				String subject = rs.getString("SUBJECT");
				String writer = rs.getString("WRITER");
				//String content = rs.getString("CONTENTS");
				int hit = rs.getInt("HIT");
				//String ip = rs.getString("IP");
				String reg_date = rs.getString("REG_DATE");
				//String mod_date = rs.getString("MOD_DATE");
				
				System.out.println("num: " + num + ", subject: " + subject + ", writer: " + writer
						+ ", hit: " + hit + ", reg_date " + reg_date);
				System.out.println("===================");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
		}
			if (conn != null) try {conn.close();} catch (Exception e) {}
	}
	
	// ========================= WRITE
	public static void mainWrite(String[] args) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://14.32.7.49:1122/boarddb", "board", "board");
			pstmt = conn.prepareStatement("INSERT INTO BOARD (SUBJECT, WRITER, REG_DATE) VALUES (?, ?, NOW())");
			pstmt.setString(1, "ㄹㄹㄹㄹㄹ");
			pstmt.setString(2, "ㄹㄹㄹㄹㄹ");
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
			if (conn != null) try {conn.close();} catch (Exception e) {}
		}
	}
}
