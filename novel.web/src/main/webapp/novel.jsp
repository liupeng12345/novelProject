<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script src="js/jquery-1.12.4.min.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<style>
*#head {
	background-color: #000000;
}

body {
	font-family: Lato, "Helvetica Neue", Helvetica, Arial, "Microsoft Yahei",
		"微软雅黑", sans-serif;
	text-shadow: 0 1px 3px rgba(0, 0, 0, .5);
	font-size: 21px;
}
</style>
<body onload="find();">

	<div class="container">
		<div class="row">
			<div class="clo-md-2 col-md-offset-10">
				<a href="checkweather.jsp">天气</a> <a href="login.jsp">登录</a>&nbsp; <a
					href="register.jsp">注册</a>
				<h4 hidden="hidden">欢迎！</h4>
			</div>
		</div>

		<div class="row" id="AppBar">
			<ul class="nav nav-tabs">
				<li role="presentation" class="active"><a href="novel.jsp">小说</a>
				</li>
				<li role="presentation"><a href="film.jsp">电影</a></li>
				<li role="presentation"><a href="joke.jsp">段子</a></li>
				<li role="presentation"><a href="game.jsp">游戏</a></li>
			</ul>
		</div>
		<div></div>
		<div class="page-header" style="background-image: url(img/bg.jpg);">
			<h1>
				小说网<small>寻找最初的快乐</small>
			</h1>
		</div>
		<img src="img/test.jpg" style="float: lift; width: 15%;" />
		<div class="row" style="width: 87%; float: right;">

			<div class="row">
				<form class="form-inline" id="from1">
					<div class="clo-md-4 col-md-offset-3">
						<input type="text" class="form-control" style="width: 300px;"
							id="keyword" name="keyword"
							value="<%=request.getParameter("keyword") == null ? "" : request.getParameter("keyword")%>" />
						<input type="button" class="btn btn-primary" value="搜索" id="btn">
						<input type="hidden" id="page" value="0" />

					</div>
				</form>
			</div>

			<br> <br>
			<div class="row" style="width: 87%; float: right; height: 100%">
				<div>
					<table class="table">
						<tr>
							<th>#</th>
							<th>书名</th>
							<th>作者</th>
							<th>最新章节</th>
							<th>状态</th>
						</tr>
						<tbody id="list">
						</tbody>
						<tr>
							<td><a href="javascript:up();">上一页</a></td>
							<td><a href="javascript:f();">下一页</a></td>
							<tr>
					
					</table>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			var keyword = document.getElementById("keyword").value;
			$(function() {

				$("#btn").click(f);

			})
			function find() {
				if (keyword != "") {
					f();
				}
			}
			function up() {
				$("#page").val($("#page").val() - 2);

				f();
			}
			function f() {
				var a = $("#keyword").val().trim();
				if (a == "") {
					alert("请输入：");
					return;
				}
				$.ajax({
					url : "./novel.do",
					type : "POST",
					dataType : "json",
					data : {
						"keyword" : a,
						"page" : $("#page").val()
					},

					success : function(inf) {
						var msg = "";
						var ins = eval(inf.data.novels);
						$.each(ins, function(i) {
							msg += "<tr><td>" + (i + 1) + "</td><td>"
									+ "<a href='list.jsp?url=" + ins[i].url
									+ "'>" + ins[i].name + "</a>" + "</td><td>"
									+ ins[i].author + "</td><td>"
									+ ins[i].lastUpdateChapter + "</td><td>"
									+ ins[i].status + "</td></tr>"
						});
						$("#list").html(msg);
						$("#page").val(inf.data.page);
					},
					error : function() {
						alert("请求失败！");
					},
				});
			}
		</script></
						body>
</html>