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
<script src="http://jquery.bassistance.de/validate/lib/jquery-1.9.0.js"></script>
<!-- <script src="http://jquery.bassistance.de/validate/jquery.validate.js"></script> -->
<link href="http://bootstrapk.com/BS3/dist/css/bootstrap.css"
	rel="stylesheet">
	<style>
	form div {
	margin: 3px 0;
	}
	label {
line-height: 40px;
	margin: 0;
	}
	
	#content{
	margin-top : 50px;
	}
	#spanleft{
	margin-left: 250px;
	}
	
	#spancenter{
	margin-left: 22px;
	}
	
	h1{
	margin-bottom: 25px;
	}
	
	.lastlow{
	margin-bottom: 15px;
	}
	</style>
</head>
<body>
<div class='row'>
<div class='col-md-4'></div>
	<div class="col-md-4" id="content">
<h1>사용자 정보 변경</h1>
<form action='update.bit' method='post' id= 'update'>
<div class="row">
	<label class="col-lg-3 control-label">번호</label>
	<div class="col-lg-9"><input type='text' name='no' required class="form-control" value='<%=user.getNo()%>' readonly></div>
</div>

<div class="row">
	<label class="col-lg-3 control-label">이메일</label>
	<div class="col-lg-9"><input type='email' name='email' required class="form-control" value='<%=user.getEmail()%> '></div>
</div>


<div class="row">
	<label class="col-lg-3 control-label">비밀번호</label>
	<div class="col-lg-9"><input type='text' name='pwd' required class="form-control" value='<%=user.getPwd()%>'></div>
</div>
<div class="row">
	<label class="col-lg-3">이름</label>
	<div class="col-lg-9"><input type='text' name='name' required class="form-control" value='<%=user.getName()%>'></div>
</div>
<div class="row">
	<label class="col-lg-3">전화번호</label>
	<div class="col-lg-9"><input type='text' name='tel' required class="form-control"  value='<%=user.getTel()%>'></div>
</div>
<div class="row">
	<label class="col-lg-3">팩스</label>
	<div class="col-lg-9"><input type='text' name='fax' required class="form-control" value='<%=user.getFax()%>'></div>
</div>
<div class="row">
	<label class="col-lg-3">우편번호</label>
	<div class="col-lg-9"><input type='text' name='postno' required class="form-control" value='<%=user.getPostno()%>'></div>
</div>
<div class="row lastlow">
	<label class="col-lg-3">주소</label>
	<div class="col-lg-9"><input type='text' name='addr' required class="form-control" value='<%=user.getAddr()%>'></div>
</div>



<span id='spanleft'></span><input id='submit' type='submit' value='변경'  class="btn btn-primary"  ><span id='spancenter'></span>
<input type='reset' value='취소' class="btn btn-info">
</form>
</div>
<div class='col-md-4'></div>
</div>
<script>
$.validator.setDefaults({
	submitHandler: function() { 
		$("#submit").click(function() {
			$("#update").submit();
		})
	}
});
</script>
</body>
</html>