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
	<a href="signup">ユーザー新規登録</a><br/>
	<a href="edit">ユーザー編集</a>
	<div class="users">
	<table border="1">
		<tr>
			<td>ID</td>
			<td>Name</td>
			<td>State</td>
			<td>Switch</td>
		</tr>
		<c:forEach items="${users}" var="users">

			<form action="userManage" method="post">
				<div class="users">
				<div class="loginid-name">
					<input type="hidden"id="id" name="id" value="${users.id}"/>
					<input type="hidden"id="state" name="state" value="${users.state}"/>

					<tr>
					<td><span class="loginid"><c:out value="${users.loginid}" /></span></td>
					<td><span class="name"><c:out value="${users.name}" /></span></td>
					<td><c:if test="${users.state==0}">
					<div>通常</div>
					</c:if>
					<c:if test="${users.state==1}">
					<div>停止</div>
					</c:if></td>

					<td><c:if test="${users.state==0}">
						<input type="submit" value="停止" onClick="return confStop()"/>
						</c:if>
						<c:if test="${users.state==1}">
						<input type="submit" value="復活" onClick="return confStart()"/>
						</c:if></td>

					<td><input type="button" value="編集" onClick="document.location"/></td>

					</tr>
				</div>
				</div>
			</form>
		</c:forEach>
	</table>
	</div>
</body>

</html>