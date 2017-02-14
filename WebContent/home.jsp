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

function narrow(){
	var element = document.getElementById( "target" ) ;
	var rect = element.getBoundingClientRect() ;
	var positionX = rect.left + window.pageXOffset ;
	var positionY = rect.top + window.pageYOffset ;

	window.scrollTo( positionX, positionY ) ;
}

-->

</script>
</head>
<body>
こんにちは ${user.name} さん。 <a href="logout">ログアウト</a><br/>
<span onMouseOver="menu()" onMouseOut="outMenu()">menu</span>

<div id="new" style="visibility:hidden"><a href="newPost" onMouseOver="menu()" onMouseOut="outMenu()">新規投稿</a></div>
<div id="manage" style="visibility:hidden"><a href="userManage" onMouseOver="menu()" onMouseOut="outMenu()">ユーザー管理</a></div>



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
<h4 onClick="narrow()">絞込み↓</h4>
<br/>
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
<c:forEach items="${com}" var="com">
<c:if test="${com.post_id==posts.id}">
>> ${com.post_id}
<br/>
 ${com.text}
 <br/>
 ${com.user_name}
<br/>
${com.insert_date}
<br/>
</c:if>
</c:forEach>



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

<h4 id="target">絞込み</h4>
<br/>
<form action="home" method="Get">
カテゴリー:
<br/>
<input type="text" id="cate" name="cate" maxlength=10/>
<br/>

日時:
<br/>
<input type="date" id="date1" name="date1"/>
～
<input type="date" id="date2" name="date2"/>
<br/>
<input type="submit" value="実行"/>

</form>
<br/>
<br/>
<br/>

</body>
</html>