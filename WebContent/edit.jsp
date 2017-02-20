<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー編集</title>
	<!-- Bootstrap CDN -->
<link rel="stylesheet"
    href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
    href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<!-- Bootstrap CDN -->
	<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container">

        <div class="navbar-header">
            <a class="navbar-brand">ユーザー編集画面</a>
        </div>

    </div>
</nav>

<div class="main-contents">

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>

</c:if>

<form action="edit" method="post"><br/>

	<input type="hidden" id="id" name="id" value="${userInf.id}">
	<input type="hidden" id="old_login" name="old_login" value="${userInf.login_id}">

	<label for="loginid">ID</label><br/>
	<input name="loginid" id="loginid" maxlength="20" value="${userInf.login_id}"/>（半角英数6文字以上20文字以下）<br/><br/>

	<label for="pass1">パスワード(変更する場合のみ)</label><br/>
	<input name="pass1" type="password" id="pass1" maxlength="255"/>（6文字以上255文字以下）<br/><br/>

	<label for="pass2">パスワード(再入力)</label><br/>
	<input name="pass2" type="password" id="pass2" maxlength="255"/> <br/><br/>

	<label for="name">名前</label><br/>
	<input name="name"  id="name" maxlength="10" value="${userInf.name}"/>（10文字以内）<br/><br/>

<c:choose>
	<c:when test="${userInf.id!=loginUser.id}">

		<label for="store">店舗</label><br/>
		<select name="store" id="store">
			<option value="1"<c:if test="${userInf.store==1}">selected</c:if>>本社</option>
			<option value="2"<c:if test="${userInf.store==2}">selected</c:if>>支店A</option>
			<option value="3"<c:if test="${userInf.store==3}">selected</c:if>>支店B</option>
			<option value="4"<c:if test="${userInf.store==4}">selected</c:if>>支店C</option>
		</select>
		<br/><br/>

		<label for="dept">役職</label><br/>
		<select name="dept"id="dept">
			<option value="1"<c:if test="${userInf.dept==1}">selected</c:if>>総務人事担当者</option>
			<option value="2"<c:if test="${userInf.dept==2}">selected</c:if>>情報管理担当者</option>
			<option value="3"<c:if test="${userInf.dept==3}">selected</c:if>>店長</option>
			<option value="4"<c:if test="${userInf.dept==4}">selected</c:if>>社員</option>

		</select>

	</c:when>
	<c:when test="${userInf.id==loginUser.id}">
		<input type="hidden" name="store" id="store" value="${userInf.store}"/>
		<input type="hidden" name="dept" id="dept" value="${userInf.dept}"/>
	</c:when>
	</c:choose>

	<br/><br/>

	<input type="submit" value="登録"  class="btn btn-primary"/> <br /><br />
	<a href="userManage">戻る</a>
	</div>

</form>
	<!-- Bootstrap CDN -->
    <script
        src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<!-- Bootstrap CDN -->


</body>
</html>
