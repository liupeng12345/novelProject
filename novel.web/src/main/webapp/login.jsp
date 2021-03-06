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
		<title></title>
		<style type="text/css">
			body {
				padding-top: 50px;
				padding-bottom: 40px;
				background-color: #f5f5f5;
			}
			
			.form-signin {
				max-width: 400px;
				padding: 29px 29px 29px;
				margin: 0 auto 20px;
				background-color: #fff;
				border: 1px solid #e5e5e5;
				-webkit-border-radius: 5px;
				-moz-border-radius: 5px;
				border-radius: 5px;
				-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
				-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
				box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
			}
			
			.form-signin .form-signin-heading,
			.form-signin .checkbox {
				margin-bottom: 10px;
				width: 200px;
			}
			
			.form-signin input[type="text"],
			.form-signin input[type="password"] {
				font-size: 16px;
				height: auto;
				margin-bottom: 15px;
				padding: 7px 9px;
			}
		</style>
	</head>

	<body>
		<div class="container">

			<form class="form-signin"  action="./login.do" name="form1" method="post">
				<h3 class="form-signin-heading">请登录</h3>
				<label class="text-left">用户名：<input type="text" class="input-block-level" placeholder="用户名" name="username" onkeyup="test();"></label>
				<label class="text-left">密&nbsp;&nbsp;&nbsp;码：<input type="password" class="input-block-level" placeholder="密码" name="password" onkeyup="test();" ></label>
				<label class="text-left">验证码：<input type="text" class="input-block-level" placeholder="验证码" name="code" id="tcode" style="width: 100px;" onkeyup="test(); ">&nbsp;&nbsp;&nbsp;<img src="code.action" width="80" id="code" src="code.action" onclick="changeCode();" height="40"><span id="info" aria-hidden="true"></span></label>
				<label class="checkbox">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" onclick="test();"/>记住密码 </label>
				<input class="btn btn-large btn-primary" type="submit" value="登录" id="submit" disabled="disabled" style="width: 250px;">
			</form>

		</div>
		<script>
			function test() {
				for(var i = 0; i < $(".input-block-level").length; i++) {
					if($(".input-block-level")[i].value.trim() == "") {
						return;
					}
				}
				$.ajax({
					url : "./testCode.do",
					type : "POST",
					dataType : "json",
					data :{
						"code" :$("#tcode").val()
					},
					success : function(inf) {
						
						$("#info").attr("class","glyphicon glyphicon-ok");
						$("#submit").removeAttr("disabled");
						
					},
					error : function() {
						$("#info").attr("class","glyphicon glyphicon-remove");
					},
					
				});
			
			}
			function changeCode(){
				$("#code").attr("src","code.action?t="+Date.now());
			}
			
		</script>
	</body>
</html>