<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>X-admin v1.0</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lib/layuinew/css/layui.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/x-admin.css" media="all">
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>操作权限管理</cite></a>
			<a><cite>查看后台管理员</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<form class="layui-form x-center" action="" style="width:80%">
			<div class="layui-form-pane" style="margin-top: 15px;">
				<div class="layui-form-item">
					<div class="layui-input-inline">
						<input type="text" name="username" id="keywords"
							placeholder="请输入工号或姓名" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-input-inline" style="width:80px">
						<button class="layui-btn"  type="button" onclick="search()">
							<i class="layui-icon">搜索</i>
						</button>
					</div>
				</div>
			</div>
		</form>
		<div style="text-align: center;">
			<table class="layui-table">
				<thead>
					<tr>
						<td>编号</td>
						<td>工号</td>
						<td>姓名</td>
						<td>电话</td>
						<td>用户管理权限</td>
						<td>车位管理权限</td>
						<td>合同管理权限</td>
						<td>投诉管理权限</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody id="content">

				</tbody>
			</table>
			<div id="pagebar"></div>
			<input id="totalPage" type="hidden" />
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/lib/layuinew/layui.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"
		charset="utf-8">
        </script>
	<script>
	function getContext(page){
	$.ajax({
				url : "../findall.action",
				type : "post",
				dataType : "json",
				data : {
					page : page
				},
				success : function(data) {
					
					var content = "";
					for (var i = 0; i < data.length; i++) {
						var car = "未开通";
						var complain = "未开通";
						var pact = "未开通";
						var user = "未开通";
						var li = i + 1;
						if (data[i].authorityCar == 1) {
							car = "已开通"
						}
						if (data[i].authorityComplain == 1) {
							complain = "已开通"
						}
						if (data[i].authorityPact == 1) {
							pact = "已开通"
						}
						if (data[i].authorityUser == 1) {
							user = "已开通"
						}
						content += "<tr><td>" + li + "</td><td>" + data[i].account + "</td><td>" + data[i].name + "</td><td>" + data[i].tel + "</td><td>" +
							user + "</td><td>" + car + "</td><td>" + pact + "</td><td>" + complain + "</td><td><a href=${pageContext.request.contextPath}/jsp/super-admin-edit.jsp?id=" +
							data[i].id + "><i class='layui-icon'>&#xe642;</i>修改</a></td></tr>";
					}
					$("#content").html(content);
				}
			})
	
	}
		//编辑
		function admin_edit(title, url, id, w, h) {
			x_admin_show(title, url, w, h);
		}
		/*删除*/
		function admin_del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				//发异步删除数据
				$(obj).parents("tr").remove();
				layer.msg('已删除!', {
					icon : 1,
					time : 1000
				});
			});
		}
		$(function() {
		getPages() ;
			$.ajax({
				url : "../findall.action",
				type : "post",
				dataType : "json",
				data : {
					page : $("#mypage").html()
				},
				success : function(data) {
					var content = "";
					for (var i = 0; i < data.length; i++) {
						var car = "未开通";
						var complain = "未开通";
						var pact = "未开通";
						var user = "未开通";
						var li = i + 1;
						if (data[i].authorityCar == 1) {
							car = "已开通"
						}
						if (data[i].authorityComplain == 1) {
							complain = "已开通"
						}
						if (data[i].authorityPact == 1) {
							pact = "已开通"
						}
						if (data[i].authorityUser == 1) {
							user = "已开通"
						}
						content += "<tr><td>" + li + "</td><td>" + data[i].account + "</td><td>" + data[i].name + "</td><td>" + data[i].tel + "</td><td>" +
							user + "</td><td>" + car + "</td><td>" + pact + "</td><td>" + complain + "</td><td><a href=${pageContext.request.contextPath}/jsp/super-admin-edit.jsp?id=" +
							data[i].id + "&name=" + data[i].account + ">修改</a></td></tr>";
					}
					$("#content").html(content);
				}
			})
	
		})
		
		/*获取到总页数*/
	function getPages() {
			$.ajax({
				url : "../findcount.action",
				type : "post",
				dataType : "json",
				async: false,
				data:{},
				success:function(data){
				$("#totalPage").val(data.pages);
				}
			});
			
		}
		//搜索功能
		function search() {
			var keywords = $("#keywords").val();
			var page_count = $("#mypage").html();
			if (page_count == undefined) {
				page_count = 1;
			};
			$.ajax({
				url : "../searchAdmin.action",
				type : "post",
				dataType : "json",
				data : {
					keywords : $("#keywords").val(),
					page : page_count
				},
				success : function(data) {
					var content = "";
					for (var i = 0; i < data.length; i++) {
						var car = "未开通";
						var complain = "未开通";
						var pact = "未开通";
						var user = "未开通";
						var li = i + 1;
						if (data[i].authorityCar == 1) {
							car = "已开通"
						}
						if (data[i].authorityComplain == 1) {
							complain = "已开通"
						}
						if (data[i].authorityPact == 1) {
							pact = "已开通"
						}
						if (data[i].authorityUser == 1) {
							user = "已开通"
						}
						content += "<tr><td>" + li + "</td><td>" + data[i].account + "</td><td>" + data[i].name + "</td><td>" + data[i].tel + "</td><td>" +
							user + "</td><td>" + car + "</td><td>" + pact + "</td><td>" + complain + "</td><td><a href=${pageContext.request.contextPath}/jsp/super-admin-edit.jsp?id=" +
							data[i].id + ">修改</a></td></tr>";
					}
					if (content == "") {
						$("#content").html("对不起，没有搜索到结果");
					} else {
						window.location.href = "${pageContext.request.contextPath}/jsp/admin-search.jsp?keywords=" + keywords;
	
					}
	
				}
			})
		}
			layui.use([ 'laydate', 'element', 'laypage', 'layer', 'form' ], function() {
			$ = layui.jquery; //jquery
			lement = layui.element; //面包导航
			layer = layui.layer; //弹出层
			form = layui.form;
			
		 
			layui.use('laypage', function() {
			var laypage = layui.laypage;
			var lastPage = Math.ceil(totalPage/6)
			//执行一个laypage实例
			laypage.render({
				elem : 'pagebar', //注意，这里的 test1 是 ID，不用加 # 号
				count : $("#totalPage").val(),
				limit:6, //数据总数，从服务端得到
				layout : [ 'prev', 'page', 'next', 'count', 'skip' ],
				jump: function(obj, first){
               var myPage= obj.curr;
               getContext(myPage);
              }
			});
		});
		});
		/* function add() {
			$.ajax({
				url : "../findall.action",
				type : "post",
				dataType : "json",
				data : {
					page : parseInt($("#mypage").html()) + 1
				},
				success : function(data) {
					if (data[0].name != null) {
						$("#mypage").html(parseInt($("#mypage").html()) + 1);
					} else {
						return;
					}
					var content = "";
					for (var i = 0; i < data.length; i++) {
						var car = "未开通";
						var complain = "未开通";
						var pact = "未开通";
						var user = "未开通";
						var li = i + 1;
						if (data[i].authorityCar == 1) {
							car = "已开通"
						}
						if (data[i].authorityComplain == 1) {
							complain = "已开通"
						}
						if (data[i].authorityPact == 1) {
							pact = "已开通"
						}
						if (data[i].authorityUser == 1) {
							user = "已开通"
						}
						content += "<tr><td>" + li + "</td><td>" + data[i].account + "</td><td>" + data[i].name + "</td><td>" + data[i].tel + "</td><td>" +
							user + "</td><td>" + car + "</td><td>" + pact + "</td><td>" + complain + "</td><td><a href=http://localhost:8080/parkingms/jsp/super-admin-edit.jsp?id=" +
							data[i].id + ">修改</a></td></tr>";
					}
					$("#content").html(content);
				}
			})
		
		}
		function redius() {
			if (parseInt($("#mypage").html()) <= 1) {
				return;
			}
			$.ajax({
				url : "../findall.action",
				type : "post",
				dataType : "json",
				data : {
					page : parseInt($("#mypage").html()) - 1
				},
				success : function(data) {
					if (data != null) {
						if (data[0].name != null) {
							$("#mypage").html(parseInt($("#mypage").html()) - 1);
						} else {
							return;
						}
						var content = "";
						for (var i = 0; i < data.length; i++) {
							var car = "未开通";
							var complain = "未开通";
							var pact = "未开通";
							var user = "未开通";
							var li = i + 1;
							if (data[i].authorityCar == 1) {
								car = "已开通"
							}
							if (data[i].authorityComplain == 1) {
								complain = "已开通"
							}
							if (data[i].authorityPact == 1) {
								pact = "已开通"
							}
							if (data[i].authorityUser == 1) {
								user = "已开通"
							}
							content += "<tr><td>" + li + "</td><td>" + data[i].account + "</td><td>" + data[i].name + "</td><td>" + data[i].tel + "</td><td>" +
								user + "</td><td>" + car + "</td><td>" + pact + "</td><td>" + complain + "</td><td><a href=http://localhost:8080/parkingms/jsp/super-admin-edit.jsp?id=" +
								data[i].id + ">修改</a></td></tr>";
						}
						$("#content").html(content);
					}
				}
			})
		
		} */
		/* 搜索功能的实现 */
	
	
		
	</script>
</body>
</html>
