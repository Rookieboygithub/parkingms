
<!-- 
    @auther:杨向东
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
        <title> 修改 </title>
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
                        <input type="text" id="account" name="account" value=${param.account} disabled required="" lay-verify="account"
                        autocomplete="off" class="layui-input"  >
                    </div>
                </div>
                
               <div class="layui-form-item">
               
                    <label for="phone" class="layui-form-label">
                        <span class="x-red">  *  </span>   admin外键   
                    </label>
                    <div class="layui-input-inline">
                        <input type="type" id="loginid" name="loginid"  value=${param.loginid} disabled required="" lay-verify="loginid"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                
                
                <div class="layui-form-item">
                    <label for="name" class="layui-form-label">
                        <span class="x-red">*</span>姓名
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="name" name="name"  value=${param.name} disabled required="" lay-verify="name"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                
                  
                <div class="layui-form-item">
                    <label for="phone" class="layui-form-label">
                        <span class="x-red">*</span>手机号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="tel" name="tel" value=${param.tel} disabled required="" lay-verify="phone"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label for="L_pass" class="layui-form-label">
                        <span class="x-red"> * </span>密码
                    </label>
                    <div class="layui-input-inline">
                        <input type="type" id="pwd" name="pwd" value=${param.pwd} disabled required="" lay-verify="pass"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                
                <!-- 
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                        <span class="x-red">*</span>确认密码
                    </label>
                    <div class="layui-input-inline">
                        <input type="password" id="pwd2" name="pwd2" required="" lay-verify="repass"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                -->
                
                <!--  
                <div class="layui-form-item">
                    <label for="character" class="layui-form-label">
                        <span class="x-red">*</span>角色
                    </label>
                    <div class="layui-input-inline">
                    
                        <select type="text" id="character" name="character" required="" lay-verify="character" autocomplete="off" class="layui-input" >
                            <option value="0" >超管</option>
                            <option value="1" >管理员</option>
                            <option value="2" >包租婆</option>
                            <option value="3" >散户租客</option>
                            <option value="4" >企业租客</option>
                        </select>
                         
                    </div>
                </div>
                -->
                
                <div class="layui-form-item">
                    <label class="layui-form-label">
                        <span class="x-red">*</span>权限设置
                    </label>
                    <div class="layui-input-block" id="checkbox"> 
                        <!--  
                        <input type="checkbox" name="authorityUser" title="用户管理权限" id="authorityUser">
                        <input type="checkbox" name="authorityCar" title="车位管理权限"  id="authorityCar" checked="">
                        <input type="checkbox" name="authorityPact" title="合同管理权限" id="authorityPact">
                        <input type="checkbox" name="authorityComplain" title="投诉管理权限" id="authorityComplain">
                        -->
                        <input type="hidden" id="authorityUser" value=${param.authorityUser}>
                        <input type="hidden" id="authorityCar" value=${param.authorityCar}>
                        <input type="hidden" id="authorityPact" value=${param.authorityPact}>
                        <input type="hidden" id="authorityComplain" value=${param.authorityComplain}>
                        <input type="hidden" id="loginid" value=${param.loginid}>
                    </div>
                </div>
                
                
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                    <button  onclick="checkbox()" class="layui-btn" lay-filter="edit" lay-submit="">
                                                                      修改
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
            
              //自定义验证规则
              form.verify({
                nikename: function(value){
                  if(value.length < 5){
                    return '昵称至少得5个字符啊';
                  }
                }
                ,pass: [/(.+){6,12}$/, '密码必须6到12位']
                ,repass: function(value){
                    if($('#L_pass').val()!=$('#L_repass').val()){
                        return '两次密码不一致';
                    }
                }
              });

              //监听提交
              form.on('submit(add)', function(data){
                console.log(data);
                //发异步，把数据提交给php
                layer.alert("增加成功", {icon: 6},function () {
                    // 获得frame索引
                    var index = parent.layer.getFrameIndex(window.name);
                    //关闭当前frame
                    parent.layer.close(index);
                });
                return false;
              });
              
              
            });
        </script>
       
        <script type="text/javascript" src="../js/updateAdminLogin.js"></script>
    </body>
    
    

</html>