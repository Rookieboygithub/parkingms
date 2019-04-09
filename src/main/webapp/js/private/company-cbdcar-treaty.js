var allNumber = -1;
/*引入lay_ui组件*/
function lay_ui() {
	layui.use([ 'element' ], function() {
		var element = layui.element;
	})
}
lay_ui();

/*显示企业企业合同*/
function pageingAjax(page, limit) {
	var s = "";
	$.ajax({
		url : "companyFront/streaty.action",
		type : "post",
		async : "true",
		data : {
			page : 0,
			limit : 5,
			id : $("#treaty_id").val()
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
					"</td>" +
					"</tr>";

			}
			$("#x-link").html(s);

		}
	});
}
pageingAjax(1, 0);