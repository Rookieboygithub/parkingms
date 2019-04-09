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
	<!--导航栏-->
	<%-- <div style="line-height:35px">
		<span class="layui-breadcrumb"> <a href="jsp/welcome.html">首页</a>
			<a>包租婆管理</a> <a><cite>查看出租订单</cite></a>
		</span>
		<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-sm"
				onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
		</div>
		<hr class="layui-bg-gray" style="margin-top: 0">
	</div> --%>
	
	<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-normal"
				onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
	</div>
	
	<div style="margin: 0 auto">
		<form class="layui-form layui-form-pane">
			<label class="layui-form-label">搜索</label>
			<div class="layui-input-inline">
				<input type="text" placeholder="车位编号" class="layui-input" id='carNo'>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="车位地址" class="layui-input" id='location'>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="招租客用户名" class="layui-input" id='tenementname'>
			</div>
			<div class="layui-input-inline">
				<select name="status" id='status' lay-filter='*' >
					<option value="">订单状态</option>
					<option value="0">待支付</option>
					<option value="1">已支付</option>
				</select>
			</div>
			<div class="layui-input-inline">
				<button class="layui-btn layui-btn-normal" type='button' >
					<i class="layui-icon">&#xe615;</i>
				</button>
			</div>
		</form>
	</div>
	
	<div style="text-align: center">
		<table class="layui-table">
			<thead>
				<tr>
					<td colspan="7">预约信息</td>
					<td colspan="5">招租客信息</td>
					<td rowspan="2" style='width:166px'>操作</td>
				</tr>
				<tr>	
					<td class="dealid" style="display:none">交易ID</td>
					<td>ID</td>
					<td>车位信息</td>
					<td>出租开始时间</td>
					<td>出租结束时间</td>
					<td>价格</td>
					<td>预订时间</td>
					<td>支付状态</td>
					<td>用户名</td>
					<td>职业</td>
					<td>电话</td>
					<td>信誉度</td>
					<td>留言</td>
				</tr>
			</thead>
			<tbody id="carlist">
				<c:forEach items="${list}" var="data" varStatus="status">
					<tr>
						<td class="dealid" style="display:none">${data.id}</td>
						<td class='carId'>${data.landlordCar.id}</td>
						<td>${data.landlordCar.location}${data.landlordCar.carNo}</td>
						<td><fmt:formatDate
								value="${data.beginTime}" pattern="yyyy-MM-dd HH:mm" /></td>
						<td><fmt:formatDate
								value="${data.endTime}" pattern="yyyy-MM-dd HH:mm" /></td>
						<td>${data.price}</td>
						<td><fmt:formatDate
								value="${data.orderTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td class="td-status">${data.status}</td>
						<td>${data.tenement.alias}</td>
						<td>${data.tenement.career}</td>
						<td>${data.tenement.tel}</td>
						<td class='credit'>${data.tenement.credit}</td>
						<td>${data.message}</td>
						<td class='td-manage'>
							<button class='layui-btn layui-btn-normal layui-btn-xs agreebtn'
								onclick='agreeOrder(this)'>
								<i class='layui-icon'>&#xe642;</i>同意预约
							</button>
							<button class='layui-btn layui-btn-normal layui-btn-xs cancelbtn'
								onclick='cancelOrder(this)'>
								<i class='layui-icon'>&#xe642;</i>取消预约
							</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<input type="hidden" id="userId" value="${userId}">
	<input type="hidden" id="totalPage" value="${total}">
	<div id="page" style='margin-bottom:20px'></div>
	<!-- 底部固定区域 -->
	<div class="layui-layout-admin">
		<div class="layui-footer" style="left:0;text-align: center;">
			<p>Copyright ©2018 CBD车位管理系统 v1.0 All Rights Reserved.</p>
		</div>
	</div>
	<script src="lib/layuinew/layui.js"></script>
	<script src="js/jQuery-2.2.2.js"></script>
	<script>
		// 分页参数
		var currentPage=1;
		var limitPage = 5;
		// 加载layui模块
		layui.use([ 'form','element', 'layer'], function() {
			var form = layui.form,
				element = layui.element, //面包导航
				layer = layui.layer;
			
			// 监听select选择事件
			form.on('select(*)', function(data){
				search(1,limitPage);
			});
		});
		// 搜索栏文本框失去焦点时发送AJAX查询
		$(".layui-form input").change(function(){
			search(1,limitPage);
		})
		// 加载layui分页模块，并显示分页栏
		addPage();
		// 将layui分页封装成一个函数
		function addPage() {
			layui.use('laypage', function() {
				var laypage = layui.laypage;
				//执行一个laypage实例
				lypage = laypage.render({
					elem : 'page', //注意，这里的 test1 是 ID，不用加 # 号
					count : $("#totalPage").val(), //数据总数，从服务端得到
					limit : limitPage, //每页显示的条数
					curr : currentPage,
					limits : [ 5, 10, 15 ],
					layout : [ 'prev', 'page', 'next', 'count', 'limit', 'skip' ],
					jump : function(obj, first) {
						//obj包含了当前分页的所有参数，比如：
						//obj.curr得到当前页，以便向服务端请求对应页的数据。
						//obj.limit得到每页显示的条数
						currentPage = obj.curr;
						limitPage =  obj.limit;
						//首次不执行
						if (!first) {
							showList();
						}
					}
				});
			});
		}
		// 根据请求的页数curr及每页显示数量limit发送AJAX请求显示对应页信息
		function showList(curr,limit){
			$.ajax({
				url : "<%=path%>/showBookingByPage.action",
				type : "post",
				dataType : "json",
				data : {
					curr : currentPage,
					limit : limitPage,
					"landlord.id":$("#userId").val(), //包租婆id
					"landlordCar.CarNo":$("#carNo").val(),
					"landlordCar.location":$("#location").val(),
					"tenement.alias":$("#tenementname").val(),
					 status:$("#status").val()
				},
				success : function(result) {
					var data = result.list;
					var str = "";
					if(data.length==0){
						layer.msg('没有符合的预约信息', {
							icon : 5,
							time : 2000
						});
					}
					for (var i = 0; i < data.length; i++) {
						str += "<tr><td class='dealid' style='display:none'>"+data[i].id+"</td>"+
							"<td class='carId'>" + data[i].landlordCar.id + "</td>" +
							"<td>" + data[i].landlordCar.location+data[i].landlordCar.carNo + "</td>" +
							"<td>" + data[i].beginTime + "</td>" +
							"<td>" + data[i].endTime + "</td>" +
							"<td>" + data[i].price + "</td>" +
							"<td>" + data[i].orderTime + "</td>" +
							"<td class='td-status'>" + showStatus(data[i].status+'') + "</td>" +
							"<td>" + data[i].tenement.alias + "</td>" +
							"<td>" + data[i].tenement.career + "</td>" +
							"<td>" + data[i].tenement.tel + "</td>" +
							"<td>" + showCreditStar(data[i].tenement.credit)+((1-data[i].tenement.credit)*100) + "</td>" +
							"<td>" + data[i].message + "</td>" +
							"<td class='td-manage'><button class='layui-btn layui-btn-normal layui-btn-xs agreebtn' onclick='agreeOrder(this)'>"+
								"<i class='layui-icon'>&#xe642;</i>同意预约</button><button class='layui-btn layui-btn-normal layui-btn-xs cancelbtn'"+
								" onclick='cancelOrder(this)'><i class='layui-icon'>&#xe642;</i>取消预约</button></td>";
					}
					$("#carlist").html(str);
					checkButtonDisable();
				}
			});
		}
		// 将表格中订单状态栏的数据由对应数字转换为文字描述
		$(".td-status").each(function(){
			var status = $(this).html();
			$(this).html(showStatus(status));
		})
		// 将订单状态数字转换为文字描述
		function showStatus(status){
			var x;
			switch(status){
			case "0":
				x="<button class='layui-btn layui-btn-xs layui-btn-danger btn-status'>待支付</button>";
				break;
			case "1":
				x="<button class='layui-btn layui-btn-xs layui-btn-normal btn-status'>已支付</button>";
				break;
			}
			return x;
		}
		// 根据支付状态判断是否disable按钮
		checkButtonDisable();
		function checkButtonDisable(){
			$(".agreebtn").each(function() {
				var status = $(this).parents("tr").find(".btn-status").html();
				if (status == "待支付") {
					$(this).addClass('layui-btn-disabled');
					$(this).attr('disabled', true);
				}
			})
		}
		// 点击同意预约按钮的函数
		function agreeOrder(data){
			layer.confirm('确定要同意本次预约吗？', {icon: 3, title:'提示'}, function(index){
				var dealId = $(data).parents("tr").find(".dealid").html();
				$.ajax({
				url : "<%=path%>/dealwithDeal.action",
				type : "post",
				dataType : "json",
				data : {
					"id":dealId,
					"status":2,
				},
				success : function(result) {
					if(result.result=="success"){
						layer.msg('操作成功，本次交易已生效，您已经成功租出了该车位!', {icon: 1});
						$(data).parents("tr").remove();
						var total = $("#totalPage").val();
						$("#totalPage").val(total-1);
						addPage();
						showList();
					} else {
						layer.msg('操作失败，请稍后再试!', {icon: 2});
					}
				}
			});
				layer.close(index);
			});
		}
		// 点击取消预约按钮的函数
		function cancelOrder(data){
			layer.confirm('确定要取消本次预约吗？', {icon: 3, title:'提示'}, function(index){
				var dealId = $(data).parents("tr").find(".dealid").html();
				$.ajax({
				url : "<%=path%>/dealwithDeal.action",
				type : "post",
				dataType : "json",
				data : {
					"id":dealId,
					"status":3
				},
				success : function(results) {
					var result = results.result;
					if(result=="success"){
						$(data).parents("tr").remove();
						var total = $("#totalPage").val();
						$("#totalPage").val(total-1);
						addPage();
						showList();
						if(results.dealNo != null){
							layer.msg('操作成功，本次预约已取消，预约车位回到待租状态!系统正在处理退款，请稍后', {icon: 1});
							refund(results.dealNo,results.amount);
						} else {
							layer.msg('操作成功，本次预约已取消，预约车位回到待租状态!', {icon: 1});
						}
					} else {
						layer.msg('操作失败，请稍后再试!', {icon: 2});
					}
				}
			});
				layer.close(index);
			});
		}
		// 为表格中的信誉度添加五星显示
		$(".credit").each(function(){
			var status = $(this).html();
			$(this).html(showCreditStar(parseFloat(status))+((1-parseFloat(status))*100));
		})
		//为信誉度添加五星显示
		function showCreditStar(credit) {
			var i = (1 - credit) * 5;
			var j = parseInt(i);
			var k = 0;
			var str = "";
			for (var z = 1; z <= j; z++) {
				str += "<i class='layui-icon layui-icon-rate-solid' style='color: #FFB800;'></i>";
			}
			if ((i - j) > 0.5) {
				str += "<i class='layui-icon layui-icon-rate-half' style='color: #FFB800;'></i>";
				k++;
			}
			for (var z = 1; z <= (5 - j - k); z++) {
				str += "<i class='layui-icon layui-icon-rate' style='color: #FFB800;'></i>";
			}
			return str;
		}
		// 点击搜索按钮的函数
		function search(curr,limit){
			$.ajax({
				url : "<%=path%>/showBookingByPage.action",
				type : "post",
				dataType : "json",
				data : {
					curr : curr,
					limit : limit,
					"landlord.id":$("#userId").val(), //包租婆id
					"landlordCar.CarNo":$("#carNo").val(),
					"landlordCar.location":$("#location").val(),
					"tenement.alias":$("#tenementname").val(),
					 status:$("#status").val()
				},
				success : function(result) {
					var data = result.list;
					var str = "";
					if(data.length==0){
						layer.msg('没有符合的预约信息', {
							icon : 5,
							time : 2000
						});
					}
					for (var i = 0; i < data.length; i++) {
						str += "<tr><td class='dealid' style='display:none'>"+data[i].id+"</td>"+
							"<td class='carId'>" + data[i].landlordCar.id + "</td>" +
							"<td>" + data[i].landlordCar.location+data[i].landlordCar.carNo + "</td>" +
							"<td>" + data[i].beginTime + "</td>" +
							"<td>" + data[i].endTime + "</td>" +
							"<td>" + data[i].price + "</td>" +
							"<td>" + data[i].orderTime + "</td>" +
							"<td class='td-status'>" + showStatus(data[i].status+'') + "</td>" +
							"<td>" + data[i].tenement.alias + "</td>" +
							"<td>" + data[i].tenement.career + "</td>" +
							"<td>" + data[i].tenement.tel + "</td>" +
							"<td>" + showCreditStar(data[i].tenement.credit)+((1-data[i].tenement.credit)*100) + "</td>" +
							"<td>" + data[i].message + "</td>" +
							"<td class='td-manage'><button class='layui-btn layui-btn-normal layui-btn-xs agreebtn' onclick='agreeOrder(this)'>"+
								"<i class='layui-icon'>&#xe642;</i>同意预约</button><button class='layui-btn layui-btn-normal layui-btn-xs cancelbtn'"+
								" onclick='cancelOrder(this)'><i class='layui-icon'>&#xe642;</i>取消预约</button></td>";
					}
					$("#carlist").html(str);
					checkButtonDisable();
					if(result.total != $("#totalPage").val()){
						$("#totalPage").val(result.total);
						currentPage = curr;
						limitPage = limit;
						addPage(); // 重要，当currentPage超过最大页数时，会取最大页数
					}
				}
			});
		}
		// 处理退款
		function refund(dealNo,amount){
			$.ajax({
				url : "<%=path%>/pay_refund.action",
				type : "post",
				dataType : "json",
				data : {
					no : dealNo,
					price : amount,
				},
				success : function(result) {
					if(result=="success"){
						layer.msg('系统退款已完成!', {icon: 1});
					} else {
						layer.msg('由于网络原因，退款失败!请联系管理员处理或者致电支付宝客服', {icon: 2});
					}
				}
			});
		}
	</script>
</body>
</html>