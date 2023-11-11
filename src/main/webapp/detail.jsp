<%@ page import="db.Sqlite" %>
<%@ page import="db.WifiList" %>
<%@ page import="db.BookmarkGroup" %>
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
			border-collapse: collapse;
			width:100%;
		}
		tr:nth-child(even) {
  			background-color: #F2F2F2;
		}
		td, tr {
			padding : 10px 5px;
			border-style : solid;
			border-color : #DED6D9;
			border-width : 0.5px;
		}
		th {
			padding : 10px 0px;
			border-style : solid;
			border-color : #DED6D9;
			border-width : 0.5px;
			background-color:#00AB50;
			color : white;
		}
	</style>
</head>
<body>
	<%
			String MGR_NO = request.getParameter("X_SWIFI_MGR_NO");
			String LAT = request.getParameter("LAT");
			String LNT = request.getParameter("LNT");
			Sqlite sqlite = new Sqlite();
			WifiList detail = sqlite.selectDetail(MGR_NO);
			List<BookmarkGroup> list = sqlite.selectBookmarkGroup();
	%>
	<h1>와이파이 상세 정보</h1>

	<div>
		<a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
		<a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</div>
	<p></p>
	<form action="bookmark-add-submit.jsp" method="post">
		<select name = "bookmark-select">
			<option value="none" hidden>북마크 그룹 이름 선택</option>
			<%
			for(BookmarkGroup bookmark : list) {
			%>
			<option value="<%=bookmark.getBOOKMARK_NAME()%>"><%=bookmark.getBOOKMARK_NAME()%></option>
			<%}%>
		</select>
		<input type="hidden" name="wifiname" value="<%=detail.getX_SWIFI_MAIN_NM()%>">
		<input type="submit" value="북마크 추가하기">
	</form>
	<table>
	<colgroup>
		<col style = "width: 20%"/>
		<col style = "width: 80%"/>
	</colgroup>
		<tbody>
			<tr>
				<th>거리(km)</th>
				<td>
					<%=Math.round(6371 * Math.acos(Math.cos(Float.parseFloat(detail.getLAT()) * 3.141592653589793 / 180.0) * Math.cos(Float.parseFloat(LAT) * 3.141592653589793 / 180.0)
							               * Math.cos((Float.parseFloat(LNT) * 3.141592653589793 / 180.0) - (Float.parseFloat(detail.getLNT()) * 3.141592653589793 / 180.0)) + Math.sin(Float.parseFloat(detail.getLAT()) * 3.141592653589793 / 180.0)
							               * Math.sin(Float.parseFloat(LAT) * 3.141592653589793 / 180.0)) * 10000)/10000.0
					%>
				</td>
			</tr>
			<tr>
				<th>관리번호</th>
				<td>
					<%=detail.getX_SWIFI_MGR_NO()%>
				</td>
			</tr>
			<tr>
				<th>자치구</th>
				<td>
					<%=detail.getX_SWIFI_WRDOFC()%>
				</td>
			</tr>
			<tr>
				<th>와이파이명</th>
				<td>
					<%=detail.getX_SWIFI_MAIN_NM()%>
				</td>
			</tr>
			<tr>
				<th>도로명주소</th>
				<td>
					<%=detail.getX_SWIFI_ADRES1()%>
				</td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td>
					<%=detail.getX_SWIFI_ADRES2()%>
				</td>
			</tr>
			<tr>
				<th>설치위치(층)</th>
				<td>
					<%=detail.getX_SWIFI_INSTL_FLOOR()%>
				</td>
			</tr>
			<tr>
				<th>설치유형</th>
				<td>
					<%=detail.getX_SWIFI_INSTL_TY()%>
				</td>
			</tr>
			<tr>
				<th>설치기관</th>
				<td>
					<%=detail.getX_SWIFI_INSTL_MBY()%>
				</td>
			</tr>
			<tr>
				<th>서비스구분</th>
				<td>
					<%=detail.getX_SWIFI_SVC_SE()%>
				</td>
			</tr>
			<tr>
				<th>망종류</th>
				<td>
					<%=detail.getX_SWIFI_CMCWR()%>
				</td>
			</tr>
			<tr>
				<th>설치년도</th>
				<td>
					<%=detail.getX_SWIFI_CNSTC_YEAR()%>
				</td>
			</tr>
			<tr>
				<th>실내외구분</th>
				<td>
					<%=detail.getX_SWIFI_INOUT_DOOR()%>
				</td>
			</tr>
			<tr>
				<th>WIFI접속환경</th>
				<td>
					<%=detail.getX_SWIFI_REMARS3()%>
				</td>
			</tr>
			<tr>
				<th>X좌표</th>
				<td>
					<%=detail.getLAT()%>
				</td>
			</tr>
			<tr>
				<th>Y좌표</th>
				<td>
					<%=detail.getLNT()%>
				</td>
			</tr>
			<tr>
				<th>작업일자</th>
				<td>
					<%=detail.getWORK_DTTM()%>
				</td>
			</tr>

		</tbody>
	</table>
	
</body>
</html>