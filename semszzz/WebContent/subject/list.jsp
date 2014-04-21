<%-- jsp:useBean 액션 태그 사용 --%>

<%@page import="java.util.ArrayList"%>
<%@page import="vo.SubjectVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<SubjectVo> list = (List<SubjectVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>과목목록</title>
	<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
</head>
<body>
<jsp:include page="/header.jsp"/>
<h1>과목 목록(byJSP)</h1>
<a href='form.html' class="btn btn-primary">새과목</a><br>
<table border='1' class="table table-striped">
<tr>
  <th>번호</th>
  <th>과목명</th>
</tr>
<%for(SubjectVo subject : list) {%>
<tr>
  <td><%=subject.getNo()%></td>
  <td><a href='detail.bit?no=<%=subject.getNo()%>'><%=subject.getTitle()%></a></td>
</tr>
<%}%>
</table>
<jsp:include page="/footer.jsp"/>
</body></html>






<%-- <%-- jsp:useBean 액션 태그 사용 --%>
<%-- 
<%@page import="java.util.ArrayList"%>
<%@page import="vo.SubjectVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

1. jsp:useBean 사용 
 1) type 만 사용  -->
 --%>
<%-- <jsp:useBean id="list" type="java.util.List<vo.SubjectVo>" scope="request"/>
<%
	List<SubjectVo> list = (List<SubjectVo>) request.getAttribute("list");
%>

2) type 만 사용  
<jsp:useBean id="list" class="java.util.ArrayList<vo.SubjectVo>" scope="request"/>

	List<SubjectVo> list = (List<SubjectVo>) request.getAttribute("list");
if (list == null) {
	list = new ArrayList<SubjectVo>();
	request.setAttribute("list", list);
}

) class, type  모두 사용  
<jsp:useBean id="list" class="java.util.ArrayList"   type ="java.util.List<vo.SubjectVo> scope="request"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과목목록</title>
</head>
<body>
<h1>과목목록(JSP, JSP ACcion)</h1>
<a href='form.html'>새과목</a>
<br>
<table border='1'>
<tr>
	<th>번호</th>
	<th>과목명</th>
</tr>

<%for(SubjectVo subject : list) { %>
	<tr>
		<td><%=subject.getNo()%></td>
		<td><a href='detail.bit?no=<%= subject.getNo()%>'><%=subject.getTitle()%></a></td>
	</tr>
<%} %>


</table>
<jsp:include page="/footer.jsp"/>
</body>
</html> --%>