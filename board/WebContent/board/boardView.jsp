<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

String num = request.getParameter("num");
String pageNum = request.getParameter("pageNum");
String searchType = request.getParameter("searchType");

try {
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mysql://14.32.7.49:1122/boarddb", "board", "board");
	// 조회수 증가
	pstmt = conn.prepareStatement("UPDATE BOARD SET HIT = HIT + 1 WHERE NUM=?");
	pstmt.setString(1, num);
	pstmt.executeUpdate();
	if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
	
	pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM=?" );
	pstmt.setString(1, num);
	rs = pstmt.executeQuery();
	rs.next();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세보기</title>
<style type="text/css">
body { font-size: 9pt;}
.right { width: 600px; text-align: right;}
table thead tr th { background-color: #ddd;;}
</style>
</head>
<body>

<form class="right" name="searchForm" action="" method="get">
<select name="searchType">
	<option value="ALL">전체검색</option>
	<option value="SUBJECT">제목</option>
	<option value="WRITER">작성자</option>
	<option value="CONTENTS">내용</option>
</select>
<input type="text" name="searchText" value="" />
<input type="submit" value="검색" />
</form>

<table border="1" summary="게시판 상세보기">
<caption>게시판 상세보기</caption>
<colgroup>
	<col width="50" />
	<col width="300" />
	<col width="80" />
	<col width="100" />
	<col width="70" />
</colgroup>  
<thead>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>등록 일시</th>
		<th>조회수</th>
	</tr>
</thead>
<tbody>
	<tr>
		<td align="center"><%=rs.getInt("NUM") %></td>
		<td><a href="boardView.jsp?num=<%=rs.getInt("NUM")%>"><%=rs.getString("SUBJECT") %></a></td>
		<td align="center"><%=rs.getString("WRITER") %></td>
		<td align="center"><%=rs.getString("REG_DATE").substring(0, 10) %></td>
		<td align="center"><%=rs.getInt("HIT") %></td>
	</tr>
	<tr>
		<td colspan="5"><%=rs.getString("CONTENTS") %></td>
	</tr>
</tbody>
</table>

<div class="right">
	<input type="button" value="목록" onclick="goUrl('boardList.jsp');" />
	<input type="button" value="수정" onclick="goUrl('boardModifyForm.jsp?num=<%=num %>');" />
	<input type="button" value="삭제" onclick="deleteCheck('boardProcess.jsp?mode=d&amp;num=<%=num %>');" />
</div>

</body>
<script type="text/javascript">
function goUrl(url) {
	location.href = url;
}
function deleteCheck(url) {
	if (confirm('삭제하시겠습니까?')) {
	location.href = url;
	}
}
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