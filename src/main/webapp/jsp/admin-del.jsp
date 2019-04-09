
<!-- 
    @auther:杨向东--引用
-->
 
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title> 软删除 </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="../css/x-admin.css" media="all">
        
        <script type="text/javascript" src="../js/jQuery-2.2.2.js"></script>
    </head>
    
    <body>
        <div class="x-body">
            <form class="layui-form">
            
            
                <div class="layui-form-item">
                    <label for="username" class="layui-form-label">
                                                                    工号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="account" id="account">
                    </div>
                </div>
                
                
<!--                 <div class="layui-form-item">
                    <label for="name" class="layui-form-label">
                                                                        姓名
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" disabled="" value="S001">
                    </div>
                </div>
                
                
                <div class="layui-form-item">
                    <label for="phone" class="layui-form-label">
                                                                        手机号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" disabled="" value="S001">
                    </div>
                    <div class="layui-input-inline">
                        <button class="layui-btn" >修改</button>
                    </div>
                </div>
                
                
                <div class="layui-form-item">
                    <label class="layui-form-label">
                                                                                    权限管理
                    </label>
                    <div class="layui-form-mid">用户管理权限 </div>
                    <input type="checkbox" name=""  checked="" lay-skin="switch" lay-text="开启|关闭" disabled>
                </div>
                
                
                <div class="layui-form-item">
                    <label class="layui-form-label"></label>
                    <div class="layui-form-mid">车位管理权限 </div>
                    <input type="checkbox" name=""  checked="" lay-skin="switch" lay-text="开启|关闭" disabled>
                </div>
                
                
                <div class="layui-form-item">
                    <label class="layui-form-label"></label>
                    <div class="layui-form-mid">合同管理权限 </div>
                    <input type="checkbox" name=""  lay-skin="switch" lay-text="开启|关闭" disabled>
                </div>
                
                
                <div class="layui-form-item">
                    <label class="layui-form-label"></label>
                    <div class="layui-form-mid">投诉管理权限 </div>
                    <input type="checkbox" name=""  lay-skin="switch" lay-text="开启|关闭" disabled>
                </div> -->
                
                
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                    <button  onclick="deleteFlag()" type="submit" class="layui-btn" lay-filter="add" lay-submit="">
                                                                             删除
                    </button>
                </div> 
                
                
            </form>
        </div>
        <script src="../lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="../js/x-layui.js" charset="utf-8">
        </script>
        <script>
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form()
              ,layer = layui.layer;
            });
        </script>
    </body>
    <script type="text/javascript" src="../js/deleteAdminLogin.js"></script>
</html>