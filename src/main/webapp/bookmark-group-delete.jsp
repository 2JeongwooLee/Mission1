<%@ page import="db.BookmarkGroup" %>
<%@ page import="db.Sqlite" %>
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
		
		tr:nth-child(even) {
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
		#sub {
			text-align : center;
		}
	</style>
</head>
<body>
	<h1>북마크 그룹 삭제</h1>

	<div>
		<a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
		<a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</div>
	<%
		String id = request.getParameter("id");
		Sqlite sqlite = new Sqlite();
		BookmarkGroup bookmark = new BookmarkGroup();
		bookmark = sqlite.selectBookmarkGroup(id);
	%>
	<p></p>
	<div>
		북마크 그룹 이름을 삭제하시겠습니까?
	</div>
	<p></p>
	<form action="bookmark-group-delete-submit.jsp" method="post">
	<table>
	<colgroup>
		<col style = "width: 20%"/>
		<col style = "width: 80%"/>
	</colgroup>
		<tbody>
			<tr>
				<th class="head">북마크 이름</th>
				<td class="body">
					<input type="text" name="bookmarkName" value="<%=bookmark.getBOOKMARK_NAME()%>">
				</td>
			</tr>
			<tr>
				<th class="head">순서</th>
				<td class="body">
					<input type="text" name="sequence" value="<%=bookmark.getSEQUENCE()%>">
				</td>
			</tr>
			<tr id="sub">
				 <td  class="body" colspan=2>
				 	<input type="hidden" name="id" value="<%=id%>">
				 	<a href="bookmark-group.jsp">돌아가기</a> |
					<input type="submit" value="삭제">
				</td>
			</tr>
		</tbody>
	</table>
	</form>
</body>
</html>