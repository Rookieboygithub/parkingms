/**
 * 
 */
function getCode(){
	document.getElementById("vercodepicture").src="../public-usercontrol/verifyCode?a="+Math.random();
}
getCode();
function register(){
	alert("我进入register这个方法了");
	var account=$("#account").val();
	var password=$("#password").val();
	var verifypwd=$("#vpass").val();
	var name=$("#username").val();
	var alias=$("#alias").val();
	var address=$("#address").val();
	var phone=$("#phone").val();
	var cardNo=$("#cardNo").val();
	var career=$("#career").val();
	var email=$("#email").val();
	var character= $("input[name='character']:checked").val();
	var verCode=$("#vercode").val();
	
	$.ajax({
		url:"../public-usercontrol/restful/register",
		method:"post",
		async:true,
		
	    data:{
	    	account:account,
	    	password:password,
	    	name:name,
	    	alias:alias,
	    	address:address,
	    	phone:phone,
	    	cardNo:cardNo,
	    	career:career,
	    	email:email,
	    	character:character,
	    	verCode:verCode,
	    	verifypwd:verifypwd
	    },
		success:function(data){
			alert("我进入这里了")
			alert(data);
			if(data==0){
				$("#showMes").text("请填写完整的信息!");
				$("#vercodepicture").click();
			}else if(data==1){
				$("#showMes").text("账号已存在!");
				$("#vercodepicture").click();
			}else if(data==2){
				$("#showMes").text("账号不合法！");
				$("#vercodepicture").click();
			}else if(data==3){
				$("#showMes").text("密码必须包含字母和数字的组合 !");
				$("#vercodepicture").click();
			}else if(data==4){
				$("#showMes").text("第一次密码和第二次密码不一致!");
				$("#vercodepicture").click();
			}else if(data==5){
				$("#showMes").text("验证码不正确!");
				$("#vercodepicture").click();
			}else if(data==6){
				$("#showMes").text("邮箱仅支持qq邮箱!");
				$("#vercodepicture").click();
			}else if(data==7){
				$("#showMes").text("电话号码不合法");
				$("#vercodepicture").click();
			}else if(data==8){
				$("#showMes").text("身份证号码不合法！");
				$("#vercodepicture").click();
			}else if(data==9){
				$("#showMes").text("注册成功！");
			}else{
				$("#showMes").text("注册失败！");
			}
			}
	})
}