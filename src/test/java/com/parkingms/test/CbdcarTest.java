package com.parkingms.test;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.parkingms.bean.CbdcarBean;
import com.parkingms.bean.CompanytreatyBean;
import com.parkingms.bean.OuttreatyBean;
import com.parkingms.dao.ICbdcarDao;
import com.parkingms.dao.IPayinfoDao;
import com.parkingms.service.ICbdcarService;
import com.parkingms.util.ConvertUtil;

public class CbdcarTest {
	/**
	 * 
	 *查询所有车位service测试 
	 */
	@Test
	public void test(){
		ApplicationContext context=new ClassPathXmlApplicationContext("config/applicationContext.xml");
		ICbdcarDao dao=context.getBean(ICbdcarDao.class);
		System.out.println(dao);
		List<CbdcarBean> bean=dao.showCbdcar();
		for (CbdcarBean cbdcarBean : bean) {
			System.out.println(cbdcarBean);
		}
	}
	/**
	 * 批量添加service测试
	 */
	@Test
	public void  test2(){
		CbdcarBean bean=new CbdcarBean();
		bean.setAddr("中国成都");
		bean.setNo("CF88-99");
		ApplicationContext context=new ClassPathXmlApplicationContext("config/applicationContext.xml");
		ICbdcarService dao=context.getBean(ICbdcarService.class);
		OuttreatyBean out=new OuttreatyBean();
		out.setId(1);
		bean.setOuttreaty(out);
		System.out.println(dao.insertCbdcar(bean));
	}
	/**
	 *更新service测试 
	 */
	@Test
	public void test3(){
		ApplicationContext context=new ClassPathXmlApplicationContext("config/applicationContext.xml");
		ICbdcarService dao=context.getBean(ICbdcarService.class);
		CbdcarBean bean=new CbdcarBean();
		bean.setAddr("中国成都");
		bean.setNo("cf20-30");
		CompanytreatyBean CC=new CompanytreatyBean();
		CC.setId(1);
		bean.setCompanytreaty(CC);
		List<CbdcarBean> c=ConvertUtil.conv(bean);
		System.out.println(dao.updateCbdcar(bean));
		System.out.println(c);
	}
	@Test
	/**
	 * 软删除测试
	 */
	public void test4(){
		System.out.println(2<<3);
	}
	
	
	public static void main(String[] args) {
		
	}
	
}
