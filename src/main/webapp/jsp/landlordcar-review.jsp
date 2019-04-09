<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <title>表单模块 - layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="lib/layuinew/css/layui.css">

    <style>
        body{padding: 20px;}
    </style>
</head>
<body>
<!-- 导航栏 -->

<div style="width: 620px;border: solid 2px #e6e6e6;padding: 20px;margin-bottom:40px">
	<input type="hidden"  id="id" value="${data.id}"/>
	<input type="hidden"  id="status" value="${data.status}"/>
    <form class="layui-form layui-form-pane">
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 608px">
                个人信息
            </label>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                用户名
            </label>
            <div class="layui-input-inline">
                <input type="text"  class="layui-input" value="${data.landlord.alias}" disabled>
            </div>
            <label class="layui-form-label">
                姓名
            </label>
            <div class="layui-input-inline">
                <input type="text"  class="layui-input" value="${data.landlord.name}" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                身份证号码
            </label>
            <div class="layui-input-inline">
                <input type="text"  class="layui-input" value="${data.landlord.cardNo}" disabled>
            </div>
            <label class="layui-form-label">
                电话号码
            </label>
            <div class="layui-input-inline">
                <input type="text"  class="layui-input" value="${data.landlord.tel}" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                家庭地址
            </label>
            <div class="layui-input-inline" style="width: 500px">
                <input type="text" class="layui-input" value="${data.landlord.addr}" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                职业
            </label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" value="${data.landlord.career}" disabled>
            </div>
            <label class="layui-form-label">
                申请时间
            </label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" value="<fmt:formatDate value="${data.time}" pattern="yyyy-MM-dd HH:mm:ss" />" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 608px">
                车位信息
            </label>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                产权证编号
            </label>
            <div class="layui-input-inline">
                <input type="text"  class="layui-input" value="${data.certificateNo}" disabled>
            </div>
            <label class="layui-form-label">
                车位编号
            </label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" value="${data.carNo}" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                车位地址
            </label>
            <div class="layui-input-inline" style="width: 500px">
                <input type="text"  class="layui-input" value="${data.location}" disabled>
            </div>
        </div>
        <div class="layui-form-item" >
            <label class="layui-form-label" style="height: 200px;line-height: 200px">
                产权证图片
            </label>
            <div class="layui-input-inline" style="width: 500px">
                <img src="<%=basePath%>/upload/${data.img}" style="height: 200px;margin-left: 20px"/>
            </div>
        </div>
        <div class="layui-form-item" >
            <label class="layui-form-label">
                审核状态
            </label>
             <input type="checkbox" id="reviewResult" lay-skin="switch" lay-text="审核通过|审核不通过">
        </div>
    </form>
    <button  class="layui-btn" id="submitButton" onclick="submitReview()">
        确定
    </button>
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
    layui.use(['element','form'],function(){
        var element = layui.element,
            form = layui.form;
    });
    $(function(){
    	if($("#status").val()=='5'){
    		$("#submitButton").hide();
    		$("#reviewResult").prop("disabled",true);
    	}
    	else if ($("#status").val()!='0'){
			$("#submitButton").hide();
			$("#reviewResult").prop({"disabled":true,"checked":true});  		
    	}
    })
    function submitReview(){
    	var status = 5;
    	if($("#reviewResult").is(":checked")){
    		status = 1;
    	}
    	$.ajax({
				url : "<%=basePath%>/submitLandlordCarReview.action",
				type : "post",
				dataType : "json",
				data : {
					id:$("#id").val(),
					status:status
				},
				async: true,
				success : function(data) {
					if(data==true){
						layer.msg('车位审核结果提交成功', {icon: 1, time: 2000});
					} else {
						layer.msg('车位审核结果提交失败!可能该车位已删除,请尝试刷新页面后重试', {icon: 2, time: 2000});
					}
				}
			});
    }
</script>
</body>
</html>

