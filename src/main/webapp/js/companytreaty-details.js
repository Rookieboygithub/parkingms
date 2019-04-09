// 根据参数返回想要的值
function showComTreaty() {
	$.ajax({
		url : "/parkingms/findCompanytreaty.action",
		method : "post",
		async : true, //是否异步请求
		dataType : "json",
		data : {
			id : $("#cid").val()
		},
		success : function(data) {
			$("#companyname").val(data.companyname);
			$("#contact").val(data.contact);
			$("#tel").val(data.tel);
			var min = data.cbdcarlist[0].no;
			var max = data.cbdcarlist[data.cbdcarlist.length - 1].no;
			$("#car_no_min").val(min);
			$("#car_no_max").val(max);
			$("#car_no").val(min + "-" + max.substr(2));
			$("#car_addr").val(data.cbdcarlist[0].addr);
			$("#newno").val(data.newno);
			$("#beginTime").val(data.begintime);
			$("#endTime").val(data.endtime);
			$("#demo1").attr('src', $("#path").val() + "upload/" + data.img);
		}
	});
}

showComTreaty();

layui.use([ 'form', 'element' ], function() {
	var form = layui.form,
		element = layui.element; //面包导航
	// 动态生成的HTML自动渲染无效，需手动渲染。
	form.render();
});

function removeComTreaty() {
	$.ajax({
		url : "/parkingms/removeCompanytreaty.action",
		method : "post",
		async : false, //是否异步请求
		dataType : "json",
		data : {
			cid : $("#cid").val()
		},
		success : function(data) {
			if (data == "合同解约成功!") {
				layer.msg('解约成功!', {
					icon : 1,
					time : 2000
				});
			} else {
				layer.msg('合同解约失败!', {
					icon : 2,
					time : 2000
				});
			}
		}
	});
}

function admin_renew(title, url, id, w, h) {
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
			body.contents().find("#tel").val($("#tel").val());
			body.contents().find("#companyname").val($("#companyname").val());
			body.contents().find("#formerno").val($("#newno").val());
			body.contents().find("#contact").val($("#contact").val());
			body.contents().find("#beginTime").val($("#endTime").val());
			body.contents().find("#cid").val($("#cid").val());
			body.contents().find("#car_no").val($("#car_no").val());
			body.contents().find("#car_addr").val($("#car_addr").val());
		}
	});
}

function updateComTreaty() {
	$.ajax({
		url : "/parkingms/newnoCompanytreaty.action",
		method : "post",
		async : true, //是否异步请求
		dataType : "json",
		data : {
			id : 1
		},
		success : function(data) {
			alert(data);
		}
	});
}