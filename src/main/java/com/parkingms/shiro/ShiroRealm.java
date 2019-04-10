package com.parkingms.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.parkingms.bean.UserBean;
import com.parkingms.dao.IUserDao;
import com.parkingms.util.pointLogin;
import com.parkingms.util.SaveInSession;
import com.parkingms.util.ShiroCharacter;

public class ShiroRealm extends AuthorizingRealm {
	@Autowired
	private IUserDao dao;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("进入doGetAuthenticationInfo");
		// 默认密码当为短信登陆时用这个
		Object credentials = "202cb962ac59075b964b07152d234b70";
		// 1. 把 AuthenticationToken 转换为 UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		// 2. 从 UsernamePasswordToken 中来获取 username
		String username = upToken.getUsername();
		// 判断username中是否有 *(number!=-1)，若有表示是短信登陆则不需查数据库，给一个固定的密码即可完成验证。
		int number = username.indexOf("*");
		if (number != -1) {
			username = username.substring(0, number);
		}
		// 通过用户名获取相关信息:loginid character,pwd;
		System.out.println("用户名:" + username);
		// 单点登陆
		pointLogin.pointLogins(username);
		UserBean user = dao.getUserByAccount(username);
		// 若用户不存在, 则可以抛出 UnknownAccountException 异常
		if (user == null) {
			throw new UnknownAccountException("用户不存在!");
		}
		// 如果是短信登陆密码就为默认值
		if (number == -1) {
			credentials = user.getPwd();
		}
		// 将用户信息存到session中,可以封装到方法中
		SaveInSession.save(user);
		Object principal = username;
		String realmName = getName();
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);
		return info;
	}

	// 授权会被 shiro 回调的方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		String principal = (String) principals.getPrimaryPrincipal();
		System.out.println("从认证中取" + principal);
		UserBean user = dao.getUserByAccount(principal);
		System.out.println("进入认证doGetAuthorizationInfo-->" + user);
		int character = user.getCharacter();
		Set<String> roles = ShiroCharacter.character(character);
		// 3. 创建 SimpleAuthorizationInfo, 并设置其 reles 属性.
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		// 4. 返回 SimpleAuthorizationInfo 对象.
		return info;
	}

}