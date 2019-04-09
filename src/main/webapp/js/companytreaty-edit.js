// 渲染页面
layui.use([ 'form', 'element' ], function() {
	var form = layui.form,
		element = layui.element; //面包导航
});

// 往后台发数据
function updataComTreaty() {
	if($("#oldcar_no").val() == $("#car_no").val() && $("#oldcontact").val() == $("#contact").val()
			&& $("#oldtel").val() == $("#tel").val() && $("#oldendTime").val() == $("#endTime").val()
			&& $("#oldbeginTime").val() == $("#beginTiem").val() 
			&& $("#oldcar_addr").val() == $("#car_addr").val()){
		layer.msg("合同数据没有更新!", {
			icon : 3,
			time : 3000
		});
	}else{
		$.ajax({
			url : "/parkingms/updataCompanytreaty.action",
			method : "post",
			async : true, //是否异步请求
			dataType : "json",
			data : {
				id:$("#cid").val(),
				companyname : $("#companyname").val(),
				newno : $("#newno").val(),
				contact : $("#contact").val(),
				tel : $("#tel").val(),
				oldcar_no:$("#oldcar_no").val(),
				cbdcar_no : $("#car_no").val(),
				cbdcar_addr : $("#car_addr").val(),
				img : $("#img").val(),
				begintime : $("#beginTime").val(),
				endtime : $("#endTime").val()
			},
			success : function(data) {
				if ("租户合同更新成功!" == data) {
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
}


// 规范车位编号
function car_no() {
	var reg = "[a-zA-Z][a-zA-Z][0-9]{1,4}";
	var min = $("#car_no_min").val();
	var max = min;
	if ($("#car_no_max").val() != null && $("#car_no_max").val() != "") {
		max = $("#car_no_max").val();
	}
	if(min.substring(0,2) != max.substring(0,2)){
		layer.msg('车位区域号需相同！', {
			icon : 2,
			time : 1500
		});
		$("#car_no_max").val("");
		$("#car_no_max").focus().select();
		return;
	}
	if (min.match(reg) == null) {
		layer.msg('车位编号不规范！', {
			icon : 2,
			time : 1500
		});
		$("#car_no_min").val("");
		$("#car_no_min").focus().select();
		return;
	} else if (max.match(reg) == null) {
		layer.msg('车位编号不规范！', {
			icon : 2,
			time : 1500
		});
		$("#car_no_max").val("");
		$("#car_no_max").focus().select();
		return;
	}
	var min2 = parseInt(min.substring(2));
	var max2 = parseInt(max.substring(2));
	var car_no = "";
	if (max2 > min2) {
		$("#car_no").val(min + "-" + max2);
	} else if (max2 == min2) {
		$("#car_no").val(min);
	} else {
		layer.msg('车位编号后数必须比前数大！', {
			icon : 2,
			time : 1500
		});
		$("#car_no_max").val("");
		$("#car_no_max").focus().select();
		return;
	}
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