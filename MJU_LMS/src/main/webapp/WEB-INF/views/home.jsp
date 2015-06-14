<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>명지대학교 학사관리 시스템</title>
</head>
<body>
<H1>학사관리 시스템</H1><br>
1. 로그인
<form action="${pageContext.request.contextPath}/LoginController/login.do" method="POST">
	<input type="text" name="ID">
	<input type="password" name="Password"><br>
	<button>로그인</button><br><br>
2. 회원가입
</form><a href="${pageContext.request.contextPath}/LoginController/joinPage.do">회원가입</a> 
</body>
</html>