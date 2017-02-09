<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿画面</title>
</head>
<body>

	<form action="newPost" method="post">

		件名<br/>
		<input type="text" id="title" name="title" maxlength="50"/><br/>

		本文<br/>
		<textarea id="text" name="text" cols="35" rows="10" maxlength="1000"></textarea><br/>

		カテゴリー<br/>
		<input type="text" id="category" name="category" maxlength="10"/><br/>

		<input type="submit" value="送信"/>

	</form>


</body>
</html>