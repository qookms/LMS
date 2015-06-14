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
<h1>명지대학교 학사관리시스템 - 성적 부여</h1>
<a href="${pageContext.request.contextPath}/LoginController/logout.do">로그아웃</a>
	<table border = 1>
		<tr>
			<td>학생</td>
			<td>성적</td>
			<td></td>
		</tr>
		<%for (Grade g : allGrades) {%>
		<form action="${pageContext.request.contextPath}/CourseController/gradeAssign" method="post">
		<tr>
			<td><input type="hidden" name="student_id" value=<%=g.getStudent_id()%>>
			<input type="hidden" name="lecture_id" value=<%=g.getLecture_id()%>><%=g.getStudent_id()%></td>
			<td><%=g.getGrade()%></td>
			<td>
				<input type="text" name="Grade" placeholder="성적을 입력하세요.">
				<button class="btn_registForCourse">성적 부여</button>
			</td>
		</tr>
		</form>
		<%}%>
		<a href="${pageContext.request.contextPath}/CourseController/gradeManagerPage">성적 부여 메인 화면</a>
	</table>				
</body>
</html>
<script type="text/javascript">

</script>
