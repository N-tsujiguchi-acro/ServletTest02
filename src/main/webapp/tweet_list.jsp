<%@page import="java.util.List"%>
<%@page import="jp.co.aforce.beans.Tweet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ツイート一覧</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
	<div class="container">
		<h1>ツイート一覧</h1>
		<%-- 新規投稿 --%>
		<p>
			<a href="new_tweet.jsp">新規投稿</a>
		</p>

		<%-- ツイート一覧の表示 --%>
		<ul class="tweet-list">
	<%
		Boolean success = (Boolean) session.getAttribute("success");
		if (success != null) {
			if (success == true) {
		%>
			<p>投稿に成功しました！</p>
		<%
			} else {
		%>
			<p>投稿に失敗しました！</p>
		<%
			}
			session.removeAttribute("success");
		}
	%>
			<li>
				<div class="tweet-content">
					<p>content（ツイート内容）</p>
					<p class="tweet-info">
					
						<% List<Tweet> list = (List<Tweet>)request.getAttribute("tweets");%>
						<%
						for(Tweet t : list){
						%>
						<%= t.getAuthor() %> : <%= t.getPostedAt() %> :<%= t.getContent() %> <br>
						<%
						}
						%>
					</p>
				</div>
			</li>
		</ul>
	</div>
</body>
</html>
