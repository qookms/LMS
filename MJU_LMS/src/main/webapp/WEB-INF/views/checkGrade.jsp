<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.example.LMS.model.*"%>

<%
	ArrayList<Grade> allGrades = (ArrayList<Grade>) request.getAttribute("allGrades");
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<link
	href="${pageContext.request.contextPath}/resources/stylesheets/registForCourse.css"
	rel="stylesheets">

<title>명지대학교 학사관리 시스템</title>
</head>
<body>
<h1>명지대학교 학사관리시스템 - 성적 열람 화면</h1>
<a href="${pageContext.request.contextPath}/LoginController/logout.do">로그아웃</a>
	<table border = 1>
		
		<tr>
			<td>강좌번호</td>
			<td>과목명</td>
			<td>담당교수</td>
			<td>개설년도</td>
			<td>학년</td>
			<td>학점</td>
			<td>정원</td>
			<td>평점</td>
		</tr>
		<%for (Grade g : allGrades) {%>
		<tr>
			<td><%=g.getLecture_id()%></td>
			<td><%=g.getLecture_name()%></td>
			<td><%=g.getLecture_professor()%></td>
			<td><%=g.getLecture_year()%></td>
			<td><%=g.getLecture_level()%></td>
			<td><%=g.getLecture_score()%></td>
			<td><%=g.getLecture_limit()%></td>
			<td><%=g.getGrade()%></td>
		</tr>
		<%}%>
	</table>				
</body>
</html>
<script type="text/javascript">

</script>
