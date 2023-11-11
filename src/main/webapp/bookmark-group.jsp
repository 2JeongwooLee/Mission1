<%@ page import="db.BookmarkGroup" %>
<%@ page import="db.Sqlite" %>
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
	<h1>북마크 그룹</h1>

	<div>
		<a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
		<a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</div>
	<p></p>
	<button type="button" onclick="location.href='bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>
	<%
			Sqlite sqlite = new Sqlite();
			List<BookmarkGroup> list = sqlite.selectBookmarkGroup();
	%>
	<table>
		<thead style ="background-color:#00AB50">
			<tr>
				<th class="head">ID</th>
				<th class="head">북마크 이름</th>
				<th class="head">순서</th>
				<th class="head">등록일자</th>
				<th class="head">수정일자</th>
				<th class="head">비고</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%
					for(BookmarkGroup bookmarklist : list) {
				%>
						<tr>
							<td class="body"> <%=bookmarklist.getBOOKMARK_ID() %> </td>
							<td class="body"> <%=bookmarklist.getBOOKMARK_NAME() %></td>
							<td class="body"> <%=bookmarklist.getSEQUENCE() %></td>
							<td class="body"> <%=bookmarklist.getREGISTER_DATE() %></td>
							<td class="body"> <%=bookmarklist.getMODIFY_DATE() %> </td>
							<td class="body">
								
								<a href="bookmark-group-edit.jsp?id=<%=bookmarklist.getBOOKMARK_ID()%>">수정</a>
								<a href="bookmark-group-delete.jsp?id=<%=bookmarklist.getBOOKMARK_ID()%>">삭제</a>
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