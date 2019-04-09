package com.parkingms.listener;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * 统计在线人数监听器
 * @author 张怡
 *
 */
public class CountOnlinePeopleListener extends SessionListenerAdapter{
	int s=0;
     @Override
	public void onStart(Session session) {
		// TODO Auto-generated method stub
		 System.out.println("当前在线人数:"+(s+=1));
	}

	@Override
	public void onStop(Session session) {
		// TODO Auto-generated method stub
		 System.out.println("当前在线人数:"+(s-=1));
	}

	@Override
	public void onExpiration(Session session) {
		// TODO Auto-generated method stub
		
	}

}
