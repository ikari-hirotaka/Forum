<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿画面</title>
</head>
<body>
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
	<form action="newPost" method="post">

		件名<br/>
		<input type="text" id="title" name="title" maxlength="50"/><br/>

		本文<br/>
		<textarea id="text" name="text" cols="35" rows="10" maxlength="1000"></textarea><br/>

		カテゴリー<br/>
		<input type="text" id="category" name="category" maxlength="10"/><br/>

		<input type="submit" value="送信"/><br/>
		<a href="home">戻る</a>

	</form>


</body>
</html>