<%@ page language="java" import="java.util.*,java.lang.*,java.math.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>登录</title>

<style>
	.input{
            display: block;
            padding: 9px 0 9px 50px;
            width: 250px;
            height: 20px;
            line-height: 20px;
            border: 1px solid #dedede;
            font-size: 12px;
            font-family: Tahoma,Helvetica,"Microsoft Yahei","微软雅黑",Arial,STHeiti;
        }
        form{
            padding: 0 28px;
            margin: 0px auto;
        }
        .div{
            width: 354px;
            height: 387px;
            border: 1px solid #efefef;
            box-shadow: 1px 1px 1px #efefef;
            padding-top: 25px;
            margin: 0 auto;
        }
        #submit{
            height: 40px;
            width:302px;
            font-size: 14px;
            font-weight: 800;
            color: #fff;
            line-height: 40px;
            background: #2e82ff;
            border-radius: 2px;
            border: 0;
        }
        #register{
            float: right;
            font-size: 14px;
            margin-top: 20px;
            margin-right: 26px;
        }
        label{
            border: 1px solid #dedede;
            position: absolute;
            z-index: 3;
            width: 38px;
            height: 38px;
            background: url("img/icons.png") no-repeat;
        }
        #iron_bottom{
            background-position: -48px 0;
        }
</style>
 
</head>

<body>
	    <div class="div">
        <form action="<%=path%>/checkUserLogin.action" method="post">
            <label id="iron_top" for="account"></label>
            <input id="account" class="input"  name="account" placeholder="手机/邮箱/用户名"><br>
            <label id="iron_bottom" for="pwd"></label>
            <input id="pwd" class="input" type="password" name="pwd" placeholder="密码"><br>
            <input id="submit" type="submit" value="登录">
        </form>
        <div id="register"><a href="jsp/register.jsp" style="text-decoration: none">立即注册</a></div>
    </div>
	<script language="JavaScript">
	
		if (window != top)
	
			top.location.href = location.href;
	</script>
</body>
</html>
