<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="com.parkingms.bean.OuttreatyBean"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>显示合约</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href=" <%=basePath %>/lib/layuinew/css/layui.css">
<script type="text/javascript" src="<%=basePath %>/js/jQuery-2.2.2.js"></script>

<style>
body {
	padding: 20px;
}

.x-nav {
	padding: 0 20px;
	position: relative;
	z-index: 99;
	border-bottom: 1px solid #e5e5e5;
	line-height: 39px;
	height: 39px;
	overflow: hidden;
}
</style>
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a
			href="<%=basePath %>/jsp/welcome.jsp"><cite>首页</cite></a> <a
			href="<%=basePath %>/jsp/outtreaty-add.jsp"><cite>外部合约管理</cite></a> <a
			href="<%=basePath %>/jsp/outtreaty-list.jsp"><cite>查看外部合约</cite></a>
		</span>
	</div>

	<div style="text-align: center">
		<table class="layui-table">
			<thead>
				<tr>
					<td>序号</td>
					<td>合同编号</td>
					<td>合同单位</td>
					<td>双方联系人</td>
					<td>对方联系电话</td>
					<td>单位详细地址</td>
					<td>合同生效日期</td>
					<td>合同截止日期</td>
					<td>车位编号</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="OuttreatyBean" items="${list}">
					<tr>
						<td><c:out value="${OuttreatyBean.id}"></c:out></td>
						<td><c:out value="${OuttreatyBean.formerno}"></c:out></td>
						<td><c:out value="${OuttreatyBean.company}"></c:out></td>
						<td><c:out value="${OuttreatyBean.contact}"></c:out></td>
						<td><c:out value="${OuttreatyBean.tel}"></c:out></td>
						<td><c:out value="${OuttreatyBean.addr}"></c:out></td>
						<td><fmt:formatDate value="${OuttreatyBean.begintime}"
								pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${OuttreatyBean.endtime}"
								pattern="yyyy-MM-dd" /></td>
						<td><c:out value="${OuttreatyBean.carNum}"></c:out></td>

						<td class="td-manage"><i class="layui-icon"
							onclick="window.location.href='jsp/outtreaty-renew.jsp?id=${OuttreatyBean.id}&no=${OuttreatyBean.formerno}'">&#xe642;</i>
							<i class="layui-icon" onclick="del(${OuttreatyBean.id})">&#xe640;</i>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>



	<script src="<%=basePath %>/lib/layuinew/layui.js"></script>
	<script>

    layui.use(['form','element'], function(){
        var form = layui.form,
            element = layui.element;//面包导航
    });

</script>

	<script type="text/javascript">
		function show() {
			$.ajax({
				url : "/parkingms/show.action",
				method : "get",
				dataType : 'json',
				async : true,
				success : function(message) {}
			});
		}
		show();
	
		function del(oid) {
			$.ajax({
				url : "<%=basePath%>/del.action",
				method : "post",
				dataType : 'json',
				async : true,
				data : {
					id : oid
				},
				success : function(message) {
					window.location.reload();
				}
			});
		}
	</script>
</body>
</html>
