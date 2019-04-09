<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>注册页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<meta name="keywords" content="" />
<link href="${pageContext.request.contextPath}/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" media="all">
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
<link
	href="http://fonts.googleapis.com/css?family=Raleway:400,500,600,700,800,900"
	rel="stylesheet">
</head>
<body>
	<div class="signupform">
		<h1>注册页面</h1>
		<div class="container">

			<div class="agile_info">
				<div class="w3l_form">
					<div class="left_grid_info">
						<h3>欢迎注册！</h3>
						<h4>请在右侧输入注册信息</h4>
						<p>注意：注册信息中身份证号码、真实姓名及身份一旦确定，不可更改，请谨慎填写！</p>
						<ul class="social_section_1info">

						</ul>
					</div>
				</div>
				<div class="w3_info">
					<h2>创建账户</h2>
					<form>
						<div class="input-group">
							<span><i class="fa fa-user" aria-hidden="true"></i></span> <input
								id="account" type="text" placeholder="请输入注册账户" required=""
								onblur="check1()">
						</div>
						<div class="tips"
							style="height:18px;line-height: 18px;font-size: 14px;color: red"></div>
						<div class="input-group">
							<span><i class="fa fa-lock" aria-hidden="true"></i></span> <input
								id="pwd" type="Password" placeholder="请输入密码" required=""
								onblur="check_pwd()">
						</div>
						<div class="tips"
							style="height:18px;line-height: 18px;font-size: 14px;color: red"></div>
						<div class="input-group">
							<span><i class="fa fa-lock" aria-hidden="true"></i></span> <input
								id="verifyPwd" type="Password" placeholder="请再次输入密码" required=""
								onblur="check_repwd()">
						</div>
						<div class="tips"
							style="height:18px;line-height: 18px;font-size: 14px;color: red"></div>
						<div class="input-group">
							<span><i class="fa fa-user" aria-hidden="true"></i></span> <input
								id="name" type="text" placeholder="请输入真实姓名" required=""
								onblur="check_name()">
						</div>
						<div class="tips"
							style="height:18px;line-height: 18px;font-size: 14px;color: red"></div>
						<div class="input-group">
							<span><i class="fa fa-user" aria-hidden="true"></i></span> <input
								id="cardno" type="text" placeholder="请输入身份证号码" required=""
								onblur="check_cardno()">
						</div>
						<div class="tips"
							style="height:18px;line-height: 18px;font-size: 14px;color: red"></div>
						<div class="input-group">
							<span><i class="fa fa-user" aria-hidden="true"></i></span> <input
								id="address" type="text" placeholder="家庭地址" required=""
								onblur="check_addr">
						</div>
						<div class="tips"
							style="height:18px;line-height: 18px;font-size: 14px;color: red"></div>
						<div class="input-group">
							<span><i class="fa fa-user" aria-hidden="true"></i></span> <input
								id="phone" type="text" placeholder="请输入电话号码" required=""
								onblur="check_tel()">
						</div>
						<div class="tips"
							style="height:18px;line-height: 18px;font-size: 14px;color: red"></div>
						<div class="input-group">
							<span><i class="fa fa-envelope" aria-hidden="true"></i></span> <input
								id="email" type="text" placeholder="请输入邮箱" required=""
								onblur="check_email()">
						</div>
						<div class="tips"
							style="height:18px;line-height: 18px;font-size: 14px;color: red"></div>
						<div class="input-group">
							<span><i class="fa fa-envelope" aria-hidden="true"></i></span> <input
								id="career" type="text" placeholder="请输入职业描述" required="">
						</div>
						<div class="tips"
							style="height:18px;line-height: 18px;font-size: 14px;color: red"></div>
						身份：<input type="radio" name="iden" value="3" />
						<h4>招租客</h4>
						<input type="radio" name="iden" value="2" />
						<h4>包租婆</h4>
						<div class="tips"
							style="height:18px;line-height: 18px;font-size: 14px;color: red"></div>
						<button class="btn btn-danger btn-block" type="button"
							onclick="sub()">创建账户</button>
					</form>
				</div>
				<div class="clear"></div>
			</div>

		</div>
		<div class="footer">


			<p>Copyright &copy; 2018.Company name All rights reserved.</p>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/jQuery-2.2.2.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/layer.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/theme/default/layer.css"></script>
	<script type="text/javascript">
		function sub() {
			check_iden();
			if (check1() && check_pwd() && check_repwd() && check_name() && check_cardno() && check_tel() && check_email() && check_addr() && check_iden()) {
				$.ajax({
					url : "../register.action",
					type : "post",
					dataType : "json",
					data : {
						account : $("#account").val(),
						address : $("#address").val(),
						cardno : $("#cardno").val(),
						career : $("#career").val(),
						email : $("#email").val(),
						name : $("#name").val(),
						phone : $("#phone").val(),
						pwd : $("#pwd").val(),
						verifyPwd : $("#verifyPwd").val(),
						character : $('input:radio[name="iden"]:checked').val()
					},
					success : function(data) {
						if (data.rs == "fail" || data.rs == null) {
							layer.alert('注册失败,请重新注册!', {
								icon : 1,
								skin : 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
							});
							window.location.href = "${pageContext.request.contextPath}/jsp/register.jsp";
						} else if (data.rs == "repeat") {
							$(".tips").eq(0).html("您输入的账号已存在,请重新输入!");
						} else if (data.rs == "success") {
						 layer.open({
							 area: 'auto',
                            content: '恭喜您已注册成功！',
                            btn: ['确认'],
                            yes: function(index, layero) {
                                window.location.href='${pageContext.request.contextPath}/jsp/login.jsp';
                            },
                            btn2: function(index, layero) {
     
                            }
                            ,
                            cancel: function() {
              
     
                            }
                        });
							
						}
					}
				})
			}
		}
		/* 对注册信息进行前端验证 */
		function check1() {
			var account = $("#account").val();
			var reg = /^[a-zA-Z0-9_-]{6,16}$/;
			if (!reg.test(account)) {
				$(".tips").eq(0).html("请输入6-16文英文或数字字符");
				return false;
			} else {
				$(".tips").eq(0).html("");
				return true;
			}
		}
		function check_pwd() {
			var reg = /^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)\S{6,16}$/;
			var pwd = $("#pwd").val();
			if (!reg.test(pwd)) {
				$(".tips").eq(1).html("请输入6-16位数字和字母组合密码！");
				return false;
			} else {
				$(".tips").eq(1).html("");
				return true;
			}
		}
		function check_repwd() {
			var pwd = $("#pwd").val();
			var repwd = $("#verifyPwd").val();
			if (pwd != repwd) {
				$(".tips").eq(2).html("两次密码输入不一致");
				return false;
			} else {
				$(".tips").eq(2).html("");
				return true;
			}
		}
		function check_name() {
			var name = $("#name").val();
			var reg = /^[\u4e00-\u9fa5]{2,5}$/;
			if (!reg.test(name)) {
				$(".tips").eq(3).html("请输入2-5个中文字符");
				return false;
			} else {
				$(".tips").eq(3).html("");
				return true;
			}
		}
		function check_cardno() {
			var cardno = $("#cardno").val();
			var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
			if (!reg.test(cardno)) {
				$(".tips").eq(4).html("请输入18位正确身份证号码");
				return false;
			} else {
				$(".tips").eq(4).html("");
				return true;
			}
		}
		function check_tel() {
			var tel = $("#phone").val();
			var reg = /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-9]))\d{8}$/;
			if (!reg.test(tel)) {
				$(".tips").eq(6).html("请输入正确的电话号码！");
				return false;
			} else {
				$(".tips").eq(6).html("");
				return true;
			}
		}
		function check_email() {
			var reg = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
			var email = $("#email").val();
			if (!reg.test(email)) {
				$(".tips").eq(7).html("请输入正确的邮箱！");
				return false;
			} else {
				$(".tips").eq(7).html("");
				return true;
			}
		}
		function check_addr() {
			var addr = $("#address").val();
			if (addr == "" || addr == null) {
				$(".tips").eq(5).html("地址不能为空");
				return false;
			} else {
				$(".tips").eq(5).html("");
				return true;
			}
		}
		function check_iden() {
			var iden = $('input:radio[name="iden"]:checked').val();
			if (iden == "undefined" || iden == "" || iden == null) {
				$(".tips").eq(9).html("请选择您的身份！");
				return false;
			} else {
				$(".tips").eq(9).html("");
				return true;
			}
		}
	</script>
</body>
</html>
