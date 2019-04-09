//渲染页面
layui.use([ 'form', 'element' ], function() {
	var form = layui.form,
		element = layui.element; //面包导航
});

//文件上传
layui.use('upload', function() {
	var upload = layui.upload;
	//执行实例
	var uploadInst = upload.render({
		elem : '#uploadImg', //绑定元素
		url : '/parkingms/companytreatyUpload.action', //上传接口 （这里发送请求）
		before : function(obj) {
			//预读本地文件示例，不支持ie8
			obj.preview(function(index, file, result) {
				$('#demo1').attr('src', result); //图片链接（base64）
			});
		},
		accept : 'images', //上传格式
		size : 1024, //最大允许的文件大小
		exts : 'jpg|png',
		done : function(res) {
			$("#img").val(res.uploadImg)
		},
		error : function() {
			layer.msg('文件有误，无法上传！', {
				icon : 2,
				time : 3000
			});
		}
	});
});

// 往后台发数据
function renewComTreaty() {
	$.ajax({
		url : "/parkingms/renewCompanytreaty.action",
		method : "post",
		async : true, //是否异步请求
		dataType : "json",
		data : {
			id:$("#cid").val(),
			companyname : $("#companyname").val(),
			newno : $("#newno").val(),
			formerno : $("#formerno").val(),
			contact : $("#contact").val(),
			tel : $("#tel").val(),
			cbdcar_no : $("#car_no").val(),
			cbdcar_addr : $("#car_addr").val(),
			img : $("#img").val(),
			begintime : $("#beginTime").val(),
			endtime : $("#endTime").val()
		},
		success : function(data) {
			if ("租户合同续约成功!" == data) {
				layer.msg(data, {
					icon : 1,
					time : 3000
				});
			} else {
				layer.msg(data, {
					icon : 2,
					time : 3000
				});
			}
		}
	});
}

// 手机号判断
$("#tel").change(function() {
	var reg = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
	var tel = $("#tel").val();
	if (tel.match(reg) == null) {
		$("#tel_msg").html("请输入正确的手机号");
	} else {
		$("#tel_msg").html("");
	}
});

$("#tel").focus(function() {
	$("#tel_msg").html("");
});

$("#car_no_min").change(function() {
	car_no()
});

$("#car_no_max").change(function() {
	car_no()
});

// 限定日期
layui.use('laydate', function() {
	var startDate = layui.laydate.render({
		elem : '#beginTime',
		min : $("#beginTime").val(),
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
		elem : '#endTime',
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