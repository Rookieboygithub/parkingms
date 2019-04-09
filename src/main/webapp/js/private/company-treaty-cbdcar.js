var allNumber = -1;
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
/*显示企业企业车位*/
function pageingAjax(page, limit) {
	var s = "";
	$.ajax({
		url : "companyFront/cbdcar.action",
		type : "post",
		async : "true",
		dataType : "json",
		data : {
			page : page - 1,
			limit : limit,
			treatyID : $("#treaty_id").val(),
			no : $("#cbdcar_no").val(),
			addr : $("#cbdcar_addr").val(),
		},
		success : function(data) {
			var newAllNumber = data.cbdnumber;
			for (var i = 0; i < data.list.length; i++) {
				s += "<tr>" +
					"<td>" +
					data.list[i].no +
					"</td>" +
					"<td>" +
					data.list[i].addr +
					"</td>" +
					"</tr>";
			}
			if (s == "") {
				contSelect("合同未签订车位")
			} else {
				$("#x-link").html(s);
			}
			if ( (allNumber == -1) ) {
				lay_ui_page(newAllNumber);
				allNumber = newAllNumber;
			}
		}
	});
}
pageingAjax(1, 5);