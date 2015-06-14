<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.example.LMS.model.*"%>

<%
	ArrayList<Course> allCourses = (ArrayList<Course>) request.getAttribute("allCourses");
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
<h1>명지대학교 학사관리시스템 - 수강내역</h1>
<a href="${pageContext.request.contextPath}/LoginController/logout.do">로그아웃</a>
	<table border = 1>
		
		<tr>
			<td>강좌번호</td>
			<td>과목명</td>
			<td>담당교수</td>
			<td>연도</td>
			<td>학년</td>
			<td>학점</td>
			<td>정원</td>
			<td>취소하기</td>
		</tr>
		<%for (Course c : allCourses) {%>
		<form action="${pageContext.request.contextPath}/CourseController/deleteCourse" method="post">
		<tr>
			<td><input type="hidden" name="lecture_id" value=<%=c.getLecture_id() %>><%=c.getLecture_id()%></td>
			<td><%=c.getLecture_name()%></td>
			<td><%=c.getLecture_professor_name()%></td>
			<td><%=c.getLecture_year()%></td>
			<td><%=c.getLecture_level()%></td>
			<td><%=c.getLecture_score()%></td>
			<td><%=c.getLecture_limit()%></td>
			<td>
				<button class="btn_registForCourse">취소하기</button>
			</td>
		</tr>
		</form>
		<%}%>
		<a href="${pageContext.request.contextPath}/CourseController/registForCoursePage">수강신청 메인화면</a>
	</table>				
</body>
</html>
<script type="text/javascript">

</script>
