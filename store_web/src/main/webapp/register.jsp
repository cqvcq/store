<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">


	<title>
		XX商城 | Log in</title>


	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
	<!-- Bootstrap 3.3.6 -->
	<!-- Font Awesome -->
	<!-- Ionicons -->
	<!-- Theme style -->
	<!-- iCheck -->
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

	<link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugins/ionicons/css/ionicons.min.css">
	<link rel="stylesheet" href="plugins/adminLTE/css/AdminLTE.css">
	<link rel="stylesheet" href="plugins/iCheck/square/blue.css">
	<style type="text/css">
		.form-group{
			display:flex;
		}
	</style>
</head>

<body class="hold-transition register-page">
<div class="register-box">
	<div class="register-logo">


		<a href="all-admin-index.html">XX商城</a>


	</div>

	<div class="register-box-body">
		<p class="login-box-msg">新用户注册</p>
		<form id="registerForm" method="post">
			<span id="spanUsername">&nbsp;</span>
			<div class="form-group has-feedback">
				<input type="text" class="form-control" placeholder="用户名" id="username" name="username" onchange="isUniqueUsername()">
				<span class="glyphicon glyphicon-user form-control-feedback"></span>
			</div>
			<div class="form-group has-feedback">
				<input type="email" class="form-control" placeholder="Email" id="email" name="email" >
				<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
			</div>
			<div class="form-group has-feedback">
				<input type="text" class="form-control" placeholder="邮箱验证码" id="verifyCode" name="verifyCode" onchange="isEquals()" >
				<button  class="btn btn-primary btn-flat" id="sendVerifyCode">&nbsp;&nbsp;发送验证码&nbsp;&nbsp;</button>
			</div>

			<div class="form-group has-feedback">
				<input type="password" class="form-control" placeholder="密码" id="password" name="password">
				<span class="glyphicon glyphicon-lock form-control-feedback"></span>
			</div>
			<div class="form-group has-feedback">
				<input type="password" class="form-control" placeholder="确认密码" id="rePassword" name="rePassword" onchange="isEqualsPassword()">
				<span class="glyphicon glyphicon-log-in form-control-feedback" ></span>
			</div>
			<div class="row">
				<div class="col-xs-8">
					<div class="checkbox icheck">
						<label>
							<input id="agree" type="checkbox"> 我同意 <a href="#">协议</a>
						</label>
					</div>
				</div>
				<!-- /.col -->
				<div class="col-xs-4">
					<button type="button" onclick="register()" id="registerBtn" class="btn btn-primary btn-block btn-flat">注册</button>
				</div>
				<!-- /.col -->
			</div>
		</form>

		<a href="${pageContext.request.contextPath}/login" class="text-center">我有账号，现在就去登录</a>
	</div>
	<!-- /.form-box -->
</div>
<!-- /.register-box -->

<!-- jQuery 2.2.3 -->
<!-- Bootstrap 3.3.6 -->
<!-- iCheck -->
<script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="plugins/iCheck/icheck.min.js"></script>
<script>
    $(function() {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
    //用户名是否唯一
    function isUniqueUsername() {
		var username = $("#username").val();
		$.ajax({
			url:"${pageContext.request.contextPath}/user/isUniqueUsername",
			data:{"username":username},
			type:"post",
			success:function (res) {
                if(res.flag==false){
                    $("#spanUsername").html("用户名重复，请重新输入");
                    $("#username").attr("style","border:red 1px solid");
                    $("#registerBtn").prop("disabled",true);
                }else{
                    $("#spanUsername").html("&nbsp;");
                    $("#username").attr("style","");
                    $("#registerBtn").prop("disabled",false);
                }
            }

		});
    }
    //发送验证码
    $("#sendVerifyCode").click(function () {
        var params = "emailAddress="+$("#email").val();
		$.post('${pageContext.request.contextPath}/mail/send',params,function (res) {
			console.log(res);
			if(res.flag==false){
			    alert("邮箱不正确,请重新输入")
			}else{
				countdownHandler();
				return;
			}
        });
		return false;
    });
    //倒计时
    var countdownHandler = function(){
        var $button = $("#sendVerifyCode");
        var number = 60;
        var countdown = function(){
            if (number == 0) {
                $button.attr("disabled",false);
                $button.html("&nbsp;&nbsp;发送验证码&nbsp;&nbsp;");
                number = 60;
                return;
            } else {
                $button.attr("disabled",true);
                $button.html(number + "秒 重新发送");
                number--;
            }
            setTimeout(countdown,1000);
        };
        setTimeout(countdown,1000);
    };
    //验证码是否相同
    function isEquals() {
		var verifyCode = $("#verifyCode").val();
		console.log(verifyCode);
		$.ajax({
			url:"${pageContext.request.contextPath}/user/isEquals",
			data:{"verifyCode":verifyCode},
			type:"post",
			success:function (res) {
				if(res.flag == false){
				    alert("验证码错误");
                    $("#verifyCode").attr("style","border:red 1px solid");
                    $("#registerBtn").prop("disabled",true);
				}else{
                    $("#verifyCode").attr("style","");
                    $("#registerBtn").prop("disabled",false);
				}
            }
		});
    }
    //密码是否相同
	function isEqualsPassword() {
		var password = $("#password").val();
		var rePassword = $("#rePassword").val();
		console.log(password);
		console.log(rePassword);
		if(password==""|rePassword==""|password==rePassword){
            $("#password").attr("style","");
            $("#rePassword").attr("style","");
            $("#registerBtn").prop("disabled",false);
		}else{
            alert("2次密码输入不一致");
            $("#password").attr("style","border:red 1px solid");
            $("#rePassword").attr("style","border:red 1px solid");
            $("#registerBtn").prop("disabled",true);
		}
    }
    //注册
	function register() {
		var username = $.trim($("#username").val());
		var email = $.trim($("#email").val());
        var verifyCode = $.trim($("#verifyCode").val());
		var password = $.trim($("#password").val());
		var rePassword = $.trim($("#rePassword").val());
		// var checked = $("#agree").parent("div").attr("aria-checked");
		var params = {
		    "username":username,
			"email":email,
			"password":password
		};
		console.log(params);
		if(username==""){
			alert("请输入用户名");
		}
        if(email==""){
            alert("请输入邮箱");
        }
        if(verifyCode==""){
            alert("请输入验证码");
        }
        if(password==""){
            alert("请输入密码");
        }
        if(rePassword==""){
            alert("请确认密码");
        }
        if(password!=rePassword){
            alert("2次密码输入不一致");
		}else{
            $.ajax({
                url:"${pageContext.request.contextPath}/user/register",
                data:params,
                type:"post",
                success:function (res) {
                    if(res.flag==true){
                        alert(res.message);
                        location.href="${pageContext.request.contextPath}/login";
                    }else{
                        alert(res.message);
                    }
                }
            });
        }
    }
</script>
</body>

</html>