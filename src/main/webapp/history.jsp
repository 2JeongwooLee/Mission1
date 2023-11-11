<%@ page import="db.Sqlite" %>
<%@ page import="db.HistoryList" %>
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
			padding : 7px 0px;
			border-width : 0.5px;
			border-style : solid;
			border-color : #DED6D9;
		}
	#del {
		text-align : center;
	}
</style>
</head>
<body>
	<h1>위치 히스토리 목록</h1>

	<div>
		<a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
		<a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</div>
	<%
		Sqlite sqlite = new Sqlite();
		List<HistoryList> historylist = sqlite.selectHistory();
		
		String ID = request.getParameter("ID");
		if(ID != null) {
			sqlite.deleteHistory(ID);
	%>
		<meta http-equiv="Refresh" content="0" />
	<%
		}	
	%>
	<p></p>
	<table>
		<thead style ="background-color:#00AB50">
			<tr>
				<th class="head">ID</th>
				<th class="head">X좌표</th>
				<th class="head">Y좌표</th>
				<th class="head">조회일자</th>
				<th class="head">비고</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%
					for(HistoryList list : historylist) {
				%>
						<tr>
							<td class="body"> <%=list.getHISTORY_ID()%></td>
							<td class="body"> <%=list.getLAT()%></td>
							<td class="body"> <%=list.getLNT()%></td>
							<td class="body"> <%=list.getREFERENCE_DATE()%></td>
							<td  class="body" id="del">
								<form method="POST">
									<input type = "hidden" name="ID" value =<%=list.getHISTORY_ID()%>>
									<input type = "submit" value = "삭제">
								</form>
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