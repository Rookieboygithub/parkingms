package com.parkingms.listener;

import java.util.Hashtable;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
/**
 * 单态登录的监听器
 * @author 杨吉竹
 *
 */
//@WebListener
public class LoginListener implements HttpSessionAttributeListener {
private static	Hashtable<String, HttpSession> map=new Hashtable<String, HttpSession>();;
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		/*	
		 * 
		 * 将session存入hashtable,
		 * 如果有新的登录进来，验证session是否一致,一致就证明是同一用户；
		 * 如果session不一致，将hashtable的中的session，user去掉
	*/		
			//设置一个hashtable用于存储session
					 
					//先取出提交的sessionAttribute的键值对
					//获取了账户对应的键
					String user=se.getName();				
					//取得账户对应的值
					Object obj=se.getValue();
				
					if(user.equals("username")){
				
					//获取新的session
					HttpSession session=se.getSession();
				
					//先判断是否已登录
					HttpSession oldsession=map.get((String)obj);
				//	判断是否已登录，session是否相等,如果hs为空值，则未登录
					//若hs有值，则判断session是否相等,若不等，将原session删除
					if(oldsession!=null
							&&!(oldsession.getId().equals(session.getId()))){
						//将原来的会话session删除，或毁掉attribute
						oldsession.removeAttribute("username");
						System.out.println("已将oldsession中的attribute删除");
//						oldsession.removeAttribute("id");
					}
					//将当前的session存入map中
					map.put((String)obj, session);
					}
		             
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		
	}

}
