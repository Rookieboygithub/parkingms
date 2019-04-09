var allNumber = -1;
/*引入lay_ui组件*/
function lay_ui() {
	layui.use([ 'element', 'laypage', 'layer', 'form', 'jquery', 'laydate' ], function() {
		var element = layui.element,
			$ = layui.jquery,
			laydate = layui.laydate,
			layer = layui.layer,
			form = layui.form;
		var startDate = laydate.render({
			elem : '#treaty_beginTime', //指定元素
			done : function(value, date) {
				if (value !== '') {
					endDate.config.min.year = date.year;
					endDate.config.min.month = date.month - 1;
					endDate.config.min.date = date.date;
				} else {
					endDate.config.min.year = '';
					endDate.config.min.month = '';
					endDate.config.min.date = '';
				}
			}
		});
		var endDate = laydate.render({
			elem : '#treaty_endTime', //指定元素
			done : function(value, date) {
				if (value !== '') {
					startDate.config.max.year = date.year;
					startDate.config.max.month = date.month - 1;
					startDate.config.max.date = date.date;
				} else {
					startDate.config.max.year = '';
					startDate.config.max.month = '';
					startDate.config.max.date = '';
				}
			}
		});
	})
}
lay_ui();
/*引入分页*/
function lay_ui_page(number) {
	layui.use('laypage', function() {
		var laypage = layui.laypage; //分页
		//执行一个laypage实例
		laypage.render({
			elem : 'page', //注意，这里的 page 是 ID，不用加 # 号
			count : number, //数据总数，从服务端得到
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
	})
}
/*查询不到数据弹框*/
function contSelect(result) {
	layui.use('layer', function() {
		var layer = layui.layer;
		layer.alert(result);
	});
}
/*显示企业合同*/
function pageingAjax(page, limit) {
	var begintime = $("#treaty_beginTime").val();
	var endtime = $("#treaty_endTime").val();
	var s = "";
	$.ajax({
		url : "companyFront/streaty.action",
		type : "post",
		async : "true",
		data : {
			'page' : page - 1,
			'limit' : limit,
			'newno' : $("#treaty_no").val(),
			'begintime' : begintime,
			'endtime' : endtime,
		},
		success : function(data) {
			/*查看获取新的总条数*/
			var newAllNumber = data.streatyNumber;
			/*合同结束时间*/
			var endTime;
			/*合同状态*/
			var stat;
			/*获取当前时间*/
			var date = new Date();

			for (var i = 0; i < data.list.length; i++) {
				/*合同结束时间格式转换*/
				var endTime = new Date(Date.parse(data.list[i].endtime));
				/*判断合同是否过期*/
				if (date < endTime) {
					stat = "未过期";
				} else {
					stat = "已过期";
				}
				s += "<tr>" +
					"<td>" +
					data.list[i].newno +
					"</td>" +
					"<td>" +
					data.list[i].companyname +
					"</td>" +
					"<td>" +
					data.list[i].contact +
					"</td>" +
					"<td>" +
					data.list[i].tel +
					"</td>" +
					"<td>" +
					data.list[i].begintime +
					"<td>" +
					data.list[i].endtime +
					"</td>" +
					"<td>" +
					"<span class='layui-btn layui-btn-danger layui-btn-mini'> " +
					stat +
					"</span>" +
					"</td>" +
					"<td class='td-manage'>" +
					"<button class='layui-btn  layui-btn-mini' onclick='test(" + data.list[i].id + ")'>" +
					"<i class='layui-icon'>" +
					"&#xe642;" +
					"</i>" +
					"详情" +
					"</button>" +
					"</td>" +
					"</tr>";
			}
			if (s == 1 && allNumber != -1) {
				contSelect("暂未签订合同");
			} else {
				$("#x-link").html(s);
			}
			/*判断总页数是否发生变化*/
			if (allNumber == -1) {
				lay_ui_page(newAllNumber)
				allNumber = newAllNumber;
			}
		}
	});
}
pageingAjax(1, 0);
function x_admin_show(title, url, w, h) {
	if (title == null || title == '') {
		title = false;
	}
	;
	if (url == null || url == '') {
		url = "404.html";
	}
	;
	if (w == null || w == '') {
		w = 800;
	}
	;
	if (h == null || h == '') {
		h = ($(window).height() - 50);
	}
	;
	layui.use([ 'layer', 'form' ], function() {
		var layer = layui.layer,
			form = layui.form;
		layer.open({
			type : 2,
			area : [ w + 'px', h + 'px' ],
			fix : false, //不固定
			maxmin : true,
			shadeClose : true,
			shade : 0.4,
			title : title,
			content : url
		});
	})
}
/*弹出页面*/
function test(id) {
	x_admin_show("车位", "jsp/company-treaty-cbdcar.jsp?id=" + id);
}