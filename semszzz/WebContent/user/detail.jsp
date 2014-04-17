<%@page import="user.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
UserVo user = (UserVo)request.getAttribute("user");
if (user.getFax() == null) {
	user.setFax("");
}
if (user.getPostno() == null) {
	user.setPostno("");
}
if (user.getAddr() == null) {
	user.setAddr("");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head><body><div id = 'all'>
<h1>사용자 상세정보</h1>
<table border='1'>
<tr>
<th id='left'>번호</th>
<td id='right'><%=user.getNo()%></td>
</tr>
<tr>
<th>이메일</th>
<td><%=user.getEmail()%></td>
</tr>
<tr>
<th>암호</th>
<td><%=user.getPwd()%></td>
</tr>
<tr>
<th>이름</th>
<td><%=user.getName()%></td>
</tr>
<tr>
<th>전화번호</th>
<td><%=user.getTel()%></td>
</tr>
<tr>
<th>팩스</th>
<td><%=user.getFax()%></td>
</tr>
<tr>
<th>우편번호</th>
<td><%=user.getPostno()%></td>
</tr>
<tr>
<th>주소</th>
<td><%=user.getAddr()%></td>
</tr>
</table>
<a class='link' href='list.bit?pageNo=1&pageSize=10'>목록</a> 
<a class='link' href='delete.bit?no=<%=user.getNo()%>'>삭제</a> 
<a class='link' href='update.bit?no=<%=user.getNo()%>'>변경</a><br>
</div></body></html>
	