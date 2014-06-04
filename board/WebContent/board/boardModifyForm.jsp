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
String contents = "";
try {
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mysql://14.32.7.49:1122/boarddb", "board", "board");
	pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM=?" );
	pstmt.setString(1, num);
	rs = pstmt.executeQuery();
	rs.next();
	
	contents = rs.getString("CONTENTS");
	if (contents != null) contents = contents.replaceAll("<br>", "\r\n");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정 폼</title>
<style type="text/css">
body { font-size: 9pt;}
.right { width: 600px; text-align: right;}
table thead tr th { background-color: #ddd;;}
</style>
<script type="text/javascript">
function goUrl(url) {
   location.href=url;
}

function boardWriteCheck() {
	var form = document.boardWriteForm;
	if (form.subject.value == '') {
		alert('제목을 입력하세요.');
		form.subject.focus();
		return false;
	}
	if (form.writer.value == '') {
		alert('작성자를 입력하세요.');
		form.writer.focus();
		return false;
	}
	if (form.contents.value == '') {
		alert('내용을 입력하세요.');
		form.contents.focus();
		return false;
	}
	return true;
}
</script>
</head>
<body>
<!--   -->
<form name="boardWriteForm" action="boardProcess.jsp" method="post" onsubmit="return boardWriteCheck();">
<input type="hidden" name="mode" value="e">
<input type="hidden" name="num" value="<%=num%>">
<table border="1" summary="게시판 수정 폼">
<caption>게시판 수정</caption>
<colgroup>
	<col width="100" />
	<col width="500" />
</colgroup>  
<tbody>
	<tr>
		<th>제목</th>
		<td><input type="text" name="subject" size="80" maxlength="100" value="<%=rs.getString("SUBJECT")%>"></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><input type="text" name="writer" maxlength="20" value="<%=rs.getString("WRITER")%>"></td>
	</tr>
	<tr>
		<td colspan="2">
			<textarea name="contents" rows="10" cols="80"><%=contents%></textarea>
		</td>
	</tr>
</tbody>
</table>

<div class="right">
	<input type="button" value="목록" onclick="goUrl('boardList.jsp');" />
	<input type="submit" value="수정">
</div>
</form>

</body>
</html>
<%
} catch (Exception e) {
	e.printStackTrace();
} finally {
	if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
	if (conn != null) try {conn.close();} catch (Exception e) {}
}
%>