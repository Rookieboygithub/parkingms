<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>CBD车位后台管理系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="../css/x-admin.css" media="all">
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header header header-demo">
			<div class="layui-main">
				<a class="logo" href="./index.jsp"> CBD车位后台管理系统 </a>
				<ul class="layui-nav" lay-filter="">
					<li class="layui-nav-item"><a href="javascript:;">${sessionScope.username}</a>
						<dl class="layui-nav-child">
							<!-- 二级菜单 -->
							<dd>
								<a href="${pageContext.request.contextPath}/jsp/showOneAdmin.jsp">个人信息</a>
							</dd>
							<dd>
								<a href="${pageContext.request.contextPath}/jsp/login.jsp">切换帐号</a>
							</dd>
							<dd>
								<a href="../logout">退出</a>
							</dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>
		<div class="layui-side layui-bg-black x-side">
			<div class="layui-side-scroll">
				<ul class="layui-nav layui-nav-tree site-demo-nav" lay-filter="side">
					<li class="layui-nav-item"><a class="javascript:;"
						href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe612;</i><cite>个人信息管理</cite>
					</a>
						<dl class="layui-nav-child">
							<dd class="">
								<a href="javascript:;"
									_href="${pageContext.request.contextPath}/jsp/showOneAdmin.jsp">
									<cite>个人信息</cite>
								</a>
							</dd>
							<dd class="">
								<a href="javascript:;"
									_href="<%=path %>/jsp/password-edit.jsp">
									<cite>修改密码</cite>
								</a>
							</dd>
						</dl></li>
					<shiro:hasRole name="super">
						<li class="layui-nav-item"><a class="javascript:;"
							href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe62d;</i><cite>操作权限管理</cite>
						</a>
							<dl class="layui-nav-child">
								<dd class="">
									<a href="javascript:;" _href="./admin-list.jsp"> <cite>查看后台管理员</cite>
									</a>
								</dd>
								<dd class="">
									<a href="javascript:;" _href="./admin-add2.jsp"> <cite>新增后台管理员</cite>
									</a>
								</dd>
								<dd class="">
									<a href="javascript:;"
										_href="${pageContext.request.contextPath}/jsp/admin-edit.jsp">
										<cite>修改后台管理员</cite>
									</a>
								</dd>
								<dd class="">
									<a href="javascript:;" _href="./admin-del.jsp"> <cite>删除后台管理员</cite>
									</a>
								</dd>
							</dl></li>
					</shiro:hasRole>
					<li class="layui-nav-item"><a class="javascript:;"
						href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe62d;</i><cite>企业用户管理</cite>
					</a>
						<dl class="layui-nav-child">
							<dd class="">
								<a href="javascript:;" _href="./company-list.jsp"> <cite>查看企业</cite>
								</a>
							</dd>
							<dd class="">
								<a href="javascript:;" _href="./company-add.jsp"> <cite>新增企业</cite>
								</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item">
                            <a class="javascript:;" href="javascript:;">
                                <i class="layui-icon" style="top: 3px;">&#xe62d;</i><cite>包租婆管理</cite>
                            </a>
                            <dl class="layui-nav-child">
                                <dd class="">
                                    <a href="javascript:;" _href="../showFirstLandlords.action">
                                        <cite>查看包租婆</cite>
                                    </a>
                                </dd>
                                <dd class="">
                                    <a href="javascript:;" _href="../showFirstLandlordCars.action">
                                        <cite>查看招租车位</cite>
                                    </a>
                                </dd>
                                <dd class="">
                                    <a href="javascript:;" _href="../showFirstDeal.action">
                                        <cite>查看出租订单</cite>
                                    </a>
                                </dd>
                            </dl>
                        </li>
					<li class="layui-nav-item"><a class="javascript:;"
						href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe62d;</i><cite>外部合约管理</cite>
					</a>
						<dl class="layui-nav-child">
							<dd class="">
								<a href="javascript:;" _href="./outtreaty-list.jsp"> <cite>查看外部合约</cite>
								</a>
							</dd>
							<dd class="">
								<a href="javascript:;" _href="./outtreaty-add.html"> <cite>新增外部合约</cite>
								</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a class="javascript:;"
						href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe62d;</i><cite>租户合约管理</cite>
					</a>
						<dl class="layui-nav-child">
							<dd class="">
								<a href="javascript:;" _href="companytreaty-list.html"> <cite>租户合约查看</cite>
								</a>
							</dd>
							<dd class="">
								<a href="javascript:;" _href="companytreaty-add.html"> <cite>租户签约</cite>
								</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a class="javascript:;"
						href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe62d;</i><cite>投诉受理管理</cite>
					</a>
						<dl class="layui-nav-child">
							<dd class="">
								<a href="javascript:;" _href="./allComplaint.jsp"> <cite>查看投诉</cite>
								</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a class="javascript:;"
						href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe62d;</i><cite>CBD车位管理</cite>
					</a>
						<dl class="layui-nav-child">
							<dd class="">
								<a href="javascript:;" _href="./showCbdcar.jsp"> <cite>查看管辖车位</cite>
								</a>
							</dd>
							<dd class="">
								<a href="javascript:;" _href="./insertOwnCbdcar.jsp"> <cite>添加管辖车位</cite>
								</a>
							</dd>
						</dl></li>
				</ul>
			</div>

		</div>
		<div class="layui-tab layui-tab-card site-demo-title x-main"
			lay-filter="x-tab" lay-allowclose="true">
			<div class="x-slide_left"></div>
			<ul class="layui-tab-title">
				<li class="layui-this">我的桌面 <i
					class="layui-icon layui-unselect layui-tab-close">ဆ</i>
				</li>
			</ul>
			<div class="layui-tab-content site-demo site-demo-body">
				<div class="layui-tab-item layui-show">
					<iframe frameborder="0" src="welcome.jsp" class="x-iframe"></iframe>
				</div>
			</div>
		</div>
		<div class="site-mobile-shade"></div>
	</div>
	<script src="${pageContext.request.contextPath}/lib/layui/layui.js"
		charset="utf-8"></script>
	<script src="${pageContext.request.contextPath}/js/x-layui.js"
		charset="utf-8"></script>
	<script src="${pageContext.request.contextPath}/js/x-admin.js"></script>
</body>
</html>