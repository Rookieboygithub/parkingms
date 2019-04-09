
<!-- 
    @auther:杨向东
-->


<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%-- <base href="<%=basePath%>"> --%>
    
    <title>layui分页</title>
    
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="../css/x-admin.css" media="all">
    <link rel="stylesheet" href="../lib/layuinew/css/layui.css">
    
    
  <script type="text/javascript" src="../js/jQuery-2.2.2.js"></script>
  </head>
  
    <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>操作权限管理</cite></a>
              <a><cite>查看后台管理员</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        
        
        <div class="x-body">
        
        
            <form class="layui-form x-center" action="" style="width:80%">         
                <div class="layui-form-pane" style="margin-top: 15px;">
                  <div class="layui-form-item">
                    <label class="layui-form-label">日期范围</label>
                    <div class="layui-input-inline">
                      <input class="layui-input" placeholder="开始日" id="LAY_demorange_s">
                    </div>
                    <div class="layui-input-inline">
                      <input class="layui-input" placeholder="截止日" id="LAY_demorange_e">
                    </div>
                    <div class="layui-input-inline">
                      <input type="text" name="username"  placeholder="请输入工号" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline" style="width:80px">
                        <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
                    </div>
                  </div>
                </div> 
            </form>
            
            
            <xblock><button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon">&#xe640;</i>批量删除</button><button class="layui-btn" onclick="admin_add('添加用户','admin-add.html','600','500')"><i class="layui-icon">&#xe608;</i>添加</button><span class="x-right" style="line-height:40px">共有数据：<span id="totalData"></span> 条</span></xblock>
            
            
            <table class="layui-table" id="layui-table">
                <thead>
                    <tr>
                        <th><input type="checkbox" name="" value=""></th>
                        <th>编号</th>
                        <th>工号</th>
                        <th>姓名</th>
                        <th>电话</th>
                        <th>用户管理权限</th>
                        <th>车位管理权限</th>
                        <th>合同管理权限</th>
                        <th>投诉管理权限</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody id="layui-tbody">
				
				</tbody>
                
                

            </table>
            
            <!--  -->
            <div id="page"></div>
            
        </div>
        

        <script src="../lib/layui/layui.js" charset="utf-8"></script>
        <script src="../js/x-layui.js" charset="utf-8"></script>
        <script src="../lib/layuinew/layui.js"></script>
        
        <!-- 这个js放的顺序 -->
        <script type="text/javascript" src="../js/operationAdmin_Login.js"></script>

            
    </body>
    
</html>
