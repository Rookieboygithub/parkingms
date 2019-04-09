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
<title>招租车位列表</title>
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
	<div style="line-height:35px;display:none" id="topelement">
		<span class="layui-breadcrumb"><a href="javascript:top.location.reload()">首页</a>
			<a href="javascript:window.history.back(-1)">包租婆管理</a> <a><cite>查看车位信息</cite></a>
		</span>
		<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-sm" id="backbutton"
				onclick="window.history.back(-1)" style="display:none">
				<i class="layui-icon">&#xe65c;</i>
			</button>
			<button class="layui-btn layui-btn-sm"
				onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
		</div>
		<hr class="layui-bg-gray" style="margin-top: 0">
	</div>
	
	<div class="layui-input-inline" style="float:right" id="top">
			<button class="layui-btn layui-btn-normal"
				onclick="window.history.back(-1)">
				<i class="layui-icon">&#xe65c;</i>
			</button>
			<button class="layui-btn layui-btn-normal"
				onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
	</div>
	<div style="margin: 0 auto">
		<form class="layui-form layui-form-pane">
			<label class="layui-form-label">搜索</label>
			<div class="layui-input-inline">
				<input type="text" placeholder="车位产权证编号" class="layui-input" id='certificateNo'>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="车位编号" class="layui-input" id='carNo'>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="车位地址" class="layui-input" id='location'>
			</div>
			<div class="layui-input-inline">
				<select name="status" id='status' lay-filter='*'>
					<option value="">请选择车位状态</option>
					<option value="0">待审核</option>
					<option value="1">已审核</option>
					<option value="2">待出租</option>
					<option value="3">已预约</option>
					<option value="4">已出租</option>
					<option value="5">审核失败</option>
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
		<table class="layui-table">
				<thead>
					<tr>
						<td colspan="5">车位信息</td>
						<td colspan="3">招租信息</td>
						<td rowspan="2" class='td-manage' style='width:187px'>操作</td>
					</tr>
					<tr>
						<td>车位ID</td>
						<td>车位产权证编号</td>
						<td>车位编号</td>
						<td>车位地址</td>
						<td>状态</td>
						<td>租借开始时间</td>
						<td>租借结束时间</td>
						<td>价格(元/每小时)</td>
					</tr>
				</thead>

				<tbody id="carlist">
					<c:forEach items="${list}" var="data" varStatus="status">
						<tr>
							<td class='carId'>${data.landlordCar.id}</td>
							<td>${data.landlordCar.certificateNo}</td>
							<td class="carNo">${data.landlordCar.carNo}</td>
							<td>${data.landlordCar.location}</td>
							<td class="td-status">${data.landlordCar.status}</td>
							<td class="beginTime"><fmt:formatDate
									value="${data.beginTime}" pattern="yyyy-MM-dd HH:mm" /></td>
							<td class="endTime"><fmt:formatDate value="${data.endTime}"
									pattern="yyyy-MM-dd HH:mm" /></td>
							<td class="price"><fmt:formatNumber type="number" value="${data.price}" maxFractionDigits="0"/></td>
							<td class='td-manage'>
								<button class='layui-btn layui-btn-normal layui-btn-xs addLet-btn'
									onclick='addLet(this)'>
									<i class='layui-icon'>&#xe654;</i>发布
								</button>
								<button class='layui-btn layui-btn-warm layui-btn-xs removeLet-btn'
									onclick='removeLet(this)'>
									<i class='layui-icon'>&#x1006;</i>撤销
								</button>
								<button class='layui-btn layui-btn-danger layui-btn-xs deleteCar-btn'
									onclick='deleteCar(this)'>
									<i class='layui-icon'>&#xe640;</i>删除
								</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
	<input type="hidden" id="userId" value="${userId}">
	<input type="hidden" id="userCharacter" value="${sessionScope.character}">
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
		// 记录分页参数
		var currentPage=1;
		var limitPage = 10;
		//标记是否在执行操作处理
		var flag = 0;
		// 加载layui模块
		layui.use(['form','element', 'layer'], function() {
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
				laypage.render({
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
						limitPage = obj.limit;
						//首次不执行
						if (!first) {
							showList(obj.curr, obj.limit);
						}
					}
				});
			});
		}
		// 将layui日期模块封装成函数
    	function addDate(){
        	layui.use(['laydate','util'], function(){
            	var laydate = layui.laydate,
            		util = layui.util;
	
            	//执行一个laydate实例
            	var beginDate = laydate.render({
                	elem: '#beginTime' //指定元素
                	,type: 'datetime'
                	,min:util.toDateString(new Date(), "yyyy-MM-dd HH:mm:ss")
                	,format:'yyyy-MM-dd HH:mm'
                	,done: function(value, date){
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
                	elem: '#endTime' //指定元素
                	,type: 'datetime'
                	,min:0
                	,format:'yyyy-MM-dd HH:mm'
                	,done: function(value, date){
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
    	}
		// 将表格中车位状态栏的数据由对应数字转换为文字描述
		$(".td-status").each(function(){
			var status = $(this).html();
			$(this).html(showStatus(status));
		})
		// 将车位状态数字转换为文字描述
		function showStatus(status){
			var x;
			switch(status){
			case "0":
				x="<button class='layui-btn layui-btn-xs layui-btn-danger btn-status'>待审核</button>";
				break;
			case "1":
				x="<button class='layui-btn layui-btn-xs layui-btn-warm btn-status'>已审核</button>";
				break;
			case "2":
				x="<button class='layui-btn layui-btn-xs layui-btn-normal btn-status'>待出租</button>";
				break;
			case "3":
				x="<button class='layui-btn layui-btn-xs layui-btn-normal btn-status'>已预约</button>";
				break;
			case "4":
				x="<button class='layui-btn layui-btn-xs layui-btn-normal btn-status'>已出租</button>";
				break;
			case "5":
				x="<button class='layui-btn layui-btn-xs layui-btn-danger btn-status'>审核失败</button>";
				break;
			case "6":
				x="已删除";
				break;
			}
			return x;
		}
		var str ="<button class='layui-btn layui-btn-normal layui-btn-xs addLet-btn' onclick='addLet(this)'><i class='layui-icon'>&#xe654;</i>发布</button>"+
							"<button class='layui-btn layui-btn-warm layui-btn-xs removeLet-btn' onclick='removeLet(this)'><i class='layui-icon'>&#x1006;</i>撤销</button>"+
							"<button class='layui-btn layui-btn-danger layui-btn-xs deleteCar-btn' onclick='deleteCar(this)'><i class='layui-icon'>&#xe640;</i>删除</button>";
		// 根据请求的页数curr及每页显示数量limit发送AJAX请求显示对应页信息
		function showList() {
			$.ajax({
				url : "<%=path%>/showLetByLandlordIdUsingPage.action",
				type : "post",
				dataType : "json",
				data : {
					curr : currentPage,
					limit : limitPage,
					"landlord.id":$("#userId").val()==""?${landlordId}:$("#userId").val(), //用户id
					status:$("#status").val(),
					certificateNo:$("#certificateNo").val(),
					carNo:$("#carNo").val(),
					location:$("#location").val()
				},
				success : function(result) {
					var data= result.list;
					var string = "";
					if(data.length==0 && flag == 0){
						layer.msg('没有符合的车位信息', {
							icon : 5,
							time : 2000
						});
					}
					for (var i = 0; i < data.length; i++) {
						string += "<tr><td class='carId'>" + data[i].landlordCar.id + "</td>" +
							"<td>" + data[i].landlordCar.certificateNo + "</td>" +
							"<td class='carNo'>" + data[i].landlordCar.carNo + "</td>" +
							"<td>" + data[i].landlordCar.location + "</td>" +
							"<td class='td-status'>" + showStatus(data[i].landlordCar.status+"") +"</td>" +
							"<td class='beginTime'>" + (data[i].beginTime==null?'':data[i].beginTime) +"</td>" + //若JSON返回数据为null，则不显示
							"<td class='endTime'>" + (data[i].endTime==null?'':data[i].endTime)+"</td>" + //若JSON返回数据为null，则不显示
							"<td class='price'>" + (data[i].price==null?'':data[i].price) +"</td>" + //若JSON返回数据为null，则不显示
							"<td class='td-manage'>"+str+"</td>";
					}
					$("#carlist").html(string);
					checkButtonDisable();
					checkShowControl();
					flag = 0;
				}
			});
		}
		// 点击发布按钮执行的函数,显示出输入框输入
		function addLet(data){
			var str="<input type='text' class='layui-input' ";
			$(data).parents("tr").find(".beginTime").html(str+" id='beginTime'>");
			$(data).parents("tr").find(".endTime").html(str+" id='endTime'>");
			$(data).parents("tr").find(".price").html(str+" id='price'  style='width:50px'>");
			var content = "<button class='layui-btn layui-btn-normal layui-btn-xs ' onclick='submitAddLet(this)'><i class='layui-icon'>&#xe642;</i>确定发布</button>"+
						"<button class='layui-btn layui-btn-normal layui-btn-xs ' onclick='cancelAddLet(this)'><i class='layui-icon'>&#xe642;</i>撤销</button>";
			$(data).parents("tr").find(".td-manage").html(content);
			addDate();
		}
		
		// 点击确认发布按钮执行的函数，发送AJAX请求更新数据
		function submitAddLet(data){
			var beginTime = $("#beginTime").val();
			var endTime = $("#endTime").val();
			var price = $("#price").val();
			var carId = $(data).parents("tr").find(".carId").html();
			var userId = $("#userId").val();
			if(beginTime==''||endTime==''|| price==''){
				layer.msg('必填项不能为空', {icon: 5, time: 2000});
				return;
			}
			if(!/^[1-9]\d*$/.test(price)){
				layer.msg('请输入合理的价格(必须为正整数)', {icon: 5, time: 2000});
				return;
			}
			if(new Date(endTime) <= new Date(beginTime)){
				layer.msg('结束时间不能小于等于开始时间，请重新选择', {icon: 5, time: 2000});
				return;
			}
			$.ajax({
				url : "<%=path%>/addLandlordLet.action",
				type : "post",
				dataType : "json",
				data : {
					"beginTime" : beginTime,
					"endTime":endTime,
					"price":price,
					"landlord.id":userId,
					"landlordCar.id":carId
				},
				success : function(result) {
					if(result==true){
						layer.msg('发布车位招租信息成功!该车位已处于待出租状态', {icon: 1, time: 2000});
						/* $(data).parents("tr").find(".beginTime").html(beginTime);
						$(data).parents("tr").find(".endTime").html(endTime);
						$(data).parents("tr").find(".price").html(price);
						$(data).parents("tr").find(".td-status").html("<button class='layui-btn layui-btn-xs layui-btn-normal btn-status'>待出租</button>");
						$(data).parents("tr").find(".td-manage").html(str);
						checkButtonDisable(); */
						/* search(); */
						flag = 1;
						search(currentPage, limitPage);
					} else {
						layer.msg('发布车位招租信息失败!可能该车位已被删除,请尝试刷新页面后重试', {icon: 6, time: 2000});
						cancelAddLet(data);
					}
				}
			});
		}
		// 点击取消发布按钮执行的函数
		function cancelAddLet(data){
			$(data).parents("tr").find(".beginTime").html("");
			$(data).parents("tr").find(".endTime").html("");
			$(data).parents("tr").find(".price").html("");
			$(data).parents("tr").find(".td-manage").html(str);
			checkButtonDisable();
		}
		// 点击撤销按钮执行的函数，根据弹框选择是否发送AJAX更新数据
		function removeLet(data){
			layer.confirm('确定要撤销车位的招租信息吗？', {icon: 3, title:'提示'}, function(index){
				
				var carId = $(data).parents("tr").find(".carId").html();
				$.ajax({
				url : "<%=path%>/removeLandlordLet.action",
				type : "post",
				dataType : "json",
				data : {
					"id":carId
				},
				success : function(result) {
					if(result==true){
						layer.msg('撤销车位招租信息成功！', {icon: 1, time: 2000});
					/* 	$(data).parents("tr").find(".beginTime").html("");
						$(data).parents("tr").find(".endTime").html("");
						$(data).parents("tr").find(".price").html("");
						$(data).parents("tr").find(".td-status").html("<button class='layui-btn layui-btn-xs layui-btn-warm btn-status'>已审核</button>");
						$(data).parents("tr").find(".td-manage").html(str);
						checkButtonDisable(); */
						/* search(); */
						flag = 1;
						search(currentPage, limitPage);
					} else {
						layer.msg('撤销车位招租信息失败!可能该车位已被预约或已删除,请尝试刷新页面后重试', {icon: 2, time: 2000});
					}
				}
			});
				layer.close(index);
			});
		}
		// 点击删除按钮执行的函数，根据弹框选择是否发送AJAX更新数据
		function deleteCar(data){
			layer.confirm('确定要删除车位吗？', {icon: 3, title:'提示'}, function(index){
				
				var carId = $(data).parents("tr").find(".carId").html();
				$.ajax({
				url : "<%=path%>/deleteLandlordCar.action",
				type : "post",
				dataType : "json",
				data : {
					"id":carId
				},
				success : function(result) {
					if(result==true){
						layer.msg('删除车位成功', {icon: 1, time: 2000});
						$(data).parents("tr").remove();
						var total = $("#totalPage").val();
						$("#totalPage").val(total-1);
						addPage();
						showList();
						
					} else {
						layer.msg('删除车位失败!可能该车位已被预约,请尝试刷新页面后重试', {icon: 6, time: 2000});
					}
				}
			});
				layer.close(index);
			});
		}
		// 根据车位状态判断是否disable操作按钮
		checkButtonDisable();
		function checkButtonDisable() {
			$(".addLet-btn").each(function() {
				var status = $(this).parents("tr").find(".btn-status").html();
				if (status != "已审核") {
					$(this).addClass('layui-btn-disabled');
					$(this).attr('disabled', true);
				}
			})
			$(".removeLet-btn").each(function() {
				var status = $(this).parents("tr").find(".btn-status").html();
				if (status != "待出租") {
					$(this).addClass('layui-btn-disabled');
					$(this).attr('disabled', true);
				}
			})
			$(".deleteCar-btn").each(function() {
				var status = $(this).parents("tr").find(".btn-status").html();
				if (status == "已预约" || status == "已出租") {
					$(this).addClass('layui-btn-disabled');
					$(this).attr('disabled', true);
				}
			})
		}
		// 根据是包租婆访问还是管理员访问来控制是否出现操作栏
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
    		$("#backbutton").show();
    	}
		// 点击搜索按钮后的函数
		function search(curr,limit){
			$.ajax({
				url : "<%=path%>/showLetByLandlordIdUsingPage.action",
				type : "post",
				dataType : "json",
				data : {
					curr : curr,
					limit : limit,
					"landlord.id":$("#userId").val()==""?${landlordId}:$("#userId").val(), //用户id
					status:$("#status").val(),
					certificateNo:$("#certificateNo").val(),
					carNo:$("#carNo").val(),
					location:$("#location").val()
				},
				success : function(result) {
					var data= result.list;
					var string = "";
					if(data.length==0 && flag == 0){
						layer.msg('没有符合的车位信息', {
							icon : 5,
							time : 2000
						});
					}
					for (var i = 0; i < data.length; i++) {
						string += "<tr><td class='carId'>" + data[i].landlordCar.id + "</td>" +
							"<td>" + data[i].landlordCar.certificateNo + "</td>" +
							"<td class='carNo'>" + data[i].landlordCar.carNo + "</td>" +
							"<td>" + data[i].landlordCar.location + "</td>" +
							"<td class='td-status'>" + showStatus(data[i].landlordCar.status+"") +"</td>" +
							"<td class='beginTime'>" + (data[i].beginTime==null?'':data[i].beginTime) +"</td>" + //若JSON返回数据为null，则不显示
							"<td class='endTime'>" + (data[i].endTime==null?'':data[i].endTime)+"</td>" + //若JSON返回数据为null，则不显示
							"<td class='price'>" + (data[i].price==null?'':data[i].price) +"</td>" + //若JSON返回数据为null，则不显示
							"<td class='td-manage'>"+str+"</td>";
					}
					$("#carlist").html(string);
					checkButtonDisable();
					checkShowControl();
					flag = 0;
					
					if(result.total != $("#totalPage").val()){
						$("#totalPage").val(result.total);
						currentPage = curr;
						limitPage = limit;
						addPage(); // 重要，当currentPage超过最大页数时，会取最大页数
					}
					if(curr != currentPage){
						showList();
					}
				}
			});
		}
	</script>
</body>
</html>