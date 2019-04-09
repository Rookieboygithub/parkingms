
//查询所有合同
function showAllComTreaty(ipage, inum) {
	$.ajax({
		url : "/parkingms/findCompanytreatyList.action",
		method : "post",
		async : false, //是否异步请求
		dataType : "json",
		data : {
			page : ipage,
			num : inum,
			begintime : $("#stime").val(),
			endtime : $("#etime").val(),
			companyname : $("#cname").val()
		},
		success : function(data) {
			var tpage = data.totalpage;
			var temp = data.list;
			var content = "";
			var num = 0;
			for (var i = 0; i < temp.length; i++) {
				var comtreaty = temp[i];
				var carlist = comtreaty.cbdcarlist;
				num++;
				content += "<tr> <input type='hidden' id='cid" + num + "' value='" + comtreaty.id + "'>"
					+ "<td id='no" + num + "'>" + num + "</td>"
					+ "<td id='newno" + num + "'>" + comtreaty.newno + "</td>"
					+ "<td id='companyname" + num + "'>" + comtreaty.companyname + "</td>"
					+ "<td id='contact" + num + "'>" + comtreaty.contact + "</td>"
					+ "<td id='tel" + num + "'>" + comtreaty.tel + "</td>"
					+ "<td id='begintime" + num + "'>" + comtreaty.begintime + "</td>"
					+ "<td id='endtime" + num + "'>" + comtreaty.endtime + "</td>"
					+ "<td id='car_addr" + num + "'>" + carlist[0].addr + "</td>"
					+ "<td id='car_no" + num + "'>" + carlist[0].no + "-" + carlist[carlist.length - 1].no + "</td>"
					+ "<td class='td-manage' style='padding: 0px;width: 126px;'>"
					+ "	<button class='layui-btn layui-btn-primary layui-btn-xs' type='button' "
					+ "		onclick='admin_details(" + comtreaty.id + ")'>详情</button>"
					+ "	<button class='layui-btn layui-btn-normal layui-btn-xs' type='button' style='margin-left: 4px;' "
					+ "		onclick='admin_edit(\"编辑\",\"companytreaty-edit.html\"," + num + ",\"900\",\"670\")'>编辑</button>"
					+ "	<button class='layui-btn layui-btn-danger layui-btn-xs' style='margin-left: 4px;' "
					+ "		type='button' onclick='admin_del(this," + comtreaty.id + ")'>删除</button>"
					+ "</td>"
					+ "</tr>";
			}
			if(temp.length == 0){
				layer.msg('查找到的数据为0条,请换一个查询条件!', {
					icon : 3,
					time : 3000
				});
			}
			$("#pagenum").val(tpage);
			$("#companytreaty_content").html(content);
		}
	});
}

showAllComTreaty(1, 5);
page();

//页面渲染
layui.use([ 'form', 'element' ], function() {
	var form = layui.form,
		element = layui.element; //面包导航
	// 动态生成的HTML自动渲染无效，需手动渲染。
	form.render();
});

function page() {
	// 分页
	layui.use('laypage', function() {
		var laypage = layui.laypage;

		//执行一个laypage实例
		laypage.render({
			elem : 'test1', //注意，这里的 test1 是 ID，不用加 # 号
			count : $("#pagenum").val(), //数据总数，从服务端得到
			limit : 5, //每页显示的条数
			limits : [ 5, 10, 15 ],
			layout : [ 'prev', 'page', 'next', 'count', 'limit', 'skip' ],
			jump : function(obj, first) {
				//obj包含了当前分页的所有参数，比如：
				//console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
				//console.log(obj.limit); //得到每页显示的条数

				//首次不执行
				if (!first) {
					showAllComTreaty(obj.curr, obj.limit);
				//do something
				}
			}
		});
	});
}

//查看
function admin_details(cid) {
	window.location.href = "./companytreaty-details.jsp?id=" + cid;
}

//编辑
function admin_edit(title, url, id, w, h) {
	layer.open({
		type : 2,
		area : [ w + 'px', h + 'px' ],
		fix : false, //不固定
		maxmin : true,
		shadeClose : true,
		shade : 0.4,
		title : title,
		content : url,
		success : function(layero, index) {
			//传入参数，并赋值给iframe的元素
			var body = layer.getChildFrame('body', index);
			body.contents().find("#tel").val($("#tel" + id).html());
			body.contents().find("#companyname").val($("#companyname" + id).html());
			body.contents().find("#newno").val($("#newno" + id).html());
			body.contents().find("#contact").val($("#contact" + id).html());
			body.contents().find("#beginTime").val($("#begintime" + id).html());
			body.contents().find("#car_addr").val($("#car_addr" + id).html());
			body.contents().find("#endTime").val($("#endtime" + id).html());
			var car_no = $("#car_no" + id).html();
			var m = car_no.indexOf("-");
			body.contents().find("#car_no_min").val(car_no.substring(0, m));
			body.contents().find("#car_no_max").val(car_no.substring(m + 1));
			body.contents().find("#cid").val($("#cid" + id).val());
			body.contents().find("#car_no").val(car_no.substring(0, m) + "-" + car_no.substring(m + 3));
			body.contents().find("#oldcar_no").val(car_no.substring(0, m) + "-" + car_no.substring(m + 3));
			body.contents().find("#oldtel").val($("#tel" + id).html());
			body.contents().find("#oldcontact").val($("#contact" + id).html());
			body.contents().find("#oldbeginTime").val($("#begintime" + id).html());
			body.contents().find("#oldcar_addr").val($("#car_addr" + id).html());
			body.contents().find("#oldendTime").val($("#endtime" + id).html());

		}
	});
}

/*删除*/
function admin_del(obj, cid) {
	layer.confirm('确认要删除吗？', function() {
		var flag = false;
		//发异步删除数据
		$.ajax({
			url : "/parkingms/removeCompanytreaty.action",
			method : "post",
			async : false, //是否异步请求
			dataType : "json",
			data : {
				cid : cid
			},
			success : function(data) {
				if (data == "合同解约成功!") {
					flag = true;
				} else {
					flag = false;
				}
			}
		});
		// 删除页面信息
		if (flag) {
			$(obj).parents("tr").remove();
			layer.msg('已删除!', {
				icon : 1,
				time : 2000
			});
		} else {
			layer.msg('删除失败!', {
				icon : 2,
				time : 2000
			});
		}
	});
}

// 查询
function sreach() {
	showAllComTreaty(1, 5);
	page();
}

//限定日期
layui.use('laydate', function() {
	var startDate = layui.laydate.render({
		elem : '#stime',
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

	var endDate = layui.laydate.render({
		elem : '#etime',
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
});