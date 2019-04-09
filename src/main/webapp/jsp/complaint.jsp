<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>表单模块 - layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">

<link rel="stylesheet" href="./lib/layuinew/css/layui.css">

<style>
body {
	padding: 20px;
}
</style>
</head>
<script type="text/javascript" src="js/jQuery-2.2.2.js"></script>
<body>
	<!-- 导航栏 -->
	<div>
		<span class="layui-breadcrumb"> <a href="jsp/welcome.html">首页</a>
			<a>投诉</a> <a><cite>投诉信息详情</cite></a>
		</span>
		<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-sm"
				onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
		</div>
		<hr class="layui-bg-gray">
	</div>
	<div style="width: 620px;border: solid 2px #e6e6e6;padding: 20px">
		<form class="layui-form layui-form-pane">
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 608px"> 车位信息 </label>
			</div>
			<div class="layui-form-item">
				<div class="layui-form-label">小区地址</div>
				<div class="layui-input-inline">
					<div id="location" class="layui-input">${requestScope.Complain[0].location}</div>
				</div>
				<div class="layui-form-label">车位编号</div>
				<div class="layui-input-inline">
					<div id="carNumber" class="layui-input">${requestScope.Complain[0].carNumber}</div>
				</div>
				<div class="layui-form-label">开始时间</div>
				<div class="layui-input-inline">
					<div id="begintime" class="layui-input">${requestScope.Complain[0].begintime}</div>
				</div>
				<div class="layui-form-label">结束时间</div>
				<div class="layui-input-inline">
					<div id="endtime" class="layui-input">${requestScope.Complain[0].endtime}</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 608px">
					投诉人个人信息 </label>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 用户名 </label>
				<div class="layui-input-inline">
					<div id="alias" class="layui-input">${requestScope.Complain[0].alias}</div>
				</div>
				<label class="layui-form-label"> 姓名 </label>
				<div class="layui-input-inline">
					<div id="name" class="layui-input">${requestScope.Complain[0].name}</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 身份证号码 </label>
				<div class="layui-input-inline">
					<div id="cardno" class="layui-input">${requestScope.Complain[0].cardno}</div>
				</div>
				<label class="layui-form-label"> 电话号码 </label>
				<div class="layui-input-inline">
					<div id="tel" class="layui-input">${requestScope.Complain[0].tel}</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 家庭地址 </label>
				<div class="layui-input-inline" style="width: 500px">
					<div id="ulocation" class="layui-input">${requestScope.Complain[0].carNumber}</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 职业 </label>
				<div class="layui-input-inline">
					<div id="career" class="layui-input">${requestScope.Complain[0].career}</div>
				</div>
				<label class="layui-form-label"> 申请理由 </label>
				<div class="layui-input-inline">
					<div id="reason1" class="layui-input">${requestScope.Complain[0].reason}</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 608px">
					被投诉人个人信息 </label>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 用户名 </label>
				<div class="layui-input-inline">
					<div id="byalias" class="layui-input">${requestScope.Complain[0].byalias}</div>
				</div>
				<label class="layui-form-label"> 姓名 </label>
				<div class="layui-input-inline">
					<div id="byname" class="layui-input">${requestScope.Complain[0].byname}</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 身份证号码 </label>
				<div class="layui-input-inline">
					<div id="bycardno" class="layui-input">${requestScope.Complain[0].bycardno}</div>
				</div>
				<label class="layui-form-label"> 电话号码 </label>
				<div class="layui-input-inline">
					<div id="bytel" class="layui-input">${requestScope.Complain[0].bytel}</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 家庭地址 </label>
				<div class="layui-input-inline" style="width: 500px">
					<div id="bylocation" class="layui-input">${requestScope.Complain[0].carNumber}</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 职业 </label>
				<div class="layui-input-inline">
					<div id="bycareer" class="layui-input">${requestScope.Complain[0].bycareer}</div>
				</div>

			</div>
			

		</form>
		<form class="layui-form" >
            <select id="choice" >
                <option value="0">请选择处理结果</option>
                <option value="2">通过</option>
                <option value="1">不通过</option>
            </select>
            <hr class="hr15">
            <input name="dealreason" placeholder="请输入处理理由"  class="layui-input" >
            <hr class="hr15">
            <input value="确定" onclick="dealComplain()" style="width:100%;" type="button">
            <hr class="hr20" >
        </form>
	</div>

	<!-- 底部固定区域 -->
	<div class="layui-layout-admin">
		<div class="layui-footer" style="left:0;text-align: center;">
			<p>Copyright ©2018 CBD车位管理系统 v1.0 All Rights Reserved.</p>
		</div>
	</div>


	<script src="./lib/layuinew/layui.js"></script>
	<script>
    layui.use(['element','form'],function(){
        var element = layui.element,
            form = layui.form;
    });
    function dealComplain(){
			$.ajax({
				url : "<%=path%>/dealComplain.action",
				type : "post",
				dataType : "json",
				data : {
					deal : jQuery("#choice option:selected").val(),
					dealreason : $("input[name='dealreason']").val(),
					id : ${requestScope.Complain[0].id}
				},
				success : function(data) {
					alert(data);
					window.location.href="jsp/allComplaint.jsp";
				}
			})

		}
    
</script>
</body>
</html>
