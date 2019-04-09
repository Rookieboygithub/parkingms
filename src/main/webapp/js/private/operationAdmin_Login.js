function showAllAdmin(apage){
	$.ajax({
		url:"/management-companytreaty/ShowAllAdminLoginAction.action",
		type:"post",
		async:true,
		dataType:"json",
		data:{
			page:apage
		},
		success:function(data){
			var list = data.list;
			var totalPage = data.totalPage;
			var totalData = data.totalData;
			var content = "";
			
			for(var i =0;i<list.length;i++){
				var admins = list[i];
				
				content += 
			      "<tr> " +
			          "<td><input type='checkbox' name='' value=''></td>" +
			          
			          "<td>"+admins.loginid+"</td> " +
			          
			          "<td>"+admins.account+"</td> " +
			          
			          "<td>"+admins.name+"</td> " +
			          
			          "<td>"+admins.tel+"</td> " +
			          
			          "<td>"+admins.authorityUser+"</td> " +
			          
			          "<td>"+admins.authorityCar+"</td> " +
			          
			          "<td>"+admins.authorityPact+"</td> " +
			          
			          "<td>"+admins.authorityComplain+"</td> " +
			          			          						          			          
			          "<td> " +
			              //因为我是视图view操作，里面只要存在的数据，就是和login表；有关联的数据
			              " <a title='编辑'  href='../jsp/admin-edit.jsp?loginid="+admins.loginid+"&account="+admins.account+" ' onclick='admin_edit('编辑','admin-edit.html','4','','510')' " +
			                     "class='ml-5' style='text-decoration:none'> " +
			                     "<i class='layui-icon'>&#xe642;</i> " +
			              "</a> " +
			              "<a title='删除' href='javascript:;' onclick='admin_del(this,'1')' " +
			                     "style='text-decoration:none'> " +
			                     "<i class='layui-icon'>&#xe640;</i> " +
			              "</a> " +
			          "</td> "
			          
			      "</tr> " ;
				
				
			}
			//写入到html中
			$("#layui-tbody").html(content);
			
			//$("#layui-table").append(content);//追加内容，之前我用的.html(),直接把标题栏给覆盖了
			
			//我想直接在jsp页面，获取js里面的属性值，没弄出来，后来一想，在js里面，
			//直接写到jsp里面
			$("#totalData").html(totalData);
		}
		
	});
	
}
/**
 * 启动函数
 * 当引入这个js文件时，就会自动执行
 */
showAllAdmin(1);


/**
 * 首页
 * 
 */
function homePage(){
	showAllAdmin(1);
}
/**
 * 上一页
 * 
 */
function upPage(){
	//从html中取出的都是字符串类型
	var stringCurrentpage = $("#currentpage").html();
	var currentPage = parseInt(stringCurrentpage);
	var upPage = currentPage-1;
	if(upPage > 0){
		showAllAdmin(upPage);
	}
}
/**
 * 下一页
 * 
 */
function nextPage(){
	//从html中取出的都是字符串类型
	var stringCurrentpage = $("#currentpage").html();
	
	var currentPage = parseInt(stringCurrentpage);
	var nextPage = currentPage+1;
	var totalPage = parseInt($("#totalpage").html());
	
	if(nextPage <= totalPage){
		showAllAdmin(nextPage);
	}
}
/**
 * 末页
 * 
 */
function endPage(){
	var totalPage = parseInt($("#totalpage").html());
	showAllAdmin(totalPage);
}
////加载layui分页模块，并显示分页栏
//addPage();
//// 将layui分页封装成一个函数
//function addPage() {
//	layui.use('laypage', function() {
//		var laypage = layui.laypage;
//		//执行一个laypage实例
//		laypage.render({
//			elem : 'page', //注意，这里的 test1 是 ID，不用加 # 号
//			count : 10, //数据总数，从服务端得到
//			limit : 5, //每页显示的条数
//			limits : [ 5, 10, 15 ],
//			layout : [ 'prev', 'page', 'next', 'count', 'limit', 'skip' ],
//			jump : function(obj, first) {
//				//obj包含了当前分页的所有参数，比如：
//				//obj.curr得到当前页，以便向服务端请求对应页的数据。
//				//obj.limit得到每页显示的条数
//				//首次不执行
//				if (!first) {
//					showList(obj.curr,obj.limit);
//				}
//			}
//		});
//	});
//}












