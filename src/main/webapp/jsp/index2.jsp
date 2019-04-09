<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit">
  		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>CBD车位租赁管理系统</title>
		<link rel="stylesheet" type="text/css" href="lib/admin/layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="lib/admin/css/admin.css"/>
	</head>
	<body>
		<div class="main-layout" id='main-layout'>
			<!--侧边栏-->
			<div class="main-layout-side">
				
				<a class="logo" href="<%=path%>/jsp/index2.jsp">
					<div class="m-logo">
					</div>
				</a>
				
				<ul class="layui-nav layui-nav-tree landlord" lay-filter="leftNav" style="display: none">
				 	<li class="layui-nav-item">
				  		<a href="javascript:;" data-url="<%=path%>/jsp/person-info.jsp" data-id='1' data-text="个人信息"><i class="iconfont">&#xe606;</i>个人信息</a>
				  	</li>
				  	<li class="layui-nav-item">
				  		<a href="javascript:;" data-url="<%=path%>/jsp/landlordcar-apply.jsp" data-id='2' data-text="发布车位"><i class="iconfont">&#xe600;</i>发布车位</a>
				  	</li>
				  	<li class="layui-nav-item">
				  		<a href="javascript:;" data-url="<%=path%>/showFirstLetByLandlordId.action?landlord.id=${userId}" data-id='3' data-text="车位管理"><i class="iconfont">&#xe604;</i>车位管理</a>
				  	</li>
				  	<li class="layui-nav-item">
				  		<a href="javascript:;" data-url="<%=path%>/showFirstBooking.action?landlord.id=${userId}" data-id='4' data-text="预约信息"><i class="iconfont">&#xe603;</i>预约信息</a>
				  	</li>
				  	<li class="layui-nav-item">
				  		<a href="javascript:;" data-url="<%=path%>/showFirstDeal.action?landlord.id=${userId}" data-id='5' data-text="出租订单"><i class="iconfont">&#xe60c;</i>出租订单</a>
				  	</li>				
					<li class="layui-nav-item">
						<a href="javascript:;" data-url="<%=path%>/jsp/log-list.jsp" data-id='7' data-text="日志信息"><i class="iconfont">&#xe60b;</i>日志信息</a>
					</li>
				</ul>
				<ul class="layui-nav layui-nav-tree tenement" lay-filter="leftNav" style="display: none">
				 	<li class="layui-nav-item">
				  		<a href="javascript:;" data-url="<%=path%>/jsp/person-info.jsp" data-id='8' data-text="个人信息"><i class="iconfont">&#xe606;</i>个人信息</a>
				  	</li>
				  	<li class="layui-nav-item">
				  		<a href="javascript:;" data-url="<%=path%>/showFirstAvailableLet.action" data-id='9' data-text="车位查询"><i class="iconfont">&#xe600;</i>车位查询</a>
				  	</li>
				  	<li class="layui-nav-item">
				  		<a href="javascript:;" data-url="<%=path%>/showFirstDeal.action?tenement.id=${userId}" data-id='10' data-text="交易订单"><i class="iconfont">&#xe60c;</i>交易订单</a>
				  	</li>				
					<li class="layui-nav-item">
						<a href="javascript:;" data-url="<%=path%>/jsp/log-list.jsp" data-id='11' data-text="日志信息"><i class="iconfont">&#xe60b;</i>日志信息</a>
					</li>
				</ul>
				<ul class="layui-nav layui-nav-tree company" lay-filter="leftNav" style="display: none">
					<li class="layui-nav-item"><a href="javascript:;" data-url="<%=path%>/jsp/company-edit.jsp" data-id='12' data-text="企业信息"><i class="iconfont">&#xe606;</i>企业信息</a></li>
					<li class="layui-nav-item"><a href="javascript:;" data-url="<%=path%>/jsp/company-treaty.jsp" data-id='13' data-text="企业合同"><i
						class="iconfont">&#xe600;</i>企业合同</a></li>
					<li class="layui-nav-item"><a href="javascript:;" data-url="<%=path%>/jsp/company-cbdcar.jsp" data-id='14' data-text="企业车位"><i
						class="iconfont">&#xe604;</i>企业车位</a></li>
					<li class="layui-nav-item"><a href="javascript:;" data-url="<%=path%>/jsp/notLeaseCbdcar.jsp" data-id='15' data-text="CBD平台车位"><i class="iconfont">&#xe603;</i>CBD平台车位</a></li>
					<li class="layui-nav-item"><a href="javascript:;" data-url="<%=path%>/jsp/log-list.jsp" data-id='16' data-text="日志信息"><i class="iconfont">&#xe60b;</i>日志信息</a></li>
			</ul>
			</div>
			
			<!--右侧内容-->
			<div class="main-layout-container">
				<!--头部-->
				<div class="main-layout-header">
					<div class="menu-btn" id="hideBtn">
						<a href="javascript:;">
							<span class="iconfont">&#xe60e;</span>
						</a>
					</div>
					<ul class="layui-nav" lay-filter="rightNav">
					  <li class="layui-nav-item">
					    <a href="javascript:;" data-url="" data-id='5' data-text="个人信息">${username}</a>
					  </li>
					  <li class="layui-nav-item"><a href="<%=path%>/logout">退出</a></li>
					</ul>
				</div>
				<!--主体内容-->
				<div class="main-layout-body">
					<!--tab 切换-->
					<div class="layui-tab layui-tab-brief main-layout-tab" lay-filter="tab" lay-allowClose="true">
					  <ul class="layui-tab-title">
					    <li class="layui-this welcome">我的主页</li>
					  </ul>
					  
					  <div class="layui-tab-content">
					    <div class="layui-tab-item layui-show" style="background: #f5f5f5;">
					    	<!--1-->
					    	<iframe src="jsp/welcome.html" width="100%" height="100%" id="iframe" name="iframe" scrolling="auto" class="iframe" framborder="0"></iframe>
					    	<!--1end-->
					    </div>
					  </div>
					</div>
				</div>
			</div>
			<!--遮罩-->
			<div class="main-mask">
				
			</div>
		</div>
		<input type="hidden" id="userCharacter" value="${sessionScope.character}">
		<input type="hidden" id="loginId" value="${sessionScope.loginId}">
		<input type="hidden" id="userId">
	<!-- 	<script type="text/javascript">
			var scope={
				link:'./welcome.html'
			}
		</script> -->
		<script src="lib/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script src="lib/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
		<script src="lib/admin/js/main.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/jQuery-2.2.2.js"></script>
        <script type="text/javascript">
        	if($("#userCharacter").val()==2){
        		$(".landlord").show();
        	}
			else if($("#userCharacter").val()==3){
        		$(".tenement").show();
        	}
        	else if($("#userCharacter").val()==4){
        		$(".company").show();
        	}
        </script>
	</body>
</html>

