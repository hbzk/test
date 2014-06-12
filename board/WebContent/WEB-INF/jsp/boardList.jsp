<%@page import="model.BoardModel"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<BoardModel> boardList = (List<BoardModel>)request.getAttribute("boardList");
BoardModel boardModel = (BoardModel) request.getAttribute("boardModel");
int totalCount = (Integer)request.getAttribute("totalCount");

/* String searchType = boardModel.getSearchType(); 
String searchText = boardModel.getSearchText(); */
 

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta name="viewport" content="width=device-width, height=device-height, target-densitydpi=device-dpi" /> -->
<title>게시판 목록</title>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<style type="text/css">
table { width: 95% !important; margin: 30px auto 0px; table-layout:fixed;}
table thead th { background-color: #ddd; text-align: center; white-space:nowrap; }
/* .no { width: 8%;}
.writer { width: 15%; margin: 0; max-height: 20px; max-width: 15%; 
white-space:nowrap; overflow:hidden; text-overflow:ellipsis;}
.date { width: 12%;}
.hit { width: 10%;} */
td { white-space:nowrap; overflow:hidden; text-overflow:ellipsis;}
/* table td { margin: 0; max-height: 20px; max-width: 120px; 
white-space:nowrap; overflow:hidden; text-overflow:ellipsis;} */
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
<table summary="게시판 목록" class="table table-hover">
<caption>게시판 목록</caption>
<thead>
	<tr>
		<th style="width: 8%;">번호</th>
		<th>제목</th>
		<th style="width: 15%;">작성자</th>
		<th style="width: 15%;">등록 일시</th>
		<th style="width: 10%;">조회수</th>
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
		BoardModel bm;
		for (int i = 0; i < boardList.size(); i++) {
			bm = boardList.get(i);
	%>
	<tr>
		<td class="no" align="center"><%=bm.getNum() %></td>
		<td class="subject"><a href="boardView.jsp?num=<%=bm.getNum()%>"><%=bm.getSubject() %></a></td>
		<td class="writer" align="center"><%=bm.getWriter()%></td>
		<td class="date" align="center"><%=bm.getRegDate().substring(0, 10) %></td>
		<td class="hit" align="center"><%=bm.getHit() %></td>
	</tr>
	<% 
		}
	}
	%>
</tbody>
<tfoot>
	<tr>
		<td align="center" colspan="5">
<%-- <%
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

%> --%>
		</td>
	</tr>
</tfoot>
</table>

<%-- <form class="center" name="searchForm" action="boardList.jsp" method="get" onsubmit="return searchCheck();">
<select class="form-control input-sm" name="searchType" >
	<option value="ALL">전체검색</option>
	<option value="SUBJECT" <%if ("SUBJECT".equals(searchType)) out.print(" selected='selected' "); %>>제목</option>
	<option value="WRITER" <%if ("WRITER".equals(searchType)) out.print(" selected='selected' "); %>>작성자</option>
	<option value="CONTENTS" <%if ("CONTENTS".equals(searchType)) out.print(" selected='selected' "); %>>내용</option>
</select>
<input class="form-control input-sm"  type="text" name="searchText" value="<%=searchText%>" />
<input class="btn btn-info btn-sm" type="submit" value="검색" />
</form> --%>


<div class="right">
	<input class="btn btn-success" type="button" value="목록" onclick="goUrl('boardList.jsp');" />
	<input class="btn btn-primary" type="button" value="글쓰기" onclick="goUrl('boardWriteForm.jsp');" />
</div>

<script type="text/javascript">
/* var tableWidth = $('table').attr('width');
console.log(tableWidth);
$('table').find('span').width(tableWidth); */
</script>

</body>
</html>
