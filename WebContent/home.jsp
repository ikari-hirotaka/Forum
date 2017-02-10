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
<div align="right"><a href="logout">ログアウト</a></div>
<div onMouseOver="menu()" onMouseOut="outMenu()">menu</div>
<div id="new" style="visibility:hidden" onMouseOver="menu()" onMouseOut="outMenu()"><a href="newPost">新規投稿</a></div>
<div id="manage" style="visibility:hidden" onMouseOver="menu()" onMouseOut="outMenu()"><a href="userManage">ユーザー管理</a></div>

<br/>
<br/>
<c:forEach items="${posts}" var="posts">
title: <br/>${posts.title}
<br/>
text: <br/>${posts.text}
<br/>
category: <br/>${posts.category}
<br/>
insert_date: <br/>${posts.insert_date}
<br/>
name: <br/>${posts.name}
<br/>
<br/>

</c:forEach>

</body>
</html>