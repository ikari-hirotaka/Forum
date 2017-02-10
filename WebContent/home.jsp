<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ForumTop</title>

<script type="text/javascript">

<!--
function menu(){
	document.getElementById("new").style="";
	document.getElementById("manage").style="";
	document.getElementById("out").style="";

}
function outMenu(){
	document.getElementById("new").style="visibility:hidden";
	document.getElementById("manage").style="visibility:hidden";
	document.getElementById("out").style="visibility:hidden";
}

-->

</script>
</head>
<body>
こんにちは ${user.name} さん。 <a href="logout">ログアウト</a><br/>
<span onMouseOver="menu()" onMouseOut="outMenu()">menu</span>

<div id="new" style="visibility:hidden"><a href="newPost" onMouseOver="menu()" onMouseOut="outMenu()">新規投稿</a></div>
<div id="manage" style="visibility:hidden"><a href="userManage" onMouseOver="menu()" onMouseOut="outMenu()">ユーザー管理</a></div>


<c:forEach items="${posts}" var="posts">
${posts.id}
 <br/>

title:${posts.title}
<br/>
text: <br/><br/>${posts.text}
<br/><br/>
category:${posts.category}
<br/>
insert_date:${posts.insert_date}
<br/>
name:${posts.name}
<c:if test="${comments!=null}"></c:if>
<br/>
Comment:<br/>
>> ${posts.id}
<form action="comment" method="Post">
<input type="hidden" id="post_id" name="post_id" value="${posts.id}"/>
<input type="hidden" id="user_id" name="user_id" value="${user.id}"/>
<textarea id="comment" name="comment" cols="30" rows="10" maxlength="500"></textarea>
<br/>
<input type="submit" value="コメント送信"/>
</form>
<br/>
<br/>

</c:forEach>


</body>
</html>