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
<script type="text/javascript" async="" src="http://www.google-analytics.com/ga.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	var topBtn = $('#page-top');
	topBtn.hide();
	$(window).scroll(function () {
		if ($(this).scrollTop() > 100) {
			topBtn.fadeIn();
		} else {
			topBtn.fadeOut();
		}
	});
	//スクロールしてトップ
    topBtn.click(function () {
		$('body,html').animate({
			scrollTop: 0
		}, 500);
		return false;
    });
});
</script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">

        <div class="navbar-header">
            <a class="navbar-brand">ホーム画面</a>
        </div>

        <ul class="nav navbar-nav">
            <li><a href="newPost">新規投稿</a></li>
            <li><c:if test="${user.dept==1}"><a href="userManage">ユーザー管理</a></c:if></li>


        </ul>

		<ul class="nav navbar-nav navbar-right">
            <li><a class="navbar-brand">こんにちは <c:out value="${user.name}" /> さん。</a></li>
            <li><a href="logout" class="text-info">ログアウト</a></li>

		</ul>

    </div>
</nav>
<br/>
<br/>
<br/>
<br/>

<p id="page-top"><a href="#top" >PAGE TOP</a></p>

<div class="main-contents">
	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages}" var="message">
					<li><code><c:out value="${message}" /></code>
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
<div class="btn-group">
<input type="submit" value="実行" class="btn btn-primary"/>
<a href="./"  class="btn btn-warning">リセット</a>
</div>
</form>
<br/>
<br/>
<br/>

<c:forEach items="${posts}" var="posts">

 <br/>

<p class="textname">件名:</p><p class="textmain"><c:out value="${posts.title}"/></p><br/>

<p class="textname">本文:</p><p class="textmain"><c:out value="${posts.text}"/></p><br/>

<p class="textname">カテゴリー:</p><p class="textmain"><c:out value="${posts.category}"/></p><br/>

<p class="textname">投稿日:</p><p class="textmain"><c:out value="${posts.insert_date}"/></p><br/>

<p class="textname">投稿者:</p><p class="textmain"><c:out value="${posts.name}"/></p><br/>
<c:if test="${comments!=null}"></c:if>
<form action="postDelete" method="Post">
<input type="hidden" id="post_id" name="post_id" value="${posts.id}"/>
<input type="hidden" id="post_user_id" name="post_user_id" value="${posts.user_id}"/>
<input type="hidden" id="post_store" name="post_store" value="${posts.store}"/>
<br/>
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
<br/>
<p class="textname">コメント: </p>
<c:forEach items="${com}" var="com">
<c:if test="${com.post_id==posts.id}">
<form action="CommentDelete" method="Post">
<input type="hidden" id="com_id" name="com_id" value="${com.id}"/>
<input type="hidden" id="com_user_id" name="com_user_id" value="${com.user_id}"/>
<input type="hidden" id="com_store" name="com_store" value="${com.store}"/>
<br/>
 <c:out value="${com.text}"/>
 <br/>
<c:out value="${com.user_name}"/>
<br/>
<c:out value="${com.insert_date}"/>
<br/>
<c:choose>
	<c:when test="${user.dept==2}">
		<input type="submit" value="削除" onClick="return confirm('削除してよろしいですか？')" class="btn btn-danger"/>
	</c:when>

	<c:when test="${com.user_id==user.id}">
		<input type="submit" value="削除" onClick="return confirm('削除してよろしいですか？')" class="btn btn-danger"/>
	</c:when>

	<c:when test="${user.store==com.store&&user.dept==3}">
		<input type="submit" value="削除" onClick="return confirm('削除してよろしいですか？')" class="btn btn-danger"/>
	</c:when>
</c:choose>
<br/>
<br/>
</form>
</c:if>
</c:forEach>



>> <c:out value="${posts.title}"/>
<form action="comment" method="Post">
<input type="hidden" id="post_id" name="post_id" value="${posts.id}"/>
<input type="hidden" id="user_id" name="user_id" value="${user.id}"/>
<textarea id="comment" name="comment" cols="30" rows="10" maxlength="500"><c:if test=" ${!comme.isEmpty()}">${comme}</c:if></textarea>
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

</div>
</body>
</html>