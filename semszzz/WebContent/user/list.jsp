<%@page import="user.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%List<UserVo> list = (List<UserVo>)request.getAttribute("list"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="http://bootstrapk.com/BS3/dist/css/bootstrap.css" rel="stylesheet">
<style type="text/css">

body {

}
p {
font-size: 210%;
padding-bottom: 20px;
margin: 0;
}
p .left {
margin-right: 55px;
}
p .right {
margin-left: 115px;
}
table {
}

</style>
</head>
<body>
<br><br><br>
	<div id='all' class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">
		
		<p><span class="left"></span>사용자목록<span class="right"><a id='link' href='form.html' class="btn btn-warning">새사용자</a></span></p>
		<table class="table table-striped">
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
		<div class="col-md-4"></div>
	</div>
</body>
</html>
