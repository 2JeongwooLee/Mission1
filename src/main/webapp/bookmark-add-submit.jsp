<%@ page import="db.Sqlite" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
	<script type="text/javascript">
      alert("북마크 정보를 추가하였습니다.");
      location.href ="bookmark-list.jsp";
    </script>
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
		String bookmarkName = request.getParameter("bookmark-select");
		String wifiName = request.getParameter("wifiname");
		Sqlite sqlite = new Sqlite();
		sqlite.insertBookmarkList(bookmarkName, wifiName);
	%>
</body>
</html>