<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Connection conn = null;
java.sql.PreparedStatement pstmt = null;
try {
//	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mysql://14.32.7.49:1122/boarddb", "board", "board");
	pstmt = conn.prepareStatement("SELECT COUNT(NUM) AS TOTAL FROM BOARD");
	ResultSet rs = pstmt.executeQuery();
	rs.next();
	int totalCount = rs.getInt("TOTAL");  
	
} catch (Exception e) {
	e.printStackTrace();
} finally {
	if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
	if (conn != null) try {conn.close();} catch (Exception e) {}
}

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<style type="text/css">
body { font-size: 9pt;}
.right { width: 600px; text-align: right;}
table thead tr th { background-color: #ddd;;}
</style>
<script type="text/javascript">
function goUrl(url) {
   location.href=url;
}
</script>
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

<table border="1" summary="게시판 목록">
<caption>게시판 목록</caption>
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
		<td align="center" colspan="5">등록된 게시물이 없습니다.</td>
	</tr>
	<tr>
		<td align="center">1</td>
		<td><a href="boardView.jsp">동해물과 백두산이 마르고 닳도록 하...</a></td>
		<td align="center">개똥이</td>
		<td align="center">2012.03.03</td>
		<td align="center">10</td>
	</tr>
</tbody>
<tfoot>
    <tr>
         <td align="center" colspan="5">1</td>
    </tr>
</tfoot>
</table>

<div class="right">
	<input type="button" value="목록" onclick="goUrl('boardList.jsp');" />
	<input type="button" value="글쓰기" onclick="goUrl('boardWriteForm.jsp');" />
</div>

</body>
</html>
