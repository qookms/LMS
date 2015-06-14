<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>명지대학교 학사관리 시스템</title>
</head>
<body>
<h1>회원가입 페이지</h1>

	<a href="${pageContext.request.contextPath}/">돌아가기</a>
	<form action="${pageContext.request.contextPath}/LoginController/join.do"  method="post">
	<table border=1>
		<tr>
			<td>사용자아이디</td>
			<td><input type="text" name="ID" placeholder="ID를 입력하세요."></td>
		</tr>
		<tr>
			<td>성함</td>
			<td><input type="text" name="Name" placeholder="이름을 입력하세요."></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="Password" placeholder="비밀번호를 입력하세요."></td>
		</tr>
		<tr>
			<td>분류</td>
			<td><input type="radio" name="Category" value="student" checked>학생
				<input type="radio" name="Category" value="professor">교수
				<input type="radio" name="Category" value="admin">관리자
			</td>
		</tr>
	</table>
	<input type="submit" value="가입하기">
</form>
</body>
</html>