<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>私人招租车位申请</title>
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

.layui-form-label {
	width: 150px;
}
</style>
</head>
<body>
	<!-- 导航栏 -->
	<!-- <div style="line-height:35px">
		<span class="layui-breadcrumb"> <a href="jsp/welcome.html">首页</a>
			<a>包租婆</a> <a><cite>发布车位</cite></a>
		</span>
		<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-sm"
				onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
		</div>
		<hr class="layui-bg-gray" style="margin-top: 0">
	</div> -->
	<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-normal"
				onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
	</div>
	<form class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label"> 车位产权证编号 </label>
			<div class="layui-input-inline">
				<input type="text" name="certificateNo" required=""
					lay-verify="required" autocomplete="off" class="layui-input">
				<input type="hidden" name="landlord.id" class="layui-input" value="${userId}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 小区车位编号 </label>
			<div class="layui-input-inline">
				<input type="text" name="carNo" required="" lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 车位所在小区地址 </label>
			<div class="layui-input-inline" style="width: 380px">
				<input type="text" name="location" required="" lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 上传车位产权证复印件 </label>
			<div class="layui-input-inline">
				<button type="button" class="layui-btn  layui-btn-normal" id="uploadImg">
					<i class="layui-icon">&#xe67c;</i>上传图片
				</button>
				<!--隐藏的input，用于保持图片上传后返回的图片地址-->
				<input type="hidden" id="img_url" name="img" value="" lay-verify="img"/>
				<!--预览区域-->
				<div class="layui-upload-list">
					<img class="layui-upload-img" width="100px" height="80px"
						id="demo1" />
				</div>
			</div>
			<div class="layui-input-inline">
				<p id="demoText"></p>
			</div>

		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> </label>
			<button class="layui-btn layui-btn-normal" lay-submit lay-filter="*" type="button">立即提交</button>
		</div>
	</form>

	<!-- 底部固定区域 -->
	<div class="layui-layout-admin">
		<div class="layui-footer" style="left:0;text-align: center;">
			<p>Copyright ©2018 CBD车位管理系统 v1.0 All Rights Reserved.</p>
		</div>
	</div>


	<script src="lib/layuinew/layui.js"></script>
	<script src="js/jQuery-2.2.2.js"></script>
	<script>

    layui.use(['element','form','upload'], function () {
        var element = layui.element,
        	form = layui.form,
        	upload = layui.upload;
        form.on('submit(*)',function(data){
        	console.log(data.field);
        	$.ajax({
				url : "<%=path%>/landlordCarApply.action",
				type : "post",
				dataType : "json",
				data : data.field,
				async: true,
				success : function(data) {
					if(data==true){
						layer.msg('申请车位成功，请等待审核', {icon: 1, time: 2000});
						setTimeout(function(){
							window.location.reload();
						}, 2000);
					} else {
						layer.msg('申请车位失败，请稍后再试', {icon: 2, time: 2000});
					}
				}
			});
			//阻止表单跳转
			return true;
        });
        form.verify({
        	img:[/\S/,'请上传车位产权证复印件']
        });
        //执行实例
        var uploadInst = upload.render({
            elem: '#uploadImg' //绑定元素
            ,url: '<%=path%>/uploadImgFile.action' //上传接口 （这里发送请求）
            ,before:function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            ,accept: 'images' //上传格式
            ,size:2048 //最大允许的文件大小
            ,exts:'jpg|png'
            ,done: function(res){
                //上传完毕回调
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功
                /* alert("上传成功"+res.url); */
                // 将上传成功处理后的新文件名保存到隐藏文本框中
                $("#img_url").val(res.url);
            }
            ,error: function(){
                //请求异常回调
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });
    });

</script>
</body>
</html>

