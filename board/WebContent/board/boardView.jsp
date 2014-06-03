<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 내용</title>
<style type="text/css">
body { font-size: 9pt;}
.right { width: 600px; text-align: right;}
table thead tr th { background-color: #ddd;;}
</style>
<script type="text/javascript">
function goUrl(url) {
   location.href=url;
}
</script>
</head>
<body>

<form class="right" name="searchForm" action="" method="get">
<select name="searchType">
	<option value="ALL">전체검색</option>
	<option value="SUBJECT">제목</option>
	<option value="WRITER">작성자</option>
	<option value="CONTENTS">내용</option>
</select>
<input type="text" name="searchText" value="" />
<input type="submit" value="검색" />
</form>

<table border="1" summary="게시판 내용">
<caption>게시판 내용</caption>
<colgroup>
	<col width="50" />
	<col width="300" />
	<col width="80" />
	<col width="100" />
	<col width="70" />
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
	<tr>
		<td align="center">1</td>
		<td>Eggscellent - 업무 집중력 향상을 위한 뽀모도로 기법을 맥에서 실천해보자!</td>
		<td align="center">개똥이</td>
		<td align="center">2012.03.03</td>
		<td align="center">10</td>
	</tr>
	<tr>
		<td colspan="5">뽀모도로 기법이란 무엇인가?
90년대 초 이탈리아의 프란체스코 시릴로가 만든 시간 활용 방법으로 단순히 25분간 집중해서 일을 한 다음 5분간 휴식하고 이를 계속 반복해 나가는 방식입니다. 25분간 집중 5분간 휴식이 한 세트가 되는데 이를 1 뽀모도로 또는 뽀모도로 1 사이클이라고 부릅니다.
참고로 ’뽀모도로(Pomodoro)’는 이탈리아어로 토마토를 뜻하는데, 프란체스코 시릴로가 대학생 시절 토마토 모양으로 생긴 요리용 타이머를 이용해 25분간 집중 후 휴식하는 일처리 방법을 제안한데서 그 이름이 유래했다고 합니다.

뽀모도로 4 사이클, 즉 25분간 집중 5분간의 ’짧은 휴식(short break)’을 네 차례 반복하면 15~30분가량 쉬어주는데 이를 ’긴 휴식(long break)’이라고 부릅니다. 각각의 텀 뒤에 있는 이런 휴식 시간은 자기 자신에게 주는 일종으로 보상으로 작용합니다.
하루를 얼마나 보람차게 일했는가? 즉 “얼마나 생산적으로 일했는가?”는 하루에 이 뽀모도로를 몇 번이나 반복했냐로 판단합니다. 야근이 잦은 분들에게는 별나라 이야기 같겠지만 보통 근무시간이 8시간이니 1214개를 할 수 있는 셈인데, 뽀모도로에 관한 다른 분들의 글을 읽어 보면 하루에 8개를 넘기기가 무척이나 어렵다고 합니다. 그만큼 자아와 외부로부터 잦은 방해 요소가 등장하면서 집중력이 깨지는 경우가 빈번하기 때문입니다. 뽀모도로를 진행하면서 방해 요소가 등장할 때마다 횟수를 기입하고 하루 일과가 끝날 때 검토하고 반성하는 시간도 가집니다.</td>
	</tr>
</tbody>
</table>

<div class="right">
	<input type="button" value="목록" onclick="goUrl('boardList.jsp');" />
	<input type="button" value="글쓰기" onclick="goUrl('boardWriteForm.jsp');" />
</div>

</body>
</html>
