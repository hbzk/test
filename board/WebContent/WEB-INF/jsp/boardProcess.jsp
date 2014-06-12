<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");

Connection conn = null;
PreparedStatement pstmt = null;

String mode = request.getParameter("mode");
String subject = request.getParameter("subject");
String writer = request.getParameter("writer");
String contents = request.getParameter("contents");
if (contents != null) contents = contents.replaceAll("\r\n", "<br>");
String num = request.getParameter("num");
String pageNum = request.getParameter("pageNum");
String searchType = request.getParameter("searchType");
String searchText = request.getParameter("searchText");
String ip = request.getRemoteAddr();

try {
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mysql://14.32.7.49:1122/boarddb", "board", "board");
	
	if ("w".equals(mode)) {
		pstmt = conn.prepareStatement("INSERT INTO BOARD (SUBJECT, WRITER, CONTENTS, IP, HIT, REG_DATE, MOD_DATE) " +
			" VALUES (?,?,?,?,0,NOW(),NOW())");
		pstmt.setString(1, subject);
		pstmt.setString(2, writer);
		pstmt.setString(3, contents);
		pstmt.setString(4, ip);
		pstmt.executeUpdate();
		
		response.sendRedirect("boardList.jsp");
	} else if ("d".equals(mode)) {
		pstmt = conn.prepareStatement("DELETE FROM BOARD WHERE NUM=?");
		pstmt.setString(1, num);
		pstmt.executeUpdate();
		
		response.sendRedirect("boardList.jsp");
	} else if ("e".equals(mode)) {
		pstmt = conn.prepareStatement("UPDATE BOARD SET SUBJECT=?, WRITER=?, CONTENTS=?, IP=?, MOD_DATE=NOW() " +
				" WHERE NUM=?");
		pstmt.setString(1, subject);
		pstmt.setString(2, writer);
		pstmt.setString(3, contents);
		pstmt.setString(4, ip);
		pstmt.setString(5, num);
		pstmt.executeUpdate();
		
		response.sendRedirect("boardView.jsp?num=" + num);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

</body>
<script type="text/javascript">
</script>
</html>

<%
} catch (Exception e) {
	e.printStackTrace();
} finally {
	if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
	if (conn != null) try {conn.close();} catch (Exception e) {}
}
%>