<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>预约车位</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">

<link rel="stylesheet" href="lib/layuinew/css/layui.css">

<style>
body {
	padding: 20px;
}
</style>
</head>
<body>
	<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-normal"
				onclick="window.history.back(-1)">
				<i class="layui-icon">&#xe65c;</i>
			</button>
			<button class="layui-btn layui-btn-normal"
				onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
	</div>
	
	<input type="hidden" id="carid" value="${data.landlordCar.id}" />
	<input type="hidden" id="landlordid" value="${data.landlord.id}" />
	<div style="width: 620px;border: solid 2px #e6e6e6;padding: 20px;margin:20px auto">
		<form class="layui-form layui-form-pane">
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 608px"> 车位信息 </label>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 车位编号 </label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input"
						value="${data.landlordCar.carNo}" disabled>
				</div>
				<label class="layui-form-label"> 价格 </label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" value="${data.price}元/小时"
						disabled>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 车位地址 </label>
				<div class="layui-input-inline" style="width: 500px">
					<input type="text" class="layui-input"
						value="${data.landlordCar.location}" disabled>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 招租开始 </label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="begin"
						value="<fmt:formatDate
								value="${data.beginTime}" pattern="yyyy-MM-dd HH:mm" />"
						disabled>
				</div>
				<label class="layui-form-label"> 招租结束 </label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="end"
						value="<fmt:formatDate
								value="${data.endTime}" pattern="yyyy-MM-dd HH:mm" />"
						disabled>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 608px"> 预约信息 </label>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 出租开始 </label>
				<div class="layui-input-inline">
					<input class="layui-input" placeholder="出租开始时间" id="beginTime">
				</div>
				<label class="layui-form-label"> 出租结束 </label>
				<div class="layui-input-inline">
					<input class="layui-input" placeholder="出租结束时间" id="endTime">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="height: 200px;line-height: 200px"> 留言 </label>
				<div class="layui-input-inline" style="width: 500px">
					<textarea id="message" class="layui-input" style="height: 200px;resize: none"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 总金额 </label>
				<div class="layui-input-inline">
					<input type="text" id="totalPrice" class="layui-input" disabled>
				</div>
				<div class="layui-input-inline">
					<button class="layui-btn layui-btn-normal" type="button" onclick="order()">立即预约</button>
				</div>
			</div>

		</form>
	</div>
	<!-- 底部固定区域 -->
	<div class="layui-layout-admin">
		<div class="layui-footer" style="left:0;text-align: center;">
			<p>Copyright ©2018 CBD车位管理系统 v1.0 All Rights Reserved.</p>
		</div>
	</div>
	<script src="lib/layuinew/layui.js"></script>
	<script src="js/jQuery-2.2.2.js"></script>
	<script>
		layui.use(['laydate', 'layer','util' ], function() {
			var laydate = layui.laydate,
				layer = layui.layer,
				util = layui.util;
	
			
			//执行一个laydate实例
			var start = laydate.render({
				elem : '#beginTime', //指定元素
				type : 'datetime',
				min : new Date($("#begin").val())>new Date()?$("#begin").val():util.toDateString(new Date(), "yyyy-MM-dd HH:mm:ss"),
				max : $("#end").val(),
				done : function(value,date) {
					var begin = new Date(value);
					var end = new Date($("#endTime").val());
					if(end <= begin){
						layer.msg('开始时间不能大于结束时间，请重新选择', {icon: 2, time: 2000});
					}
				}
			});
	
			var end = laydate.render({
				elem : '#endTime', //指定元素
				type : 'datetime',
				min : new Date($("#begin").val())>new Date()?$("#begin").val():util.toDateString(new Date(), "yyyy-MM-dd HH:mm:ss"),
				max : $("#end").val(),
				done : function(value,date) {
					var begin = new Date($("#beginTime").val());
					var end = new Date(value);
					if(end <= begin){
						layer.msg('结束时间不能小于开始时间，请重新选择', {icon: 2, time: 2000});
					}
					calculatePrice(begin,end);
				}
			});
		});
		//根据选择的开始时间和结束时间计算总价
		function calculatePrice(begin,end){
			var stime = Date.parse(begin);
			var etime = Date.parse(end);
			if(etime > stime){
				var hour = Math.ceil((etime - stime)/(1000*3600));
				var total = hour*${data.price};
				$('#totalPrice').val(total);
			}
		}
		// 点击预约按钮的函数
		function order(){
			if($("#beginTime").val()==''||$("#endTime").val()==''){
				layer.msg('必填项不能为空', {icon: 2, time: 2000});
				return;
			}
			var begin = new Date($("#beginTime").val());
			var end = new Date($("#endTime").val())
			if(end <= begin){
				layer.msg('结束时间不能小于开始时间，请重新选择', {icon: 2, time: 2000});
				return;
			}
			$.ajax({
				url : "<%=basePath%>/orderDeal.action",
				type : "post",
				dataType : "json",
				data : {
					beginTime:$("#beginTime").val(),
					endTime:$("#endTime").val(),
					price:$('#totalPrice').val(),
					"landlordCar.id":$('#carid').val(),
					"landlord.id":$('#landlordid').val(),
					message:$('#message').val(),
					'tenement.id':${userId}
				},
				async: true,
				success : function(data) {
					if(data!=null && data.result==true){
						layer.msg('预约成功!正在跳转支付界面,请稍后', {icon: 6, time: 3000});
						setTimeout(function(){
							window.location.href='<%=path%>/pay.action?dealid='+data.dealId;
						}, 3000);
					} else {
						layer.msg('预约失败!可能该车位已预约、已撤销出租或者已删除,请尝试刷新页面后重试', {icon: 5, time: 2000});
					}
				}
			});
		}
	</script>
</body>
</html>
