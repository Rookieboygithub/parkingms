<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"> 
    <title>单个admin信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="../lib/layuinew/css/layui.css">

    <style>
        body{padding: 20px;}
        .layui-form-pane .layui-form-label{
            width: 130px;
        }
    </style>
    
    
    <script type="text/javascript" src="../js/jQuery-2.2.2.js"></script>
</head>
<body>
<div>
    <form class="layui-form layui-form-pane">
    
    
    
        <div class="layui-form-item">
            <label class="layui-form-label">
                                           管理员登录工号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="loginAccount" name="" required="" lay-verify="required"
                       autocomplete="off" class="layui-input"  value=${sessionScope.username}  disabled >
            </div>
        </div>
<!--         <div class="layui-form-item">
            <label class="layui-form-label">
                                          管理员登录密码 
            </label>
            <div class="layui-input-inline">
                <input type="text" id="" name="" required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div> -->
        
        
        
        
        <div class="layui-form-item">
            <label class="layui-form-label">
                                          管理员姓名 
            </label>
            <div class="layui-input-inline">
                <input type="text" id="name" name="" required="" lay-verify="required"
                       autocomplete="off" class="layui-input" disabled >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                                          管理员电话
            </label>
            <div class="layui-input-inline">
                <input type="text" id="tel" name="" required="" lay-verify="required"
                       autocomplete="off" class="layui-input" disabled >
            </div>
        </div>
        
        
        
        
                 
<!--                <div class="layui-form-item" id="authorityUser">
                    <label class="layui-form-label">
                        <span class="x-red">*</span>权限设置
                    </label>
                    <label class="layui-form-label">
                                                              用户管理
                    </label>
                    <input type="checkbox" name="close" lay-skin="switch" title="开关">
                </div>
                <div class="layui-form-item" id="authorityCar">
                    <label class="layui-form-label"></label>
                    <label class="layui-form-label">
                                                              车位管理
                    </label>
                    <input type="checkbox" name="close" lay-skin="switch" title="开关">
                </div>
                <div class="layui-form-item" id="authorityPact">
                    <label class="layui-form-label"></label>
                    <label class="layui-form-label">
                                                              合同管理
                    </label>
                    <input type="checkbox" name="close" lay-skin="switch" title="开关" checked disabled>
                </div>
                <div class="layui-form-item" id="authorityComplain">
                    <label class="layui-form-label"></label>
                    <label class="layui-form-label">
                                                              投诉管理
                    </label>
                    <input type="checkbox" name="close" lay-skin="switch" title="开关">
                </div> -->
                
                
                <div class="layui-form-item">
                    <label class="layui-form-label">
                        <span class="x-red">*</span>权限设置
                    </label>
                    <div class="layui-input-block" id="checkbox">
                        <!--  
                        <input type="hidden" id="authorityUser" value=${param.authorityUser}>
                        <input type="hidden" id="authorityCar" value=${param.authorityCar}>
                        <input type="hidden" id="authorityPact" value=${param.authorityPact}>
                        <input type="hidden" id="authorityComplain" value=${param.authorityComplain}>
                        <input type="hidden" id="loginid" value=${param.loginid}>
                        --> 
                    </div>
                </div>
                
     
    </form>
</div>



<script src="../lib/layuinew/layui.js"></script>
<script>

    layui.use('form', function(){
        var form = layui.form;
    });

</script>
</body>

 <script type="text/javascript" src="../js/showOneAdmin.js"></script>
</html>
