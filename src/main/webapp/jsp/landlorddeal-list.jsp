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
<title>出租订单</title>
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
	<div style="line-height:35px;display:none" id="topelement">
		<span class="layui-breadcrumb"> <a href="javascript:top.location.reload()">首页</a>
			<a>包租婆管理</a> <a><cite>查看出租订单</cite></a>
		</span>
		<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-sm"
				onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
		</div>
		<hr class="layui-bg-gray" style="margin-top: 0">
	</div>
	
	<div class="layui-input-inline" style="float:right" id="top">
			<button class="layui-btn layui-btn-normal"
				onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
	</div>
	
	<div style="margin: 0 auto">
		<form class="layui-form layui-form-pane">
			<label class="layui-form-label">搜索</label>
			<div class="layui-input-inline">
				<input type="text" placeholder="车位地址" class="layui-input" id='location'>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="车位编号" class="layui-input" id='carNo'>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="包租婆用户名" class="layui-input" id='landlordname'>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="招租客用户名" class="layui-input" id='tenementname'>
			</div>
			<div class="layui-input-inline">
				<select name="status" id='status' lay-filter='*' >
					<option value="">订单状态</option>
					<option value="0">待支付</option>
					<option value="1">已支付</option>
					<option value="2">已成交</option>
					<option value="3">已失效</option>
					<option value="4">已过期</option>
				</select>
			</div>
			<div class="layui-input-inline">
				<button class="layui-btn layui-btn-normal" type='button' id="searchbutton">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</div>
		</form>
	</div>
	
	<div style="text-align: center">
		<table class="layui-table" lay-even>
			<thead>
				<tr>
					<td>序号</td>
					<td>交易编号</td>
					<td class="dealid" style="display:none">交易ID</td>
					<td>出租开始时间</td>
					<td>出租结束时间</td>
					<td>价格</td>
					<td>车位信息(车位地址+车位编号)</td>
					<td class="td-landlord">包租婆用户名</td>
					<td class="td-landlordid" style="display:none">包租婆ID</td>
					<td class="td-tenement">抢租客用户名</td>
					<td class="td-tenementid" style="display:none">抢租客ID</td>
					<td>交易状态</td>
					<td>预订时间</td>
					<td>成交时间</td>
					<td class='td-manage'>操作</td>
				</tr>
			</thead>
			<tbody id="carlist">
				<c:forEach items="${list}" var="data" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${data.no}</td>
						<td class="dealid" style="display:none">${data.id}</td>
						<td><fmt:formatDate value="${data.beginTime}"
								pattern="yyyy-MM-dd HH:mm" /></td>
						<td><fmt:formatDate value="${data.endTime}"
								pattern="yyyy-MM-dd HH:mm" /></td>
						<td>${data.price}</td>
						<td>${data.landlordCar.location}${data.landlordCar.carNo}</td>
						<td class="td-landlord">${data.landlord.alias}</td>
						<td class="td-landlordid" style="display:none">${data.landlord.id}</td>
						<td class="td-tenement">${data.tenement.alias}</td>
						<td class="td-tenementid" style="display:none">${data.tenement.id}</td>
						<td class="td-status">${data.status}</td>
						<td><fmt:formatDate value="${data.orderTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${data.doneTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td class='td-manage'>
							<button
								class='layui-btn layui-btn-normal layui-btn-xs complainbtn'
								onclick='submitComplain(this)'>
								<i class='layui-icon'>&#xe642;</i>我要投诉
							</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<input type="hidden" id="userId" value="${sessionScope.userId}">
	<input type="hidden" id="userRole" value="${sessionScope.character}">
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
		// 加载layui模块
		layui.use([ 'form','element', 'layer'], function() {
			var form = layui.form,
				element = layui.element, //面包导航
				layer = layui.layer;

			
			// 监听select选择事件
			form.on('select(*)', function(data){
				search();
			});
		});
		// 加载layui分页模块，并显示分页栏
		addPage();
		// 搜索栏文本框失去焦点时发送AJAX查询
		$(".layui-form input").change(function(){
			search();
		})
		// 将layui分页封装成一个函数
		function addPage() {
			layui.use('laypage', function() {
				var laypage = layui.laypage;
				//执行一个laypage实例
				laypage.render({
					elem : 'page', //注意，这里的 test1 是 ID，不用加 # 号
					count : $("#totalPage").val(), //数据总数，从服务端得到
					limit : 5, //每页显示的条数
					limits : [ 5, 10, 15 ],
					layout : [ 'prev', 'page', 'next', 'count', 'limit', 'skip' ],
					jump : function(obj, first) {
						//obj包含了当前分页的所有参数，比如：
						//obj.curr得到当前页，以便向服务端请求对应页的数据。
						//obj.limit得到每页显示的条数
						//首次不执行
						if (!first) {
							showList(obj.curr,obj.limit);
						}
					}
				});
			});
		}
		// 根据请求的页数curr及每页显示数量limit发送AJAX请求显示对应页信息
		function showList(curr,limit){
			var jsondata;
			if($("#userRole").val()=='2'){
				jsondata = {curr:curr,limit:limit,"landlordCar.CarNo":$("#carNo").val(),
				"landlordCar.location":$("#location").val(),"landlord.alias":$("#landlordname").val(),
				"tenement.alias":$("#tenementname").val(),status:$("#status").val(),"landlord.id":$("#userId").val()};
			} 
			else if($("#userRole").val()=='3'){
				jsondata = {curr:curr,limit:limit,"landlordCar.CarNo":$("#carNo").val(),
				"landlordCar.location":$("#location").val(),"landlord.alias":$("#landlordname").val(),
				"tenement.alias":$("#tenementname").val(),status:$("#status").val(),"tenement.id":$("#userId").val()};
			}
			else {
				jsondata = {curr:curr,limit:limit,"landlordCar.CarNo":$("#carNo").val(),
				"landlordCar.location":$("#location").val(),"landlord.alias":$("#landlordname").val(),
				"tenement.alias":$("#tenementname").val(),status:$("#status").val()};
			}
			/* var data = '{curr : '+curr+',limit : '+limit+',"landlordCar.CarNo":'+$("#carNo").val()+
				',"landlordCar.location":'+$("#location").val()+',"landlord.alias":'+$("#landlordname").val()+
				',"tenement.alias":'+$("#tenementname").val()+',status:'+$("#status").val();
			if($("#userRole").val()=='2'){
				data+='"landlord.id":'+$("#userId").val()+'}';
			} 
			else if($("#userRole").val()=='3'){
				data+='"tenement.id":'+$("#userId").val()+'}';
			}
			else {
				data+='}';
			}
			alert(data); */
			$.ajax({
				url : "<%=path%>/showDealByPage.action",
				type : "post",
				dataType : "json",
				data : jsondata,
				success : function(result) {
					var data = result.list;
					var str = "";
					if(data.length==0){
						layer.msg('没有符合的交易信息', {
							icon : 5,
							time : 2000
						});
					}
					for (var i = 0; i < data.length; i++) {
						str += "<tr><td>" + ((curr-1)*limit+1+i) + "</td>" +
							"<td>" + data[i].no + "</td>" +
							"<td class='dealid' style='display:none'>" + data[i].id + "</td>" +
							"<td>" + data[i].beginTime + "</td>" +
							"<td>" + data[i].endTime + "</td>" +
							"<td>" + data[i].price + "</td>" +
							"<td>" + data[i].landlordCar.location + data[i].landlordCar.carNo+"</td>" +
							"<td class='td-landlord'>" + data[i].landlord.alias + "</td>" +
							"<td class='td-landlordid' style='display:none'>" + data[i].landlord.id + "</td>" +
							"<td class='td-tenement'>" + data[i].tenement.alias + "</td>" +
							"<td class='td-tenementid' style='display:none'>" + data[i].tenement.id + "</td>" +
							"<td class='td-status'>" + showStatus(data[i].status+'') + "</td>" +
							"<td>" + data[i].orderTime + "</td>" +
							"<td>" + (data[i].doneTime==null?'':data[i].doneTime) +"</td>" + //若JSON返回数据为null，则不显示
							"<td class='td-manage'><button class='layui-btn layui-btn-normal layui-btn-xs complainbtn'"+
								" onclick='submitComplain(this)'><i class='layui-icon'>&#xe642;</i>我要投诉</button></td>";
					}
					$("#carlist").html(str);
					checkButtonDisable();
					checkShowUser();
					checkShowControl();
				}
			});
		}
		// 根据是用户访问还是管理员访问来控制是否出现操作栏
    	checkShowControl();
    	function checkShowControl(){
    		if($("#userId").val()==""){
    			$(".td-manage").remove();
    		}
    	}
    	if($("#userId").val()==""){
    		$("#topelement").show();
    		$("#top").hide();
    		$("#searchbutton").removeClass("layui-btn-normal");
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
				x="<button class='layui-btn layui-btn-xs layui-btn-warm btn-status'>已支付</button>";
				break;
			case "2":
				x="<button class='layui-btn layui-btn-xs layui-btn-normal btn-status'>已成交</button>";
				break;
			case "3":
				x="<button class='layui-btn layui-btn-xs layui-btn-danger btn-status'>已失效</button>";
				break;
			case "4":
				x="<button class='layui-btn layui-btn-xs layui-btn-danger btn-status'>已过期</button>";
				break;
			}
			
			return x;
		}
		// 根据订单状态判断是否disable处理预约按钮
		checkButtonDisable();
		function checkButtonDisable(){
			$(".complainbtn").each(function() {
				var status = $(this).parents("tr").find(".btn-status").html();
				if (status != "已成交" && status !="已过期") {
					if($("#userRole").val()=='3' && status == "待支付"){
						var str = "<button class='layui-btn layui-btn-xs layui-btn-warm' onclick='gopay(this)'><i class='layui-icon'>&#xe60e;</i>立即支付</button>";
						$(this).parents("tr").find(".td-manage").html(str);
					} else{
						$(this).addClass('layui-btn-disabled');
						$(this).attr('disabled', true);
					}

				}
			})
		}
		// 点击支付按钮的函数
		function gopay(data){
			var dealid = $(data).parents("tr").find(".dealid").html(); 
			layer.msg("正在跳转支付界面......", {icon: 6, time: 3000});
			setTimeout(function(){
				window.location.href='<%=path%>/pay.action?dealid='+dealid;
			}, 3000);
		}
		// 点击我要投诉按钮的函数
		function submitComplain(data){
			var dealid = $(data).parents("tr").find(".dealid").html(); 
			var defendantid = $(data).parents("tr").find(".defendantid").html();
			layer.prompt({
  				formType: 2,
  				title: '请输入投诉理由',
  				area: ['400px', '350px'] //自定义文本域宽高
				}, function(value, index, elem){
  					$.ajax({
						url : "<%=path%>/insertComplain.action",
						type : "post",
						dataType : "json",
						data : {
							"deal.id" : dealid,
							"complaint.id":$("#userId").val(),
							"defendant.id":defendantid,
							"reasons" : value
						},
						success : function(data) {
							if(data=="success"){
								layer.msg('投诉提交成功!请耐心等待处理结果', {icon : 1});
							} else {
								layer.msg('投诉提交失败!请稍后再试', {icon : 2});
							}
							layer.close(index);
						}
					});	
			});
		}
		// 根据登录用户状态隐藏对应列，若登录用户为招租婆则隐藏招租婆用户名
		checkShowUser();
		function checkShowUser(){
			/* 角色代码2代表包租婆，3代表招租客*/
			if($("#userRole").val()=='2'){
				$(".td-landlord").hide();
				$(".td-tenementid").addClass('defendantid');
			} 
			else if($("#userRole").val()=='3'){
				$(".td-tenement").hide();
				$(".td-landlordid").addClass('defendantid');	
			}
		}
		// 点击搜索按钮的函数
		function search(){
			var curr = 1;
			var limit = 5;
			var jsondata;
			if($("#userRole").val()=='2'){
				jsondata = {curr:curr,limit:limit,"landlordCar.CarNo":$("#carNo").val(),
				"landlordCar.location":$("#location").val(),"landlord.alias":$("#landlordname").val(),
				"tenement.alias":$("#tenementname").val(),status:$("#status").val(),"landlord.id":$("#userId").val()};
			} 
			else if($("#userRole").val()=='3'){
				jsondata = {curr:curr,limit:limit,"landlordCar.CarNo":$("#carNo").val(),
				"landlordCar.location":$("#location").val(),"landlord.alias":$("#landlordname").val(),
				"tenement.alias":$("#tenementname").val(),status:$("#status").val(),"tenement.id":$("#userId").val()};
			}
			else {
				jsondata = {curr:curr,limit:limit,"landlordCar.CarNo":$("#carNo").val(),
				"landlordCar.location":$("#location").val(),"landlord.alias":$("#landlordname").val(),
				"tenement.alias":$("#tenementname").val(),status:$("#status").val()};
			}
			$.ajax({
				url : "<%=path%>/showDealByPage.action",
				type : "post",
				dataType : "json",
				data : jsondata,
				async:false,
				success : function(result) {
					var data = result.list;
					var str = "";
					if(data.length==0){
						layer.msg('没有符合的交易信息', {
							icon : 5,
							time : 2000
						});
					}
					for (var i = 0; i < data.length; i++) {
						str += "<tr><td>" + ((curr-1)*limit+1+i) + "</td>" +
							"<td>" + data[i].no + "</td>" +
							"<td class='dealid' style='display:none'>" + data[i].id + "</td>" +
							"<td>" + data[i].beginTime + "</td>" +
							"<td>" + data[i].endTime + "</td>" +
							"<td>" + data[i].price + "</td>" +
							"<td>" + data[i].landlordCar.location + data[i].landlordCar.carNo+"</td>" +
							"<td class='td-landlord'>" + data[i].landlord.alias + "</td>" +
							"<td class='td-landlordid' style='display:none'>" + data[i].landlord.id + "</td>" +
							"<td class='td-tenement'>" + data[i].tenement.alias + "</td>" +
							"<td class='td-tenementid' style='display:none'>" + data[i].tenement.id + "</td>" +
							"<td class='td-status'>" + showStatus(data[i].status+'') + "</td>" +
							"<td>" + data[i].orderTime + "</td>" +
							"<td>" + (data[i].doneTime==null?'':data[i].doneTime) +"</td>" + //若JSON返回数据为null，则不显示
							"<td class='td-manage'><button class='layui-btn layui-btn-normal layui-btn-xs complainbtn'"+
								" onclick='submitComplain(this)'><i class='layui-icon'>&#xe642;</i>我要投诉</button></td>";
					}
					$("#carlist").html(str);
					checkButtonDisable();
					checkShowUser();
					checkShowControl();
					$("#totalPage").val(result.total);
					addPage();
				}
			});
		}
		// 根据用户登陆类型来判断是否隐藏部分搜索框
		if($("#userRole").val()=='2'){
			$("#landlordname").hide();
		} 
		else if($("#userRole").val()=='3'){
			$("#tenementname").hide();
		}
	</script>
</body>
</html>