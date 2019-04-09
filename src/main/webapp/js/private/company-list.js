/*企业显示页面js：BHH*/
/*状态码：正常显示状态为：0，删除企业显示状态：1*/
var statNumber = 0;
function pageingAjax(page, limit) {
	var s = "";
	$.ajax({
		url : "company/page.action",
		type : "post",
		async : "true",
		data : {
			pageNumber : page - 1,
			limit : limit,
		},
		success : function(data) {
			for (var i = 0; i < data.length; i++) {
				s += "<tr><td>" + ((page - 1) * limit + i + 1) + "</td>" +
					"<td>" + data[i].account + "</td>" +
					"<td>" + data[i].name + "</td>" +
					"<td>" + data[i].addr + "</td>" +
					"<td>" + data[i].contact + "</td>" +
					"<td>" + data[i].tel + "</td>" +
					"<td>" + "<a href='/parkingms/jsp/company-edit-admin.jsp?comaccount=" +
					data[i].account + "'>" +
					"<i class='layui-icon' onclick=''>&#xe642;</i></a>" +
					"<i class='layui-icon td-manage'  onclick='delet(" + data[i].id + ")' >&#xe640;</i>" +
					"</td>" +
					"</tr>";
			}
			if (s == "" && (page - 1) == 0) {
				contSelect("查询不到相关数据，请再次确认数据")
			} else if (s == "" && (page - 1) != 0) {
				pageingAjax(page - 1, limit)
			} else {
				$("#company_id").html(s);

			}
		}
	});
}
/*删除函数:id：删除企业对应的id：删除企业提示信息*/
function delet(id) {
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

/*查询不到数据弹框*/
function contSelect(result) {
	layui.use('layer', function() {
		var layer = layui.layer;
		layer.alert(result);
	});
}
/*删除ajax信息传送*/
function deletAjax(id) {
	$.ajax({
		url : "company/deleteCompany.action",
		type : "post",
		async : "true",
		data : {
			id : id,
		},
		success : function(data) {
			/*删除后返回总条数，更新页面信息*/
			statNumber = 1;
			pageing(data)
		}
	})
}
/*layui分页*/
function pageing(allNumber) {
	layui.use([ 'laypage' ], function() {
		var laypage = layui.laypage;
		//执行一个laypage实例
		laypage.render({
			//注意，这里的 page 是 ID，不用加 # 号
			elem : 'page',
			//数据总数，从服务端得到
			count : allNumber,
			//每页显示的条数
			limit : 5,
			limits : [ 5, 10, 15 ],
			layout : [ 'prev', 'page', 'next', 'count', 'limit', 'skip' ],
			jump : function(obj, first) {
				if (statNumber == 0) {
					$("#pageAt").val(obj.curr);
					$("#limit").val(obj.limit);
					pageingAjax(obj.curr, obj.limit);
				} else if (statNumber == 1) {
					statNumber = 0;
					pageingAjax($("#pageAt").val(), $("#limit").val());

				}
			}
		});
	});
}
/*查询请求发送*/
function company_select() {
	$.ajax({
		url : "company/selectCompany.action",
		type : "post",
		async : "true",
		data : {
			name : $("#company_name").val(),
			addr : $("#company_addr").val(),
			contact : $("#company_contact").val(),
			tel : $("#company_tel").val(),
		},
		success : function(data) {
			//获取总条数，调用layui组件显示页面
			$("#pageAll").val(data);
			pageing(data);
		}
	});
}
company_select();
/*加载layui组件*/
function layui_element() {
	layui.use([ 'jquery', 'element', 'laydate' ], function() {
		var element = layui.element,
			laydate = layui.laydate,
			$ = layui.jquery;
	});
}
layui_element();