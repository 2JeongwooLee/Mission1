<%@page import="db.WifiList"%>
<%@page import="db.Okhttp3"%>
<%@page import="db.Sqlite"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>

<body>

	<%
		Sqlite sqlite = new Sqlite();
		sqlite.deleteWifi();
		int cnt = 0;
		int startNum = 1;
		int lastNum = 1000;
		
		while(true) {
			try{
				WifiList[] list = Okhttp3.Get(startNum, lastNum);
				for(int i = 0; i < list.length; i++) {
					sqlite.insertWifi(list[i]);
					cnt++;
				}
				startNum += 1000;
				lastNum += 1000;
			} catch(Exception e) {
				break;
			}
		}
		
	%>
	<h1><center><%=cnt%>개의 WIFI정보를 정상적으로 저장하였습니다.</center></h1>
	<a href="index.jsp"><center>홈으로 가기</center></a>
</body>
</html>