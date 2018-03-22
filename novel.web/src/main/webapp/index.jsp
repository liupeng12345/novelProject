<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<title>主页</title>
		<style>
			*#head {
				background-color: #000000;
			}
			body {
				font-family: Lato, "Helvetica Neue", Helvetica, Arial, "Microsoft Yahei", "微软雅黑", sans-serif;
				text-shadow: 0 1px 3px rgba(0,0,0,.5);
            	font-size: 21px;
			}
		</style>
	</head>

	<body>
		<div id="head">
			<div class="container">
				<div class="row">
					<div class="clo-md-2 col-md-offset-10">
						<a href="checkweather.jsp">天气</a>
						<a href="login.jsp">登录</a>&nbsp;
						<a href="register.jsp">注册</a>
						<h4 hidden="hidden">欢迎！</h4>
					</div>
				</div>

				<div class="row" id="AppBar">
					<ul class="nav nav-tabs">
						<li role="presentation" class="active">
							<a href="novel.jsp">小说</a>
						</li>
						<li role="presentation">
							<a href="film.jsp">电影</a>
						</li>
						<li role="presentation">
							<a href="joke.jsp">段子</a>
						</li>
						<li role="presentation">
							<a href="game.jsp">游戏</a>
						</li>
					</ul>
				</div>
				<div>

				</div>

			</div>
		</div>
		<img src="img/35571199.jpg" id="bg" style="height:100%;width: 100% ;" align="center">
	</body>
	<script>
	</script>

</html>