<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理画面</title>

<!-- Bootstrap CDN -->
<link rel="stylesheet"
    href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
    href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<!-- Bootstrap CDN -->

	<link href="css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">

<!--
function confStop(){
	if(confirm('ユーザーを停止しますか？')){
		return true;
	}else{
		window.alert('キャンセルしました。')
		return false;
	}
}
function confStart(){
	if(confirm('ユーザーを復活させますか？')){
		return true;
	}else{
		window.alert('キャンセルしました。')
		return false;
	}
}

//-->

</script>
</head>

<body>
<h3>ユーザー管理画面</h3>
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

	<a href="./" class="btn btn-info">ホーム</a>
	<a href="signup" class="btn btn-info">ユーザー新規登録</a><br/><br/>

	<div class="container">
		<table class="table table-striped table-bordered info" >
			<tr>
				<td align="center">ID</td>
				<td align="center">名前</td>
				<td align="center">所属</td>
				<td align="center">役職</td>
				<td align="center">状態</td>
				<td align="center">編集</td>
			</tr>
			<c:forEach items="${users}" var="users">

				<form id="form" name="form" action="userManage" method="post">
					<div class="users">

						<input type="hidden"id="id" name="id" value="${users.id}"/>
						<input type="hidden"id="state" name="state" value="${users.state}"/>

						<tr>
							<td align="center">
								<span class="loginid"><c:out value="${users.login_id}" /></span>
							</td>

							<td align="center">
								<span class="name"><c:out value="${users.name}" /></span>
							</td>

							<td align="center">
								<span class="store_name"><c:out value="${users.store_name}" /></span>
							</td>
							<td align="center">
								<span class="department_name"><c:out value="${users.department_name}" /></span>
							</td>
							<td align="center">
								<c:if test="${users.state==0}">
									<div>通常</div>
								</c:if>
								<c:if test="${users.state==1}">
									<div>停止</div>
								</c:if>
							</td>

							<td align="center">

								<c:if test="${users.state==0&&users.id!=loginUser.id}">
									<input type="submit" value="停止" onClick="return confStop()" class="btn btn-warning"/>
								</c:if>

								<c:if test="${users.state==1&&users.id!=loginUser.id}">
									<input type="submit" value="復活" onClick="return confStart()" class="btn btn-success"/>
								</c:if>

								<a href="edit?id=${users.id}" class="btn btn-info">編集</a>

								<c:if test="${users.id!=loginUser.id}">
								<a href="delete?id=${users.id}" onClick="return confirm('${users.name}を削除してよろしいですか？')"  class="btn btn-danger">削除</a>
								</c:if>
							</td>
						</tr>
					</div>
				</form>
			</c:forEach>

		</table>
	</div>

	</div>
	<!-- Bootstrap CDN -->
    <script
        src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<!-- Bootstrap CDN -->

</body>

</html>