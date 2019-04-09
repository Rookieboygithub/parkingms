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
<meta charset="utf-8">
<base href="<%=basePath%>">
<title>可用招租车位列表</title>
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
	<!-- 导航栏 -->
	<%-- <div style="line-height:35px">
		<span class="layui-breadcrumb"> <a href="jsp/welcome.html">首页</a>
			<a>包租婆管理</a> <a><cite>查看招租车位</cite></a>
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
				<input type="text" placeholder="车位地址" class="layui-input" id='location'>
			</div>
			<div class="layui-inline">
				<input class="layui-input" placeholder="开始时间" id="beginTime">
			</div>
			<div class="layui-inline">
				<input class="layui-input" placeholder="截止时间" id="endTime">
			</div>
			<div class="layui-input-inline">
				<select name="status" id='status' lay-filter='*'>
					<option value="">请选择车位状态</option>
					<option value="2">待出租</option>
					<option value="3">已预约</option>
				</select>
			</div>
			<div class="layui-input-inline">
				<button class="layui-btn layui-btn-normal" type='button' onclick="search(1,5)">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</div>
		</form>
	</div>

	<div style="text-align: center">
		<table class="layui-table">
			<thead>
				<tr>
					<td colspan="7">招租车位信息</td>
					<td colspan="3">包租婆信息</td>
					<td rowspan="2">操作</td>
				</tr>
				<tr>
					<td>ID</td>
					<td>编号</td>
					<td>地址</td>
					<td>开始时间</td>
					<td>结束时间</td>
					<td>出租价格(元/时)</td>
					<td>状态</td>
					<td>用户名</td>
					<td>职业</td>
					<td>信誉度</td>
				</tr>
			</thead>
			<tbody id="carlist">
				<c:forEach items="${list}" var="data" varStatus="status">
					<tr>
						<td class='carId'>${data.landlordCar.id}</td>
						<td class="carNo">${data.landlordCar.carNo}</td>
						<td>${data.landlordCar.location}</td>
						<td class="beginTime"><fmt:formatDate
								value="${data.beginTime}" pattern="yyyy-MM-dd HH:mm" /></td>
						<td class="endTime"><fmt:formatDate value="${data.endTime}"
								pattern="yyyy-MM-dd HH:mm" /></td>
						<td class="price">${data.price}</td>
						<td class="td-status">${data.landlordCar.status}</td>
						<td>${data.landlord.alias}</td>
						<td>${data.landlord.career}</td>
						<td class="credit">${(1-data.landlord.credit)*100}</td>
						<td class='td-manage'>
							<button class='layui-btn layui-btn-normal layui-btn-xs orderbtn'
								onclick='booking(this)'>
								<i class='layui-icon'>&#xe642;</i>我要预约
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
		//记录分页参数
		var currentPage = 1;
		var limitPage = 5;
		// 加载layui模块
		layui.use([ 'form', 'layer', 'laydate' ], function() {
			var form = layui.form,
				layer = layui.layer,
				laydate = layui.laydate;
			
			// 监听select选择事件
				form.on('select(*)', function(data){
					search(1,limitPage);
				});
			//执行一个laydate实例
			var beginDate = laydate.render({
				elem : '#beginTime', //指定元素
				type : 'datetime',
				done: function(value, date){
                		endDate.config.min ={
                   			year:date.year,
                    		month:date.month-1, //关键
                    		date: date.date,
                    		hours: date.hours,
                    		minutes: date.minutes,
                    		seconds : date.seconds
                		};	
            		}
			});
	
			var endDate = laydate.render({
				elem : '#endTime', //指定元素
				type : 'datetime',
				done: function(value, date){
                		beginDate.config.max ={
                   			year:date.year,
                    		month:date.month-1, //关键
                    		date: date.date,
                    		hours: date.hours,
                    		minutes: date.minutes,
                    		seconds : date.seconds
                		};
            		}
			});
		});
		// 加载layui分页模块，并显示分页栏
		addPage();
		// 将layui分页封装成一个函数
		function addPage() {
			layui.use('laypage', function() {
				var laypage = layui.laypage;
				//执行一个laypage实例
				laypage.render({
					elem : 'page', //注意，这里的 test1 是 ID，不用加 # 号
					count : $("#totalPage").val(), //数据总数，从服务端得到
					limit : limitPage,//每页显示的条数
					curr : currentPage,
					limits : [ 5, 10, 15 ],
					layout : [ 'prev', 'page', 'next', 'count', 'limit', 'skip' ],
					jump : function(obj, first) {
						//obj包含了当前分页的所有参数，比如：
						//obj.curr得到当前页，以便向服务端请求对应页的数据。
						//obj.limit得到每页显示的条数
						currentPage = obj.curr;
						limitPage = obj.limit;
						//首次不执行
						if (!first) {
							showList();
						}
					}
				});
			});
		}
		// 搜索栏文本框内容改变时发送AJAX查询
		$(".layui-form input").change(function(){
			search(1,limitPage);
		})
		// 将表格中车位状态栏的数据由对应数字转换为文字描述
		$(".td-status").each(function() {
			var status = $(this).html();
			$(this).html(showStatus(status));
		})
		// 将车位状态数字转换为文字描述
		function showStatus(status) {
			var x;
			switch (status) {
			case "2":
				x = "<button class='layui-btn layui-btn-xs layui-btn-normal btn-status'>待出租</button>";
				break;
			case "3":
				x = "<button class='layui-btn layui-btn-xs layui-btn-warm btn-status'>已预约</button>";
				break;
			}
			return x;
		}
		checkButtonDisable();
		// 根据车位状态判断是否disable我要预约按钮
		function checkButtonDisable() {
			$(".orderbtn").each(function() {
				var status = $(this).parents("tr").find(".btn-status").html();
				if (status == "已预约") {
					$(this).addClass('layui-btn-disabled');
					$(this).attr('disabled', true);
				}
			})
		}
		// 根据请求的页数curr及每页显示数量limit发送AJAX请求显示对应页信息
		function showList() {
			$.ajax({
				url : "<%=path%>/showAvailableLetByPage.action",
				type : "post",
				dataType : "json",
				data : {
					curr : currentPage,
					limit : limitPage,
					beginTime : $("#beginTime").val(),
					endTime : $("#endTime").val(),
					"landlordCar.status":$("#status").val(),
					"landlordCar.location":$("#location").val()
				},
				success : function(result) {
					var data = result.list;
					if (data.length == 0) {
						$("#carlist").html('');
						layer.msg('没有符合的车位招租信息', {
							icon : 5,
							time : 2000
						});
	
					} else {
						var str = "";
						for (var i = 0; i < data.length; i++) {
							str += "<tr><td class='carId'>" + data[i].landlordCar.id + "</td>" +
								"<td class='carNo'>" + data[i].landlordCar.carNo + "</td>" +
								"<td>" + data[i].landlordCar.location + "</td>" +
								"<td class='beginTime'>" + data[i].beginTime + "</td>" +
								"<td class='endTime'>" + data[i].endTime + "</td>" +
								"<td class='price'>" + data[i].price + "</td>" +
								"<td class='td-status'>" + showStatus(data[i].landlordCar.status + "") + "</td>" +
								"<td>" + data[i].landlord.alias + "</td>" +
								"<td>" + data[i].landlord.career + "</td>" +
								"<td>" + showCreditStar(data[i].landlord.credit)+((1-data[i].landlord.credit)*100) + "</td>" +
								"<td class='td-manage'><button class='layui-btn layui-btn-normal layui-btn-xs orderbtn' onclick='booking(this)'>" +
								"<i class='layui-icon'>&#xe642;</i>我要预约</button></td>";
						}
						$("#carlist").html(str);
						checkButtonDisable();
					}
				}
			});
		}
		//点击我要预订按钮的函数
		function booking(data) {
			var id = $(data).parents("tr").find(".carId").html();
			window.location.href='<%=path%>/makeLetOrder.action?id='+id;
		}
			
		<%-- 	layer.open({
            		type: 2,
            		area: ['720px', '600px'],
            		maxmin: true,
           			shadeClose: true,
            		title: '预约车位',
            		fixed: false,
            		content: '<%=path%>/makeLetOrder.action?id='+id
       			 }); --%>
		// 为表格中的信誉度添加五星显示
		$(".credit").each(function(){
			var status = $(this).html();
			$(this).html(showCreditStar(1-parseFloat(status)/100)+status);
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
		//点击搜索按钮的函数
		function search(curr,limit) {
			$.ajax({
				url : "<%=path%>/showAvailableLetByPage.action",
				type : "post",
				dataType : "json",
				data : {
					curr : curr,
					limit : limit,
					beginTime : $("#beginTime").val(),
					endTime : $("#endTime").val(),
					"landlordCar.status":$("#status").val(),
					"landlordCar.location":$("#location").val()
				},
				success : function(result) {
					var data = result.list;
					if (data.length == 0) {
						$("#carlist").html('');
						layer.msg('没有符合的车位招租信息', {
							icon : 5,
							time : 2000
						});
	
					} else {
						var str = "";
						for (var i = 0; i < data.length; i++) {
							str += "<tr><td class='carId'>" + data[i].landlordCar.id + "</td>" +
								"<td class='carNo'>" + data[i].landlordCar.carNo + "</td>" +
								"<td>" + data[i].landlordCar.location + "</td>" +
								"<td class='beginTime'>" + data[i].beginTime + "</td>" +
								"<td class='endTime'>" + data[i].endTime + "</td>" +
								"<td class='price'>" + data[i].price + "</td>" +
								"<td class='td-status'>" + showStatus(data[i].landlordCar.status + "") + "</td>" +
								"<td>" + data[i].landlord.alias + "</td>" +
								"<td>" + data[i].landlord.career + "</td>" +
								"<td>" + showCreditStar(data[i].landlord.credit)+((1-data[i].landlord.credit)*100) + "</td>" +
								"<td class='td-manage'><button class='layui-btn layui-btn-normal layui-btn-xs orderbtn' onclick='booking(this)'>" +
								"<i class='layui-icon'>&#xe642;</i>我要预约</button></td>";
						}
						$("#carlist").html(str);
						checkButtonDisable();
					}
					
					if(result.total != $("#totalPage").val()){
						$("#totalPage").val(result.total);
						currentPage = curr;
						limitPage = limit;
						addPage(); // 重要，当currentPage超过最大页数时，会取最大页数
					}
				}
			});
		}
	</script>
</body>
</html>