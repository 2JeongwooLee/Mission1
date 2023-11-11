<%@ page import="db.Sqlite" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
	<script type="text/javascript">
      alert("북마크 그룹 정보를 삭제하였습니다.");
      location.href ="bookmark-group.jsp";
    </script>
</head>
<body>

	<%
		String id = request.getParameter("id");
		Sqlite sqlite = new Sqlite();
		sqlite.deleteBookmarkGroup(id);
	%>
</body>
</html>