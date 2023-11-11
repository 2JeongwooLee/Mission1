<%@ page import="db.Sqlite" %>
<%@ page import="db.WifiList" %>
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
		
		.init {
			padding : 20px 40%;
			border-style : solid;
			border-width : 0.5px;
			border-color : #DED6D9;
		}
		
	</style>
</head>
<body>
	<%
		String LAT = request.getParameter("LAT");
		String LNT = request.getParameter("LNT");
		
		Sqlite sqlite = new Sqlite();
		List<WifiList> wifilist = sqlite.selectWifi(LAT, LNT);
	%>
	<h1>와이파이 정보 구하기</h1>

	<div>
		<a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
		<a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</div>
	<p></p>
	<div>
	<%if(LAT == null && LNT == null) { %>
		<form method="get">
			LAT : <input type="text" name="LAT" id="LAT" value = "0.0">
			LNT : <input type="text" name="LNT" id="LNT" value = "0.0">
			<input type="button" onclick="getposition();" value = "내 위치 가져오기">
			<script type="text/javascript">
			function getposition() {
				navigator.geolocation.getCurrentPosition(function(position) {
					var lat = position.coords.latitude;
					var lon = position.coords.longitude;
					
					document.getElementById("LAT").value = lat;
					document.getElementById("LNT").value = lon;
				});
			}
			</script>
			<input type='submit' value="근처 WIFI 정보 보기">
		</form>
	<% } 
	else {
	%>
		<form method="get">
			LAT:<input type="text" name="LAT" id="LAT" value = <%=LAT%>>
			LNT:<input type="text" name="LNT" id="LNT" value = <%=LNT%>>
			<input type="button" onclick="getposition();" value = "내 위치 가져오기">
			<script type="text/javascript">
			function getposition() {
				navigator.geolocation.getCurrentPosition(function(position) {
					var lat = position.coords.latitude;
					var lon = position.coords.longitude;
					
					document.getElementById("LAT").value = lat;
					document.getElementById("LNT").value = lon;
				});
			}
			</script>
			
			<input type='submit' value="근처 WIFI 정보 보기">
		</form>
	<%
	}
	%>
	</div>
	<p></p>
	<table>
		<thead>
			<tr>
				<th class="head">거리(km)</th>
				<th class="head">관리번호</th>
				<th class="head">자치구</th>
				<th class="head">와이파이명</th>
				<th class="head">도로명주소</th>
				<th class="head">상세주소</th>
				<th class="head">설치위치(층)</th>
				<th class="head">설치유형</th>
				<th class="head">설치기관</th>
				<th class="head">서비스구분</th>
				<th class="head">망종류</th>
				<th class="head">설치년도</th>
				<th class="head">실내외구분</th>
				<th class="head">WIFI접속환경</th>
				<th class="head">X좌표</th>
				<th class="head">Y좌표</th>
				<th class="head">작업일자</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%
					if(LAT != null && LNT != null) {
						sqlite.insertHistory(LAT,LNT);
						for(WifiList list : wifilist) {
				%>
						<tr>
							<td class="body"> 
								<%=Math.round(6371 * Math.acos(Math.cos(Float.parseFloat(list.getLAT()) * 3.141592653589793 / 180.0) * Math.cos(Float.parseFloat(LAT) * 3.141592653589793 / 180.0)
							               * Math.cos((Float.parseFloat(LNT) * 3.141592653589793 / 180.0) - (Float.parseFloat(list.getLNT()) * 3.141592653589793 / 180.0)) + Math.sin(Float.parseFloat(list.getLAT()) * 3.141592653589793 / 180.0)
							               * Math.sin(Float.parseFloat(LAT) * 3.141592653589793 / 180.0)) * 10000)/10000.0
						    	%>
							</td>
							<td class="body"> <%=list.getX_SWIFI_MGR_NO()%></td>
							<td class="body"> <%=list.getX_SWIFI_WRDOFC()%></td>
							<td class="body"> 
								<a href="detail.jsp?X_SWIFI_MGR_NO=<%=list.getX_SWIFI_MGR_NO()%>&LAT=<%=LAT%>&LNT=<%=LNT%>">
									<%=list.getX_SWIFI_MAIN_NM()%>
								</a>
							</td>
							<td class="body"> <%=list.getX_SWIFI_ADRES1()%></td>
							<td class="body"> <%=list.getX_SWIFI_ADRES2()%></td>
							<td class="body"> <%=list.getX_SWIFI_INSTL_FLOOR()%></td>
							<td class="body"> <%=list.getX_SWIFI_INSTL_TY()%></td>
							<td class="body"> <%=list.getX_SWIFI_INSTL_MBY()%></td>
							<td class="body"> <%=list.getX_SWIFI_SVC_SE()%></td>
							<td class="body"> <%=list.getX_SWIFI_CMCWR()%></td>
							<td class="body"> <%=list.getX_SWIFI_CNSTC_YEAR()%></td>
							<td class="body"> <%=list.getX_SWIFI_INOUT_DOOR()%></td>
							<td class="body"> <%=list.getX_SWIFI_REMARS3()%></td>
							<td class="body"> <%=list.getLAT()%></td>
							<td class="body"> <%=list.getLNT()%></td>
							<td class="body"> <%=list.getWORK_DTTM()%></td>
						</tr>
				<%
						}
					}
				%>
			</tr>
		</tbody>
	</table>
	<%
		if(LAT == null && LNT == null) {
	%>
			<div class="init">위치 정보를 입력한 후에 조회해주세요.</div>
	<%
		}
	%>
	
</body>
</html>