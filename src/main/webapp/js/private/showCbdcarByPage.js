/*获取总条数*/
var allNumber;
/*企业显示页面js：BHH*/
function allNumber(){
	$.ajax({
		data:{no:$("#car_no").val(),
			addr:$("#car_addr").val()
		},
		url : "allPage.action",
		type : "post",
		async : "true",
		success : function(data) {
			pageing(data);
		}
		})
	
	
}
function pageingAjax(page, limit) {
	var s = "";
	$.ajax({
		url : "showcbdcarByPage.action",
		type : "post",
		async : "true",
		data : {
			no:$("#car_no").val(),
			addr:$("#car_addr").val(),
			pageNumber : page - 1,
			limit : limit,
		},
		success : function(data) {
			var data=data.list;
			for (var i = 0; i < data.length; i++) {
				s += "<tr><td>" + ((page - 1) * limit + i + 1) + "</td>" +
					"<td>" + data[i].addr + "</td>" +
					"<td>" + data[i].no + "</td>" +
					"<td>" + showStatus(data[i].status) + "</td>" +
					"<td class='td-manage' onclick='delet(" + data[i].id+","+data[i].status+")'>" +
					"<i class='layui-icon'>&#xe640;</i>" +
					"</td>" +
					"</tr>";
			}
			$("#company_id").html(s);
		}
	});
}
/*layui分页*/
function pageing(allNumber) {
	layui.use([ 'form', 'laypage', 'jquery', 'element' ], function() {
		var form = layui.form,
			laypage = layui.laypage,
			element = layui.element,
			$ = layui.jquery;
		//执行一个laypage实例
		laypage.render({
			elem : 'page', //注意，这里的 page 是 ID，不用加 # 号
			count : allNumber, //数据总数，从服务端得到
			limit : 5, //每页显示的条数
			limits : [ 5, 10, 15 ],
			layout : [ 'prev', 'page', 'next', 'count', 'limit', 'skip' ],
			jump : function(obj, first) {
				//obj包含了当前分页的所有参数，比如：
				console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
				console.log(obj.limit); //得到每页显示的条数
				$("#pageAt").val(obj.curr);
				$("#limit").val(obj.limit);
				pageingAjax(obj.curr, obj.limit);
				//首次不执行
				if (!first) {
				}
			}
		});
	});
}
function delet(id,status) {
	if(status==1){
		layer.msg('该车位已出租不能删除', {
			icon : 5,
			time : 2000
		});
		return;
	}
	layui.use('layer', function() {
		var layer = layui.layer;
		layer.confirm('确认删除', {
			btn : [ '确认', '取消' ],
			title : "提示" //按钮
		}, function() {
			deletAjax(id);
			layer.msg('删除成功');
		}, function() {
			layer.msg('取消', {
				time : 1000,
			});
		});
	});
}
/*删除ajax信息传送*/
function deletAjax(id,status) {
	$.ajax({
		url : "deletecbdcar.action",
		type : "post",
		async : "true",
		data : {
			no:$("#car_no").val(),
			addr:$("#car_addr").val(),
			id : id,
		},
		success : function(data) {
			/*删除后返回总条数，更新页面信息*/
			statNumber = 1;
			pageing(data)
		}
	});
}
function showStatus(data){
	var str;
	if(data==0){
		str="待出租";
	}else{
		str="已出租";
	}
	return str;
}
allNumber();