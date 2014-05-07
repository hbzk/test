<%@page import="sems.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html><html><head>
<meta charset='UTF-8'>
<title>사용자 입력폼</title>
<script type="text/javascript" src="jquery.validate.js"></script>
</head>
<body>

<h1>사용자 입력</h1>
<form id="signup" action='insert.bit' method='post'>
이메일: <input   type='text' name='email'><br>
암호:    <input  type="text" name='pwd'><br>
이름:     <input  type='text' name='name'><br>
전화번호: <input  type='text' name='tel'><br>
팩스:    <input  type='text' name='fax'><br>
우편번호: <input  type='text' name='postno'><br>
주소:    <input  type='text' name='addr'><br>
<input type='submit' value='등록'>
<input type='reset' value='취소'>
</form>



<script type="text/javascript">
/* $("#commentForm").validate(); */
/* (function($){

    $(document).ready(function() { */

    $('#signup').validate({
            rules: {
                email: { required: true, email: true },
                pwd: { required: true},
                name: { required: true},
                tel: { required: true},
            },
            messages: {
                user_email: { required: "필수 입력 항목입니다.", email: "올바른 이메일주소를 입력하시오." 
                },
                pwd: { required: "필수 입력 항목입니다."},
                name: { required: "필수 입력 항목입니다."},
                tel: { required: "필수 입력 항목입니다."},

            },
            submitHandler: function (frm) {
                frm.submit();
            },
            success: function (e) { 
            }
        });
/*     });
})(jQuery); */
</script> 

</body>
</html>