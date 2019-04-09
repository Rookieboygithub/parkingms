/**
 * 登录
 */
function getCode(){
	document.getElementById("vercodepicture").src="../public-usercontrol/verifyCode?a="+Math.random();
}
getCode();
function login(){
	alert("我进入到这里了");
	var account=$("#account").val();
	var password=$("#password").val();
	var verifyCode=$("#vercode").val();	
	
	$.ajax({
		url:"../public-usercontrol/restful/login",
		method:"post",
		async:true,
		data:{
			account:account,
			password:password,
			verCode:verifyCode,
		},
		success:function(data){
			if(data==0){
				$("#showMes").text("账号，密码和验证码不可为空！");
				$("#vercodepicture").click();
			}else if(data==1){
				$("#showMes").text("验证码错误！");
				$("#vercodepicture").click();
			}else if(data==2){
				$("#showMes").text("账号或密码错误！");
				$("#vercodepicture").click();
			}else{
				$("#showMes").text("登录成功！");
			}
			
		}  
	})
}