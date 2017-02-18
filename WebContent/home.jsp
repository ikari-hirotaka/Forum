<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ForumTop</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
    href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
    href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<!-- Bootstrap CDN -->
	<link href="css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
  <div class="col-xs-3">こんにちは ${user.name} さん。</div><div class="col-xs-2"><a href="logout">ログアウト</a></div></div><br/>
<span>menu</span>

<a href="newPost">新規投稿</a>
<c:if test="${user.dept==1}"><a href="userManage">ユーザー管理</a></c:if>


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


<form action="./" method="Get">
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
<input type="submit" value="実行" class="btn btn-primary"/>
<a href="./">リセット</a>

</form>
<br/>
<br/>
<br/>

<c:forEach items="${posts}" var="posts">

 <br/>

<p class="textname">件名:</p><p class="textmain">${posts.title}</p>

<p class="textname">本文:</p><p class="textmain">${posts.text}</p>

<p class="textname">カテゴリー:</p><p class="textmain">${posts.category}</p>

<p class="textname">投稿日:</p><p class="textmain">${posts.insert_date}</p>

<p class="textname">投稿者:</p><p class="textmain">${posts.name}</p>
<c:if test="${comments!=null}"></c:if>
<form action="postDelete" method="Post">
<input type="hidden" id="post_id" name="post_id" value="${posts.id}"/>
<input type="hidden" id="post_user_id" name="post_user_id" value="${posts.user_id}"/>
<input type="hidden" id="post_store" name="post_store" value="${posts.store}"/>
<c:choose>
	<c:when test="${user.dept==2}">
		<input type="submit" value="削除" onClick="return confirm('削除してよろしいですか？')" class="btn btn-danger"/>
	</c:when>

	<c:when test="${posts.user_id==user.id}">
		<input type="submit" value="削除" onClick="return confirm('削除してよろしいですか？')" class="btn btn-danger"/>
	</c:when>

	<c:when test="${user.store==posts.store&&user.dept==3}">
		<input type="submit" value="削除" onClick="return confirm('削除してよろしいですか？')" class="btn btn-danger"/>
	</c:when>
</c:choose>
</form>

<p class="textname">コメント: </p>
<c:forEach items="${com}" var="com">
<c:if test="${com.post_id==posts.id}">

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



>> ${posts.title}
<form action="comment" method="Post">
<input type="hidden" id="post_id" name="post_id" value="${posts.id}"/>
<input type="hidden" id="user_id" name="user_id" value="${user.id}"/>
<textarea id="comment" name="comment" cols="30" rows="10" maxlength="500"><c:if test=" ${!comme.isEmpty()}"> ${comme}</c:if></textarea>
<br/>
<input type="submit" value="コメント送信" class="btn btn-primary"/>
</form>
<br/>
<br/>

</c:forEach>

	<!-- Bootstrap CDN -->
    <script
        src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<!-- Bootstrap CDN -->


</body>
</html>