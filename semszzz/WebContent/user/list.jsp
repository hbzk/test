<%@page import="user.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%List<UserVo> list = (List<UserVo>)request.getAttribute("list"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div id='all'>
		<h1>사용자목록</h1>
		<a id='link' href='form.html'>새사용자</a><br>
		<table border='1'>
			<tr>
				<th id='left'>번호</th>
				<th id='right'>사용자 이름</th>
			</tr>
<%for (UserVo user : list) {%>
			<tr>
				<td><%=user.getNo()%></td>
				<td><a href='detail.bit?no=<%=user.getNo()%>'><%=user.getName()%></a></td>
			</tr>
<%}%>
		</table>
	</div>
</body>
</html>
