package com.parkingms.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingms.bean.JoinBean;
import com.parkingms.service.ILoginService;
import com.parkingms.service.IRegisterService;

/**
 * 注册页面的action
 * 
 * @author 杨吉竹
 *
 */
@Controller
public class RegisterAction {
	@Autowired
	private IRegisterService registerSer;

	public void setRegisterSer(IRegisterService registerSer) {
		this.registerSer = registerSer;
	}

	@SuppressWarnings("unused")
	@Autowired
	private ILoginService loginSer;

	public void setLoginSer(ILoginService loginSer) {
		this.loginSer = loginSer;
	}

	@RequestMapping("register.action")
	public @ResponseBody Map<String, String> addInfo(JoinBean bean) {
		Map<String, String> map = new HashMap<String, String>();
		if (registerSer.isRepeat(bean)) {
			map.put("rs", "repeat");
			return map;
		}
		boolean rs = registerSer.insert(bean);

		if (rs) {
			map.put("rs", "success");
		} else {
			map.put("rs", "fail");
		}
		return map;

	}

}
