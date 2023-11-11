<%@ page import="db.Sqlite" %>
<%@ page import="db.Bookmark" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<style>
	table {
		width:100%;
		border-collapse: collapse;
	}
	tr:nth-child(odd) {
  		background-color: #F2F2F2;
	}
		
	.head {
		padding : 10px 0px;
		background-color:#36AB6D;
		border-width : 1px;
		border-style : solid;
		border-color : #DED6D9;
		color : white;
	}
		
	.body {
		padding : 8px 5px;
		border-width : 0.5px;
		border-style : solid;
		border-color : #DED6D9;
	}
</style>
</head>
<body>
	<h1>북마크 목록</h1>

	<div>
		<a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
		<a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</div>
	<%
		Sqlite sqlite = new Sqlite();
		List<Bookmark> bookmarkList = sqlite.selectBookmarkList();
	%>
	<p></p>
	<table>
		<thead>
			<tr>
				<th class="head">ID</th>
				<th class="head">북마크이름</th>
				<th class="head">와이파이명</th>
				<th class="head">등록일자</th>
				<th class="head">비고</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%
					for(Bookmark list : bookmarkList) {
				%>
						<tr>
							<td class="body"> <%=list.getBOOKMARK_LIST_ID()%></td>
							<td class="body"> <%=list.getBOOKMARK_NAME()%></td>
							<td class="body"> <%=list.getWIFI_NAME()%></td>
							<td class="body"> <%=list.getREGISTER_DATE()%></td>
							<td class="body"> 
								<center><a href="bookmark-delete.jsp?id=<%=list.getBOOKMARK_LIST_ID()%>">삭제</a></center>
							</td>
						</tr>
				<%
					}
				%>
			</tr>
		</tbody>
	</table>

</body>
</html>