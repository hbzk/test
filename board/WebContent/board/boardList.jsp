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

int pageNumInteger = 1;
int listCount = 3;
int pagePerList = 3;
String searchSQL = "";

String pageNum = request.getParameter("pageNum");
String searchType = request.getParameter("searchType");
String searchText = request.getParameter("searchText");

if (searchText == null) {
	searchType = "";
	searchText = "";
}
if (pageNum != null) {
	pageNumInteger = Integer.parseInt(pageNum);
}

// 검색어 한글 처리
searchText = new String(searchText.getBytes("ISO-8859-1"), "UTF-8") ;

// 검색어 쿼리문 준비
if (!"".equals(searchText)) {
	if ("ALL".equals(searchType)) {
		searchSQL = " WHERE SUBJECT LIKE CONCAT('%',?,'%') OR WRITER LIKE CONCAT('%',?,'%') OR CONTENTS LIKE CONCAT('%',?,'%') ";
	} else if ("SUBJECT".equals(searchType)) {
		searchSQL = " WHERE SUBJECT LIKE CONCAT('%',?,'%')";
	} else if ("WRITER".equals(searchType)) {
		searchSQL = " WHERE WRITER LIKE CONCAT('%',?,'%')";
	} else if ("CONTENTS".equals(searchType)) {
		searchSQL = " WHERE CONTENTS LIKE CONCAT('%',?,'%')";
	}
}


try {
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mysql://14.32.7.49:1122/boarddb", "board", "board");
	pstmt = conn.prepareStatement("SELECT COUNT(NUM) AS TOTAL FROM BOARD" + searchSQL);
	if (!"".equals(searchSQL)) {
		if ("ALL".equals(searchType)) {
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			pstmt.setString(3, searchText);
		} else {
			pstmt.setString(1, searchText);
		}
	}
	rs = pstmt.executeQuery();
	rs.next();
	int totalCount  = rs.getInt("TOTAL");
	
	if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
	
	// 목록 쿼리
	pstmt = conn.prepareStatement("SELECT NUM, SUBJECT, WRITER, HIT, REG_DATE FROM BOARD " + searchSQL + " ORDER BY NUM DESC LIMIT ?,?" );
	
	if (!"".equals(searchSQL)) {
		// 전체 검색
		if ("ALL".equals(searchType)) {
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			pstmt.setString(3, searchText);
			pstmt.setInt(4, listCount * (pageNumInteger - 1));
			pstmt.setInt(5, listCount);
		} else {
			pstmt.setString(1, searchText);
			pstmt.setInt(2, listCount * (pageNumInteger - 1));
			pstmt.setInt(3, listCount);
		}
		
	} else {
		pstmt.setInt(1, listCount * (pageNumInteger - 1));
		pstmt.setInt(2, listCount);
	}
	rs = pstmt.executeQuery();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<style type="text/css">
table { width: 95% !important; margin-top: 30px;}
table thead th { background-color: #ddd; text-align: center; white-space:nowrap; }
table td { margin: 0; max-height: 20px; max-width: 120px; 
white-space:nowrap; overflow:hidden; text-overflow:ellipsis;}
.center { text-align: center;}
.center select, .center input { display: inline-block; width: auto; vertical-align: middle;}
.right { position: absolute; right: 2.5%; margin-top: 20px;}
</style>
<script type="text/javascript">
function goUrl(url) {
   location.href=url;
}

function searchCheck() {
	var form = document.searchForm;
	if (form.searchText.value == '') {
		alert('검색어를 입력하세요.');
		form.searchText.focus();
		return false;
	}
	return true;
}

</script>
</head>
<body>

<table summary="게시판 목록" class="table table-hover container">
<caption>게시판 목록</caption>
<colgroup>
	<col width="80" />
	<col width="550" />
	<col width="150" />
	<col width="120" />
	<col width="100" />
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
	
	<%
	if (totalCount == 0) {
	%>
	<tr>
		<td align="center" colspan="5">등록된 게시물이 없습니다.</td>
	</tr>
	<%
	} else {
		while (rs.next()) {
	%>
	<tr>
		<td align="center"><%=rs.getInt("NUM") %></td>
		<td><a href="boardView.jsp?num=<%=rs.getInt("NUM")%>"><%=rs.getString("SUBJECT") %></a></td>
		<td align="center"><%=rs.getString("WRITER") %></td>
		<td align="center"><%=rs.getString("REG_DATE").substring(0, 10) %></td>
		<td align="center"><%=rs.getInt("HIT") %></td>
	</tr>
	<% 
		}
	}
	%>
</tbody>
<tfoot>
	<tr>
		<td align="center" colspan="5">
<%
if (totalCount > 0) {
	int totalPageNum = (totalCount % listCount == 0 ) ?
		totalCount / listCount :
		totalCount / listCount + 1;
	
	int totalPageListNum = (totalPageNum % pagePerList == 0) ?
		totalPageNum / pagePerList :
		totalPageNum / pagePerList + 1;
	
	int currentPageList = (pageNumInteger % pagePerList == 0) ?
		pageNumInteger / pagePerList :
		pageNumInteger / pagePerList + 1;
	
	int pageListStart = (currentPageList - 1) * pagePerList + 1;
	int pageListEnd = pageListStart + pagePerList -1;
	
	if (pageListEnd > totalPageNum) 
		pageListEnd = totalPageNum;
	
	boolean hasNext = false;
	boolean hasPrev = false;
	
	if (currentPageList < totalPageListNum) 
		hasNext = true;
	if (currentPageList > 1)
		hasPrev = true;
	if (totalPageListNum == 1) {
		hasNext = false;
		hasPrev = false;
	}
	
	StringBuffer sb = new StringBuffer();
	if (pageNumInteger > 1) {
		sb.append("<a href=\"boardList.jsp?pageNum=1&searchType=" + searchType
			+ "&searchText=" + searchText + "\" title=\"abcdefg\">맨 앞으로</a> &nbsp;&nbsp; ");
	}
	if (hasPrev) {
		int prevPage = pageListStart - pagePerList;
		sb.append(" <a href=\"boardList.jsp?pageNum=" + prevPage + "&searchType=" + searchType
			+ "&searchText" + searchText + "\">◀이전</a> &nbsp; ");
	}
	
	for (int i = pageListStart; i <= pageListEnd; i ++) {
		if (i == pageNumInteger) {
			//sb.append(" <a href=\"#\"><strong>" + i + "</strong></a> ");
			sb.append(" <strong>" + i + "</strong> ");
		} else {
			sb.append(" <a href=\"boardList.jsp?pageNum=" + i + "&searchType=" + searchType
				+ "&searchText" + searchText + "\">" + i + "</a> ");
		}
	}
	
	if (hasNext) {
		int nextPage = pageListStart + pagePerList;
		sb.append(" &nbsp; <a href=\"boardList.jsp?pageNum=" + nextPage + "&searchType=" + searchType
				+ "&searchText" + searchText + "\">다음▶</a>");
	}
	if (totalPageNum > pageNumInteger) {
		sb.append(" &nbsp; &nbsp; <a href=\"boardList.jsp?pageNum=" + totalPageNum + "&searchType=" + searchType
				+ "&searchText=" + searchText + "\" title=\"abcdefg\">맨 뒤로</a>");
	}
	out.print(sb.toString());
}

%>
		</td>
	</tr>
</tfoot>
</table>

<form class="center" name="searchForm" action="boardList.jsp" method="get" onsubmit="return searchCheck();">
<select class="form-control input-sm" name="searchType" >
	<option value="ALL">전체검색</option>
	<option value="SUBJECT" <%if ("SUBJECT".equals(searchType)) out.print(" selected='selected' "); %>>제목</option>
	<option value="WRITER" <%if ("WRITER".equals(searchType)) out.print(" selected='selected' "); %>>작성자</option>
	<option value="CONTENTS" <%if ("CONTENTS".equals(searchType)) out.print(" selected='selected' "); %>>내용</option>
</select>
<input class="form-control input-sm"  type="text" name="searchText" value="<%=searchText%>" />
<input class="btn btn-info btn-sm" type="submit" value="검색" />
</form>


<div class="right">
	<input class="btn btn-success" type="button" value="목록" onclick="goUrl('boardList.jsp');" />
	<input class="btn btn-primary" type="button" value="글쓰기" onclick="goUrl('boardWriteForm.jsp');" />
</div>

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