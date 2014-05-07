<%@page import="sems.vo.UserVo"%>
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
<link href="http://bootstrapk.com/BS3/dist/css/bootstrap.css" rel="stylesheet">
<style type="text/css">
.left {
margin-right: 170px;
}

p a {
margin: 0 5px;
}
th {
width: 30%;
border-right: 1px solid #ddd;
}


</style>
<title>사용자 상세정보</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div id = 'all row'>
<div class="col-md-4"></div>
<div class="col-md-4">
<br><br><br>
<h1>사용자 상세정보</h1>
<br><br>
<table class="table table-striped">
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
<p>
<span class="left"></span>
<a class='link btn btn-primary' href='update.bit?no=<%=user.getNo()%>'>변경</a>
<a class='link btn btn-success' href='list.bit?pageNo=1&pageSize=10' >목록</a> 
<a class='link btn btn-info ' href='delete.bit?no=<%=user.getNo()%>'>삭제</a> 
<span class="right"></span>
</p>


</div>

<div class="col-md-3"></div>
</div>

</body>
</html>
	