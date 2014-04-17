<%@page import="user.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%UserVo user = (UserVo) request.getAttribute("user");
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
<!DOCTYPE html><html><head>
<meta charset='UTF-8'>
<title>사용자 정보 수정</title>
</head>
<body>
<h1>사용자 입력</h1>
<form action='update.bit' method='post' id= insert>
<span id= no>번호: </span>  <input type='text' name='no' value='<%=user.getNo()%>' readonly><br>
<span id= email>이메일:</span> <input   type='text' name='email'  value='<%=user.getEmail()%>'><br>
<span id= pwd>암호: </span>   <input  type='text' name='pwd' value='<%=user.getPwd()%>'><br>
<span id= name>이름: </span>    <input  type='text' name='name' value='<%=user.getName()%>'><br>
<span id= tel>전화번호: </span><input  type='text' name='tel' value='<%=user.getTel()%>'><br>
<span id= fax>팩스: </span>   <input  type='text' name='fax' value='<%=user.getFax()%>'><br>
<span id= postno>우편번호:</span> <input  type='text' name='postno' value='<%=user.getPostno()%>'><br>
<span id= addr>주소: </span>   <input  type='text' name='addr' value='<%=user.getAddr()%>'><br>
<input type='submit' value='등록'>
<input type='reset' value='취소'>
</form>
</body>
</html>