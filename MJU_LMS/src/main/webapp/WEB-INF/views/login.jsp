<%@page import="com.example.LMS.model.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  UserInfo User = (UserInfo) session.getAttribute("User");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>명지대학교 학사관리 시스템</title>
</head>
<body>
<ul>
	<li>
	<%if(User.getReturncode() == 0){%>
		<script language="javascript"> alert("해당하는 유저가 없습니다."); </script>
		로그인 실패
		<a href="${pageContext.request.contextPath}/LoginController/logout.do">돌아가기</a>
	<%}else if(User.getReturncode() == 1){%>
		<script language="javascript"> alert("비밀번호가 틀렸습니다."); </script>
		로그인 실패
		<a href="${pageContext.request.contextPath}/LoginController/logout.do">돌아가기</a>
	<% }else{ %>
		<script language="javascript"> alert("로그인을 환영합니다!"); </script>
		${User.getId()} / ${User.getName()}님, 환영합니다!<br><br>
		<%
			if(User.getCategory().equals("admin")){
		%>
		<strong>관리자 페이지</strong><br>
		관리자 기능은 아직 미구현입니다.
		<%
			}else if(User.getCategory().equals("professor")){
		%>
		<strong>교수 페이지</strong><br>
		<a href="${pageContext.request.contextPath}/CourseController/registForLecturePage">1. 강좌 개설</a><br>
		<a href="${pageContext.request.contextPath}/CourseController/gradeManagerPage">2. 성적 부여</a><br><br>
		<% 
			}else{
		%>
		<strong>학생 페이지</strong><br>
			<a href="${pageContext.request.contextPath}/CourseController/registForCoursePage">1. 수강 신청</a><br>
			<a href="${pageContext.request.contextPath}/CourseController/checkGradePage">2. 성적 열람</a><br><br>
		<% 
			}
		%>
		<a href="${pageContext.request.contextPath}/LoginController/logout.do">로그아웃</a>
	<%} %>
		
	</li>
</ul> 
</body>
</html>