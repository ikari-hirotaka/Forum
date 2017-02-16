<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ForumTop</title>
	<link href="css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
こんにちは ${user.name} さん。 <a href="logout">ログアウト</a><br/>
<span>menu</span>

<a href="newPost">新規投稿</a>
<a href="userManage">ユーザー管理</a>


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


<form action="home" method="Get">
<h4 id="target">絞込み</h4>
カテゴリー:
<br/>
<select id="category" name="category">
	<option value="" <c:if test="${defcate==null}">selected</c:if> >すべて
	<c:forEach items="${cate}" var="cate">
		<option value="${cate.category}" <c:if test="${defcate==cate.category}">selected</c:if> > ${cate.category} </option>
	</c:forEach>
</select>
<br/>

日時:
<br/>
<input type="date" id="date1" name="date1" value="${sdate}"/>
～
<input type="date" id="date2" name="date2"value="${gdate}"/>
<br/>
<input type="submit" value="実行"/>

</form>
<br/>
<br/>
<br/>

<c:forEach items="${posts}" var="posts">

 <br/>
<p class="textname"> ${posts.id} </p>
<p class="textname">件名:</p><p class="textmain">${posts.title}</p>

<p class="textname">本文:</p><p class="textmain">${posts.text}</p>

<p class="textname">カテゴリー:</p><p class="textmain">${posts.category}</p>

<p class="textname">投稿日:</p><p class="textmain">${posts.insert_date}</p>

<p class="textname">投稿者:</p><p class="textmain">${posts.name}</p>
<c:if test="${comments!=null}"></c:if>

<c:choose>
	<c:when test="${user.dept==2}">
		<a href="postDelete?postId=${posts.id}">削除</a>
	</c:when>

	<c:when test="${posts.user_id==user.id}">
		<a href="postDelete?postId=${posts.id}">削除</a>
	</c:when>

	<c:when test="${user.store==posts.store&&user.dept==3}">
		<a href="postDelete?postId=${posts.id}">削除</a>
	</c:when>
</c:choose>

<p class="textname">コメント: </p>
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



</body>
</html>